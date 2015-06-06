package unibratec.controlequalidade.dao;

import java.util.List;

import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;

public interface IDAOProduto extends IDAOGenerico<Produto> {
	
	/**
	 * M�todo para buscar uma lista de produtos por estado.
	 * 
	 * @param estadoProduto
	 * 
	 * @return List<Produto>
	 */
	public List<Produto> pesquisarProdutoPorEstadoList(EstadoProdutoEnum estadoProduto) throws ProdutoNaoEncontradoExcecption;

	/**
	 * M�todo para buscar um produto por estado.
	 * 
	 * @param produto
	 * 
	 * @return Produto
	 */
	public Produto pesquisarProdutoPorEstado(Produto produto) throws ProdutoNaoEncontradoExcecption;
	
	/**
	 * M�todo para buscar uma lista de produtos por categoria.
	 * 
	 * @param categoria
	 * 
	 * @return List<Produto>
	 */
	public List <Produto> pesquisarProdutoPorCategoria(Categoria categoria);

	/**
	 * M�todo para buscar uma lista de produtos pelo nome.
	 * 
	 * @param nomeProduto
	 * 
	 * @return List<Produto>
	 */
	List<Produto> pesquisaProdutoPorNomeList(String nomeProduto);
	
	/**
	 * M�todo para buscar uma lista de produtos pelo nome e estado.
	 * 
	 * @param nomeProduto, estadoProduto
	 * 
	 * @return List<Produto>
	 */
	public List<Produto> pesquisaProdutoPorNomeSituacaoList(String nomeProduto, EstadoProdutoEnum estadoProduto);

}
