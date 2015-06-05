package unibratec.controlequalidade.beans;

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

		FacesContext contexto = event.getFacesContext();
		
		String paginaAtual = contexto.getViewRoot().getViewId();
		
		System.out.println(">>>>>>>>>>>>>>>>> AutorizadorAcesso.afterPhase() "
				+ "para página de ID " + event.getFacesContext().getViewRoot().getViewId()); // TIRAR DEPOIS
		
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
	
}
