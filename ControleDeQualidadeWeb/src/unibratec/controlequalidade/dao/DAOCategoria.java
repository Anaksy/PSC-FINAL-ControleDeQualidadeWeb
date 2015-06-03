package unibratec.controlequalidade.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import unibratec.controlequalidade.entidades.Categoria;

public class DAOCategoria extends DAOGenerico<Categoria> implements
IDAOCategoria {

	public DAOCategoria(EntityManager em) {
		super(em);
	}

	/**
	 * Método para verificar a existência de uma categoria com mesmo nome
	 * da categoria recebida por parâmetro.
	 * 
	 * @param Categoria
	 * 
	 * @return true/false
	 */
	@Override
	public boolean existeCategoria(Categoria categoria) {

		TypedQuery<Categoria> query = this.entityManager.createNamedQuery(Categoria.FIND_BY_NOME, this.classePersistente);

		query.setParameter("nomeCategoria", categoria.getNomeCategoria());

		try {

			Categoria cat = query.setMaxResults(1).getSingleResult();

			System.out.println(cat);

			return true;
		
		} catch (NoResultException e) {
			
			e.printStackTrace();

			System.out.println(e.getMessage());
			
			return false;
		}

	}
	
	/**
	 * Método para verificar a existência de uma categoria com mesmo nome, 
	 * e id diferente da categoria recebida por parâmetro.
	 * 
	 * @param Categoria
	 * 
	 * @return true/false
	 */
	@Override
	public boolean existeCategoriaDiferenteId(Categoria categoria) {

		TypedQuery<Categoria> query = this.entityManager.createNamedQuery(Categoria.FIND_BY_NOME_DIFERENTE_ID, this.classePersistente);

		query.setParameter("nomeCategoria", categoria.getNomeCategoria());
		
		query.setParameter("idCategoria", categoria.getIdCategoria());

		try {

			Categoria cat = query.setMaxResults(1).getSingleResult();

			System.out.println(cat);

			return true;

		} catch (NoResultException e) {
			
			e.printStackTrace();

			System.out.println(e.getMessage());
			
			return false;
		}

	}
	
	/**
	 * Método para buscar uma categoria pelo nome.
	 * 
	 * @param String nomeCategoria
	 * 
	 * @return Categoria
	 */
	@Override
	public Categoria buscaCategoria(String nomeCategoria) {

		TypedQuery<Categoria> query = this.entityManager.createNamedQuery(Categoria.FIND_BY_NOME, this.classePersistente);

		query.setParameter("nomeCategoria", nomeCategoria);

		try {

			Categoria cat = query.setMaxResults(1).getSingleResult();

			System.out.println(cat);
			
			return cat;

		} catch (NoResultException e) {

			e.printStackTrace();

			System.out.println(e.getMessage());
			
			return null;
		}
	}
	
}
