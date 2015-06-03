package unibratec.controlequalidade.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
@NamedQueries({
	@NamedQuery(name="Usuario.findByName", query="Select u from Usuario u where u.nomeUsuario = :nomeUsuario"),
	@NamedQuery(name="Usuario.findByNameAndSenha", query="Select u from Usuario u where u.nomeUsuario = :nomeUsuario and u.senhaUsuario = :senhaUsuario")
})

public class Usuario {
	
	public static final String FIND_BY_NAME = "Usuario.findByName";
	public static final String FIND_BY_NAME_AND_SENHA = "Usuario.findByNameAndSenha";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
    private long idUsuario;
    
    @Column(name="NOME_USUARIO", nullable=false, unique=true)
    private String nomeUsuario;
    
    @Column(name="SENHA_USUARIO", nullable=false)
    private String senhaUsuario;
    
    @Enumerated(EnumType.STRING)
    @Column(name="PERFIL_USUARIO", nullable=false)
    private PerfilUsuarioEnum perfilUsuario;
    
    @Column(name="STATUS_SESSAO_USUARIO", nullable=false)
    private int statusUsuario;
    
    public Usuario() {}
    
    public Usuario(String nomeUsuario, String senhaUsuario, PerfilUsuarioEnum perfilUsuario) {
    	this.setNomeUsuario(nomeUsuario);
    	this.setSenhaUsuario(senhaUsuario);
    	this.setPerfilUsuario(perfilUsuario);
    	this.setStatusUsuario(StatusUsuarioEnum.INATIVO.getValue());
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

	public PerfilUsuarioEnum getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(PerfilUsuarioEnum perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public int getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(int statusUsuario) {
		this.statusUsuario = statusUsuario;
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
