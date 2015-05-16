package unibratec.controlequalidade.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;
import unibratec.controlequalidade.exceptions.NenhumaCategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.dataDeValidadeMenorPermitidaCategoriaException;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.negocio.NegocioProdutoLote;
import unibratec.controlequalidade.util.MensagensGui;

@ManagedBean(name="CriarProdutoLoteMB")
public class CriarProdutoLoteMB {

	private Categoria categoria = new Categoria();
	private Produto produto = new Produto();
	private Lote lote = new Lote();
	private List<Categoria> listaCategoria;
	private String mensagem;
	private Date dataValidade;

	private Fachada fachada = new Fachada();

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public String criarProdutoLote(){
		produto.setCategoriaProduto(categoria);
		try {
			fachada.criarProdutoLote(produto, lote);
		} 
		catch(LoteCadastradoException e){
			setMensagem("Nome de lote ja existe.");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch (dataDeValidadeMenorPermitidaCategoriaException e) {
			setMensagem(MensagensGui.LOTE_DATA_VALIDADE_MENOR_CATEGORIA_EXCEPTION);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		setMensagem("Produto//Lote criado com sucesso!");
		return mensagem;
	}

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
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

}
