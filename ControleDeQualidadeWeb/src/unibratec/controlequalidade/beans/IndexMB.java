package unibratec.controlequalidade.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="IndexMB")
public class IndexMB {
	

	public String manterCategoria(){
		return "/categoria-manter.xhtml";
	}
	
	public String criarprodutoLote(){
		return "/produto-lote-criar.xhtml";
	}
	
	public String rotinaProduto(){
		return "/produto-rotina.xhtml";
	}
	
	public String pesquisarProdutos(){
		return "/produto-pesquisar.xhtml";
	}
}
