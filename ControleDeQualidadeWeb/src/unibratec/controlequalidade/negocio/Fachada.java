package unibratec.controlequalidade.negocio;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.CategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.ProdutoComCategoriaException;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.exceptions.ProdutoNaoPrestesAVencerException;
import unibratec.controlequalidade.exceptions.dataDeValidadeMenorPermitidaCategoriaException;

public class Fachada implements IFachada {

	//private NegocioLote negocioLote;
	private NegocioProduto negocioProduto;
	private NegocioCategoria negocioCategoria;
	//private NegocioVenda negocioVenda;
	//private NegocioProdutoLote npl;

	public Fachada() {
		//this.negocioLote = new NegocioLote(em);
		//this.negocioProduto = new NegocioProduto();
		this.negocioCategoria = new NegocioCategoria();
		//this.negocioVenda = new NegocioVenda(em);
	}

	@Override
	public void inserirCategoria(Categoria categoria) throws CategoriaCadastradaException {
		this.negocioCategoria.inserirCategoria(categoria);

	}

	@Override
	public List<Categoria> listaTodasCategorias() {
		return this.negocioCategoria.listaTodasCategorias();
	}

	@Override
	public void alteraCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, CategoriaCadastradaException {
		this.negocioCategoria.alteraCategoria(categoria);
	}

	@Override
	public void removeCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, ProdutoComCategoriaException {
		this.negocioCategoria.removeCategoria(categoria);
	}
	
	@Override
	public Categoria buscaCategoriaPorNomeCategoria(String nomeCategoria) throws CategoriaNaoCadastradaException {
		return this.negocioCategoria.buscaCategoriaPorNomeCategoria(nomeCategoria);
}

	@Override
	public void inserirProduto(Produto p) {
		this.negocioProduto.inserirProduto(p);
		
	}

	//	@Override
	//	public void inserirProdutoLote(Produto produto, Lote lote, Calendar dataValidade, int qtdProdutos) throws dataDeValidadeMenorPermitidaCategoriaException {
	//		npl.associaLoteProduto(lote, produto, dataValidade, qtdProdutos);
	//		negocioLote.gerarLote(lote);
	//		negocioProduto.inserirProduto(produto);
	//		
	//	}
	//
	//	@Override
	//	public void inserirCategoria(Categoria categoria) {
	//		negocioCategoria.inserirCategoria(categoria);
	//		
	//	}
	//
	//	@Override
	//	public List<Produto> getListaProdutoPrestesVencer() throws ProdutoNaoEncontradoExcecption {
	//		return negocioVenda.retornaListaProdutosPrestesAVencer();
	//	}
	//
	//	@Override
	//	public Produto getProdutoPrestesVencer(Produto produto) throws ProdutoNaoPrestesAVencerException, ProdutoNaoEncontradoExcecption {
	//		
	//		return negocioVenda.retornaProdutoPrestesAVencer(produto);
	//	}
	//
	//	@Override
	//	public void setDescontoProdutoPrestesVencer(Produto produto, double desconto) {
	//		negocioVenda.darDescontoProdutoPrestesAVencer(produto, desconto);
	//		
	//	}

}
