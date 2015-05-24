package unibratec.controlequalidade.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.negocio.Fachada;

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
		try {
			listaProduto = fachada.buscaProdutosPorSituacaoList(getEstadoProdutoEnum());
		} catch (ProdutoNaoEncontradoExcecption e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
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
	
	public void teste(){
		getEstadoProdutoEnum();
		System.out.println(getEstadoProdutoEnum().name());
		System.out.println(getEstadoProdutoEnum());
	}

}










