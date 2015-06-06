package unibratec.controlequalidade.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import unibratec.controlequalidade.entidades.Usuario;

public class DAOUsuario extends DAOGenerico<Usuario> implements IDAOUsuario {

	public DAOUsuario(EntityManager em) {
		super(em);
	}

	@Override
	public Usuario getUsuarioByNome(String nomeUsuario) {

		TypedQuery<Usuario> query = this.entityManager.createNamedQuery(Usuario.FIND_BY_NAME, this.classePersistente);

		query.setParameter("nomeUsuario", nomeUsuario);

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
