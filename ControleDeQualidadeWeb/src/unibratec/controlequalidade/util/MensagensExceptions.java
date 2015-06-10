package unibratec.controlequalidade.util;

public abstract class MensagensExceptions {

	public static final String CATEGORIA_CADASTRADA_EXCEPTION = "Categoria j� cadastrada na base.";
	public static final String CATEGORIA_NAO_CADASTRADA_EXCEPTION = "Categoria n�o cadastrada na base.";
	public static final String LOTE_CADASTRADO_EXCEPTION = "Lote j� cadastrado na base.";
	public static final String LOTE_NAO_CADASTRADA_EXCEPTION = "Lote n�o cadastrado na base.";
	public static final String DATA_VALIDADE_MENOR_CATEGORIA_EXCEPTION = "Data de validade inserida menor que a permitida para essa categoria.";
	public static final String PRODUTO_COM_CATEGORIA_EXCEPTION = "Categoria selecionar possui produtos cadastrados";
	public static final String PRODUTO_NAO_CADASTRADO_EXCEPTION = "Produto n�o cadastrado na base.";
	public static final String NENHUMA_CATEGORIA_CADASTRADA_EXCEPTION = "Nenhuma categoria cadastrada at� o momento.";
	public static final String NENHUM_PRODUTO_CADASTRADO_NOME_EXCEPTION = "Nenhum produto cadastrado com esse nome at� o momento.";
	public static final String NENHUM_PRODUTO_CADASTRADO_EXCEPTION = "Nenhum produto cadastrado at� o momento.";
	
	public static final String ROTINA_PRODUTO_FALHA = "N�o existem produtos com o status EM_ESTOQUE no sistema.";
}
