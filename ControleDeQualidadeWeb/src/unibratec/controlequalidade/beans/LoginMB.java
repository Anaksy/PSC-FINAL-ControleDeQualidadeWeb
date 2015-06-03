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

	public LoginMB() {}

	@PostConstruct
	public void inicializar() {
		this.fachada = new Fachada();
		this.usuario = new Usuario();
		this.controladorAcesso = new ControladorAcesso();
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Inicializando um bean de login.");
	}

	/**
	 * Utilizado para tentativas de login no sistema, confrontando dados
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
					
					// Recuperando a sess�o para o usu�rio
					HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

					// Adicionando o usu�rio na sess�o
					sessao.setAttribute(USUARIO_SESSAO, usuarioAutenticado);


					//controladorAcesso.configurarAcesso();


					// Setando o status do usu�rio para ativo na sess�o
					usuarioAutenticado.setStatusUsuario(StatusUsuarioEnum.ATIVO.getValue());

					// Atualizando a informa��o de usu�rio ativo na sess�o
					this.fachada.atualizaStatusUsuario(usuarioAutenticado);		

					return OUTCOME_LOGIN;
					
				} else{
					
					Funcoes.erroMsg(MensagensGui.LOGIN_USUARIO_LOGADO_OUTRA_SESSAO);
					return null;
				}
		
			} else{
				
				Funcoes.avisoMsg(MensagensGui.LOGIN_PREENCHER_CAMPOS);
				return null;
			
			}

		} catch (UsuarioNaoCadastradoException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			Funcoes.erroMsg(MensagensGui.LOGIN_USUARIO_NAO_CADASTRADO);
			return null;

		} catch (UsuarioSenhaIncorretaException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			Funcoes.avisoMsg(MensagensGui.LOGIN_USUARIO_SENHA_INCORRETO);
			return null;
		}
	}

	/**
	 * Utilizado para finalizar uma sess�o de um usu�rio no sistema.
	 *
	 * @return Outcome associado a fracasso ou sucesso na tentativa de logout.
	 */
	public String fazLogout() {

		FacesContext context = FacesContext.getCurrentInstance();

		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		Usuario usuarioSessao = (Usuario) sessao.getAttribute(USUARIO_SESSAO);

		if (usuarioSessao != null) {

			usuarioSessao.setStatusUsuario(StatusUsuarioEnum.INATIVO.getValue());

			this.fachada.atualizaStatusUsuario(usuarioSessao);
		}

		context.getExternalContext().invalidateSession();

		return OUTCOME_LOGOUT;
	}

	/**
	 * Utilizado para verificar se as credenciais necess�rias para realiza��o do
	 * login foram preenchidas.
	 *
	 * @return <code>true</code> em caso de dados preenchidos.
	 *         <code>false</code> caso contr�rio.
	 */
	private boolean camposPreenchidos() {
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
	 * @return <code>true</code> se j� existir uma sess�o ativa para o usu�rio.
	 *         <code>false</code> caso contr�rio.
	 * @throws UsuarioNaoCadastradoException 
	 */
	private boolean isUsuarioLogado() throws UsuarioNaoCadastradoException {

		fachada.getUsarioByNome(this.usuario.getNomeUsuario());
					
		return this.fachada.isUsuarioLogado(this.usuario);
	}

	/**
	 * Limpa todos os dados da tela de login.
	 */
	public void limparTela() {
		usuario = new Usuario();
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

}
