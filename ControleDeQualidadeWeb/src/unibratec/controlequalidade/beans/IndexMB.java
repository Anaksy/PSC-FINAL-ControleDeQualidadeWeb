package unibratec.controlequalidade.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="IndexMB")
public class IndexMB {
	

	public String manterCategoria(){
		return "/ManterCategoria.xhtml";
	}
	
	public String criarprodutoLote(){
		return "/CriarProdutoLote.xhtml";
	}
	
	public String rotinaProduto(){
		return "/RotinaProduto.xhtml";
	}
}
