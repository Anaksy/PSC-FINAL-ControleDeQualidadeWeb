package unibratec.controlequalidade.dao;

import unibratec.controlequalidade.entidades.Usuario;

public interface IDAOUsuario extends IDAOGenerico<Usuario> {
	
	/**
	 * M�todo utilizado para retornar um usu�rio do banco de dados.
	 * 
	 * @param nomeUsuario utilizado como par�metro da query.
	 * 
	 * @return Usuario 
	 */
	public Usuario getUsuarioByNome(String nomeUsuario);
	
}
