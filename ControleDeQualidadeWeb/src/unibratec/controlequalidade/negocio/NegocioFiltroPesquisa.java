package unibratec.controlequalidade.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import unibratec.controlequalidade.dao.DAOFactory;
import unibratec.controlequalidade.dao.IDAOProduto;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.FiltroPesquisaProdutoNaoEncontradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoCadastradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.util.Funcoes;
import unibratec.controlequalidade.util.MensagensExceptions;

public class NegocioFiltroPesquisa {

	private NegocioProduto negocioProduto = new NegocioProduto();
	private IDAOProduto daoProduto;

	public NegocioFiltroPesquisa() {
		this.daoProduto = DAOFactory.getProdutoDAO();
	}

	public List <Produto> filtrarPesquisaSituacao(EstadoProdutoEnum estadoProdutoEnum) throws ProdutoNaoEncontradoExcecption{
		return negocioProduto.buscaProdutosPorSituacaoList(estadoProdutoEnum);
	}


	public List <Produto> filtrarPesquisaNome(String nomeProduto) throws ProdutoNaoCadastradoException{
		return negocioProduto.buscaProdutosPorNome(nomeProduto);
	}


	public List<Produto> filtrarPesquisaFaixaDataValidade(Date dataInicial, Date dataFinal) throws ProdutoNaoCadastradoException, FiltroPesquisaProdutoNaoEncontradoException{

		List<Produto> listaProduto = negocioProduto.listaTodosProdutos();

		List<Produto> novaListaProduto = new ArrayList<Produto>();

		for (Produto produto : listaProduto) {

			Date dataProdutoLote = produto.getLoteProduto().getDataDeValidade().getTime();

			if(Funcoes.procurarEntreDatas(dataProdutoLote, dataInicial, dataFinal) == true){
				novaListaProduto.add(produto);
			}
		}

		if (novaListaProduto.isEmpty()) {
			throw new FiltroPesquisaProdutoNaoEncontradoException(MensagensExceptions.FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO);
		}
		else {
			return novaListaProduto;
		}

	}

	public List<Produto> filtrarPesquisaNomeSituacao(String nomeProduto, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException{

		List<Produto> listaProduto = daoProduto.pesquisaProdutoPorNomeSituacaoList(nomeProduto, estadoProdutoEnum);

		if (listaProduto.isEmpty()) {
			throw new FiltroPesquisaProdutoNaoEncontradoException(MensagensExceptions.FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO);
		}
		else {
			return listaProduto;
		}
	}

	public List<Produto> filtrarPesquisaFaixaDataNome(Date dataInicial, Date dataFinal, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException{

		List<Produto> listaTodosProdutos = negocioProduto.listaTodosProdutos();

		List<Produto> listaFiltradaData = new ArrayList<Produto>();

		List<Produto> listaFiltradaNome = new ArrayList<Produto>();

		for (Produto produto : listaTodosProdutos) {

			Date dataProdutoLote = produto.getLoteProduto().getDataDeValidade().getTime();

			if(Funcoes.procurarEntreDatas(dataProdutoLote, dataInicial, dataFinal) == true){
				listaFiltradaData.add(produto);
			}
		}

		if (listaFiltradaData.isEmpty()) {
			throw new FiltroPesquisaProdutoNaoEncontradoException(MensagensExceptions.FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO);
		}
		else {

			for (Produto produto : listaFiltradaData) {

				String nomeDoproduto = produto.getNomeProduto();

				if (nomeDoproduto.toLowerCase().contains(nomeProduto.toLowerCase())) {
					listaFiltradaNome.add(produto);
				}
			}

			if (listaFiltradaNome.isEmpty()) {
				throw new FiltroPesquisaProdutoNaoEncontradoException(MensagensExceptions.FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO);
			}
			else {
				return listaFiltradaNome;
			}
		}

	}

	public List<Produto> filtrarPesquisaFaixaDataSituacao(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException{

		List<Produto> listaTodosProdutos = negocioProduto.listaTodosProdutos();

		List<Produto> listaFiltradaData = new ArrayList<Produto>();

		List<Produto> listaFiltradaSituacao= new ArrayList<Produto>();

		for (Produto produto : listaTodosProdutos) {

			Date dataProdutoLote = produto.getLoteProduto().getDataDeValidade().getTime();

			if(Funcoes.procurarEntreDatas(dataProdutoLote, dataInicial, dataFinal) == true){
				listaFiltradaData.add(produto);
			}
		}

		if (listaFiltradaData.isEmpty()) {
			throw new FiltroPesquisaProdutoNaoEncontradoException(MensagensExceptions.FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO);
		}
		else {

			for (Produto produto : listaFiltradaData) {

				EstadoProdutoEnum situacaoDoProduto = produto.getEstadoProduto();

				if (situacaoDoProduto.equals(estadoProdutoEnum)) {
					listaFiltradaSituacao.add(produto);
				}
			}

			if (listaFiltradaSituacao.isEmpty()) {
				throw new FiltroPesquisaProdutoNaoEncontradoException(MensagensExceptions.FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO);
			}
			else {
				return listaFiltradaSituacao;
			}
		}

	}

	public List<Produto> filtrarPesquisaFaixaDataSituacaoNome(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException{

		List<Produto> listaTodosProdutos = negocioProduto.listaTodosProdutos();

		List<Produto> listaFiltradaData = new ArrayList<Produto>();

		List<Produto> listaFiltradaSituacao= new ArrayList<Produto>();

		List<Produto> listaFiltradaNome= new ArrayList<Produto>();

		for (Produto produto : listaTodosProdutos) {

			Date dataProdutoLote = produto.getLoteProduto().getDataDeValidade().getTime();

			if(Funcoes.procurarEntreDatas(dataProdutoLote, dataInicial, dataFinal) == true){
				listaFiltradaData.add(produto);
			}
		}

		if (listaFiltradaData.isEmpty()) {
			throw new FiltroPesquisaProdutoNaoEncontradoException(MensagensExceptions.FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO);
		}
		else {

			for (Produto produto : listaFiltradaData) {

				EstadoProdutoEnum situacaoDoProduto = produto.getEstadoProduto();

				if (situacaoDoProduto.equals(estadoProdutoEnum)) {
					listaFiltradaSituacao.add(produto);
				}
			}

			if (listaFiltradaSituacao.isEmpty()) {
				throw new FiltroPesquisaProdutoNaoEncontradoException(MensagensExceptions.FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO);
			}
			else {

				for (Produto produto : listaFiltradaSituacao) {

					String nomeDoproduto = produto.getNomeProduto();

					if (nomeDoproduto.toLowerCase().contains(nomeProduto.toLowerCase())) {
						listaFiltradaNome.add(produto);
					}
				}

				if (listaFiltradaNome.isEmpty()) {
					throw new FiltroPesquisaProdutoNaoEncontradoException(MensagensExceptions.FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO);
				}
				else {
					return listaFiltradaNome;
				}
			}
		}
	}
}
