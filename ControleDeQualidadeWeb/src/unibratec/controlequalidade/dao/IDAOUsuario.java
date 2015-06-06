package unibratec.controlequalidade.dao;

import unibratec.controlequalidade.entidades.Usuario;

public interface IDAOUsuario extends IDAOGenerico<Usuario> {
	
	/**
	 * Método utilizado para retornar um usuário do banco de dados.
	 * 
	 * @param nomeUsuario utilizado como parâmetro da query.
	 * 
	 * @return Usuario 
	 */
	public Usuario getUsuarioByNome(String nomeUsuario);
	
}
