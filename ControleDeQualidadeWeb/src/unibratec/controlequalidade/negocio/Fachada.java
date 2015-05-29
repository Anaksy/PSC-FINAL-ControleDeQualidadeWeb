package unibratec.controlequalidade.negocio;

import java.util.Date;
import java.util.List;

import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.entidades.Usuario;
import unibratec.controlequalidade.exceptions.CategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.DescontoProdutoPrestesAVencerException;
import unibratec.controlequalidade.exceptions.DescontoValorException;
import unibratec.controlequalidade.exceptions.FiltroPesquisaProdutoNaoEncontradoException;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;
import unibratec.controlequalidade.exceptions.NenhumaCategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.ProdutoComCategoriaException;
import unibratec.controlequalidade.exceptions.ProdutoNaoCadastradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.exceptions.UsuarioNaoCadastradoException;
import unibratec.controlequalidade.exceptions.UsuarioSenhaIncorretaException;
import unibratec.controlequalidade.exceptions.dataDeValidadeMenorPermitidaCategoriaException;

public class Fachada implements IFachada {

	private NegocioCategoria negocioCategoria;
	private NegocioVenda negocioVenda;
	private NegocioProdutoLote negocioProdutoLote;
	private NegocioUsuario negocioUsuario;
	private NegocioFiltroPesquisa negocioFiltroPesquisa;
	private NegocioProduto negocioProduto;

	public Fachada() {
		this.negocioFiltroPesquisa = new NegocioFiltroPesquisa();
		this.negocioCategoria = new NegocioCategoria();
		this.negocioVenda = new NegocioVenda();
		this.negocioProdutoLote = new NegocioProdutoLote();
		this.negocioUsuario = new NegocioUsuario();
		this.negocioProduto = new NegocioProduto();
	}

	@Override
	public void inserirCategoria(Categoria categoria) throws CategoriaCadastradaException {
		this.negocioCategoria.inserirCategoria(categoria);

	}

	@Override
	public List<Categoria> listaTodasCategorias() throws NenhumaCategoriaCadastradaException{
		return this.negocioCategoria.listaTodasCategorias();
	}

	@Override
	public void alteraCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, CategoriaCadastradaException {
		this.negocioCategoria.alteraCategoria(categoria);
	}

	@Override
	public void removeCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, ProdutoComCategoriaException {
		this.negocioCategoria.removeCategoria(categoria);
	}

	@Override
	public Categoria buscaCategoriaPorNomeCategoria(String nomeCategoria) throws CategoriaNaoCadastradaException {
		return this.negocioCategoria.buscaCategoriaPorNomeCategoria(nomeCategoria);
	}

	@Override
	public void criarProdutoLote(Produto produto, Lote lote) throws dataDeValidadeMenorPermitidaCategoriaException, LoteCadastradoException {
		this.negocioProdutoLote.criarProdutoLote(lote, produto);
	}

	@Override
	public Categoria buscarCategoriaPorId(Categoria categoria) throws CategoriaNaoCadastradaException {
		return this.negocioCategoria.buscaCategoriaPorId(categoria); 
	}

	@Override
	public void executarRotinaProdutos() throws ProdutoNaoEncontradoExcecption {
		this.negocioVenda.executarRotinaProdutos();
	}
	
	@Override
	public Produto buscaProdutoPorId(long idProduto) throws ProdutoNaoCadastradoException {
		return negocioProduto.buscaProdutoPorId(idProduto);
	}

	@Override
	public List<Produto> filtrarPesquisaSituacao(EstadoProdutoEnum estadoProdutoEnum) throws ProdutoNaoEncontradoExcecption {
		return negocioFiltroPesquisa.filtrarPesquisaSituacao(estadoProdutoEnum);
	}

	@Override
	public List<Produto> filtrarPesquisaNome(String nomeProduto)throws ProdutoNaoCadastradoException {
		return negocioFiltroPesquisa.filtrarPesquisaNome(nomeProduto);
	}

	@Override
	public List<Produto> filtrarPesquisaFaixaDataValidade(Date dataInicial, Date dataFinal) throws ProdutoNaoCadastradoException, FiltroPesquisaProdutoNaoEncontradoException {
		return negocioFiltroPesquisa.filtrarPesquisaFaixaDataValidade(dataInicial, dataFinal);
	}

	@Override
	public List<Produto> filtrarPesquisaNomeSituacao(String nomeProduto, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException {
		return negocioFiltroPesquisa.filtrarPesquisaNomeSituacao(nomeProduto, estadoProdutoEnum);
	}
	
	@Override
	public List<Produto> filtrarPesquisaFaixaDataNome(Date dataInicial, Date dataFinal, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException {
		return negocioFiltroPesquisa.filtrarPesquisaFaixaDataNome(dataInicial, dataFinal, nomeProduto);
	}

	@Override
	public List<Produto> filtrarPesquisaFaixaDataSituacao(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException {
		return negocioFiltroPesquisa.filtrarPesquisaFaixaDataSituacao(dataInicial, dataFinal, estadoProdutoEnum);
	}
	
	@Override
	public List<Produto> filtrarPesquisaFaixaDataSituacaoNome(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException {
		return negocioFiltroPesquisa.filtrarPesquisaFaixaDataSituacaoNome(dataInicial, dataFinal, estadoProdutoEnum, nomeProduto);
	}

	@Override
	public Usuario buscaUsuario(String nomeUsuario, String senhaUsuario) throws UsuarioNaoCadastradoException, UsuarioSenhaIncorretaException {
		return negocioUsuario.buscaUsuario(nomeUsuario, senhaUsuario);
	}

	@Override
	public void DescontoProduto(Produto produto, double desconto) throws ProdutoNaoCadastradoException, DescontoValorException, DescontoProdutoPrestesAVencerException {
		negocioVenda.DescontoProduto(produto, desconto);
	}





}
