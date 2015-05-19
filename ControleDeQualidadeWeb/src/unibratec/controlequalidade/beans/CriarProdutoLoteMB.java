package unibratec.controlequalidade.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import unibratec.controlequalidade.entidades.Categoria;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;
import unibratec.controlequalidade.exceptions.NenhumaCategoriaCadastradaException;
import unibratec.controlequalidade.exceptions.dataDeValidadeMenorPermitidaCategoriaException;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.negocio.NegocioProdutoLote;
import unibratec.controlequalidade.util.Datas;
import unibratec.controlequalidade.util.MensagensGui;

@ManagedBean(name="CriarProdutoLoteMB")
public class CriarProdutoLoteMB {

	private Fachada fachada = new Fachada();
	private Categoria categoria = new Categoria();
	private Produto produto = new Produto();
	private Lote lote = new Lote();
	private Date dataValidade;
	private List<Categoria> listaCategoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public String criarProdutoLote(){
		try {
			Categoria categoriaSelecionada = fachada.buscarCategoriaPorId(categoria);
			produto.setCategoriaProduto(categoriaSelecionada);
			lote.setDataDeValidade(Datas.converterDateToCalendar(dataValidade));
			fachada.criarProdutoLote(produto, lote);
			infoMsg(MensagensGui.PRODUTO_LOTE_CRIADO_SUCESSO);
		} 
		catch(LoteCadastradoException e){
			erroMsg(MensagensGui.LOTE_NOME_JA_EXISTE_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch (dataDeValidadeMenorPermitidaCategoriaException e) {
			erroMsg(MensagensGui.LOTE_DATA_VALIDADE_MENOR_CATEGORIA_EXCEPTION);
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (CategoriaNaoCadastradaException e) {
			erroMsg(MensagensGui.CATEGORIA_SELECIONAR_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Categoria> getListaCategoria() {
		try {
			listaCategoria = fachada.listaTodasCategorias();
		} catch (NenhumaCategoriaCadastradaException e) {
			erroMsg(MensagensGui.CATEGORIA_BD_FALHA);
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return listaCategoria;
	}
	
	public void infoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", msg));
	}

	public void avisoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", msg));
	}

	public void erroMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", msg));
	}

}
