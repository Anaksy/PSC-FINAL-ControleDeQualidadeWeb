package unibratec.controlequalidade.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;

public class DAOProduto extends DAOGenerico<Produto> implements IDAOProduto {
	
	protected String NAMED_QUERY_FIND_PRODUTO_BY_CATEGORIAID = "Produto.findProdutoByCategoriaId";
	protected String NAMED_QUERY_FIND_BY_ESTADO = "Produto.findByEstado";
	protected String NAMED_QUERY_FIND_BY_NAME = "Produto.findByName";
	protected String NAMED_QUERY_FIND_BY_NOME_E_SITUACAO = "Produto.findBySituacaoENome";

	public DAOProduto(EntityManager em) {
		super(em);
	}
	
	@Override
	public List<Produto> pesquisarProdutoPorEstadoList(EstadoProdutoEnum estadoProduto) {

		TypedQuery<Produto> query = this.entityManager.createNamedQuery(
				NAMED_QUERY_FIND_BY_ESTADO, this.classePersistente);

		query.setParameter("estadoProduto", estadoProduto);
		
			return query.getResultList();
	}
	
	@Override
	public Produto pesquisarProdutoPorEstado(Produto produto) throws ProdutoNaoEncontradoExcecption {

		TypedQuery<Produto> query = this.entityManager.createNamedQuery(
				NAMED_QUERY_FIND_BY_ESTADO, this.classePersistente);

		query.setParameter("estadoProduto", produto.getEstadoProduto());
		
			return query.setMaxResults(1).getSingleResult();
	}

	@Override
	public List<Produto> pesquisarProdutoPorCategoria(Categoria categoria) {
		TypedQuery<Produto> query = this.entityManager.createNamedQuery(NAMED_QUERY_FIND_PRODUTO_BY_CATEGORIAID, this.classePersistente);

		query.setParameter("idCategoria", categoria.getIdCategoria());
		
		return query.getResultList();
	}
	
	/**
	 * Método para buscar produtos pelo nome.
	 * 
	 * @param nomeProduto
	 * 
	 * @return List<Produto>
	 */
	@Override
	public List<Produto> pesquisaProdutoPorNomeList(String nomeProduto) {

		TypedQuery<Produto> query = this.entityManager.createNamedQuery(NAMED_QUERY_FIND_BY_NAME, this.classePersistente);

		query.setParameter("nomeProduto", nomeProduto);
				
		return query.getResultList();

	}
	
	/**
	 * Método para buscar produtos pelo nome e situacao.
	 * 
	 * @param nomeProduto, situacaoProduto
	 * 
	 * @return List<Produto>
	 */
	@Override
	public List<Produto> pesquisaProdutoPorNomeSituacaoList(String nomeProduto, EstadoProdutoEnum estadoProduto) {

		TypedQuery<Produto> query = this.entityManager.createNamedQuery(NAMED_QUERY_FIND_BY_NOME_E_SITUACAO, this.classePersistente);

		query.setParameter("nomeProduto", nomeProduto);
		query.setParameter("estadoProduto", estadoProduto);
				
		return query.getResultList();

	}
}
