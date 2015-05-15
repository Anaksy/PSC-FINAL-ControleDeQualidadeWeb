package unibratec.controlequalidade.dao;

import unibratec.controlequalidade.entidades.Categoria;


public interface IDAOCategoria extends IDAOGenerico<Categoria> {

	public boolean existeCategoria(Categoria categoria);

	public Categoria buscaCategoria(String nomeCategoria);

	boolean existeCategoriaDiferenteId(Categoria categoria);

}
