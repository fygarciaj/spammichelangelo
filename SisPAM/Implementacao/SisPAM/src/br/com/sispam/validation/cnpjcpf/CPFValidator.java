package br.com.sispam.validation.cnpjcpf;

import br.com.sispam.validation.CalculatorDV;
import br.com.sispam.validation.IValidation;
import br.com.sispam.validation.Validator;
import br.com.sispam.validation.ValidatorFactory;

public class CPFValidator extends Validator
{
	private String cpf;

	public CPFValidator()
	{
	}

	/**
	 * Método que faz a validação de um CPF.
	 * 
	 * @return true caso o CPF seja válido e false caso contrário.
	 */
	public boolean isValid()
	{
		if ((cpf.length() == 11)
				&& !isRepeatedNumber(cpf)
				&& (Character.digit(cpf.charAt(9), 10) == CalculatorDV
						.calculateDVMod11(cpf, 9,
								IValidation.FATOR_INICIAL_CPF,
								IValidation.FATOR_FINAL_CPF))
								&& (Character.digit(cpf.charAt(10), 10) == CalculatorDV
										.calculateDVMod11(cpf, 10,
												IValidation.FATOR_INICIAL_CPF,
												IValidation.FATOR_FINAL_CPF)))
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}

	public static void main(String[] args)
	{
		CPFValidator validator = (CPFValidator) ValidatorFactory.getInstance()
		.getValidator(IValidation.VALIDATOR_CPF);
		validator.setCpf("28391900100");
		
		System.out.println(validator.isValid());
	}
}
