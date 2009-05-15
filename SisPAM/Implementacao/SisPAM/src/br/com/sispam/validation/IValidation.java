package br.com.sispam.validation;

/*
 * Interface que contém com variáveis para facilitar a procura 
 * dos tamanhos e pesos dos diversos documentos.
 */
public interface IValidation
{
	public static final String VALIDATOR_CODIGO_RECEITA = "codigoreceita.CodigoReceitaValidator";
	public static final String VALIDATOR_CAMPO_REF_UM = "camporeferencia.CampoReferenciaUmDVValidator";
	public static final String VALIDATOR_CAMPO_REF_DOIS = "camporeferencia.CampoReferenciaDoisDVValidator";
	public static final String VALIDATOR_CNPJ = "cnpjcpf.CNPJValidator";
	public static final String VALIDATOR_CPF = "cnpjcpf.CPFValidator";
	public static final String VALIDATOR_CONTA_CORRENTE = "contacorrente.ContaCorrenteValidator";
	public static final String VALIDATOR_RENAVAM = "renavam.RenavamValidator";
	public static final String VALIDATOR_IPTU = "iptu.IPTUValidator";
	public static final String VALIDATOR_CEI = "cei.CEIValidator";
	public static final String VALIDATOR_CAMPO_IDENTIFICADOR = "campoidentificador.CampoIdentificadorValidator";
	public static final String VALIDATOR_LACRE_CONECTIVIDADE = "lacreconectividade.LacreConectividadeValidator";
	public static final String VALIDATOR_AGENCIA = "agencia.AgenciaValidator";
	public static final String VALIDATOR_CONTRATO = "contrato.ContratoValidator";
	public static final String VALIDATOR_CONSTRUCARD_FGTS = "construcard.ConstrucardFGTSValidator";
	public static final String VALIDATOR_CONSTRUCARD = "construcard.ConstrucardValidator";
	public static final String VALIDATOR_INSC_ESTADUAL = "gare.InscricaoEstadual";
	public static final String VALIDATOR_CODIGO_RECEITA_GARE = "gare.CodigoReceitaGare";
	public static final String VALIDATOR_INSC_MUNICIPIO = "gare.InscricaoMunicipio";
	public static final String VALIDATOR_DIVIDA_ATIVA = "gare.DividaAtiva";
	public static final String VALIDATOR_NUMERO_DECLARACAO = "gare.NumeroDeclaracao";
	public static final String VALIDATOR_NUMERO_ETIQUETA = "gare.NumeroEtiqueta";
	public static final String VALIDATOR_AIIMM_H_Q = "gare.AIIMM_H_Q";
	public static final String VALIDATOR_AIIMM_C = "gare.AIIMM_C";
	public static final String VALIDATOR_NUMERO_DI = "gare.NumeroDI";
	public static final String VALIDATOR_NUMERO_PARCELAMENTO = "gare.NumeroParcelamento";
	public static final String VALIDATOR_NUMERO_DSI = "gare.NumeroDSI";
	public static final String VALIDATOR_NUMERO_GUIA = "penhor.NumeroGuia";
	public static final String VALIDATOR_NUMERO_LOTE = "penhor.NumeroLote";
	public static final String VALIDATOR_NUMERO_CONTRATO = "penhor.NumeroContrato";

	public static final String PESO_AGENGIA = "5432";
	public static final String PESO_COD_RECEITA = "842";
	public static final String PESO_COD_RENAVAM = "98765432";
	public static final int [] PESO_INSC_ESTADUAL_1DV = {1,3,4,5,6,7,8,10};
	public static final int [] PESO_INSC_ESTADUAL_2DV = {3,2,10,9,8,7,6,5,4,3,2};
	public static final int [] PESO_COD_RECEITA_GARE = {4,3,2};
	public static final int [] PESO_INSC_MUNICIPIO = {4,3,2};
	public static final int [] PESO_DIVIDA_ATIVA = {1,3,4,5,6,7,8,10};
	public static final int [] PESO_NUMERO_DECLARACAO = {1,3,4,5,6,7,8,10};
	public static final int [] PESO_NUMERO_ETIQUETA = {1,2,3,4,5,6,7,8,9,10,11,12};
	public static final int [] PESO_AIIMM_H_Q = {7,6,5,4,3,2};
	public static final int [] PESO_AIIMM_C = {9,8,7,6,5,4,3,2};
	public static final int [] PESO_NUMERO_PARCELAMENTO = {8,7,6,5,4,3,2,10};
	
	public static final int FATOR_INICIAL_REFERENCIA = 2;
	public static final int FATOR_FINAL_REFERENCIA = 9;

	public static final int FATOR_INICIAL_CPF = 2;
	public static final int FATOR_FINAL_CPF = 11;

	public static final int FATOR_INICIAL_CNPJ = 2;
	public static final int FATOR_FINAL_CNPJ = 9;

	public static final int FATOR_INICIAL_CONTA = 2;
	public static final int FATOR_FINAL_CONTA = 9;

	public static final int FATOR_INICIAL_CAMPO_IDENTIFICADOR = 2;
	public static final int FATOR_FINAL_CAMPO_IDENTIFICADOR = 9;
	
	public static final int FATOR_INICIAL_LACRE_CONECTIVIDADE = 2;
	public static final int FATOR_FINAL_LACRE_CONECTIVIDADE = 11;
	
	public static final int FATOR_INICIAL_CONTRATO = 2;
	public static final int FATOR_FINAL_CONTRATO = 9;
	
	public static final int FATOR_INICIAL_CONSTRUCARD = 2;
	public static final int FATOR_FINAL_CONSTRUCARD = 9;
	
	public static final int FATOR_INICIAL_NUMERO_DI = 2;
	public static final int FATOR_FINAL_NUMERO_DI = 9;
	
	public static final int FATOR_INICIAL_NUMERO_DSI = 2;
	public static final int FATOR_FINAL_NUMERO_DSI = 9;
	
	public static final int FATOR_INICIAL_NUMERO_GUIA = 2;
	public static final int FATOR_FINAL_NUMERO_GUIA = 9;
	
	public static final int FATOR_INICIAL_NUMERO_LOTE = 2;
	public static final int FATOR_FINAL_NUMERO_LOTE = 9;
	
	public static final int FATOR_INICIAL_NUMERO_CONTRATO = 2;
	public static final int FATOR_FINAL_NUMERO_CONTRATO = 9;
}
