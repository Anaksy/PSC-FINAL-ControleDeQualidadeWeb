package unibratec.controlequalidade.dao;

import unibratec.controlequalidade.entidades.Usuario;

public interface IDAOUsuario extends IDAOGenerico<Usuario> {

	public boolean existeUsuario(String nome);
	public Usuario getUsuario(String nome, String senha);
	
}
