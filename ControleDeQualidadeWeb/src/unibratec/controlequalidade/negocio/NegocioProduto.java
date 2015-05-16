package unibratec.controlequalidade.negocio;

import java.util.ArrayList;
import java.util.List;

import unibratec.controlequalidade.dao.DAOFactory;
import unibratec.controlequalidade.dao.IDAOProduto;
import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.ProdutoNaoCadastradoException;
import unibratec.controlequalidade.util.MensagensExceptions;

public class NegocioProduto {

	private IDAOProduto daoProduto;

	public NegocioProduto() {

		this.daoProduto = DAOFactory.getProdutoDAO();
	}
	
	/**
	 * Método para inserir um produto na base
	 * 
	 * @param produto
	 */
	public void inserirProduto(Produto produto){
		this.daoProduto.inserir(produto);
	}
	
	/**
	 * Método para listar todos os produtos cadastrados na base
	 * 
	 * @return List<Produto>
	 */
	public List<Produto> listaTodosProdutos(){

		return daoProduto.consultarTodos();
	}
	
	/**
	 *  Método para buscar um produto por id. 
	 * 
	 * @param Produto
	 * @return
	 * @throws ProdutoNaoCadastradoException
	 */
	public Produto buscaProdutoPorId(long idProduto) throws ProdutoNaoCadastradoException{

		Produto produto = daoProduto.consultarPorId(idProduto);

		if (produto != null) {

			return produto;

		} else {

			throw new ProdutoNaoCadastradoException(MensagensExceptions.PRODUTO_NAO_CADASTRADO_EXCEPTION);
		}		
	}
	
	/**
	 * Método para buscar uma Produto por um nome. 
	 * 
	 * @param nomeProduto
	 * @return
	 * @throws ProdutoNaoCadastradoException
	 */
	public List<Produto> buscaProdutosPorNome(String nomeProduto) throws ProdutoNaoCadastradoException{

		List<Produto> produtosList = new ArrayList<Produto>();
				
		produtosList = daoProduto.pesquisaProdutoPorNomeList(nomeProduto);

		if (produtosList.isEmpty()) {

			throw new ProdutoNaoCadastradoException(MensagensExceptions.NENHUMA_PRODUTO_CADASTRADA_EXCEPTION);

		}
		
		return produtosList;	
	}
}
