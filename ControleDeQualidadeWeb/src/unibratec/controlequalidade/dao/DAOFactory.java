package unibratec.controlequalidade.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOFactory {

	private static final EntityManagerFactory factory;
	private static IDAOCategoria categoriaDAO;
	private static IDAOLote loteDAO;
	private static IDAOProduto produtoDAO;

	static {
		factory = Persistence.createEntityManagerFactory("controleDeQualidadePSC");
	}

	public static IDAOCategoria getCategoriaDAO() {
		categoriaDAO = new DAOCategoria(factory.createEntityManager());
		return categoriaDAO;
	}

	public static IDAOLote getLoteDAO() {
		loteDAO = new DAOLote(factory.createEntityManager());
		return loteDAO;
	}
	public static IDAOProduto getProdutoDAO() {
		produtoDAO = new DAOProduto(factory.createEntityManager());
		return produtoDAO;
	}
	
	public static void close() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
	}

}
