package unibratec.controlequalidade.dao;

import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;

public interface IDAOLote extends IDAOGenerico<Lote> {
	
	public boolean existeLote(Lote lote) throws LoteCadastradoException;

	public Lote buscaLote(String nomeLote);
	
	public long pesquisaNDiasPVenderCategoriaDeLote(Lote lote);

}
