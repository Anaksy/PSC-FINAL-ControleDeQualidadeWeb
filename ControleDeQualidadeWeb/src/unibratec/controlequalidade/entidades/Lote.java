package unibratec.controlequalidade.entidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import unibratec.controlequalidade.util.Funcoes;

@Table(name = "TB_LOTE")
@Entity

@NamedQuery(name="Lote.findByNome", query="Select l from Lote l where l.nomeLote like :nomeLote")
public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_LOTE")
	private long idLote;

	@Column(name = "NOME_LOTE", nullable = false)
	private String nomeLote;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_DE_VALIDADE_LOTE", nullable = true)
	private Calendar dataDeValidade;

	@Column(name = "QTD_PRODUTOS_LOTE", nullable = true)
	private int qtdProdutos;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_LOTE")
	private EstadoLoteEnum estadoLote;

	public Lote() {}
	
	public Lote(Calendar dataDeValidade, int qtdProdutos) {
		this.setNomeLote(Funcoes.geraNomeLote());
		this.setDataDeValidade(dataDeValidade);
		this.setQtdProdutos(qtdProdutos);
		this.setEstadoLote(EstadoLoteEnum.ABERTO);
	}

	public long getIdLote() {
		return idLote;
	}

	public void setIdLote(long idLote) {
		this.idLote = idLote;
	}

	public String getNomeLote() {
		return nomeLote;
	}

	public void setNomeLote(String nomeLote) {
		this.nomeLote = nomeLote;
	}

	public Calendar getDataDeValidade() {
		return dataDeValidade;
	}

	public void setDataDeValidade(Calendar dataDeValidade) {
		// Formatação da data fica a cargo da regra de negocio.
		this.dataDeValidade = dataDeValidade;
	}

	public int getQtdProdutos() {
		return qtdProdutos;
	}

	public void setQtdProdutos(int qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

	public EstadoLoteEnum getEstadoLote() {
		return estadoLote;
	}

	public void setEstadoLote(EstadoLoteEnum estadoLote) {
		this.estadoLote = estadoLote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nomeLote == null) ? 0 : nomeLote.hashCode());
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
		Lote other = (Lote) obj;
		if (nomeLote == null) {
			if (other.nomeLote != null)
				return false;
		} else if (!nomeLote.equals(other.nomeLote))
			return false;
		return true;
	}

	@Override
	public String toString() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = sdf.format(this.getDataDeValidade().getTime());

		return "Nome do Lote: " + (this.getNomeLote()) + "\nData de validade: "
		+ dataFormatada;
	}

}
