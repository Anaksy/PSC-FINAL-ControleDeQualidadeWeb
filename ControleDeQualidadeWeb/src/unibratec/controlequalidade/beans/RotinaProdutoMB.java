package unibratec.controlequalidade.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.util.MensagensGui;

@ManagedBean(name="RotinaProdutoMB")
public class RotinaProdutoMB {
	
	private Fachada fachada = new Fachada();
	
	public void executarRotinaProduto(){
		try {
			fachada.executarRotinaProdutos();
			infoMsg(MensagensGui.ROTINA_PRODUTO_SUCESSO);
		} catch (ProdutoNaoEncontradoExcecption e) {
			avisoMsg(MensagensGui.ROTINA_PRODUTO_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	private void infoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação:", msg));
	}

	private void avisoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", msg));
	}

	private void erroMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
	}
	
	public String voltarTelaInicial(){
		return "/index.xhtml";
	}

}
