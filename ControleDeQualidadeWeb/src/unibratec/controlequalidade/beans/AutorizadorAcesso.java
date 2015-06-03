package unibratec.controlequalidade.beans;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import unibratec.controlequalidade.util.Funcoes;
import unibratec.controlequalidade.util.MensagensGui;

public class AutorizadorAcesso implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_LOGIN = "usuario-login.xhtml";
	
	@Override
	public void afterPhase(PhaseEvent event) {
/*		
		FacesContext facesContext = event.getFacesContext();

		String currentPage = facesContext.getViewRoot().getViewId();

		System.out.println(">>>>>>>>>>>>>>>>> AutorizadorAcesso.afterPhase() "
				+ "para página de ID " + event.getFacesContext().getViewRoot().getViewId());
		
		boolean isLoginPage = (currentPage.lastIndexOf("usuario-login.xhtml") > -1);

		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		LoginMB loginBean = (LoginMB) session.getAttribute("usuario");

		if (!isLoginPage && loginBean != null) {

			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acesso negado", null));

			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();

			nh.handleNavigation(facesContext, null, "loginPage");
		}
*/
		FacesContext contexto = event.getFacesContext();
		
		String paginaAtual = contexto.getViewRoot().getViewId();
		
		System.out.println(">>>>>>>>>>>>>>>>> AutorizadorAcesso.afterPhase() "
				+ "para página de ID " + event.getFacesContext().getViewRoot().getViewId());
		
		if (!(paginaAtual.lastIndexOf(PAGINA_LOGIN) > -1)) {
			
			HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
			
			Object usuarioSessao = sessao.getAttribute(LoginMB.USUARIO_SESSAO);
			
			if (usuarioSessao == null) {
				Funcoes.erroMsg(MensagensGui.LOGIN_ACESSO_NEGADO);
				NavigationHandler navHandler = contexto.getApplication().getNavigationHandler();
				//navHandler.handleNavigation(contexto, null, LoginMB.SESSAO_INVALIDA);
				navHandler.handleNavigation(contexto, null,	"/usuario-login.xhtml");
			}
		}
	}
	
	@Override
	public void beforePhase(PhaseEvent event) {
		
		if (event.getFacesContext().getViewRoot() != null) {
			
			System.out.println(">>>>>>>>>>>>>>>>> AutorizadorAcesso.beforePhase() "
					+ "para página de ID " + event.getFacesContext().getViewRoot().getViewId()); 
			
		} else {
			
			System.out.println(">>>>>>>>>>>>>>>>> AutorizadorAcesso.beforePhase() "
					+ "indicando view root ainda nula.");
		}
	}
	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}











	/*
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext facesContext = event.getFacesContext();

		String currentPage = facesContext.getViewRoot().getViewId();

		boolean isLoginPage = (currentPage.lastIndexOf("usuario-login.xhtml") > -1);

		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		LoginMB loginBean = (LoginMB) session.getAttribute("loginBean");

		if (!isLoginPage && loginBean != null && !loginBean.isUsuarioAutenticado()) {

			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acesso negado", null));

			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();

			nh.handleNavigation(facesContext, null, "loginPage");
		}
	 */
	/*				
		FacesContext facesContext = event.getFacesContext();
		String paginaAtual = facesContext.getViewRoot().getViewId();

		boolean isPaginaLogin = (paginaAtual.lastIndexOf("usuario-login.xhtml") > -1);
		HttpSession sessao = (HttpSession) facesContext.getExternalContext().getSession(true);
		LoginMB usuarioAtual = (LoginMB) sessao.getAttribute("usuario");

		System.out.println("************************* WILKIE ***********************");
		System.out.println(paginaAtual);
		System.out.println(isPaginaLogin);
		System.out.println(usuarioAtual);
		System.out.println("************************* WILKIE ***********************");


		if (!isPaginaLogin && usuarioAtual == null) {
			//FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acesso negado", null));
			NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
			navigationHandler.handleNavigation(facesContext, null, "paginaLogin");
		}

		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioAtual", usuarioAtual);
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {

		return PhaseId.RESTORE_VIEW;
	}
	 */
}
