package unibratec.controlequalidade.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;

public class DAOLote extends DAOGenerico<Lote> implements IDAOLote {

	protected String NAMED_QUERY_BYNOME = "Lote.findByNome";
	protected String NAMED_QUERY_PRODUTO_BYLOTEID = "Produto.findByLoteId";

	public DAOLote(EntityManager em) {
		super(em);
	}

	/**
	 * Verifica a existencia de um lote no banco de dados
	 * 
	 * @param Nome do lote
	 * 
	 * @return true/false
	 * @throws LoteCadastradoException 
	 */
	@Override
	public boolean existeLote(Lote lote) throws LoteCadastradoException {

		TypedQuery<Lote> query = this.entityManager.createNamedQuery(NAMED_QUERY_BYNOME, this.classePersistente);

		query.setParameter("nomeLote", "%" + lote.getNomeLote() + "%");

		try {

			Lote lt = query.setMaxResults(1).getSingleResult();

			if (!lt.equals(null)) {
				System.out.println("Retorno da base diferente de nulo!"); // APAGAR DEPOIS
				return true;
			}

		} catch (NoResultException e) {
			e.printStackTrace();
			e.getMessage();
			System.out.println("Retorno nulo da base!"); // APAGAR DEPOIS
			return false;
		}

		System.out.println("Retorno nulo da base!"); // APAGAR DEPOIS
		return false;

	}

	/**
	 * Método para buscar um Lote pelo nome.
	 * 
	 * @param nomeLote
	 * 
	 * @return Lote
	 */
	@Override
	public Lote buscaLote(String nomeLote) {

		TypedQuery<Lote> query = this.entityManager.createNamedQuery(NAMED_QUERY_BYNOME, this.classePersistente);

		query.setParameter("nomeLote", nomeLote);

		try {

			Lote lt = query.setMaxResults(1).getSingleResult();

			System.out.println(lt); // APAGAR DEPOIS

			if (!lt.equals(null)) {
				System.out.println("Retorno da base diferente de nulo!"); // APAGAR DEPOIS
				return lt;
			}

		} catch (NoResultException e) {

			System.out.println("Retorno nulo da base! - NoResultException"); // APAGAR DEPOIS
			return null;
		}

		return null;
	}

	/**
	 * Método para buscar o número de dias para vencer da categoria dos produtos do lote.
	 * 
	 * @param Lote
	 * 
	 * @return Long
	 */
	@Override
	public long pesquisaNDiasPVenderCategoriaDeLote(Lote lote){

		TypedQuery<Produto> query = this.entityManager.createNamedQuery(NAMED_QUERY_PRODUTO_BYLOTEID, Produto.class);

		query.setParameter("idLoteProduto", lote.getIdLote());
		try{

			Produto pdto = query.setMaxResults(1).getSingleResult();

			System.out.println(pdto); //APAGAR DEPOIS

			return pdto.getCategoriaProduto().getNumeroDeDiasParaVencimento();
		}
		catch (NoResultException e) {

			System.out.println("Retorno nulo da base! - NoResultException"); // APAGAR DEPOIS
			return -1;
		}
	}



}


