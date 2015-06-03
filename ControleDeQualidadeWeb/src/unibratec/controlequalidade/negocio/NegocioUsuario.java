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
	
	public void uptadeUsuario(Usuario usuarioUpdate){
		daoUsuario.alterar(usuarioUpdate);
	}
	
	public Usuario buscaUsuarioPorNome(String usuario) throws UsuarioNaoCadastradoException{
		
		Usuario usr = this.daoUsuario.getUsuarioByNome(usuario);

		if (usr != null) {

			return usr;

		} else {
			
			throw new UsuarioNaoCadastradoException(MensagensExceptions.USUARIO_NAO_CADASTRADO_EXCEPTION);
		}		
	}
	
	public boolean isUsuarioLogado(Usuario usuario){
		
		Usuario usr = this.daoUsuario.getUsuarioByNome(usuario.getNomeUsuario());
		
		if (usr.getStatusUsuario() == StatusUsuarioEnum.ATIVO.getValue()) {
			
			return true;
		
		} else {
			
			return false;
		}	
	}
}
