package unibratec.controlequalidade.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

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
	private Fachada fachada = new Fachada();
	private List<Categoria> listaCategoria;
	private String mensagem;
	private static long categoriaId;

	public List<Categoria> getListaCategoria() {
		try {
			listaCategoria = fachada.listaTodasCategorias();
		} catch (NenhumaCategoriaCadastradaException e) {
			setMensagem(MensagensGui.CATEGORIA_BD_FALHA);
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

	public void selecionarCategoria(Categoria c){
		try {
			Categoria CategoriaEncontrada = fachada.buscaCategoriaPorNomeCategoria(c.getNomeCategoria());
			categoria.setIdCategoria(CategoriaEncontrada.getIdCategoria());
			categoria.setNomeCategoria(CategoriaEncontrada.getNomeCategoria());
			categoria.setNumeroDeDiasParaVencimento(CategoriaEncontrada.getNumeroDeDiasParaVencimento());
			categoriaId = CategoriaEncontrada.getIdCategoria();
		} catch (CategoriaNaoCadastradaException e) {
			setMensagem(MensagensGui.CATEGORIA_CADASTRADA_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	} 

	public String atualizarCategoria(){
		try {
			categoria.setIdCategoria(categoriaId);
			fachada.alteraCategoria(categoria);
			setMensagem(MensagensGui.CATEGORIA_ATUALIZADA_SUCESSO);
		} catch (CategoriaCadastradaException e) {
			setMensagem(MensagensGui.CATEGORIA_CADASTRADA_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (CategoriaNaoCadastradaException e) {
			setMensagem(MensagensGui.CATEGORIA_CADASTRADA_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return mensagem;
	}

	public String criarCategoria(){
		try {
			fachada.inserirCategoria(categoria);
			setMensagem(MensagensGui.CATEGORIA_CADASTRADA_SUCESSO);
		} catch (CategoriaCadastradaException e) {
			setMensagem(MensagensGui.CATEGORIA_CADASTRADA_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return mensagem;
	}

	public String removerCategoria(){
		try {
			fachada.removeCategoria(categoria);
			setMensagem(MensagensGui.CATEGORIA_REMOVIDA_SUCESSO);
		} catch (CategoriaNaoCadastradaException e) {
			setMensagem(MensagensGui.CATEGORIA_REMOVIDA_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ProdutoComCategoriaException e) {
			setMensagem(MensagensGui.PRODUTO_COM_CATEGORIA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return mensagem;
	}
}
