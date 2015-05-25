package unibratec.controlequalidade.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import unibratec.controlequalidade.entidades.Usuario;
import unibratec.controlequalidade.exceptions.UsuarioNaoCadastradoException;
import unibratec.controlequalidade.exceptions.UsuarioSenhaIncorretaException;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.util.MensagensGui;

@ManagedBean(name ="LoginMB")
@ViewScoped
public class LoginMB {
	
	private Fachada fachada = new Fachada();
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String fazLogin(){
		
		try {
			usuario = fachada.buscaUsuario(usuario.getNomeUsuario(), usuario.getSenhaUsuario());
		} catch (UsuarioNaoCadastradoException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			usuario = new Usuario();
			erroMsg(MensagensGui.LOGIN_USUARIO_NAO_CADASTRADO);
			return null;
		} catch (UsuarioSenhaIncorretaException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			usuario = new Usuario();
			avisoMsg(MensagensGui.LOGIN_USUARIO_SENHA_INCORRETO);
			return null;
		}
		
		infoMsg(MensagensGui.LOGIN_ACESSO_PERMITIDO);
		return "/index.xhtml";
	}
	
	public void limpa(){
		usuario = new Usuario();
	}
	
	private void infoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	private void avisoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
	}

	private void erroMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}
}
