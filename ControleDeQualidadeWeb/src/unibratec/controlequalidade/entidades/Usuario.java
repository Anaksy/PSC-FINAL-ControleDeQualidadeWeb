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
@Table(name = "TB_USUARIO")
@NamedQueries({
	@NamedQuery(name="Usuario.findUsuarioByName", query="Select u from Usuario u where u.nomeUsuario = :nomeUsuario"),
	@NamedQuery(name="Usuario.findUsuario", query="Select u from Usuario u where u.nomeUsuario = :nomeUsuario and u.senhaUsuario = :senhaUsuario")
})

public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
    private long idUsuario;
    
    @Column(name="NOME_USUARIO", nullable=false, unique=true)
    private String nomeUsuario;
    
    @Column(name="SENHA_USUARIO", nullable=false)
    private String senhaUsuario;
    
    public Usuario() {}
    
    public Usuario(String nomeUsuario, String senhaUsuario) {
    	this.setNomeUsuario(nomeUsuario);
    	this.setSenhaUsuario(senhaUsuario);
    }

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		return true;
	}
    
    @Override
    public String toString() {
    	return "Usuário: "+ this.getNomeUsuario() + 
    			"\nSenha: " + this.getSenhaUsuario();
    }
}
