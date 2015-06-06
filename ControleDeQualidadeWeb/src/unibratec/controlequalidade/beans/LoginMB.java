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
	private boolean showLogoutOutraSessaoDialog; // Flag do "rendered" do logoutOutraSessaoForm

	public LoginMB() {}

	/*
	 * É importante colocar suas inicializações no post construct e não no
	 * construtor da classe, isso porque se você estiver realizando injeção de 
	 * dependência (no Spring, por exemplo) todas as dependências podem não
	 * estar carregadas na construção da sua classe, então no post construct
	 * você garante que tudo já foi carregado e agora você podem ser usados. 
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
	 * Método utilizado para tentativas de login no sistema, confrontando dados
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
					
					// Recuperando a sessão do contexto
					HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(true);

					// Adicionando o usuário na sessão
					sessao.setAttribute(USUARIO_SESSAO, usuarioAutenticado);

					// Configura o acesso das páginas e informações de acordo com perfil do usuário
					this.controladorAcesso.configuraAcesso();

					// Setando o status do usuário para ativo na sessão
					usuarioAutenticado.setStatusUsuario(StatusUsuarioEnum.ATIVO.getValue());

					// Atualizando no banco a informação de usuário ativo em uma sessão
					this.fachada.atualizaStatusUsuario(usuarioAutenticado);		
					
					System.out.println(">>>>>>>>>>>>> Login com Sucesso!!!");
					
					return OUTCOME_LOGIN;
					
				} else{
					
					Funcoes.erroMsg(null, MensagensGui.LOGIN_USUARIO_LOGADO_OUTRA_SESSAO);
					
					// Apresenta o dialog sobre fazer logout de outra sessão
					setShowLogoutOutraSessaoDialog(true);
					
					System.out.println(">>>>>>>>>>>>> Usuário logado em outra Sessão");
					
					return "";
				}
		
			} else{
				
				Funcoes.avisoMsg(null, MensagensGui.LOGIN_PREENCHER_CAMPOS);
				
				System.out.println(">>>>>>>>>>>>> Campos não preenchidos corretamente ou não preenchidos.");
				
				return "";
			
			}

		} catch (UsuarioNaoCadastradoException e) {

			e.printStackTrace();
			
			System.out.println(e.getMessage());
			
			Funcoes.erroMsg(null, MensagensGui.LOGIN_USUARIO_NAO_CADASTRADO);
			
			return "";

		} catch (UsuarioSenhaIncorretaException e) {

			e.printStackTrace();
			
			System.out.println(e.getMessage());
			
			Funcoes.avisoMsg(null, MensagensGui.LOGIN_USUARIO_SENHA_INCORRETO);
			
			return "";
		}
	}

	
	/**
	 * Método utilizado para finalizar a sessão atual de um usuário no sistema.
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

			System.out.println(">>>>>>>>>>>>> Logout com Sucesso!!!");
			
			return OUTCOME_LOGOUT;
		
		} else{
			
			System.out.println(">>>>>>>>>>>>> Logout falhou!!!");
			
			System.out.println(">>>>>>>>>>>>> Não há usuário na sessão.");
			
			return "";
		}
	}

	
	/**
	 * Método utilizado para fazer logout de um usuário no sistema em uma sessão aberta
	 * em outro navegador ou outra aba. A outra sessão é representada por uma flag no 
	 * banco de dados.
	 * <code>"0"</code> caso não haja uma sessão aberta
	 * <code>"1"</code> caso haja uma sessão aberta.
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

			System.out.println(">>>>>>>>>>>>> Logout com Sucesso!!!");

			return OUTCOME_LOGOUT;
		
		} catch (UsuarioNaoCadastradoException e) {
			
			e.printStackTrace();
			
			System.out.println(e.getMessage());
			
			Funcoes.erroMsg(null, MensagensGui.LOGIN_USUARIO_NAO_CADASTRADO);
			
			System.out.println(">>>>>>>>>>>>> Logout falhou!!!");
			
			System.out.println(">>>>>>>>>>>>> Não há usuário na sessão.");
			
			return "";
		}		
	}
	
	
	/**
	 * Método utilizado para limpar todos os dados da tela de login.
	 */
	public void limparTelaLogin() {
		
		System.out.println(">>>>>>>>>>>>> Limpando os dados da tela de login!!!");
		
		this.usuario = new Usuario();
	}
	
	
	/**
	 * Método utilizado para não fazer logout de um usuário no sistema em uma sessão aberta
	 * em outro navegador ou outra aba. O pop up de questionamento sobre fazer o logout de
	 * outra sessão é fechado e os dados da tela são resetados.
	 * 
	 * @return Outcome associado a sessão inválida. 
	 */
	public String naofazLogoutOutraSessao(){
		
		setShowLogoutOutraSessaoDialog(false);
		
		limparTelaLogin();
		
		System.out.println(">>>>>>>>>>>>> Logout de outra sessão não realizado!!!");
		
		System.out.println(">>>>>>>>>>>>> Encaminhando para outcome de sessão inválida.");
				
		return SESSAO_INVALIDA;
	}
	
	
	/**
	 * Método utilizado para verificar se as credenciais necessárias para realização do
	 * login foram preenchidas.
	 *
	 * @return <code>true</code> caso de dados preenchidos.
	 *         <code>false</code> caso contrário.
	 */
	private boolean camposPreenchidos() {
		
		System.out.println(">>>>>>>>>>>>> Verificando se as credenciais para o login foram preenchidas.");
		
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
	 * @return <code>true</code> caso haja uma sessão ativa para o usuário.
	 *         <code>false</code> caso não haja uma sessão ativa para o usuário.
	 *         
	 * @throws UsuarioNaoCadastradoException 
	 */
	private boolean isUsuarioLogado() throws UsuarioNaoCadastradoException {
		
		System.out.println(">>>>>>>>>>>>> Verificando se há sessão ativa para o usuário.");
		
		this.fachada.getUsarioByNome(this.usuario.getNomeUsuario());
					
		return this.fachada.isUsuarioLogado(this.usuario);
	}
	

}
