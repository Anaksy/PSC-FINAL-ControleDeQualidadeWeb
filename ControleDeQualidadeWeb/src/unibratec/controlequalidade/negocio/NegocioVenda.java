package unibratec.controlequalidade.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import unibratec.controlequalidade.dao.DAOProduto;
import unibratec.controlequalidade.entidades.EstadoProdutoEnum;
import unibratec.controlequalidade.entidades.Produto;
import unibratec.controlequalidade.exceptions.ProdutoNaoEncontradoExcecption;
import unibratec.controlequalidade.exceptions.ProdutoNaoPrestesAVencerException;
import unibratec.controlequalidade.util.Funcoes;

public class NegocioVenda {

	private DAOProduto daoProduto;

	public NegocioVenda(EntityManager em) {
		this.daoProduto = new DAOProduto(em);
	}
	
	// Método que retorna uma lista com o produtos prestes a vencer
	public List<Produto> retornaListaProdutosPrestesAVencer() throws ProdutoNaoEncontradoExcecption {

		List<Produto> produtosList = this.daoProduto.pesquisarProdutoPorEstadoList(EstadoProdutoEnum.EM_ESTOQUE);
		
		List<Produto> produtosPrestesAVencerList = new ArrayList<Produto>();
	
		Calendar dataAtual = Calendar.getInstance();
				
		for (Produto p : produtosList) {

			if ((Funcoes.subtrairDiasDataCalendar(dataAtual, p.getLoteProduto().getDataDeValidade()) <= p.getCategoriaProduto().getNumeroDeDiasParaVencimento())) {
				
				produtosPrestesAVencerList.add(p);
			}
		}
		
		return produtosPrestesAVencerList;
	}
	
	// Método que retorna uma produto prestes a vencer
	public Produto retornaProdutoPrestesAVencer(Produto produto) throws ProdutoNaoPrestesAVencerException, ProdutoNaoEncontradoExcecption {
		
		Produto p = this.daoProduto.pesquisarProdutoPorEstado(produto);
				
		Calendar dataAtual = Calendar.getInstance();
				
		if ((Funcoes.subtrairDiasDataCalendar(dataAtual, p.getLoteProduto().getDataDeValidade()) <= p.getCategoriaProduto().getNumeroDeDiasParaVencimento())) {
				
				return p;
				
		} else {
			
			throw new ProdutoNaoPrestesAVencerException("O produto não está prestes a vencer.");
		}

	}

	// Método que dar desconto no produto
	public void darDescontoProdutoPrestesAVencer(Produto produto, double desconto) {
		
		produto.setPrecoProduto(produto.getPrecoProduto() - desconto);
		
		daoProduto.alterar(produto);
	}

}
