package unibratec.controlequalidade.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;

public class DAOLote extends DAOGenerico<Lote> implements IDAOLote {

	public DAOLote(EntityManager em) {
		super(em);
	}

	/**
	 * Verifica a existencia de um lote.
	 * 
	 * @param Lote
	 * 
	 * @return true/false
	 * @throws LoteCadastradoException 
	 */
	@Override
	public boolean existeLote(Lote lote) throws LoteCadastradoException {

		TypedQuery<Lote> query = this.entityManager.createNamedQuery(Lote.FIND_BY_NOME, this.classePersistente);

		query.setParameter("nomeLote", "%" + lote.getNomeLote() + "%");

		try {

			Lote lt = query.setMaxResults(1).getSingleResult();

			System.out.println(lt);
				
			return true;
		
		} catch (NoResultException e) {

			e.printStackTrace();

			System.out.println(e.getMessage());
			
			return false;
		}
	}

	/**
	 * M�todo para buscar um Lote pelo nome.
	 * 
	 * @param String nomeLote
	 * 
	 * @return Lote
	 */
	@Override
	public Lote buscaLote(String nomeLote) {

		TypedQuery<Lote> query = this.entityManager.createNamedQuery(Lote.FIND_BY_NOME, this.classePersistente);

		query.setParameter("nomeLote", nomeLote);

		try {

			Lote lt = query.setMaxResults(1).getSingleResult();

			System.out.println(lt);
			
			return lt;
			

		} catch (NoResultException e) {

			e.printStackTrace();

			System.out.println(e.getMessage());

			return null;
		}
	}

	/**
	 * M�todo para buscar o n�mero de dias para vencer da categoria dos produtos do lote.
	 * 
	 * @param Lote
	 * 
	 * @return Long
	 */
	@Override
	public Long pesquisaNDiasPVenderCategoriaDeLote(Lote lote){

		TypedQuery<Produto> query = this.entityManager.createNamedQuery(Produto.FIND_BY_LOTE_ID, Produto.class);

		query.setParameter("idLoteProduto", lote.getIdLote());
		
		try{

			Produto pdto = query.setMaxResults(1).getSingleResult();

			System.out.println(pdto);

			return pdto.getCategoriaProduto().getNumeroDeDiasParaVencimento();
		
		} catch (NoResultException e) {

			e.printStackTrace();

			System.out.println(e.getMessage());
			
			return null;
		}
	}

}


