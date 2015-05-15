package unibratec.controlequalidade.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import unibratec.controlequalidade.entidades.Categoria;

public class DAOCategoria extends DAOGenerico<Categoria> implements
IDAOCategoria {

	protected String NAMED_QUERY_BYNOME = "Categoria.findByNome";
	protected String NAMED_QUERY_BYNOME_DIFERENTE_ID = "Categoria.findByNomeDiferenteId";

	public DAOCategoria(EntityManager em) {
		super(em);
	}

	/**
	 * Método para verificar a existência de uma categoria no banco de dados
	 * 
	 * @param Categoria
	 * 
	 * @return true/false
	 */
	@Override
	public boolean existeCategoria(Categoria categoria) {

		TypedQuery<Categoria> query = this.entityManager.createNamedQuery(NAMED_QUERY_BYNOME, this.classePersistente);

		query.setParameter("nomeCategoria", categoria.getNomeCategoria());

		try {

			Categoria cat = query.setMaxResults(1).getSingleResult();

			System.out.println(cat); // APAGAR DEPOIS

			if (!cat.equals(null)) {
				System.out.println("Retorno da base diferente de nulo!"); // APAGAR DEPOIS
				return true;
			}

		} catch (NoResultException e) {
			
			System.out.println("Retorno nulo da base! - NoResultException"); // APAGAR DEPOIS
			return false;
		}
		
		System.out.println("Retorno nulo da base!"); // APAGAR DEPOIS
		return false;
	}
	
	/**
	 * Método para verificar a existência de uma categoria no banco de dados
	 * 
	 * @param Categoria
	 * 
	 * @return true/false
	 */
	@Override
	public boolean existeCategoriaDiferenteId(Categoria categoria) {

		TypedQuery<Categoria> query = this.entityManager.createNamedQuery(NAMED_QUERY_BYNOME_DIFERENTE_ID, this.classePersistente);

		query.setParameter("nomeCategoria", categoria.getNomeCategoria());
		
		query.setParameter("idCategoria", categoria.getIdCategoria());

		try {

			Categoria cat = query.setMaxResults(1).getSingleResult();

			System.out.println(cat); // APAGAR DEPOIS

			if (!cat.equals(null)) {
				System.out.println("Retorno da base diferente de nulo!"); // APAGAR DEPOIS
				return true;
			}

		} catch (NoResultException e) {
			
			System.out.println("Retorno nulo da base! - NoResultException"); // APAGAR DEPOIS
			return false;
		}
		
		System.out.println("Retorno nulo da base!"); // APAGAR DEPOIS
		return false;
	}
	
	/**
	 * Método para buscar uma categoria pelo nome.
	 * 
	 * @param nomeCategoria
	 * 
	 * @return Categoria
	 */
	@Override
	public Categoria buscaCategoria(String nomeCategoria) {

		TypedQuery<Categoria> query = this.entityManager.createNamedQuery(NAMED_QUERY_BYNOME, this.classePersistente);

		query.setParameter("nomeCategoria", nomeCategoria);

		try {

			Categoria cat = query.setMaxResults(1).getSingleResult();

			System.out.println(cat); // APAGAR DEPOIS

			if (!cat.equals(null)) {
				System.out.println("Retorno da base diferente de nulo!"); // APAGAR DEPOIS
				return cat;
			}

		} catch (NoResultException e) {

			System.out.println("Retorno nulo da base! - NoResultException"); // APAGAR DEPOIS
			return null;
		}
		
		return null;
	}
	
}
