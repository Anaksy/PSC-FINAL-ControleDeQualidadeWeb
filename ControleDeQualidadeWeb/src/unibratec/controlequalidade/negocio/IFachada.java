package unibratec.controlequalidade.negocio;

import java.util.List;

import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.exceptions.CategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.ProdutoComCategoriaException;


public interface IFachada {
	
	public void inserirCategoria(Categoria categoria) throws CategoriaCadastradaException;
	
	public List<Categoria> listaTodasCategorias();
	
	public Categoria buscaCategoriaPorNomeCategoria(String nomeCategoria) throws CategoriaNaoCadastradaException;
	
	//public Categoria buscaCategoriaPorId(long idCategoria) throws CategoriaNaoCadastradaException;
	
	public void alteraCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, CategoriaCadastradaException;
	
	//public void alteraNomeCategoria(Categoria categoria, String novoNomeCategoria) throws CategoriaCadastradaException, CategoriaNaoCadastradaException;
	
	//public void alteraDiasParaVencerCategoria(Categoria categoria, long novoNumeroDeDiasParaVencimento) throws CategoriaCadastradaException, CategoriaNaoCadastradaException;
	
	public void removeCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, ProdutoComCategoriaException;
	

//	public void inserirProdutoLote(Produto produto, Lote lote, Calendar dataValidade, int qtdProdutos) throws dataDeValidadeMenorPermitidaCategoriaException;
//	
//	public List<Produto> getListaProdutoPrestesVencer() throws ProdutoNaoEncontradoExcecption;
//	
//	public Produto getProdutoPrestesVencer(Produto produto) throws ProdutoNaoPrestesAVencerException, ProdutoNaoEncontradoExcecption;
//		
//	public void setDescontoProdutoPrestesVencer(Produto produto, double desconto);
	
 
	

}
