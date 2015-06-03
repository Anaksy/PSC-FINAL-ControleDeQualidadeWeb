package unibratec.controlequalidade.dao;

import unibratec.controlequalidade.entidades.Usuario;

public interface IDAOUsuario extends IDAOGenerico<Usuario> {
	
	public Usuario getUsuarioByNome(String nomeUsuario);
	
}
