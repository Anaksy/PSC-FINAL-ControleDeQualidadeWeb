package unibratec.controlequalidade.dao;

import unibratec.controlequalidade.entidades.Categoria;


public interface IDAOCategoria extends IDAOGenerico<Categoria> {

	/**
	 * Método utilizado para verificar a existência de uma categoria
	 * com mesmo nome da categoria recebida por parâmetro.
	 * 
	 * @param Categoria
	 * 
	 * @return <code>false</code> caso não exista.
	 * 		   <code>true</code> caso exista.
	 */
	public boolean existeCategoria(Categoria categoria);

	/**
	 * Método utilizado para buscar no banco dados uma categoria.
	 * 
	 * @param nomeCategoria utilizado como parãmetro da query.
	 * 
	 * @return Categoria
	 */
	public Categoria buscaCategoria(String nomeCategoria);

	/**
	 * Método utilizado para verificar a existência de uma categoria 
	 * com mesmo nome, e id diferente da categoria recebida por parâmetro.
	 * 
	 * @param Categoria utilizado como parâmentro da query.
	 * 
	 * @return <code>false</code> caso não exista.
	 * 		   <code>true</code> caso exista.
	 */
	boolean existeCategoriaDiferenteId(Categoria categoria);

}
