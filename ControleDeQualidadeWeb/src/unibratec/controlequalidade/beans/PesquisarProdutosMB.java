package unibratec.controlequalidade.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.ProdutoNaoCadastradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.util.Datas;
import unibratec.controlequalidade.util.Funcoes;

@ManagedBean(name="PesquisarProdutosMB")
public class PesquisarProdutosMB {

	private Fachada fachada = new Fachada();
	private Produto produto;
	private boolean checkboxNome;
	private boolean checkboxSituacao;
	private boolean checkboxFaixaDataValidade;
	private List<Produto> listaProduto;
	private EstadoProdutoEnum estadoProdutoEnum;
	private List<EstadoProdutoEnum> estadoProdutoEnumList;
	private String nomeProduto;
	private Date dataInicial;
	private Date dataFinal;
	private boolean pesquisarPorEstado;
	private boolean pesquisarPorNome;
	private boolean pesquisarPorFaixaData;

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public boolean isCheckboxNome() {
		return checkboxNome;
	}
	public void setCheckboxNome(boolean checkboxNome) {
		this.checkboxNome = checkboxNome;
	}
	public boolean isCheckboxSituacao() {
		return checkboxSituacao;
	}
	public void setCheckboxSituacao(boolean checkboxSituacao) {
		this.checkboxSituacao = checkboxSituacao;
	}
	public boolean isCheckboxFaixaDataValidade() {
		return checkboxFaixaDataValidade;
	}
	public void setCheckboxFaixaDataValidade(boolean checkboxFaixaDataValidade) {
		this.checkboxFaixaDataValidade = checkboxFaixaDataValidade;
	}
	
	public List<Produto> getListaProduto() {
		//		try {
		//			listaProduto = fachada.buscaProdutosPorSituacaoList(getEstadoProdutoEnum());
		//		} catch (ProdutoNaoEncontradoExcecption e) {
		//			e.printStackTrace();
		//			System.out.println(e.getMessage());
		//		}
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public EstadoProdutoEnum getEstadoProdutoEnum() {
		return estadoProdutoEnum;
	}
	public void setEstadoProdutoEnum(EstadoProdutoEnum estadoProdutoEnum) {
		this.estadoProdutoEnum = estadoProdutoEnum;
	}

	public List<EstadoProdutoEnum> getEstadoProdutoEnumList() {
		List<EstadoProdutoEnum> listProdutos = Arrays.asList(EstadoProdutoEnum.values());
		return listProdutos;
	}
	public void setEstadoProdutoEnumList(List<EstadoProdutoEnum> estadoProdutoEnumList) {
		this.estadoProdutoEnumList = estadoProdutoEnumList;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	//Somente um teste.
	public void teste(){
		if (checkboxSituacao == true) {
			try {
				setListaProduto(fachada.buscaProdutosPorSituacaoList(getEstadoProdutoEnum()));
				//listaProduto = fachada.buscaProdutosPorSituacaoList(getEstadoProdutoEnum());
			} catch (ProdutoNaoEncontradoExcecption e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				avisoMsg("Não existem produtos cadastrados com essa situação.");

			}
		}
			if (checkboxNome == true) {
				
				try {
					//listaProduto = fachada.buscaProdutosPorNome(getNomeProduto());
					setListaProduto(fachada.buscaProdutosPorNome(getNomeProduto()));
				} catch (ProdutoNaoCadastradoException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					avisoMsg("Não existem produtos cadastrados com esse nome.");
				}
	
			}
			
			if (checkboxFaixaDataValidade == true) {

//				long resDataPesquisa = Funcoes.subtrairDiasDataCalendar(Datas.converterDateToCalendar(dataInicial), Datas.converterDateToCalendar(dataFinal));
//				Calendar dataAtual = Calendar.getInstance();
							
				try {
					List<Produto> listaProduto = fachada.listaTodosProdutos();
					List<Produto> novaListaProduto = new ArrayList<Produto>();
					for (Produto produto : listaProduto) {
						//long resDataProdutos =  Funcoes.subtrairDiasDataCalendar(dataAtual, produto.getLoteProduto().getDataDeValidade());
						//if (resDataProdutos < resDataPesquisa) {
						Date dataProdutoLote = produto.getLoteProduto().getDataDeValidade().getTime();
						if(Funcoes.procurarEntreDatas(dataProdutoLote, getDataInicial(), getDataFinal()) == true){
							novaListaProduto.add(produto);
						}
						setListaProduto(novaListaProduto);   
					}
			
				} catch (ProdutoNaoCadastradoException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					erroMsg("Nao tem nada no banco booooy!");
				}
				
		
				
				
			}
















	}












	public boolean isPesquisarPorEstado() {
		return pesquisarPorEstado;
	}
	public void setPesquisarPorEstado(boolean pesquisarPorEstado) {
		this.pesquisarPorEstado = pesquisarPorEstado;
	}
	public boolean isPesquisarPorNome() {
		return pesquisarPorNome;
	}
	public void setPesquisarPorNome(boolean pesquisarPorNome) {
		this.pesquisarPorNome = pesquisarPorNome;
	}
	public boolean isPesquisarPorFaixaData() {
		return pesquisarPorFaixaData;
	}
	public void setPesquisarPorFaixaData(boolean pesquisarPorFaixaData) {
		this.pesquisarPorFaixaData = pesquisarPorFaixaData;
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






}










