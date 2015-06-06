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
	 * M�todo utilizado para inserir uma Categoria.
	 * 
	 * @param categoria utilizado como par�metro da query.
	 * 
	 * @throws CategoriaCadastradaException
	 */
	public void inserirCategoria(Categoria categoria) throws CategoriaCadastradaException;

	/**
	 * M�todo utilizado para listar todas as Categoria cadastradas
	 * no banco de dados.
	 * 
	 * @return List<Categoria>
	 * 
	 * @throws NenhumaCategoriaCadastradaException 
	 */
	public List<Categoria> listaTodasCategorias() throws NenhumaCategoriaCadastradaException;

	/**
	 * M�todo utilizado para alterar nome e n�mero de dias para vencer de uma Categoria.
	 * 
	 * @param categoria utilizado como par�metro na query.
	 * 
	 * @throws CategoriaNaoCadastradaException
	 * @throws CategoriaCadastradaException
	 */
	public void alteraCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, CategoriaCadastradaException;

	/**
	 * M�todo para remover uma Categoria.
	 * 
	 * @param categoria
	 * 
	 * @throws CategoriaNaoCadastradaException 
	 * @throws ProdutoComCategoriaException 
	 */
	public void removeCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, ProdutoComCategoriaException;

	/**
	 * M�todo utilizado para buscar uma Categoria pelo nome
	 * no banco de dados. 
	 * 
	 * @param nomeCategoria utilizado como par�mentro na query.
	 *
	 * @return Categoria
	 *
	 * @throws CategoriaNaoCadastradaException
	 */
	public Categoria buscaCategoriaPorNomeCategoria(String nomeCategoria) throws CategoriaNaoCadastradaException;

	/**
	 * M�todo utilizado para buscar uma Categoria por id no banco de dados. 
	 * 
	 * @param categoria utilizado como par�mentro na query.
	 * 
	 * @return Categoria
	 * 
	 * @throws CategoriaNaoCadastradaException
	 */
	public Categoria buscarCategoriaPorId(Categoria categoria) throws CategoriaNaoCadastradaException;
	
	public void criarProdutoLote(Produto produto, Lote lote) throws dataDeValidadeMenorPermitidaCategoriaException, LoteCadastradoException;

	public void executarRotinaProdutos() throws ProdutoNaoEncontradoExcecption;
	
	public Produto buscaProdutoPorId(long idProduto) throws ProdutoNaoCadastradoException;

	public List <Produto> filtrarPesquisaSituacao(EstadoProdutoEnum estadoProdutoEnum) throws ProdutoNaoEncontradoExcecption;

	public List <Produto> filtrarPesquisaNome(String nomeProduto) throws ProdutoNaoCadastradoException;

	public List<Produto> filtrarPesquisaFaixaDataValidade(Date dataInicial, Date dataFinal) throws ProdutoNaoCadastradoException, FiltroPesquisaProdutoNaoEncontradoException;

	public List<Produto> filtrarPesquisaFaixaDataNome(Date dataInicial, Date dataFinal, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException;

	public List<Produto> filtrarPesquisaNomeSituacao(String nomeProduto, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException;

	public List<Produto> filtrarPesquisaFaixaDataSituacao(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException;
	
	public List<Produto> filtrarPesquisaFaixaDataSituacaoNome(Date dataInicial, Date dataFinal, EstadoProdutoEnum estadoProdutoEnum, String nomeProduto) throws FiltroPesquisaProdutoNaoEncontradoException, ProdutoNaoCadastradoException;

	public void DescontoProduto(Produto produto, double desconto) throws ProdutoNaoCadastradoException, DescontoValorException, DescontoProdutoPrestesAVencerException;

	/**
	 * M�todo utilizado para autenticar um usu�rio 
	 * fazendo uma busca no banco de dados. 
	 * 
	 * @param nomeUsuario utilizado como par�mentro da query.
	 * @param senhaUsuario utilizado para compara��o com a senha
	 * 		  do usu�rio retornado da consulta no banco de dados.
	 * 
	 * @return Usuario
	 * 
	 * @throws UsuarioNaoCadastradoException
	 * @throws UsuarioSenhaIncorretaException
	 */
	public Usuario autenticaUsuario(String usuario, String senha) throws UsuarioNaoCadastradoException, UsuarioSenhaIncorretaException;
	
	/**
	 * M�todo utilizado para atualizar o status de um usu�rio no banco de dados.
	 * 
	 * @param usuarioUpdate utilizado como par�metro da query.
	 */
	public void atualizaStatusUsuario(Usuario usuario);
	
	/**
	 * M�todo utilizado para buscar um usu�rio no banco de dados. 
	 * 
	 * @param nomeUsuario utilizado como par�mentro da query.
	 * 
	 * @return Usuario
	 * 
	 * @throws UsuarioNaoCadastradoException
	 */
	public Usuario getUsarioByNome(String usuario) throws UsuarioNaoCadastradoException;
	
	/**
	 * M�todo utilizado para verificar se o usu�rio est� logado no sistema.
	 * 
	 * @param usuario utilizado como par�metro da query.
	 * 
	 * @return <code>false</code> caso n�o esteja logado no sistema.
	 * 		   <code>true</code> caso contr�rio.
	 */
	public boolean isUsuarioLogado(Usuario usuario);









}
