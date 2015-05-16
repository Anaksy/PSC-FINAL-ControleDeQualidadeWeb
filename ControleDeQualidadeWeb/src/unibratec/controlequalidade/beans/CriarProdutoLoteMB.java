package unibratec.controlequalidade.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.negocio.Fachada;

@ManagedBean(name="CriarProdutoLoteMB")
public class CriarProdutoLoteMB {
	
	private Categoria categoria = new Categoria();
	private Fachada fachada = new Fachada();
	private List<Categoria> listaCategoria;
	private String mensagem;
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Categoria> getListaCategoria() {
		listaCategoria = fachada.listaTodasCategorias();
		return listaCategoria;
	}
	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
