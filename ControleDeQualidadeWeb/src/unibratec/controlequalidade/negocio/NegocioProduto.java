package unibratec.controlequalidade.negocio;

import javax.persistence.EntityManager;

import unibratec.controlequalidade.dao.DAOProduto;
import unibratec.controlequalidade.dao.IDAOProduto;
import unibratec.controlequalidade.entidades.Produto;

public class NegocioProduto {

	private IDAOProduto daoProduto;

	public NegocioProduto(EntityManager em) {
		this.daoProduto = new DAOProduto(em);
	}
	
	public void inserirProduto(Produto p){
		this.daoProduto.inserir(p);
	}

}
