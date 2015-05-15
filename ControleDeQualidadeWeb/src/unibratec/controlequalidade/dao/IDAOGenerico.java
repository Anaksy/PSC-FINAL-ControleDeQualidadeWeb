package unibratec.controlequalidade.dao;

import java.util.List;

public interface IDAOGenerico<Entidade> {

	public void inserir(Entidade entidade);
	
	public void alterar(Entidade entidade);
	
	public void remover(Entidade entidade);
	
	public Entidade consultarPorId(Long id);
	
	public List<Entidade> consultarTodos();
	
	public List<Entidade> consultarTodos(Integer indiceInicial,	Integer quantidade);
	

}
