package unibratec.controlequalidade.negocio;

import java.util.ArrayList;
import java.util.List;
import unibratec.controlequalidade.dao.DAOFactory;
import unibratec.controlequalidade.dao.IDAOProduto;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.ProdutoNaoCadastradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
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
		
		produto.setEstadoProduto(EstadoProdutoEnum.EM_ESTOQUE);
		
		this.daoProduto.inserir(produto);
	}
	
	/**
	 * Método para listar todos os produtos cadastrados na base
	 * 
	 * @return List<Produto>
	 * @throws ProdutoNaoCadastradoException 
	 */
	public List<Produto> listaTodosProdutos() throws ProdutoNaoCadastradoException{

		List<Produto> produtosList = this.daoProduto.consultarTodos();
		
		if (produtosList.isEmpty()) {
			
			throw new ProdutoNaoCadastradoException(MensagensExceptions.NENHUM_PRODUTO_CADASTRADO_EXCEPTION);
		}
		
		return produtosList;
	}
	
	/**
	 *  Método para buscar um produto por id. 
	 * 
	 * @param Produto
	 * @return
	 * @throws ProdutoNaoCadastradoException
	 */
	public Produto buscaProdutoPorId(long idProduto) throws ProdutoNaoCadastradoException{

		Produto produto = this.daoProduto.consultarPorId(idProduto);

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
				
		produtosList = this.daoProduto.pesquisaProdutoPorNomeList(nomeProduto);

		if (produtosList.isEmpty()) {

			throw new ProdutoNaoCadastradoException(MensagensExceptions.NENHUM_PRODUTO_CADASTRADO_NOME_EXCEPTION);

		}
		
		return produtosList;	
	}
	
	/**
	 * Método para buscar uma Produto por situação 
	 * 
	 * @param estadoProdutoEnum
	 * @return
	 * @throws ProdutoNaoEncontradoExcecption 
	 */
	public List<Produto> buscaProdutosPorSituacaoList(EstadoProdutoEnum estadoProdutoEnum) throws ProdutoNaoEncontradoExcecption{

		List<Produto> produtosList = new ArrayList<Produto>();
				
		produtosList = this.daoProduto.pesquisarProdutoPorEstadoList(estadoProdutoEnum);

		if (produtosList.isEmpty()) {

			throw new ProdutoNaoEncontradoExcecption(MensagensExceptions.NENHUM_PRODUTO_CADASTRADO_SITUACAO_EXCEPTION);

		}
		
		return produtosList;	
	}
	
	/**
	 * Método para alterar um produto.
	 * 
	 * @param produto
	 * @throws ProdutoNaoCadastradoException 
	 */
	public void alteraProduto(Produto produto) throws ProdutoNaoCadastradoException{

		Produto pdto = this.daoProduto.consultarPorId(produto.getIdProduto());

		if (pdto != null) {

			pdto.setNomeProduto(produto.getFabricanteProduto());
			pdto.setFabricanteProduto(produto.getFabricanteProduto());
			pdto.setCategoriaProduto(produto.getCategoriaProduto());
			pdto.setPrecoProduto(produto.getPrecoProduto());
			pdto.setEstadoProduto(produto.getEstadoProduto());
			
			this.daoProduto.alterar(pdto);
			
		} else {

			throw new ProdutoNaoCadastradoException(MensagensExceptions.PRODUTO_NAO_CADASTRADO_EXCEPTION);
		}
	}
	
	
	/**
	 * Método para remover um produto.
	 * 
	 * @param produto
	 * @throws ProdutoNaoCadastradoException 
	 */
	public void removeProduto(Produto produto) throws ProdutoNaoCadastradoException {

		Produto pdto = this.daoProduto.consultarPorId(produto.getIdProduto());
	
		if (pdto != null) {
			
			pdto.setEstadoProduto(EstadoProdutoEnum.INATIVO);
			
			this.daoProduto.alterar(pdto);

		} else {

			throw new ProdutoNaoCadastradoException(MensagensExceptions.PRODUTO_NAO_CADASTRADO_EXCEPTION);
		}
	}
}
