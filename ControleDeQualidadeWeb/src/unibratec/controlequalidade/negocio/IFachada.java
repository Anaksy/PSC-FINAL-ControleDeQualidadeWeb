package unibratec.controlequalidade.negocio;

import java.util.Calendar;
import java.util.List;

import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.CategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;
import unibratec.controlequalidade.exceptions.NenhumaCategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.ProdutoComCategoriaException;
import unibratec.controlequalidade.exceptions.ProdutoNaoCadastradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.exceptions.dataDeValidadeMenorPermitidaCategoriaException;


public interface IFachada {
	
	public void inserirCategoria(Categoria categoria) throws CategoriaCadastradaException;
	
	public List<Categoria> listaTodasCategorias() throws NenhumaCategoriaCadastradaException;
	
	public void alteraCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, CategoriaCadastradaException;

	public void removeCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, ProdutoComCategoriaException;
	
	public Categoria buscaCategoriaPorNomeCategoria(String nomeCategoria) throws CategoriaNaoCadastradaException;
		
	public void criarProdutoLote(Produto produto, Lote lote) throws dataDeValidadeMenorPermitidaCategoriaException, LoteCadastradoException;
	
	public Categoria buscarCategoriaPorId(Categoria categoria) throws CategoriaNaoCadastradaException;
	
	public void executarRotinaProdutos() throws ProdutoNaoEncontradoExcecption;
	
	public List<Produto> buscaProdutosPorSituacaoList(EstadoProdutoEnum estadoProdutoEnum) throws ProdutoNaoEncontradoExcecption;
	
	public List<Produto> buscaProdutosPorNome(String nomeProduto) throws ProdutoNaoCadastradoException;
	
	public List<Produto> listaTodosProdutos() throws ProdutoNaoCadastradoException;
	

//	public void inserirProdutoLote(Produto produto, Lote lote, Calendar dataValidade, int qtdProdutos) throws dataDeValidadeMenorPermitidaCategoriaException;
//	
//	public List<Produto> getListaProdutoPrestesVencer() throws ProdutoNaoEncontradoExcecption;
//	
//	public Produto getProdutoPrestesVencer(Produto produto) throws ProdutoNaoPrestesAVencerException, ProdutoNaoEncontradoExcecption;
//		
//	public void setDescontoProdutoPrestesVencer(Produto produto, double desconto);
	
 
	

}
