package unibratec.controlequalidade.negocio;

import unibratec.controlequalidade.dao.DAOFactory;
import unibratec.controlequalidade.dao.IDAOUsuario;
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

		boolean existeUsuario = daoUsuario.existeUsuario(nomeUsuario);

		if (existeUsuario) {

			Usuario usr = daoUsuario.getUsuario(nomeUsuario, senhaUsuario);

			if (usr != null) {

				return usr;

			} else {

				throw new UsuarioSenhaIncorretaException(MensagensExceptions.USUARIO_SENHA_ERRADA_EXCEPTION);
			}	

		} else {
			
			throw new UsuarioNaoCadastradoException(MensagensExceptions.USUARIO_NAO_CADASTRADO_EXCEPTION);
		}

	}
}
