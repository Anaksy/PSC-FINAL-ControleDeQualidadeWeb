package unibratec.controlequalidade.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class MensagensGui {

	public static final String CATEGORIA_CADASTRADA_SUCESSO = "Categoria cadastrada com sucesso!";
	public static final String CATEGORIA_CADASTRADA_FALHA = "Categoria j� existente.";
	public static final String CATEGORIA_REMOVIDA_SUCESSO = "Categoria removida com sucesso!";
	public static final String CATEGORIA_REMOVIDA_FALHA = "Categoria n�o existe no sistema.";
	public static final String CATEGORIA_BD_FALHA = "Nenhuma categoria encontrada no Banco de Dados.";
	public static final String CATEGORIA_ATUALIZADA_SUCESSO = "Categoria atualizada com sucesso!";
	public static final String CATEGORIA_ATUALIZADA_JA_EXISTE = "Ja existe uma categoria com esse nome cadastrado.";
	public static final String CATEGORIA_ATUALIZADA_NAO_EXISTE = "Categoria pesquisada n�o existe.";
	public static final String CATEGORIA_SELECIONAR_FALHA = "Categoria pesquisada n�o existe.";
	public static final String CATEGORIA_PRODUTO_COM_CATEGORIA= "Categoria selecionada possui produtos cadastrados";
	public static final String CATEGORIA_VALIDACAO_DADOS_INCOMPLETOS = "Nome da categoria vazio ou n�mero de dias igual a 0.";
	public static final String LOTE_DATA_VALIDADE_MENOR_CATEGORIA_EXCEPTION = "Data de validade inserida menor que a permitida para essa categoria.";
	public static final String LOTE_NOME_JA_EXISTE_FALHA = "Nome de lote ja existe.";
	public static final String PRODUTO_LOTE_CRIADO_SUCESSO = "Produto e lote criados com sucesso.";
	public static final String PRODUTO_LOTE_VALIDACAO_NOME_PRODUTO = "Nome do produto n�o foi preenchido.";
	public static final String PRODUTO_LOTE_VALIDACAO_NOME_FABRICANTE = "Nome do fabricante n�o foi preenchido.";
	public static final String PRODUTO_LOTE_VALIDACAO_PRECO = "Pre�o do produto tem que ser maior que 0.";
	public static final String PRODUTO_LOTE_VALIDACAO_QUANTIDADE = "Quantidade do produto tem que ser maior que 0.";
	public static final String PRODUTO_LOTE_VALIDACAO_DATA_VALIDADE= "Data de validade tem que ser preenchida.";
	public static final String ROTINA_PRODUTO_SUCESSO = "Rotina de valida��o de produtos executada com sucesso!";
	public static final String ROTINA_PRODUTO_FALHA = "N�o existem produtos com o status EM_ESTOQUE no sistema.";
	public static final String LOGIN_USUARIO_NAO_CADASTRADO = "Usu�rio n�o cadastrado!";
	public static final String LOGIN_USUARIO_SENHA_INCORRETO = "Usu�rio ou senha inseridos est�o incorretos.";
	public static final String LOGIN_USUARIO_LOGADO_OUTRA_SESSAO = "Usu�rio logado em outra sess�o.";
	public static final String LOGIN_PREENCHER_CAMPOS = "Preencha todos os campos abaixo!";
	public static final String LOGIN_ACESSO_NEGADO = "Fa�a login para ter acesso ao sistema!";
	public static final String NENHUM_PRODUTO_SELECIONADO = "Nenhum produto selecionado.";
	public static final String DESCONTO_SITUACAO_FALHA = "Desconto permitido somente para produtos em situa��o PRESTES_A_VENCER.";
	public static final String DESCONTO_VALOR_FALHA = "Valor do desconto maior que o valor do produto!";
	public static final String DESCONTO_INSERIDO_SUCESSO = "Desconto inserido com sucesso!";
	public static final String PRODUTO_BD_FALHA = "Nenhum produto encontrado.";
	public static final String PRODUTO_PESQUISA_FALHA = "N�o existem produtos cadastrados com essa condi��o de pesquisa.";
	public static final String SUMARIO_INFO= "Informa��o:";
	public static final String SUMARIO_AVISO = "Aviso!";
	public static final String SUMARIO_ERRO = "Erro!";
	
	
	public static void infoMsg(String sumario, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, sumario, msg));
	}

	public static void avisoMsg(String sumario, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, sumario, msg));
	}

	public static void erroMsg(String sumario, String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, sumario, msg));
	}
}
