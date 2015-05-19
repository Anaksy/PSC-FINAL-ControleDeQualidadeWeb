package unibratec.controlequalidade.negocio;

import java.util.ArrayList;
import java.util.List;

import unibratec.controlequalidade.dao.DAOFactory;
import unibratec.controlequalidade.dao.IDAOCategoria;
import unibratec.controlequalidade.dao.IDAOProduto;
import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.CategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.NenhumaCategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.ProdutoComCategoriaException;
import unibratec.controlequalidade.util.MensagensExceptions;

public class NegocioCategoria {

	private IDAOCategoria daoCategoria;
	private IDAOProduto daoProduto;

	public NegocioCategoria() {

		this.daoCategoria = DAOFactory.getCategoriaDAO();
		this.daoProduto = DAOFactory.getProdutoDAO();
	}

	/**
	 * Método para inserir uma Categoria
	 * 
	 * @param categoria
	 * @throws CategoriaCadastradaException
	 */
	public void inserirCategoria(Categoria categoria) throws CategoriaCadastradaException { 

		if (daoCategoria.existeCategoria(categoria)) {

			throw new CategoriaCadastradaException(MensagensExceptions.CATEGORIA_CADASTRADA_EXCEPTION);

		} else {

			daoCategoria.inserir(categoria);
		}
	}

	/**
	 * Método para listar todas as Categoria cadastradas na base
	 * 
	 * @return List<Categoria>
	 * @throws NenhumaCategoriaCadastradaException 
	 */
	public List<Categoria> listaTodasCategorias() throws NenhumaCategoriaCadastradaException{

		List<Categoria> categoriasLista = daoCategoria.consultarTodos();
		
		if (categoriasLista.isEmpty()) {
			
			throw new NenhumaCategoriaCadastradaException(MensagensExceptions.NENHUMA_CATEGORIA_CADASTRADA_EXCEPTION);
		}
		
		return daoCategoria.consultarTodos();
	}

	/**
	 * Método para buscar uma Categoria a partir de um nome. 
	 * 
	 * @param nomeCategoria
	 * @return
	 * @throws CategoriaNaoCadastradaException
	 */
	public Categoria buscaCategoriaPorNomeCategoria(String nomeCategoria) throws CategoriaNaoCadastradaException{

		Categoria cat = daoCategoria.buscaCategoria(nomeCategoria);

		if (cat != null) {

			return cat;

		} else {

			throw new CategoriaNaoCadastradaException(MensagensExceptions.CATEGORIA_NAO_CADASTRADA_EXCEPTION);
		}		
	}

	/**
	 *  Método para buscar uma Categoria por id. 
	 * 
	 * @param categoria
	 * @return
	 * @throws CategoriaNaoCadastradaException
	 */
	public Categoria buscaCategoriaPorId(Categoria categoria) throws CategoriaNaoCadastradaException{
		
		Categoria cat = daoCategoria.consultarPorId(categoria.getIdCategoria());

		if (cat != null) {

			return cat;

		} else {

			throw new CategoriaNaoCadastradaException(MensagensExceptions.CATEGORIA_NAO_CADASTRADA_EXCEPTION);
		}		
	}

	/**
	 * Método para alterar nome e número de dias para vencer de uma Categoria.
	 * 
	 * @param categoria
	 * @throws CategoriaCadastradaException
	 */
	public void alteraCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, CategoriaCadastradaException{

		Categoria cat = daoCategoria.consultarPorId(categoria.getIdCategoria());

		if (cat != null) {

			cat.setNomeCategoria(categoria.getNomeCategoria());
			cat.setNumeroDeDiasParaVencimento(categoria.getNumeroDeDiasParaVencimento());

			if (daoCategoria.existeCategoriaDiferenteId(cat)) {

				throw new CategoriaCadastradaException(MensagensExceptions.CATEGORIA_CADASTRADA_EXCEPTION);

			} else {

				daoCategoria.alterar(cat);
			}

		} else {

			throw new CategoriaNaoCadastradaException(MensagensExceptions.CATEGORIA_NAO_CADASTRADA_EXCEPTION);
		}

	}

	/**
	 * Método para remover uma Categoria.
	 * 
	 * @param categoria
	 * @throws CategoriaNaoCadastradaException 
	 * @throws ProdutoComCategoriaException 
	 */
	public void removeCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, ProdutoComCategoriaException {

		Categoria cat = daoCategoria.buscaCategoria(categoria.getNomeCategoria());
	
		if (cat != null) {
			
			List<Produto> produtoList = new ArrayList<Produto>(); 
					
			produtoList = daoProduto.pesquisarProdutoPorCategoria(cat);
			
			if (produtoList.isEmpty()) {
				daoCategoria.remover(cat);
			}
			else {
				throw new ProdutoComCategoriaException(MensagensExceptions.PRODUTO_COM_CATEGORIA_EXCEPTION);
			}

		} else {

			throw new CategoriaNaoCadastradaException(MensagensExceptions.CATEGORIA_NAO_CADASTRADA_EXCEPTION);
		}
	}
}