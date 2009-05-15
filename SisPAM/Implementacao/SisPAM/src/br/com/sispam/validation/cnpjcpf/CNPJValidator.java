package br.com.sispam.validation.cnpjcpf;

import br.com.sispam.validation.CalculatorDV;
import br.com.sispam.validation.IValidation;
import br.com.sispam.validation.Validator;
import br.com.sispam.validation.ValidatorFactory;

public class CNPJValidator extends Validator
{
	private String cnpj;

	public CNPJValidator()
	{
	}

	/**
	 * Método que faz a validação de um CNPJ.
	 * 
	 * @param cnpj -
	 *            CNPJ a ser validado.
	 * 
	 * @return true caso o cnpj seja validado, false caso contrário.
	 */
	public boolean isValid()
	{
		int tam = cnpj.length() - 2;
		int filial = Integer.parseInt(cnpj.substring(9, tam));

		if ((cnpj.length() == 14)
				&& (!isRepeatedNumber(cnpj))
				&& (Character.digit(cnpj.charAt(12), 10) == CalculatorDV
						.calculateDVMod11(cnpj, 12,
								IValidation.FATOR_INICIAL_CNPJ,
								IValidation.FATOR_FINAL_CNPJ))
				&& (Character.digit(cnpj.charAt(13), 10) == CalculatorDV
						.calculateDVMod11(cnpj, 13,
								IValidation.FATOR_INICIAL_CNPJ,
								IValidation.FATOR_FINAL_CNPJ)) && (filial > 0))
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	public void setCnpj(String cnpj)
	{
		this.cnpj = cnpj;
	}

	public static void main(String[] args)
	{
		CNPJValidator validator = (CNPJValidator) ValidatorFactory
				.getInstance().getValidator(IValidation.VALIDATOR_CNPJ);
		validator.setCnpj("0211");
		validator.isValid();
	}
}
