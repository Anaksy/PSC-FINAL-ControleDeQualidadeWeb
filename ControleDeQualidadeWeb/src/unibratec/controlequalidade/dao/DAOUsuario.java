package unibratec.controlequalidade.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import unibratec.controlequalidade.entidades.Usuario;

public class DAOUsuario extends DAOGenerico<Usuario> implements IDAOUsuario {

	protected String NAMED_QUERY_FIND_USUARIO_BY_NAME = "Usuario.findUsuarioByName";
	protected String NAMED_QUERY_FIND_USUARIO = "Usuario.findUsuario";

	public DAOUsuario(EntityManager em) {
		super(em);
	}

	@Override
	public boolean existeUsuario(String nomeUsuario) {
		
		TypedQuery<Usuario> query = this.entityManager.createNamedQuery(NAMED_QUERY_FIND_USUARIO_BY_NAME, this.classePersistente);

		query.setParameter("nomeUsuario", nomeUsuario);
		
		try {
			
			Usuario usuario = query.setMaxResults(1).getSingleResult();
			
			System.out.println(usuario);
			
			if (!usuario.equals(null)) {
				
				return true;
			}
			
			return false;
			
		} catch (NoResultException e) {
			
			System.out.println(e.getMessage());
			
			return false;
		}
	}
	
	@Override
	public Usuario getUsuario(String nomeUsuario, String senhaUsuario) {

		TypedQuery<Usuario> query = this.entityManager.createNamedQuery(NAMED_QUERY_FIND_USUARIO, this.classePersistente);

		query.setParameter("nomeUsuario", nomeUsuario);
		query.setParameter("senhaUsuario", senhaUsuario);

		try {
			
			Usuario usuario = query.setMaxResults(1).getSingleResult();
			
			System.out.println(usuario);
			
			return usuario;
			
		} catch (NoResultException e) {
			
			System.out.println(e.getMessage());
			
			return null;
		}

	}

}
