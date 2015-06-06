package unibratec.controlequalidade.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.exceptions.CategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.NenhumaCategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.ProdutoComCategoriaException;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.negocio.IFachada;
import unibratec.controlequalidade.util.MensagensGui;

@ManagedBean(name="manterCategoriaMB")
public class ManterCategoriaMB {

	private static final String HOME= "home";
	
	private Categoria categoria;
	private IFachada fachada;
	private List<Categoria> listaCategoria;
	
	public ManterCategoriaMB() {}
	
	@PostConstruct
	public void inicializar() {
		this.categoria = new Categoria();
		this.fachada = new Fachada();
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Inicializando um bean de Manter Categoria.");
	}

	public Categoria getCategoria() {
		
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		
		this.categoria = categoria;
	}
		
	/**
	 * Método utilizado para listar todas as Categoria cadastradas
	 * no banco de dados.
	 * 
	 * @return List<Categoria>
	 */
	public List<Categoria> getListaCategoria() {
		
		try {
		
			this.listaCategoria = fachada.listaTodasCategorias();
		
		} catch (NenhumaCategoriaCadastradaException e) {
			
			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}
		
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		
		this.listaCategoria = listaCategoria;
	}

	/**
	 * Método utilizado para cadastrar uma categoria
	 * a partir das informações fornecidas pelo usuário.
	 * 
	 * @return ""
	 */
	public String criarCategoria(){
		
		if (camposPreenchidos()) {	
			
			try {
			
				this.fachada.inserirCategoria(this.categoria);
				
				MensagensGui.infoMsg(MensagensGui.SUMARIO_INFO, MensagensGui.CATEGORIA_CADASTRADA_SUCESSO);
				
				Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Categoria cadastrada com Sucesso!!!");
				
				this.categoria = new Categoria();
				
				return "";
				
			} catch (CategoriaCadastradaException e) {
				
				MensagensGui.avisoMsg(MensagensGui.SUMARIO_AVISO, MensagensGui.CATEGORIA_CADASTRADA_FALHA);
				
				e.printStackTrace();
				
				System.out.println(e.getMessage());
			}
			
		} else {
			
			MensagensGui.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_VALIDACAO_DADOS_INCOMPLETOS);
			
			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Campos não preenchidos corretamente ou não preenchidos.");
		}
		
		return "";
	}
	
	
	/**
	 * Método utilizado para remover uma categoria
	 * selecionada no dataTable.
	 * 
	 * @return ""
	 */
	public String removerCategoria(){
		
		if (camposPreenchidos()) {
			
			try {

				this.fachada.removeCategoria(this.categoria);

				MensagensGui.infoMsg(MensagensGui.SUMARIO_INFO, MensagensGui.CATEGORIA_REMOVIDA_SUCESSO);

				Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Cateogira removida com Sucesso!!!");

				this.categoria = new Categoria();

			} catch (CategoriaNaoCadastradaException e) {

				MensagensGui.avisoMsg(MensagensGui.SUMARIO_AVISO, MensagensGui.CATEGORIA_REMOVIDA_FALHA);

				e.printStackTrace();

				System.out.println(e.getMessage());

			} catch (ProdutoComCategoriaException e) {

				MensagensGui.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_PRODUTO_COM_CATEGORIA);

				e.printStackTrace();

				System.out.println(e.getMessage());
			}
			
		} else {

			MensagensGui.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_VALIDACAO_DADOS_INCOMPLETOS);

			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Campos não preenchidos corretamente ou não preenchidos.");
		}

		return "";
	}
	
	
	/**
	 * Método utilizado para atualizar uma categoria 
	 * selecionada no dataTable.
	 * 
	 * @return ""
	 */
	public String atualizarCategoria(){
		
		if (camposPreenchidos()) {
			
			try {
				
				this.fachada.alteraCategoria(this.categoria);
				
				MensagensGui.infoMsg(MensagensGui.SUMARIO_INFO, MensagensGui.CATEGORIA_ATUALIZADA_SUCESSO);
				
				Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Categoria atualizada com Sucesso!!!");
				
				this.categoria = new Categoria();
			
			} catch (CategoriaCadastradaException e) {
				
				MensagensGui.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_ATUALIZADA_JA_EXISTE);
				
				e.printStackTrace();
				
				System.out.println(e.getMessage());
				
			} catch (CategoriaNaoCadastradaException e) {
				
				MensagensGui.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_ATUALIZADA_NAO_EXISTE);
				
				e.printStackTrace();
				
				System.out.println(e.getMessage());
			}
		
		} else {
			
			MensagensGui.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_VALIDACAO_DADOS_INCOMPLETOS);
			
			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Campos não preenchidos corretamente ou não preenchidos.");
			
		}
		
		return "";
	}
	
	
	/**
	 * Método utilizado para selecionar uma categoria do dataTable.
	 * 
	 * @param categoria
	 */
	public void selecionarCategoria(Categoria categoria){
		
		try {
		
			Categoria categoriaEncontrada = fachada.buscaCategoriaPorNomeCategoria(categoria.getNomeCategoria());
			
			this.categoria.setIdCategoria(categoriaEncontrada.getIdCategoria());
			
			this.categoria.setNomeCategoria(categoriaEncontrada.getNomeCategoria());
			
			this.categoria.setNumeroDeDiasParaVencimento(categoriaEncontrada.getNumeroDeDiasParaVencimento());
			
			Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Categoria selecionada com Sucesso!!!");
		
		} catch (CategoriaNaoCadastradaException e) {
			
			MensagensGui.avisoMsg(MensagensGui.SUMARIO_AVISO, MensagensGui.CATEGORIA_SELECIONAR_FALHA);
			
			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}
		
	} 


	/**
	 * Método utilizado para limpar a tela de manter caterogoria.
	 */
	public void limparTelaManterCategoria() {
		
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Limpando os dados da tela de manter categoria!!!");
		
		this.categoria = new Categoria();
	}
	
	
	/**
	 * Método utilizado para redirecionar para a tela inicial
	 * da aplicação. 
	 * 
	 * @return Navigation HOME
	 */
	public String voltarTelaInicial(){
		
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Redirecionando para a tela de \"menu de ações\"!!!");

		return HOME;
	}

	//private boolean camposPreenchidos(Categoria categoria){
	private boolean camposPreenchidos(){
		
		Logger.getLogger(LoginMB.class).log(Level.INFO,">>>>>>>>>>>>> Verificando se os campos foram preenchidos.");
		
		return (this.categoria.getNomeCategoria() != null 
				&& !this.categoria.getNomeCategoria().isEmpty() 
				&& this.categoria.getNumeroDeDiasParaVencimento() != 0); 	
	}
}
