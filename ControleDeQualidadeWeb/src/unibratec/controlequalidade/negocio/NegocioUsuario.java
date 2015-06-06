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
	 * M�todo utilizado para buscar um usu�rio no banco de dados. 
	 * 
	 * @param nomeUsuario utilizado como par�mentro da query.
	 * @param senhaUsuario utilizado para compara��o com a senha
	 * 		  do usu�rio retornado da consulta no banco de dados.
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
	 * M�todo utilizado para atualizar um usu�rio no banco de dados.
	 * 
	 * @param usuarioUpdate utilizado como par�metro da query.
	 */
	public void uptadeUsuario(Usuario usuarioUpdate){
		daoUsuario.alterar(usuarioUpdate);
	}
	
	
	/**
	 * M�todo utilizado para buscar um usu�rio no banco de dados. 
	 * 
	 * @param nomeUsuario utilizado como par�mentro da query.
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
	 * M�todo utilizado para verificar se o usu�rio est� logado no sistema.
	 * 
	 * @param usuario utilizado como par�metro da query.
	 * 
	 * @return <code>false</code> caso n�o esteja logado no sistema.
	 * 		   <code>true</code> caso contr�rio.
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
