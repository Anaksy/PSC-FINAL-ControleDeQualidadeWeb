package unibratec.controlequalidade.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CATEGORIA")
@NamedQueries({
	@NamedQuery(name="Categoria.findByNome", query="Select c from Categoria c where c.nomeCategoria like :nomeCategoria"),
	@NamedQuery(name="Categoria.findByNomeDiferenteId", query="Select c from Categoria c where c.idCategoria <> :idCategoria and c.nomeCategoria like :nomeCategoria")
})

public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CATEGORIA")
	private long idCategoria;
	
	@Column(name = "NOME_CATEGORIA", nullable = false)
	private String nomeCategoria;
	
	@Column(name = "N_DIAS_PARA_VENCIMENTO_CATEGORIA", nullable = false)
	private long numeroDeDiasParaVencimento;

	public Categoria() {}
	
	public Categoria(String nomeCategoria, int numeroDeDiasParaVencimento) {
		this.setNomeCategoria(nomeCategoria);
		this.setNumeroDeDiasParaVencimento(numeroDeDiasParaVencimento);
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public long getNumeroDeDiasParaVencimento() {
		return numeroDeDiasParaVencimento;
	}
	
	public void setNumeroDeDiasParaVencimento(long numeroDeDiasParaVencimento) {
		this.numeroDeDiasParaVencimento = numeroDeDiasParaVencimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nomeCategoria == null) ? 0 : nomeCategoria.hashCode());
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
		Categoria other = (Categoria) obj;
		if (nomeCategoria == null) {
			if (other.nomeCategoria != null)
				return false;
		} else if (!nomeCategoria.equals(other.nomeCategoria))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria: " + (this.getNomeCategoria())
				+ "\nNúmero de dias para o vencimento: "
				+ (this.getNumeroDeDiasParaVencimento());
	}

}
