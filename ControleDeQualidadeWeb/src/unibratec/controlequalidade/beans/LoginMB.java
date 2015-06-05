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
import unibratec.controlequalidade.util.Funcoes;
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
	private boolean showLogoutOutraSessaoDialog;

	public LoginMB() {}

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
	 * Utilizado para tentativas de login no sistema, confrontando dados
	 * fornecidos pelo usuário com registros de usuários cadastrados.
	 *
	 * @return Outcome associado a fracasso ou sucesso na tentativa de login.
	 */
	public String fazLogin() {

		try {

			if (camposPreenchidos()){
				
				// Verificando se o usuário passado na tela de login tem credenciais válidas
				Usuario usuarioAutenticado = this.fachada.autenticaUsuario(this.usuario.getNomeUsuario(), this.usuario.getSenhaUsuario());
				
				if (!isUsuarioLogado()) {
					
					// Recuperando o contexto do JSF
					FacesContext contexto = FacesContext.getCurrentInstance();
					
					// Recuperando a sessão para o usuário
					HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(true);

					// Adicionando o usuário na sessão
					sessao.setAttribute(USUARIO_SESSAO, usuarioAutenticado);

					// Configura o acesso de acordo com perfil do usuário
					this.controladorAcesso.configuraAcesso();

					// Setando o status do usuário para ativo na sessão
					usuarioAutenticado.setStatusUsuario(StatusUsuarioEnum.ATIVO.getValue());

					// Atualizando a informação de usuário ativo na sessão
					this.fachada.atualizaStatusUsuario(usuarioAutenticado);		

					return OUTCOME_LOGIN;
					
				} else{
					
					Funcoes.erroMsg(MensagensGui.LOGIN_USUARIO_LOGADO_OUTRA_SESSAO);
					
					setShowLogoutOutraSessaoDialog(true);
					
					System.out.println("ShowLogoutOutraSessaoDialog: "+ this.showLogoutOutraSessaoDialog); // TIRAR DEPOIS
					
					return "";
				}
		
			} else{
				
				Funcoes.avisoMsg(MensagensGui.LOGIN_PREENCHER_CAMPOS);
				
				return "";
			
			}

		} catch (UsuarioNaoCadastradoException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			Funcoes.erroMsg(MensagensGui.LOGIN_USUARIO_NAO_CADASTRADO);
			return "";

		} catch (UsuarioSenhaIncorretaException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			Funcoes.avisoMsg(MensagensGui.LOGIN_USUARIO_SENHA_INCORRETO);
			return "";
		}
	}

	

	/**
	 * Utilizado para finalizar uma sessão de um usuário no sistema.
	 *
	 * @return Outcome associado a fracasso ou sucesso na tentativa de logout.
	 */
	public String fazLogout() {

		// Recuperando o contexto do JSF
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		// Recuperando a sessão para o usuário
		HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);

		// Capturando o usuário da sessão
		Usuario usuarioSessao = (Usuario) sessao.getAttribute(USUARIO_SESSAO);

		if (usuarioSessao != null) {

			usuarioSessao.setStatusUsuario(StatusUsuarioEnum.INATIVO.getValue());

			this.fachada.atualizaStatusUsuario(usuarioSessao);
		}

		contexto.getExternalContext().invalidateSession();

		return OUTCOME_LOGOUT;
	}

	
	public String fazLogoutOutraSessao() throws UsuarioNaoCadastradoException{
		
		Usuario usuarioOutraSessao = this.fachada.getUsarioByNome(this.usuario.getNomeUsuario());
		
		usuarioOutraSessao.setStatusUsuario(StatusUsuarioEnum.INATIVO.getValue());

		this.fachada.atualizaStatusUsuario(usuarioOutraSessao);
		
		setShowLogoutOutraSessaoDialog(false);
		
		System.out.println("ShowLogoutOutraSessaoDialog: "+ this.showLogoutOutraSessaoDialog); // TIRAR DEPOIS
		
		return OUTCOME_LOGOUT;
	}
	
	
	/**
	 * Limpa todos os dados da tela de login.
	 */
	public void limparTelaLogin() {
		
		System.out.println("public String limparTela()"); // APAGAR DEPOIS
		
		this.usuario = new Usuario();
	}
	
	
	public String naofazLogoutOutraSessao(){
		
		setShowLogoutOutraSessaoDialog(false);
		
		System.out.println("ShowLogoutOutraSessaoDialog: "+ this.showLogoutOutraSessaoDialog); // TIRAR DEPOIS
		
		limparTelaLogin();
		
		return SESSAO_INVALIDA;
	}
	
	
	/**
	 * Utilizado para verificar se as credenciais necessárias para realização do
	 * login foram preenchidas.
	 *
	 * @return <code>true</code> em caso de dados preenchidos.
	 *         <code>false</code> caso contrário.
	 */
	private boolean camposPreenchidos() {
		return (this.usuario != null && this.usuario.getNomeUsuario() != null
				&& !this.usuario.getNomeUsuario().isEmpty()
				&& this.usuario.getSenhaUsuario() != null 
				&& !this.usuario.getSenhaUsuario().isEmpty());
	}

	
	/**
	 * Método utilizado para verificar se um usuário tentando logar na aplicação
	 * já não possui alguma sessão aberta em outro navegador ou outra aba. A
	 * aplicação está barrando múltiplos acessos simultâneos de um usuário.
	 *
	 * @return <code>true</code> se já existir uma sessão ativa para o usuário.
	 *         <code>false</code> caso contrário.
	 * @throws UsuarioNaoCadastradoException 
	 */
	private boolean isUsuarioLogado() throws UsuarioNaoCadastradoException {

		this.fachada.getUsarioByNome(this.usuario.getNomeUsuario());
					
		return this.fachada.isUsuarioLogado(this.usuario);
	}
}
