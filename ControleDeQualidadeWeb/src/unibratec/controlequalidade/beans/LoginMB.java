package unibratec.controlequalidade.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import unibratec.controlequalidade.entidades.StatusUsuarioEnum;
import unibratec.controlequalidade.entidades.Usuario;
import unibratec.controlequalidade.exceptions.UsuarioNaoCadastradoException;
import unibratec.controlequalidade.exceptions.UsuarioSenhaIncorretaException;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.util.MensagensGui;

@ManagedBean(name ="loginMB")
@SessionScoped
public class LoginMB {

	private static final String OUTCOME_LOGIN = "login";
	private static final String OUTCOME_LOGOUT = "logout";
	public static final String USUARIO_SESSAO = "usuario";
	public static final String SESSAO_INVALIDA = "sessao_invalida";
	
	private Fachada fachada;
	private Usuario usuario;
	private ControladorAcesso controladorAcesso;
	private boolean showLogoutOutraSessaoDialog; // Flag do "rendered" do logoutOutraSessaoForm

	public LoginMB() {}

	/*
	 * � importante colocar suas inicializa��es no post construct e n�o no
	 * construtor da classe, isso porque se voc� estiver realizando inje��o de 
	 * depend�ncia (no Spring, por exemplo) todas as depend�ncias podem n�o
	 * estar carregadas na constru��o da sua classe, ent�o no post construct
	 * voc� garante que tudo j� foi carregado e agora voc� podem ser usados. 
	 */
	@PostConstruct
	public void inicializar() {
		this.fachada = new Fachada();
		this.usuario = new Usuario();
		this.controladorAcesso = new ControladorAcesso();
		this.showLogoutOutraSessaoDialog = false;
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Inicializando um bean de login.");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ControladorAcesso getControladorAcesso() {
		return controladorAcesso;
	}
	
	public void setShowLogoutOutraSessaoDialog(boolean showDialog) {
		this.showLogoutOutraSessaoDialog = showDialog;
	}
	
	public boolean getShowLogoutOutraSessaoDialog() {
		return showLogoutOutraSessaoDialog;
	}	
	
	
	/**
	 * M�todo utilizado para tentativas de login no sistema, confrontando dados
	 * fornecidos pelo usu�rio com registros de usu�rios cadastrados.
	 *
	 * @return Outcome associado a fracasso ou sucesso na tentativa de login.
	 */
	public String fazLogin() {

		try {

			if (camposPreenchidos()){
				
				// Verificando se o usu�rio passado na tela de login tem credenciais v�lidas
				Usuario usuarioAutenticado = this.fachada.autenticaUsuario(this.usuario.getNomeUsuario(), this.usuario.getSenhaUsuario());
				
				if (!isUsuarioLogado()) {
					
					// Recuperando o contexto do JSF
					FacesContext contexto = FacesContext.getCurrentInstance();
					
					// Recuperando a sess�o do contexto
					HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(true);

					// Adicionando o usu�rio na sess�o
					sessao.setAttribute(USUARIO_SESSAO, usuarioAutenticado);

					// Configura o acesso das p�ginas e informa��es de acordo com perfil do usu�rio
					this.controladorAcesso.configuraAcesso();

					// Setando o status do usu�rio para ativo na sess�o
					usuarioAutenticado.setStatusUsuario(StatusUsuarioEnum.ATIVO.getValue());

					// Atualizando no banco a informa��o de usu�rio ativo em uma sess�o
					this.fachada.atualizaStatusUsuario(usuarioAutenticado);		
					
					Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Login com Sucesso!!!");
					
					return OUTCOME_LOGIN;
					
				} else{
					
					MensagensGui.erroMsg(null, MensagensGui.LOGIN_USUARIO_LOGADO_OUTRA_SESSAO);
					
					// Apresenta o dialog sobre fazer logout de outra sess�o
					setShowLogoutOutraSessaoDialog(true);
					
					Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Usu�rio logado em outra Sess�o");
					
					return "";
				}
		
			} else{
				
				MensagensGui.avisoMsg(null, MensagensGui.LOGIN_PREENCHER_CAMPOS);
				
				Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Campos n�o preenchidos corretamente ou n�o preenchidos.");
				
				return "";
			
			}

		} catch (UsuarioNaoCadastradoException e) {

			e.printStackTrace();
			
			System.out.println(e.getMessage());
			
			MensagensGui.erroMsg(null, MensagensGui.LOGIN_USUARIO_NAO_CADASTRADO);
			
			return "";

		} catch (UsuarioSenhaIncorretaException e) {

			e.printStackTrace();
			
			System.out.println(e.getMessage());
			
			MensagensGui.avisoMsg(null, MensagensGui.LOGIN_USUARIO_SENHA_INCORRETO);
			
			return "";
		}
	}

	
	/**
	 * M�todo utilizado para finalizar a sess�o atual de um usu�rio no sistema.
	 *
	 * @return Outcome associado ao sucesso na tentativa de logout.
	 */
	public String fazLogout() {

		FacesContext contexto = FacesContext.getCurrentInstance();
		
		HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);

