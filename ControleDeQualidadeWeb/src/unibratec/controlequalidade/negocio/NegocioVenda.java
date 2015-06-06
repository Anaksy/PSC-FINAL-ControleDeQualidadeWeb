package unibratec.controlequalidade.negocio;

import java.util.Calendar;
import java.util.List;

import unibratec.controlequalidade.dao.DAOFactory;
import unibratec.controlequalidade.dao.IDAOProduto;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.DescontoProdutoPrestesAVencerException;
import unibratec.controlequalidade.exceptions.DescontoValorException;
import unibratec.controlequalidade.exceptions.LoteCadastradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoCadastradoException;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.util.Funcoes;
import unibratec.controlequalidade.util.MensagensExceptions;

public class NegocioVenda {
	
	private IDAOProduto daoProduto;
	private NegocioProduto negocioProduto;
	
	public NegocioVenda() {

		this.daoProduto = DAOFactory.getProdutoDAO();
		this.negocioProduto = new NegocioProduto();
	}
		
	/**
	 * 
	 * Metodo que atribui desconto a um produto.
	 * 
	 * @param produto, desconto
	 * 
	 * @throws ProdutoNaoCadastradoException
	 * @throws DescontoValorException
	 * @throws DescontoProdutoPrestesAVencerException
	 */
	public void DescontoProduto(Produto produto, double desconto) throws ProdutoNaoCadastradoException, DescontoValorException, DescontoProdutoPrestesAVencerException {
		
		Produto produtoEncontrado = this.negocioProduto.buscaProdutoPorId(produto.getIdProduto());
		
		if (desconto > produtoEncontrado.getPrecoProduto()) {
			
			throw new DescontoValorException(MensagensExceptions.DESCONTO_VALOR_EXCEPTION);
		}
		
		else {
			
			if (produtoEncontrado.getEstadoProduto().equals(EstadoProdutoEnum.PRESTES_A_VENCER)) {
		
				produtoEncontrado.setPrecoProduto(produtoEncontrado.getPrecoProduto() - desconto);
				
				this.daoProduto.alterar(produtoEncontrado);
			}
			
			else {
				
				throw new DescontoProdutoPrestesAVencerException(MensagensExceptions.DESCONTO_PRODUTO_COM_ESTADO_INCORRETO);
			}

		}
	}

	/**
	 * Método que executa a rotina no sistema que muda a situação dos produtos de acordo com suas datas.
	 * 
	 * @throws ProdutoNaoEncontradoExcecption
	 */
	public void executarRotinaProdutos() throws ProdutoNaoEncontradoExcecption {

		List<Produto> produtosList = this.daoProduto.pesquisarProdutoPorEstadoList(EstadoProdutoEnum.EM_ESTOQUE);
			
		Calendar dataAtual = Calendar.getInstance();
				
		if (produtosList.isEmpty()) {
			
			throw new ProdutoNaoEncontradoExcecption(MensagensExceptions.ROTINA_PRODUTO_FALHA);
		}		
				
		for (Produto produto : produtosList) {
			
			if ((Funcoes.subtrairDiasDataCalendar(dataAtual, produto.getLoteProduto().getDataDeValidade()) <= produto.getCategoriaProduto().getNumeroDeDiasParaVencimento())) {
				
				produto.setEstadoProduto(EstadoProdutoEnum.PRESTES_A_VENCER);
				
				this.daoProduto.alterar(produto);
			}

			if ((Funcoes.subtrairDiasDataCalendar(dataAtual, produto.getLoteProduto().getDataDeValidade()) < 0)) {
				
				produto.setEstadoProduto(EstadoProdutoEnum.VENCIDO);
				
				this.daoProduto.alterar(produto);
			}
			
			if ((Funcoes.subtrairDiasDataCalendar(dataAtual, produto.getLoteProduto().getDataDeValidade()) < -5)) {
				
				produto.setEstadoProduto(EstadoProdutoEnum.INATIVO);
				
				this.daoProduto.alterar(produto);
			}
		}
	}
}




