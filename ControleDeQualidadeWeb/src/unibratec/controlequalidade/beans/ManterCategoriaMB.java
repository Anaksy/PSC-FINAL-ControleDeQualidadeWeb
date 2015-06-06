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
import unibratec.controlequalidade.util.Funcoes;
import unibratec.controlequalidade.util.MensagensGui;

@ManagedBean(name="ManterCategoriaMB")
public class ManterCategoriaMB {

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

	public Categoria getCategoria() {
		
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		
		this.categoria = categoria;
	}

	public void selecionarCategoria(Categoria categoria){
		
		try {
		
			Categoria categoriaEncontrada = fachada.buscaCategoriaPorNomeCategoria(categoria.getNomeCategoria());
			
			this.categoria.setIdCategoria(categoriaEncontrada.getIdCategoria());
			
			this.categoria.setNomeCategoria(categoriaEncontrada.getNomeCategoria());
			
			this.categoria.setNumeroDeDiasParaVencimento(categoriaEncontrada.getNumeroDeDiasParaVencimento());
		
		} catch (CategoriaNaoCadastradaException e) {
			
			Funcoes.avisoMsg(MensagensGui.SUMARIO_AVISO, MensagensGui.CATEGORIA_SELECIONAR_FALHA);
			
			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}
		
	} 

	public String atualizarCategoria(){
		
		if (validarCamposCategoria(this.categoria) == true) {
			
			try {
				
				this.fachada.alteraCategoria(this.categoria);
				
				Funcoes.infoMsg(MensagensGui.SUMARIO_INFO, MensagensGui.CATEGORIA_ATUALIZADA_SUCESSO);
				
				this.categoria = new Categoria();
			
			} catch (CategoriaCadastradaException e) {
				
				Funcoes.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_ATUALIZADA_JA_EXISTE);
				
				e.printStackTrace();
				
				System.out.println(e.getMessage());
				
			} catch (CategoriaNaoCadastradaException e) {
				
				Funcoes.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_ATUALIZADA_NAO_EXISTE);
				
				e.printStackTrace();
				
				System.out.println(e.getMessage());
			}
		}
		
		return "";
	}

	public String criarCategoria(){
		
		if (validarCamposCategoria(this.categoria) == true) {
			
			try {
			
				this.fachada.inserirCategoria(this.categoria);
				
				Funcoes.infoMsg(MensagensGui.SUMARIO_INFO, MensagensGui.CATEGORIA_CADASTRADA_SUCESSO);
				
				this.categoria = new Categoria();
				
			} catch (CategoriaCadastradaException e) {
				
				Funcoes.avisoMsg(MensagensGui.SUMARIO_AVISO, MensagensGui.CATEGORIA_CADASTRADA_FALHA);
				
				e.printStackTrace();
				
				System.out.println(e.getMessage());
			}
		}
		
		return "";
	}

	public String removerCategoria(){
		
		try {
			
			this.fachada.removeCategoria(this.categoria);
			
			Funcoes.infoMsg(MensagensGui.SUMARIO_INFO, MensagensGui.CATEGORIA_REMOVIDA_SUCESSO);
			
			this.categoria = new Categoria();
						
		} catch (CategoriaNaoCadastradaException e) {
			
			Funcoes.avisoMsg(MensagensGui.SUMARIO_AVISO, MensagensGui.CATEGORIA_REMOVIDA_FALHA);
			
			e.printStackTrace();
			
			System.out.println(e.getMessage());
		
		} catch (ProdutoComCategoriaException e) {
			
			Funcoes.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_PRODUTO_COM_CATEGORIA);
			
			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}
		
		return "";
	}

	public void limparTelaManterCategoria() {
		
		this.categoria = new Categoria();
	}
	
	public String voltarTelaInicial(){
		return "/menu-acoes.xhtml";
	}

	private boolean validarCamposCategoria(Categoria categoria){
		
		if ((categoria.getNomeCategoria().isEmpty() || (categoria.getNomeCategoria() == null) 
				|| (categoria.getNumeroDeDiasParaVencimento() == 0))) {
			
			Funcoes.erroMsg(MensagensGui.SUMARIO_ERRO, MensagensGui.CATEGORIA_VALIDACAO_DADOS_INCOMPLETOS);
			
			return false;
		
		}else {
		
			return true;
		}
	}
}
