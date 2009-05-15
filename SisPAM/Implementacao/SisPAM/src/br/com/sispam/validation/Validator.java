package br.com.sispam.validation;

/*
 * Classe abstrata para validação.
 */
public abstract class Validator
{
	public abstract boolean isValid();
	
	/**
	 * Método para retirar o digito verificador da string, no caso de haver
	 * somente um dígito verificador. Belo nome, eu sei ;)
	 * 
	 * @param cadeia -
	 *            Campo a ser limpo.
	 */
	protected String cadeiaLimpa(String cadeia)
	{
		return cadeia.substring(0, cadeia.length() - 1);
	}

	/**
	 * Método para retirar o digito verificador da string, no caso de haver mais
	 * de um dígito verificador.
	 * 
	 * @param cadeia -
	 *            Campo a ser limpo.
	 * @param qtdDigitosVerificadores -
	 *            Quantidade de dígitos verificadores.
	 */
	protected String cadeiaLimpa(String cadeia, int qtdDigitosVerificadores)
	{
		return cadeia.substring(0, cadeia.length() - qtdDigitosVerificadores);
	}

	/**
	 * Método que verifica se cadeia é composta apenas por caracteres numéricos
	 * repetidos (0 ou 1).
	 * 
	 * @param cadeia -
	 *            Cadeia a ser verificada.
	 */
	public boolean isRepeatedNumber(String cadeia)
	{
		int ind = 1;
		char ch = cadeia.charAt(0);

		while (ind < cadeia.length())
		{
			if (ch == cadeia.charAt(ind))
			{
				ind++;
			} else
			{
				return false;
			}
		}

		return true;
	}
}