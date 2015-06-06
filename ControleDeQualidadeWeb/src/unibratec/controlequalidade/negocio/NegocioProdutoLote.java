package unibratec.controlequalidade.negocio;

import java.util.Calendar;

import unibratec.controlequalidade.entidades.EstadoLoteEnum;
import unibratec.controlequalidade.entidades.Lote;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;
import unibratec.controlequalidade.exceptions.dataDeValidadeMenorPermitidaCategoriaException;
import unibratec.controlequalidade.util.Funcoes;
import unibratec.controlequalidade.util.MensagensExceptions;

public class NegocioProdutoLote {

	private NegocioProduto negocioProduto;
	private NegocioLote negocioLote; 
	
	public NegocioProdutoLote() {
		
		this.negocioProduto = new NegocioProduto();
		this.negocioLote = new NegocioLote();
	}

	/**
	 * Metodo que concluir o fluxo de criação de um produto/lote
	 * 
	 * @param lote, produto
	 */
	public void criarProdutoLote(Lote lote, Produto produto) throws dataDeValidadeMenorPermitidaCategoriaException, LoteCadastradoException{
		
		Calendar dataAtual = Calendar.getInstance();
		
		//Validando data de validade inserida
		if (Funcoes.subtrairDiasDataCalendar(dataAtual, lote.getDataDeValidade()) <= produto.getCategoriaProduto().getNumeroDeDiasParaVencimento()) {
			
			throw new dataDeValidadeMenorPermitidaCategoriaException(MensagensExceptions.DATA_VALIDADE_MENOR_CATEGORIA_EXCEPTION);
		}
		
		produto.setLoteProduto(lote);
		lote.setEstadoLote(EstadoLoteEnum.FECHADO);
		this.negocioLote.inserirLote(lote);
		this.negocioProduto.inserirProduto(produto);
	}
}
