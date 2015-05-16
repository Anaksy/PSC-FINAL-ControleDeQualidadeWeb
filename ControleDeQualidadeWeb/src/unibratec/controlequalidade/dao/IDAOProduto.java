package unibratec.controlequalidade.dao;

import java.util.List;

import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;

public interface IDAOProduto extends IDAOGenerico<Produto> {
	
	public List<Produto> pesquisarProdutoPorEstadoList(EstadoProdutoEnum estadoProduto) throws ProdutoNaoEncontradoExcecption;

	public Produto pesquisarProdutoPorEstado(Produto produto) throws ProdutoNaoEncontradoExcecption;
	
	public List <Produto> pesquisarProdutoPorCategoria(Categoria categoria);

	List<Produto> pesquisaProdutoPorNomeList(String nomeProduto);

}
