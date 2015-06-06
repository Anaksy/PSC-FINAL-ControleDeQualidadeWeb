package unibratec.controlequalidade.negocio;

import unibratec.controlequalidade.dao.DAOFactory;
import unibratec.controlequalidade.dao.IDAOUsuario;
import unibratec.controlequalidade.entidades.StatusUsuarioEnum;
import unibratec.controlequalidade.entidades.Usuario;
import unibratec.controlequalidade.exceptions.UsuarioNaoCadastradoException;
import unibratec.controlequalidade.exceptions.UsuarioSenhaIncorretaException;
import unibratec.controlequalidade.util.MensagensExceptions;

public class NegocioUsuario {

	private IDAOUsuario daoUsuario;

	public NegocioUsuario() {
		
		this.daoUsuario = DAOFactory.getUsuarioDAO();
	}

	/**
	 * Método utilizado para buscar um usuário no banco de dados. 
	 * 
	 * @param nomeUsuario utilizado como parâmentro da query.
	 * @param senhaUsuario utilizado para comparação com a senha
	 * 		  do usuário retornado da consulta no banco de dados.
	 * 
	 * @return Usuario
	 * 
	 * @throws UsuarioNaoCadastradoException
	 * @throws UsuarioSenhaIncorretaException
	 */
	public Usuario buscaUsuario(String nomeUsuario, String senhaUsuario) throws UsuarioNaoCadastradoException, UsuarioSenhaIncorretaException{

		Usuario usr = this.daoUsuario.getUsuarioByNome(nomeUsuario);
		
		if (usr != null) {
			
			if (usr.getSenhaUsuario().equals(senhaUsuario)) {
				
				return usr;
				
			} else {

				throw new UsuarioSenhaIncorretaException(MensagensExceptions.USUARIO_SENHA_ERRADA_EXCEPTION);
			}	
			
		} else {
			
			throw new UsuarioNaoCadastradoException(MensagensExceptions.USUARIO_NAO_CADASTRADO_EXCEPTION);
		}
	}
	
	
	/**
	 * Método utilizado para atualizar um usuário no banco de dados.
	 * 
	 * @param usuarioUpdate utilizado como parâmetro da query.
	 */
	public void uptadeUsuario(Usuario usuarioUpdate){
		daoUsuario.alterar(usuarioUpdate);
	}
	
	
	/**
	 * Método utilizado para buscar um usuário no banco de dados. 
	 * 
	 * @param nomeUsuario utilizado como parâmentro da query.
	 * 
	 * @return Usuario
	 * 
	 * @throws UsuarioNaoCadastradoException
	 */
	public Usuario buscaUsuarioPorNome(String nomeUsuario) throws UsuarioNaoCadastradoException{
		
		Usuario usr = this.daoUsuario.getUsuarioByNome(nomeUsuario);

		if (usr != null) {

			return usr;

		} else {
			
			throw new UsuarioNaoCadastradoException(MensagensExceptions.USUARIO_NAO_CADASTRADO_EXCEPTION);
		}		
	}
	
	
	/**
	 * Método utilizado para verificar se o usuário está logado no sistema.
	 * 
	 * @param usuario utilizado como parâmetro da query.
	 * 
	 * @return <code>false</code> caso não esteja logado no sistema.
	 * 		   <code>true</code> caso contrário.
	 */
	public boolean isUsuarioLogado(Usuario usuario){
		
		Usuario usr = this.daoUsuario.getUsuarioByNome(usuario.getNomeUsuario());
		
		if (usr.getStatusUsuario() == StatusUsuarioEnum.ATIVO.getValue()) {
			
			return true;
		
		} else {
			
			return false;
		}	
	}
}
