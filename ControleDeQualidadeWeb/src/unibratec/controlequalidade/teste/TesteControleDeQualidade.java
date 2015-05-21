package unibratec.controlequalidade.teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import unibratec.controlequalidade.dao.DAOCategoria;
import unibratec.controlequalidade.dao.DAOLote;
import unibratec.controlequalidade.dao.DAOProduto;
import unibratec.controlequalidade.dao.IDAOProduto;
import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.CategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.ContateOAdministradorException;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;
import unibratec.controlequalidade.exceptions.LoteNaoCadastradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.exceptions.dataDeValidadeMenorPermitidaCategoriaException;
import unibratec.controlequalidade.negocio.NegocioCategoria;
import unibratec.controlequalidade.negocio.NegocioLote;
import unibratec.controlequalidade.negocio.NegocioProduto;
import unibratec.controlequalidade.negocio.NegocioProdutoLote;
import unibratec.controlequalidade.negocio.NegocioVenda;

public class TesteControleDeQualidade {

	public static void main(String[] args) {
/*	//############# ENTITY MANAGER ############# 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controleDeQualidadePSC");
		EntityManager etManager = emf.createEntityManager(); 
		//############# ENTITY MANAGER #############
*/		
		
		NegocioVenda nv = new NegocioVenda();
		try {
			nv.executarRotinaProdutos();
		} catch (ProdutoNaoEncontradoExcecption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
/*		Categoria c0 = new Categoria("Teste", 20);
		c0.setIdCategoria(1);
		
		IDAOProduto idp = new DAOProduto(etManager);
		
		List <Produto> produtoList = new ArrayList<Produto>();
		
		produtoList =  idp.pesquisarProdutoPorCategoria(c0);
		
		for (Produto produto : produtoList) {
			System.out.println(produto);
		}*/
		
		
		
		
		
		/*
		//============================================================================================		
		//Simulando o comboBox de categoria previamente definidas
		Categoria c0 = new Categoria("Laticíneos", 30);
		NegocioCategoria negocioCategoria = new NegocioCategoria();
		try {
			negocioCategoria.inserirCategoria(c0);
		} catch (CategoriaCadastradaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//Simulando o campo de data de validade
		Calendar cl0 = Calendar.getInstance();
		cl0.set(2020, 4, 10);

		//Simulando campo de quantidade de produtos
		int qtdProdutos = 200;

		Lote l0 = new Lote();
		Produto p0 = new Produto("Toddynho", "Pepisco", c0, 1.50);

		NegocioProdutoLote npl = new NegocioProdutoLote();
		try {
			npl.associaLoteProduto(l0, p0, cl0, qtdProdutos);

			//Inserindo lote ja associado ao produto
			NegocioLote negocioLote = new NegocioLote();
			negocioLote.inserirLote(l0);

			//Inserindo produto ja associado ao lote
			NegocioProduto np = new NegocioProduto(etManager);
			np.inserirProduto(p0);

		} catch (dataDeValidadeMenorPermitidaCategoriaException e) {
			e.printStackTrace();
			e.getMessage();
		} catch (LoteCadastradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//=========== Alterando Lote
/*		Lote l0 = new Lote();
		l0.setNomeLote("LT2015MAI9-T068697x");
		
		Calendar cl0 = Calendar.getInstance();
		cl0.set(2015, 4, 10);
		
		NegocioLote negocioLote = new NegocioLote();
		try {
			negocioLote.alteraLote(l0, cl0);
		} catch (CategoriaNaoCadastradaException | LoteCadastradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoteNaoCadastradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (dataDeValidadeMenorPermitidaCategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		//============================================================================================
		//
		//		//Teste de retorno da lista de produtos prestes a vencer
		//		
		//		NegocioVenda nv = new NegocioVenda(etManager);
		//		
		//		try {
		//			
		//			for (Produto p : nv.retornaListaProdutosPrestesAVencer()) {
		//				
		//				System.out.println(p.toString());
		//			}
		//		} catch (ProdutoNaoEncontradoExcecption e) {
		//			e.printStackTrace();
		//			e.getMessage();
		//			
		//		}

		//============================================================================================	

		//============================================================================================	

		//		DAOProduto daoProduto = new DAOProduto(etManager);
		//		NegocioVenda nv = new NegocioVenda(etManager);
		//		
		//		Long idProduto = new Long(1);
		//		Long desconto = new Long(1);
		//		System.out.println(daoProduto.consultarPorId(idProduto));
		//		nv.darDescontoProdutoPrestesAVencer(daoProduto.consultarPorId(idProduto), desconto);


		//============================================================================================
		/*
		//Simulando o comboBox de categoria previamente definidas
		Categoria c1 = new Categoria("teste1", 30);
		Categoria c2 = new Categoria("teste2", 50);
		Categoria c3 = new Categoria("teste3", 20);
		NegocioCategoria ngCategoria = new NegocioCategoria();

		
		// INSERINDO UMA CATEGORIA
		try {
			ngCategoria.inserirCategoria(c0);
		} catch (CategoriaCadastradaException e) {
			System.out.println(e.getMessage());
		}

		
		// LISTANDO TODAS AS CATEGORIAS
		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		
		listaCategorias = ngCategoria.listaTodasCategorias();
		
		for (Categoria categoria : listaCategorias) {
			System.out.println(categoria);
		}
		
		// BUSCANDO CATEGORIA POR NOME
		try {
			ngCategoria.buscaCategoriaPorNomeCategoria(c1.getNomeCategoria());
		} catch (CategoriaNaoCadastradaException e) {
			System.out.println(e.getMessage());
		}

		// BUSCANDO CATEGORIA POR ID
		try {
			System.out.println(ngCategoria.buscaCategoriaPorId(1));
		} catch (CategoriaNaoCadastradaException e) {
			System.out.println(e.getMessage());
		}

		// ALTERANDO CATEGORIA COMPLETA
		try {
			ngCategoria.alteraCategoria(c3, "teste3", 20);
		
		} catch (CategoriaNaoCadastradaException e) {
		
			System.out.println(e.getMessage());
		
		} catch (CategoriaCadastradaException e) {
			
			System.out.println(e.getMessage());
		}

		// REMOVENDO CATEGORIA
		try {
			ngCategoria.removeCategoria(c3);
		} catch (CategoriaNaoCadastradaException e) {
			System.out.println(e.getMessage());
		}
*/
		
	}
}
