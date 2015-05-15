package unibratec.controlequalidade.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PRODUTO")
@NamedQueries({
	@NamedQuery(name = "Produto.findByEstado", query = "Select p from Produto p where p.estadoProduto like :estadoProduto"),
	@NamedQuery(name = "Produto.findByLoteId", query = "Select p from Produto p where p.loteProduto.idLote = :idLoteProduto"),
	@NamedQuery(name = "Produto.findProdutoByCategoriaId", query = "Select p from Produto p where p.categoriaProduto.idCategoria = :idCategoria")
})
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PRODUTO")
	private long idProduto;

	@Column(name = "NOME_PRODUTO", nullable = false)
	private String nomeProduto;

	@Column(name = "FABRICANTE_PRODUTO", nullable = false)
	private String fabricanteProduto;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoriaProduto;

	@Column(name = "PRECO_PRODUTO", nullable = false)
	private double precoProduto;

	@ManyToOne
	@JoinColumn(name = "ID_LOTE")
	private Lote loteProduto;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_PRODUTO")
	private EstadoProdutoEnum estadoProduto;

	public Produto(){}

	public Produto(String nome, String fabricante, Categoria categoria,
			double preco) {

		this.setNomeProduto(nome);
		this.setFabricanteProduto(fabricante);
		this.setCategoriaProduto(categoria);
		this.setPrecoProduto(preco);
		this.setEstadoProduto(EstadoProdutoEnum.EM_ESTOQUE);
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getFabricanteProduto() {
		return fabricanteProduto;
	}

	public void setFabricanteProduto(String fabricanteProduto) {
		this.fabricanteProduto = fabricanteProduto;
	}

	public Categoria getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(Categoria categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public Lote getLoteProduto() {
		return loteProduto;
	}

	public void setLoteProduto(Lote loteProduto) {
		this.loteProduto = loteProduto;
	}

	public EstadoProdutoEnum getEstadoProduto() {
		return estadoProduto;
	}

	public void setEstadoProduto(EstadoProdutoEnum estadoProduto) {
		this.estadoProduto = estadoProduto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((fabricanteProduto == null) ? 0 : fabricanteProduto
						.hashCode());
		result = prime * result
				+ ((nomeProduto == null) ? 0 : nomeProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (fabricanteProduto == null) {
			if (other.fabricanteProduto != null)
				return false;
		} else if (!fabricanteProduto.equals(other.fabricanteProduto))
			return false;
		if (nomeProduto == null) {
			if (other.nomeProduto != null)
				return false;
		} else if (!nomeProduto.equals(other.nomeProduto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto: " + (this.getNomeProduto()) + 
				"\nMarca: "	+ (this.getFabricanteProduto()) + 
				"\n" + (this.getCategoriaProduto()) + 
				"\nPreço: " + (this.getPrecoProduto()) + 
				"\n" + (this.getLoteProduto()) + 
				"\nSituação: "	+ (this.getEstadoProduto());
	}
}
