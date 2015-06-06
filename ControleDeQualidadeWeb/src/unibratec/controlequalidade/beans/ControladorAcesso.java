package unibratec.controlequalidade.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import unibratec.controlequalidade.entidades.PerfilUsuarioEnum;
import unibratec.controlequalidade.entidades.Usuario;

public class ControladorAcesso {

	private boolean permissaoAdministrador;
	private boolean permissaoUsuario;

	
	public boolean isPermissaoAdministrador() {
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(true);
		
		Usuario usuarioSessao = (Usuario) sessao.getAttribute(LoginMB.USUARIO_SESSAO);
		
		if (usuarioSessao != null) {
			
			this.permissaoAdministrador = (usuarioSessao.getPerfilUsuario() == PerfilUsuarioEnum.ADMIN);
		
		} else {
		
			this.permissaoAdministrador = false;
		}
		
		return this.permissaoAdministrador;
	}

	
	public boolean isPermissaoUsuario() {
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(true);
				
		Usuario usuarioSessao = (Usuario) sessao.getAttribute(LoginMB.USUARIO_SESSAO);
		
		if (usuarioSessao != null) {
			
			this.permissaoAdministrador = (usuarioSessao.getPerfilUsuario() == PerfilUsuarioEnum.ADMIN);
			
			if (this.permissaoAdministrador) {
				
				this.permissaoUsuario = true;
			
			} else {
			
				this.permissaoUsuario = (usuarioSessao.getPerfilUsuario() == PerfilUsuarioEnum.USER);
			}
			
		} else {
			
			this.permissaoUsuario = false;
		}
		
		return this.permissaoUsuario;
	}

	
	/**
	 * Método utilizado para configurar o perfil de acesso do usuário logado às
	 * funcionalidades da aplicação.
	 */
	public void configuraAcesso() {
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(true);
		
		Usuario usuarioSessao = (Usuario) sessao.getAttribute(LoginMB.USUARIO_SESSAO);

		if (usuarioSessao != null) {
			
			Logger.getLogger("ControladorAcesso").log(Level.INFO,">>>>>>>>>>>>>> Usuário da sessão tem tipo " + usuarioSessao.getPerfilUsuario());
			
			this.permissaoAdministrador = (usuarioSessao.getPerfilUsuario() == PerfilUsuarioEnum.ADMIN);
			
			if (this.permissaoAdministrador) {
				
				this.permissaoUsuario = true;
			
			} else {
				
				this.permissaoUsuario = (usuarioSessao.getPerfilUsuario() == PerfilUsuarioEnum.USER);
			}
		}
	}
}
