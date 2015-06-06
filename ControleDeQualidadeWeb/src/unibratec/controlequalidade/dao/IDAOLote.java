package unibratec.controlequalidade.dao;

import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;

public interface IDAOLote extends IDAOGenerico<Lote> {

	/**
	 * Método utilizado para verificar se um lote ja existe no sistema.
	 * 
	 * @param Lote.
	 * 
	 * @return <code>false</code> caso não exista.
	 * 		   <code>true</code> caso exista.
	 */
	public boolean existeLote(Lote lote) throws LoteCadastradoException;

	
	/**
	 * Método utilizado para buscar no banco um lote.
	 * 
	 * @param nome do lote.
	 * 
	 * @return lote.
	 */
	public Lote buscaLote(String nomeLote);
	
	/**
	 * Método utilizado para buscar o numero de dias para vencer de uma categoria dos produtos de um lote.
	 * 
	 * @param nome do lote.
	 * 
	 * @return numero de dias para vencer.
	 */
	public Long pesquisaNDiasPVencerCategoriaDeLote(Lote lote);

}
