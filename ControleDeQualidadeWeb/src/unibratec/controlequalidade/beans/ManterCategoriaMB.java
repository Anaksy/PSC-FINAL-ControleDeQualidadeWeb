package unibratec.controlequalidade.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.exceptions.CategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.NenhumaCategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.ProdutoComCategoriaException;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.negocio.IFachada;
import unibratec.controlequalidade.util.MensagensGui;

@ManagedBean(name="ManterCategoriaMB")
public class ManterCategoriaMB {

	private Categoria categoria = new Categoria();
	private IFachada fachada = new Fachada();
	private List<Categoria> listaCategoria;

	public List<Categoria> getListaCategoria() {
		try {
			listaCategoria = fachada.listaTodasCategorias();
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

	public void selecionarCategoria(Categoria c){
		try {
			Categoria CategoriaEncontrada = fachada.buscaCategoriaPorNomeCategoria(c.getNomeCategoria());
			categoria.setIdCategoria(CategoriaEncontrada.getIdCategoria());
			categoria.setNomeCategoria(CategoriaEncontrada.getNomeCategoria());
			categoria.setNumeroDeDiasParaVencimento(CategoriaEncontrada.getNumeroDeDiasParaVencimento());
		} catch (CategoriaNaoCadastradaException e) {
			avisoMsg(MensagensGui.CATEGORIA_SELECIONAR_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	} 

	public String atualizarCategoria(){
		if (validarCamposCategoria(categoria) == true) {
			try {
				fachada.alteraCategoria(categoria);
				infoMsg(MensagensGui.CATEGORIA_ATUALIZADA_SUCESSO);
				categoria = new Categoria();
			} catch (CategoriaCadastradaException e) {
				erroMsg(MensagensGui.CATEGORIA_ATUALIZADA_JA_EXISTE);
				e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (CategoriaNaoCadastradaException e) {
				erroMsg(MensagensGui.CATEGORIA_ATUALIZADA_NAO_EXISTE);
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return "";
	}

	public String criarCategoria(){
		limparIdCategoria();// Talvez deveria botar isso no metodo do DAO ?
		if (validarCamposCategoria(categoria) == true) {
			try {
				fachada.inserirCategoria(categoria);
				infoMsg(MensagensGui.CATEGORIA_CADASTRADA_SUCESSO);
				categoria = new Categoria();
			} catch (CategoriaCadastradaException e) {
				avisoMsg(MensagensGui.CATEGORIA_CADASTRADA_FALHA);
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return "";
	}

	public String removerCategoria(){
		try {
			fachada.removeCategoria(categoria);
			infoMsg(MensagensGui.CATEGORIA_REMOVIDA_SUCESSO);
			categoria = new Categoria();  
		} catch (CategoriaNaoCadastradaException e) {
			avisoMsg(MensagensGui.CATEGORIA_REMOVIDA_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ProdutoComCategoriaException e) {
			erroMsg(MensagensGui.CATEGORIA_PRODUTO_COM_CATEGORIA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "";
	}

	private void infoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação:", msg));
	}

	private void avisoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", msg));
	}

	private void erroMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
	}

	public String voltarTelaInicial(){
		return "/menu-acoes.xhtml";
	}

	private boolean validarCamposCategoria(Categoria categoria){
		if ((categoria.getNomeCategoria().isEmpty() || (categoria.getNomeCategoria() == null) 
				|| (categoria.getNumeroDeDiasParaVencimento() == 0))) {
			erroMsg(MensagensGui.CATEGORIA_VALIDACAO_DADOS_INCOMPLETOS);
			return false;
		}
		else {
			return true;
		}
	}

	private void limparIdCategoria(){
		categoria.setIdCategoria(0);
	}
}