		Usuario usuarioSessao = (Usuario) sessao.getAttribute(USUARIO_SESSAO);

		if (usuarioSessao != null) {

			usuarioSessao.setStatusUsuario(StatusUsuarioEnum.INATIVO.getValue());

			this.fachada.atualizaStatusUsuario(usuarioSessao);
			
			contexto.getExternalContext().invalidateSession();

			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Logout com Sucesso!!!");
			
			return OUTCOME_LOGOUT;
		
		} else{
			
			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Logout falhou!!!");
			
			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> N�o h� usu�rio na sess�o.");
			
			return "";
		}
	}

	
	/**
	 * M�todo utilizado para fazer logout de um usu�rio no sistema em uma sess�o aberta
	 * em outro navegador ou outra aba. A outra sess�o � representada por uma flag no 
	 * banco de dados.
	 * <code>"0"</code> caso n�o haja uma sess�o aberta
	 * <code>"1"</code> caso haja uma sess�o aberta.
	 * 
	 * @return Outcome associado ao sucesso na tentativa de logout.
	 * 
	 */
	public String fazLogoutOutraSessao() {
		
		try {
			
			Usuario usuarioOutraSessao = this.fachada.getUsarioByNome(this.usuario.getNomeUsuario());
			
			usuarioOutraSessao.setStatusUsuario(StatusUsuarioEnum.INATIVO.getValue());

			this.fachada.atualizaStatusUsuario(usuarioOutraSessao);

			setShowLogoutOutraSessaoDialog(false);

			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Logout com Sucesso!!!");

			return OUTCOME_LOGOUT;
		
		} catch (UsuarioNaoCadastradoException e) {
			
			e.printStackTrace();
			
			System.out.println(e.getMessage());
			
			MensagensGui.erroMsg(null, MensagensGui.LOGIN_USUARIO_NAO_CADASTRADO);
			
			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Logout falhou!!!");
			
			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> N�o h� usu�rio na sess�o.");
			
			return "";
		}		
	}
	
	
	/**
	 * M�todo utilizado para limpar todos os dados da tela de login.
	 */
	public void limparTelaLogin() {
		
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Limpando os dados da tela de login!!!");
		
		this.usuario = new Usuario();
	}
	
	
	/**
	 * M�todo utilizado para n�o fazer logout de um usu�rio no sistema em uma sess�o aberta
	 * em outro navegador ou outra aba. O pop up de questionamento sobre fazer o logout de
	 * outra sess�o � fechado e os dados da tela s�o resetados.
	 * 
	 * @return Outcome associado a sess�o inv�lida. 
	 */
	public String naofazLogoutOutraSessao(){
		
		setShowLogoutOutraSessaoDialog(false);
		
		limparTelaLogin();
		
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Logout de outra sess�o n�o realizado!!!");
		
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Encaminhando para outcome de sess�o inv�lida.");
				
		return SESSAO_INVALIDA;
	}
	
	
	/**
	 * M�todo utilizado para verificar se as credenciais necess�rias para realiza��o do
	 * login foram preenchidas.
	 *
	 * @return <code>true</code> caso de dados preenchidos.
	 *         <code>false</code> caso contr�rio.
	 */
	private boolean camposPreenchidos() {
		
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Verificando se as credenciais para o login foram preenchidas.");
		
		return (this.usuario != null && this.usuario.getNomeUsuario() != null
				&& !this.usuario.getNomeUsuario().isEmpty()
				&& this.usuario.getSenhaUsuario() != null 
				&& !this.usuario.getSenhaUsuario().isEmpty());
	}

	
	/**
	 * M�todo utilizado para verificar se um usu�rio tentando logar na aplica��o
	 * j� n�o possui alguma sess�o aberta em outro navegador ou outra aba. A
	 * aplica��o est� barrando m�ltiplos acessos simult�neos de um usu�rio.
	 *
	 * @return <code>true</code> caso haja uma sess�o ativa para o usu�rio.
	 *         <code>false</code> caso n�o haja uma sess�o ativa para o usu�rio.
	 *         
	 * @throws UsuarioNaoCadastradoException 
	 */
	private boolean isUsuarioLogado() throws UsuarioNaoCadastradoException {
		
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Verificando se h� sess�o ativa para o usu�rio.");
		
		this.fachada.getUsarioByNome(this.usuario.getNomeUsuario());
					
		return this.fachada.isUsuarioLogado(this.usuario);
	}
	

}
