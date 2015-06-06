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

	/**
	 * Método utilizado para inserir uma Categoria.
	 * 
	 * @param categoria utilizado como parâmetro da query.
	 * 
	 * @throws CategoriaCadastradaException
	 */
	public void inserirCategoria(Categoria categoria) throws CategoriaCadastradaException;

	/**
	 * Método utilizado para listar todas as Categoria cadastradas
	 * no banco de dados.
	 * 
	 * @return List<Categoria>
	 * 
	 * @throws NenhumaCategoriaCadastradaException 
	 */
	public List<Categoria> listaTodasCategorias() throws NenhumaCategoriaCadastradaException;

	/**
	 * Método utilizado para alterar nome e número de dias para vencer de uma Categoria.
	 * 
	 * @param categoria utilizado como parâmetro na query.
	 * 
	 * @throws CategoriaNaoCadastradaException
	 * @throws CategoriaCadastradaException
	 */
	public void alteraCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, CategoriaCadastradaException;

	/**
	 * Método para remover uma Categoria.
	 * 
	 * @param categoria
	 * 
	 * @throws CategoriaNaoCadastradaException 
	 * @throws ProdutoComCategoriaException 
	 */
	public void removeCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, ProdutoComCategoriaException;

	/**
	 * Método utilizado para buscar uma Categoria pelo nome
	 * no banco de dados. 
	 * 
	 * @param nomeCategoria utilizado como parâmentro na query.
	 *
	 * @return Categoria
	 *
	 * @throws CategoriaNaoCadastradaException
	 */
	public Categoria buscaCategoriaPorNomeCategoria(String nomeCategoria) throws CategoriaNaoCadastradaException;

	/**
	 * Método utilizado para buscar uma Categoria por id no banco de dados. 
	 * 
	 * @param categoria utilizado como parâmentro na query.
	 * 
	 * @return Categoria
	 * 
	 * @throws CategoriaNaoCadastradaException
	 */
	public Categoria buscarCategoriaPorId(Categoria categoria) throws CategoriaNaoCadastradaException;
	
	/**
	 * Metodo utilizado que cria um produto, lote e faz a associação dos mesmos.
	 * 
	 * @param produto
	 * @param lote
	 * @throws dataDeValidadeMenorPermitidaCategoriaException
	 * @throws LoteCadastradoException
	 */
	public void criarProdutoLote(Produto produto, Lote lote) throws dataDeValidadeMenorPermitidaCategoriaException, LoteCadastradoException;

	/**
	 * Metodo que executa a rotina de verificação das datas dos produtos, e dessa forma mudando seus estados.
	 * 
	 * @throws ProdutoNaoEncontradoExcecption
	 */
	public void executarRotinaProdutos() throws ProdutoNaoEncontradoExcecption;
	
	/**
	 * Metodo que busca um produto por ID.
	 * 
	 * @param idProduto
	 * @return
	 * @throws ProdutoNaoCadastradoException
	 */
	public Produto buscaProdutoPorId(long idProduto) throws ProdutoNaoCadastradoException;

	/**
	 * Metodo de filtro de pesquisa de produtos por estado.
	 * 
	 * @param estadoProdutoEnum
	 * @return
	 * @throws ProdutoNaoEncontradoExcecption
	 */
	public List <Produto> filtrarPesquisaSituacao(EstadoProdutoEnum estadoProdutoEnum) throws ProdutoNaoEncontradoExcecption;

	/**
	 * Metodo de filtro de pesquisa de produtos por nome.
	 * 
	 * @param nomeProduto
	 * @return
	 * @throws ProdutoNaoCadastradoException
	 */
	public List <Produto> filtrarPesquisaNome(String nomeProduto) throws ProdutoNaoCadastradoException;

	/**
	 * Metodo de filtro de pesquisa de produtos por faixa de data de validade.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ProdutoNaoCadastradoException
	 * @throws FiltroPesquisaProdutoNaoEncontradoException
	 */
	public List<Produto> filtrarPesquisaFaixaDataValidade(Date dataInicial, Date dataFinal) throws ProdutoNaoCadastradoException, FiltroPesquisaProdutoNaoEncontradoException;

	/**
	 * Metodo de filtro de pesquisa de produtos por faixa de data de validade e nome.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @param nomeProduto
	 * @return
	 * @throws FiltroPesquisaProdutoNaoEncontradoException
	 * @throws ProdutoNaoCadastradoException
	 */
	public List<Produto> filtrarPesquisaFaixaDataNome(Date dataInicial, Date dataFinal, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException;

	/**
	 * Metodo de filtro de pesquisa de produtos por nome e estado.
	 * 
	 * @param nomeProduto
	 * @param estadoProdutoEnum
	 * @return
	 * @throws FiltroPesquisaProdutoNaoEncontradoException
	 */
	public List<Produto> filtrarPesquisaNomeSituacao(String nomeProduto, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException;

	/**
	 * Metodo de filtro de pesquisa de produtos por faixa de data de validade e estado do produto.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @param estadoProdutoEnum
	 * @return
	 * @throws FiltroPesquisaProdutoNaoEncontradoException
	 * @throws ProdutoNaoCadastradoException
	 */
	public List<Produto> filtrarPesquisaFaixaDataSituacao(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException;
	
	/**
	 * Metodo de filtro de pesquisa de produtos por faixa de data de validade, estado do produto e nome.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @param estadoProdutoEnum
	 * @param nomeProduto
	 * @return
	 * @throws FiltroPesquisaProdutoNaoEncontradoException
	 * @throws ProdutoNaoCadastradoException
	 */
	public List<Produto> filtrarPesquisaFaixaDataSituacaoNome(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException;

	/**
	 * Metodo que atribui desconto a um produto.
	 * 
	 * @param produto
	 * @param desconto
	 * @throws ProdutoNaoCadastradoException
	 * @throws DescontoValorException
	 * @throws DescontoProdutoPrestesAVencerException
	 */
	public void DescontoProduto(Produto produto, double desconto) throws ProdutoNaoCadastradoException, DescontoValorException, DescontoProdutoPrestesAVencerException;

	/**
	 * Método utilizado para autenticar um usuário 
	 * fazendo uma busca no banco de dados. 
	 * 
	 * @param nomeUsuario utilizado como parâmentro da query.
	 * @param senhaUsuario utilizado para comparação com a senha
	 * 		  do usuário retornado da consulta no banco de dados.
	 * 
	 * @return Usuario
	 * 
	 * @throws UsuarioNaoCadastradoException
	 * @throws UsuarioSenhaIncorretaException
	 */
	public Usuario autenticaUsuario(String usuario, String senha) throws UsuarioNaoCadastradoException, UsuarioSenhaIncorretaException;
	
	/**
	 * Método utilizado para atualizar o status de um usuário no banco de dados.
	 * 
	 * @param usuarioUpdate utilizado como parâmetro da query.
	 */
	public void atualizaStatusUsuario(Usuario usuario);
	
	/**
	 * Método utilizado para buscar um usuário no banco de dados. 
	 * 
	 * @param nomeUsuario utilizado como parâmentro da query.
	 * 
	 * @return Usuario
	 * 
	 * @throws UsuarioNaoCadastradoException
	 */
	public Usuario getUsarioByNome(String usuario) throws UsuarioNaoCadastradoException;
	
	/**
	 * Método utilizado para verificar se o usuário está logado no sistema.
	 * 
	 * @param usuario utilizado como parâmetro da query.
	 * 
	 * @return <code>false</code> caso não esteja logado no sistema.
	 * 		   <code>true</code> caso contrário.
	 */
	public boolean isUsuarioLogado(Usuario usuario);









}
