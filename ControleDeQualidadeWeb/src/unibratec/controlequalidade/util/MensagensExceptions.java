package unibratec.controlequalidade.util;

public abstract class MensagensExceptions {

	public static final String CATEGORIA_CADASTRADA_EXCEPTION = "Categoria j� cadastrada na base.";
	public static final String CATEGORIA_NAO_CADASTRADA_EXCEPTION = "Categoria n�o cadastrada na base.";
	public static final String LOTE_CADASTRADO_EXCEPTION = "Lote j� cadastrado na base.";
	public static final String LOTE_NAO_CADASTRADO_EXCEPTION = "Lote n�o cadastrado na base.";
	public static final String DATA_VALIDADE_MENOR_CATEGORIA_EXCEPTION = "Data de validade inserida menor que a permitida para essa categoria.";
	public static final String PRODUTO_COM_CATEGORIA_EXCEPTION = "Categoria selecionada possui produtos cadastrados";
	public static final String PRODUTO_NAO_CADASTRADO_EXCEPTION = "Produto n�o cadastrado na base.";
	public static final String NENHUMA_CATEGORIA_CADASTRADA_EXCEPTION = "Nenhuma categoria cadastrada at� o momento.";
	public static final String NENHUM_PRODUTO_CADASTRADO_NOME_EXCEPTION = "Nenhum produto cadastrado com esse nome at� o momento.";
	public static final String NENHUM_PRODUTO_CADASTRADO_EXCEPTION = "Nenhum produto cadastrado at� o momento.";
	public static final String NENHUM_PRODUTO_CADASTRADO_SITUACAO_EXCEPTION = "Nenhum produto cadastrado com esse estado.";
	public static final String ROTINA_PRODUTO_FALHA = "N�o existem produtos com o status EM_ESTOQUE no sistema.";
	public static final String USUARIO_NAO_CADASTRADO_EXCEPTION = "Usu�rio n�o cadastrado na base.";
	public static final String USUARIO_SENHA_ERRADA_EXCEPTION = "Senha incorreta.";
	public static final String FILTRO_PESQUISA_PRODUTO_NAO_ENCONTRADO = "Produto n�o encontrado com esse filtro de pesquisa.";
	public static final String DESCONTO_VALOR_EXCEPTION = "Valor de desconto menor que o valor do produto.";
	public static final String DESCONTO_PRODUTO_COM_ESTADO_INCORRETO = "Desconto somente � permitidi em produdos com a situa��o PRESTES_A_VENCER";
}
