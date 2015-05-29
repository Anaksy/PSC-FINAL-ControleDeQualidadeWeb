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


public interface IFachada {

	public void inserirCategoria(Categoria categoria) throws CategoriaCadastradaException;

	public List<Categoria> listaTodasCategorias() throws NenhumaCategoriaCadastradaException;

	public void alteraCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, CategoriaCadastradaException;

	public void removeCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, ProdutoComCategoriaException;

	public Categoria buscaCategoriaPorNomeCategoria(String nomeCategoria) throws CategoriaNaoCadastradaException;

	public void criarProdutoLote(Produto produto, Lote lote) throws dataDeValidadeMenorPermitidaCategoriaException, LoteCadastradoException;

	public Categoria buscarCategoriaPorId(Categoria categoria) throws CategoriaNaoCadastradaException;

	public void executarRotinaProdutos() throws ProdutoNaoEncontradoExcecption;
	
	public Produto buscaProdutoPorId(long idProduto) throws ProdutoNaoCadastradoException;

	public List <Produto> filtrarPesquisaSituacao(EstadoProdutoEnum estadoProdutoEnum) throws ProdutoNaoEncontradoExcecption;

	public List <Produto> filtrarPesquisaNome(String nomeProduto) throws ProdutoNaoCadastradoException;

	public List<Produto> filtrarPesquisaFaixaDataValidade(Date dataInicial, Date dataFinal) throws ProdutoNaoCadastradoException, FiltroPesquisaProdutoNaoEncontradoException;

	public List<Produto> filtrarPesquisaFaixaDataNome(Date dataInicial, Date dataFinal, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException;

	public List<Produto> filtrarPesquisaNomeSituacao(String nomeProduto, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException;

	public List<Produto> filtrarPesquisaFaixaDataSituacao(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException;
	
	public List<Produto> filtrarPesquisaFaixaDataSituacaoNome(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException;

	public Usuario buscaUsuario(String usuario, String senha) throws UsuarioNaoCadastradoException, UsuarioSenhaIncorretaException;
	
	public void DescontoProduto(Produto produto, double desconto) throws ProdutoNaoCadastradoException, DescontoValorException, DescontoProdutoPrestesAVencerException;











}
