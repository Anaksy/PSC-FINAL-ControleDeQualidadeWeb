package unibratec.controlequalidade.dao;

import unibratec.controlequalidade.entidades.Categoria;


public interface IDAOCategoria extends IDAOGenerico<Categoria> {

	/**
	 * M�todo utilizado para verificar a exist�ncia de uma categoria
	 * com mesmo nome da categoria recebida por par�metro.
	 * 
	 * @param Categoria
	 * 
	 * @return <code>false</code> caso n�o exista.
	 * 		   <code>true</code> caso exista.
	 */
	public boolean existeCategoria(Categoria categoria);

	/**
	 * M�todo utilizado para buscar no banco dados uma categoria.
	 * 
	 * @param nomeCategoria utilizado como par�metro da query.
	 * 
	 * @return Categoria
	 */
	public Categoria buscaCategoria(String nomeCategoria);

	/**
	 * M�todo utilizado para verificar a exist�ncia de uma categoria 
	 * com mesmo nome, e id diferente da categoria recebida por par�metro.
	 * 
	 * @param Categoria utilizado como par�mentro da query.
	 * 
	 * @return <code>false</code> caso n�o exista.
	 * 		   <code>true</code> caso exista.
	 */
	boolean existeCategoriaDiferenteId(Categoria categoria);

}
