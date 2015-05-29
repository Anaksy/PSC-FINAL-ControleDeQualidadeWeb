package unibratec.controlequalidade.beans;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.DescontoProdutoPrestesAVencerException;
import unibratec.controlequalidade.exceptions.DescontoValorException;
import unibratec.controlequalidade.exceptions.FiltroPesquisaProdutoNaoEncontradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoCadastradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.negocio.Fachada;
import unibratec.controlequalidade.negocio.IFachada;

@ViewScoped
@ManagedBean(name="PesquisarProdutosMB")
public class PesquisarProdutosMB {


	private IFachada fachada = new Fachada();
	private Produto produto;
	private double valorDesconto;
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
	
	public double getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
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

	public String pesquisar(){

		//Pesquisar por situação.
		if (isCheckboxSituacao() == true && isCheckboxNome() == false && isCheckboxFaixaDataValidade() == false) {
			try {
				setListaProduto(fachada.filtrarPesquisaSituacao(getEstadoProdutoEnum()));
			} catch (ProdutoNaoEncontradoExcecption e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				avisoMsg("Não existem produtos cadastrados com essa situação.");

			}
		}
		
		//Pesquisar por nome.
		if (isCheckboxNome() == true && isCheckboxSituacao() == false && isCheckboxFaixaDataValidade() == false) {

			try {
				setListaProduto(fachada.filtrarPesquisaNome(getNomeProduto()));
			} catch (ProdutoNaoCadastradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				avisoMsg("Não existem produtos cadastrados com esse nome.");
			}

		}

		//Pesquisar por faixa de data de validade.
		if (isCheckboxFaixaDataValidade() == true && isCheckboxSituacao() == false && isCheckboxNome() == false) {

			try {
				setListaProduto(fachada.filtrarPesquisaFaixaDataValidade(getDataInicial(), getDataFinal()));
			} catch (ProdutoNaoCadastradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				erroMsg("Nenhum produto encontrado no banco de dados.");
			} catch (FiltroPesquisaProdutoNaoEncontradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				avisoMsg("Não existe produto cadastrado com essa faixa de validade.");
			}
		}
		
		//Pesquisar por nome e situação.
		if (isCheckboxNome() == true && isCheckboxSituacao() == true && isCheckboxFaixaDataValidade() == false) {
			try {
				setListaProduto(fachada.filtrarPesquisaNomeSituacao(getNomeProduto(), getEstadoProdutoEnum()));
			} catch (FiltroPesquisaProdutoNaoEncontradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				avisoMsg("Não existe produto cadastrado com esse nome e situação.");
			}
		}
		
		//Pesquisar por nome e faixa de data de validade.
		if (isCheckboxNome() == true && isCheckboxFaixaDataValidade() == true && isCheckboxSituacao() == false) {
			try {
				setListaProduto(fachada.filtrarPesquisaFaixaDataNome(getDataInicial(), getDataFinal(), getNomeProduto()));
			} catch (FiltroPesquisaProdutoNaoEncontradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				avisoMsg("Não existe produto cadastrado com essa condição de pesquisa.");
			} catch (ProdutoNaoCadastradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				erroMsg("Nenhum produto encontrado no banco de dados.");
			}
		}
		
		//Pesquisar por situação e faixa de data de validade.
		if (isCheckboxSituacao() == true && isCheckboxFaixaDataValidade() == true && isCheckboxNome() == false) {
			try {
				setListaProduto(fachada.filtrarPesquisaFaixaDataSituacao(getDataInicial(), getDataFinal(), getEstadoProdutoEnum()));
			} catch (FiltroPesquisaProdutoNaoEncontradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				avisoMsg("Não existe produto cadastrado com essa condição de pesquisa.");
			} catch (ProdutoNaoCadastradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				erroMsg("Nenhum produto encontrado no banco de dados.");
			}
		}
		
		//Pesquisar por situação, nome e faixa de data de validade.
		if (isCheckboxSituacao() == true && isCheckboxFaixaDataValidade() == true && isCheckboxNome() == true) {
			try {
				setListaProduto(fachada.filtrarPesquisaFaixaDataSituacaoNome(getDataInicial(), getDataFinal(), getEstadoProdutoEnum(), getNomeProduto()));
			} catch (FiltroPesquisaProdutoNaoEncontradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				avisoMsg("Não existe produto cadastrado com essa condição de pesquisa.");
			} catch (ProdutoNaoCadastradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				erroMsg("Nenhum produto encontrado no banco de dados.");
			}
		}
		return null;
	}
	
	public void selecionarProduto(Produto p){
		try {
			Produto produtoEncontrado = fachada.buscaProdutoPorId(p.getIdProduto());
			produto = produtoEncontrado;

		} catch (ProdutoNaoCadastradoException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	} 
	
	public String desconto(){
		if (produto != null) {
			try {
				fachada.DescontoProduto(getProduto(), getValorDesconto());
				infoMsg("Desconto inserido com sucesso!");
				limparBean();
			} catch (ProdutoNaoCadastradoException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (DescontoValorException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				erroMsg("Valor do desconto maior que o valor do produto!");
			} catch (DescontoProdutoPrestesAVencerException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				erroMsg("Desconto permitido somente para produtos em situação PRESTES_A_VENCER");
			}
		}
		else {
			erroMsg("Nenhum produto selecionado.");
		}
		return null;
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
		return "/index.xhtml";
	}
	
	private void limparBean(){
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("PesquisarProdutosMB");
	}
}










