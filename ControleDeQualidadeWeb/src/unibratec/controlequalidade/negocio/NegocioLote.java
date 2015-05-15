package unibratec.controlequalidade.negocio;

import java.util.Calendar;
import unibratec.controlequalidade.dao.DAOFactory;
import unibratec.controlequalidade.dao.IDAOLote;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.exceptions.CategoriaNaoCadastradaException;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;
import unibratec.controlequalidade.exceptions.LoteNaoCadastradoException;
import unibratec.controlequalidade.exceptions.dataDeValidadeMenorPermitidaCategoriaException;
import unibratec.controlequalidade.util.Funcoes;
import unibratec.controlequalidade.util.MensagensExceptions;

public class NegocioLote {

	private IDAOLote daoLote;

	public NegocioLote() {

		this.daoLote = DAOFactory.getLoteDAO();

	}

	/**
	 * Método para inserir um lote
	 * 
	 * @param lote
	 * @throws LoteCadastradoException
	 */
	public void inserirLote(Lote lote) throws LoteCadastradoException {

		if (daoLote.existeLote(lote)) {
			throw new LoteCadastradoException(MensagensExceptions.LOTE_CADASTRADO_EXCEPTION);
		}
		else {
			lote.setNomeLote(Funcoes.geraNomeLote());
			this.daoLote.inserir(lote);
		}
	}

	/**
	 * Método para alterar a data de validade de um Lote.
	 * 
	 * @param Lote
	 * @param novaDataLote
	 * @throws CategoriaNaoCadastradaException 
	 * @throws LoteCadastradoException
	 * @throws LoteNaoCadastradoException 
	 * @throws dataDeValidadeMenorPermitidaCategoriaException 
	 */
	public void alteraLote(Lote lote, Calendar novaDataLote) throws CategoriaNaoCadastradaException, LoteCadastradoException, LoteNaoCadastradoException, dataDeValidadeMenorPermitidaCategoriaException{

		Lote PesquisaLote = daoLote.buscaLote(lote.getNomeLote());

		if (PesquisaLote == null) {
			throw new LoteNaoCadastradoException(MensagensExceptions.LOTE_NAO_CADASTRADA_EXCEPTION);
		}

		Long nDiasCatgLote = daoLote.pesquisaNDiasPVenderCategoriaDeLote(PesquisaLote);

		if (nDiasCatgLote == -1) {
			throw new LoteNaoCadastradoException(MensagensExceptions.LOTE_NAO_CADASTRADA_EXCEPTION);
		}

		//Validando data de validade inserida
		Calendar dataAtual = Calendar.getInstance();
		if (Funcoes.subtrairDiasDataCalendar(dataAtual, novaDataLote) <= nDiasCatgLote) {
			throw new dataDeValidadeMenorPermitidaCategoriaException(MensagensExceptions.DATA_VALIDADE_MENOR_CATEGORIA_EXCEPTION);
		}

		if (PesquisaLote != null) {

			PesquisaLote.setDataDeValidade(novaDataLote);

			daoLote.alterar(PesquisaLote);
			System.out.println("Lote alterado com sucesso!");

		}

	}

}







