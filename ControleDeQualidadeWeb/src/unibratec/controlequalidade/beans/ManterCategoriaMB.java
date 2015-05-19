package unibratec.controlequalidade.beans;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.exceptions.CategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.NenhumaCategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.ProdutoComCategoriaException;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.util.MensagensGui;

@ManagedBean(name="ManterCategoriaMB")
public class ManterCategoriaMB {

	private Categoria categoria = new Categoria();
	private String mensagem;
	private Fachada fachada = new Fachada();
	private List<Categoria> listaCategoria;

	public List<Categoria> getListaCategoria() {
		try {
			listaCategoria = fachada.listaTodasCategorias();
		} catch (NenhumaCategoriaCadastradaException e) {
			erroMsg(MensagensGui.CATEGORIA_BD_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String selecionarCategoria(Categoria c){
		try {
			Categoria CategoriaEncontrada = fachada.buscaCategoriaPorNomeCategoria(c.getNomeCategoria());
			categoria.setIdCategoria(CategoriaEncontrada.getIdCategoria());
			categoria.setNomeCategoria(CategoriaEncontrada.getNomeCategoria());
			categoria.setNumeroDeDiasParaVencimento(CategoriaEncontrada.getNumeroDeDiasParaVencimento());
		} catch (CategoriaNaoCadastradaException e) {
			avisoMsg(MensagensGui.CATEGORIA_SELECIONAR_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	} 

	public String atualizarCategoria(){
		try {
			fachada.alteraCategoria(categoria);
			infoMsg(MensagensGui.CATEGORIA_ATUALIZADA_SUCESSO);
		} catch (CategoriaCadastradaException e) {
			erroMsg(MensagensGui.CATEGORIA_ATUALIZADA_JA_EXISTE);
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (CategoriaNaoCadastradaException e) {
			erroMsg(MensagensGui.CATEGORIA_ATUALIZADA_NAO_EXISTE);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String criarCategoria(){
		try {
			fachada.inserirCategoria(categoria);
			infoMsg(MensagensGui.CATEGORIA_CADASTRADA_SUCESSO);
		} catch (CategoriaCadastradaException e) {
			avisoMsg(MensagensGui.CATEGORIA_CADASTRADA_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String removerCategoria(){
		try {
			fachada.removeCategoria(categoria);
			infoMsg(MensagensGui.CATEGORIA_REMOVIDA_SUCESSO);
		} catch (CategoriaNaoCadastradaException e) {
			avisoMsg(MensagensGui.CATEGORIA_REMOVIDA_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ProdutoComCategoriaException e) {
			erroMsg(MensagensGui.CATEGORIA_PRODUTO_COM_CATEGORIA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void infoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", msg));
	}

	public void avisoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", msg));
	}

	public void erroMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
	}
}
