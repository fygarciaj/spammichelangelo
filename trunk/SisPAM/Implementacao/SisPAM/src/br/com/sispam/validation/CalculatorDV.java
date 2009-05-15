package br.com.sispam.validation;

public class CalculatorDV
{

	/**
	 * Método que calcula o dígito verificador do módulo 11. A validação será
	 * feita no sentido das strings, ou seja, da esquerda para a direita.
	 * 
	 * @param cadeia -
	 *            Cadeia ser calculado o dígito verificador.
	 * @param tam -
	 *            Número de dígitos a serem utilizados no cálculo.
	 * @param pesos -
	 *            Pesos que serão utilizados para fazer a validação.
	 * 
	 * @return Valor do dígito verificador.
	 */
	public static int calculateDVMod11(String cadeia, int tam, String pesos)
	{
		int total = 0;

		for (int i = 0; i < cadeia.length(); i++)
		{
			int charCadeia = Integer.parseInt(cadeia.substring(i, i + 1));
			int charPeso = Integer.parseInt(pesos.substring(i, i + 1));

			total += (charCadeia * charPeso);
		}
		total %= 11;

		if (total < 2)
			return 0;

		return (11 - total);
	}

	public static int calculateDVMod11NumeroParcelamento(String cadeia,
			int tam, int[] pesos)
	{
		int total = 0;

		for (int i = 0; i < cadeia.length(); i++)
		{
			int charCadeia = Integer.parseInt(cadeia.substring(i, i + 1));
			int charPeso = pesos[i];

			total += (charCadeia * charPeso);
		}
		total %= 11;

		if (total == 1)
			return 0;
		else if (total == 0)
			return -1;

		return (11 - total);
	}

	/**
	 * Método que calcula o dígito verificador do módulo 11. A validação será
	 * feita no sentido das strings, ou seja, da esquerda para a direita.
	 * 
	 * @param cadeia -
	 *            Cadeia ser calculado o dígito verificador.
	 * @param tam -
	 *            Número de dígitos a serem utilizados no cálculo.
	 * @param pesos -
	 *            Pesos que serão utilizados para fazer a validação.
	 * 
	 * @return Valor do dígito verificador.
	 */
	public static int calculateDVMod11Gare(String cadeia, int tam, int[] pesos)
	{
		int total = 0;

		for (int i = 0; i < cadeia.length(); i++)
		{
			int charCadeia = Integer.parseInt(cadeia.substring(i, i + 1));
			int charPeso = pesos[i];

			total += (charCadeia * charPeso);
		}
		total %= 11;

		String dv = new Integer(total).toString();
		return new Integer(dv.substring(dv.length() - 1));
	}

	/**
	 * Método que calcula o dígito verificador do módulo 11 de uma cadeia.
	 * 
	 * @param cadeia -
	 *            Cadeia ser calculado o dígito verificador.
	 * @param tam -
	 *            Número de dígitos a serem utilizados no cálculo.
	 * @param fatorInicial -
	 *            Valor base no fator de multiplicação.
	 * @param fatorFinal -
	 *            Valor limite do fator de multiplicação.
	 * 
	 * @return Valor do dígito verificador.
	 */
	public static int calculateDVMod11(String cadeia, int tam,
			int fatorInicial, int fatorFinal)
	{
		int total = buildDV(cadeia, tam, fatorInicial, fatorFinal);
		total %= 11;

		if (total < 2)
			return 0;

		return (11 - total);
	}

	public static int calculateDVMod11(String cadeia, int tam, int[] pesos)
	{
		int total = 0;

		for (int i = 0; i < cadeia.length(); i++)
		{
			int charCadeia = Integer.parseInt(cadeia.substring(i, i + 1));
			int charPeso = pesos[i];

			total += (charCadeia * charPeso);
		}
		total %= 11;

		if (total < 2)
			return 0;

		return (11 - total);
	}

	/**
	 * Método que calcula o dígito verificador do módulo 11 para o cartão
	 * construcard.
	 * 
	 * @param cadeia -
	 *            Cadeia ser calculado o dígito verificador.
	 * @param tam -
	 *            Número de dígitos a serem utilizados no cálculo.
	 * @param fatorInicial -
	 *            Valor base no fator de multiplicação.
	 * @param fatorFinal -
	 *            Valor limite do fator de multiplicação.
	 * 
	 * @return Valor do dígito verificador.
	 */
	public static int calculateDVConstrucardMod11(String cadeia, int tam,
			int fatorInicial, int fatorFinal)
	{
		Float total = new Float(buildDV(cadeia, tam, fatorInicial, fatorFinal));
		total *= 10;
		total += 1;
		total /= 11;
		total *= 10;

		Integer dv = new Integer(total.intValue());
		String dvString = dv.toString();
		return new Integer(dvString.substring(dvString.length() - 1))
				.intValue();
	}

	public static int buildDV(String cadeia, int tam, int fatorInicial,
			int fatorFinal)
	{
		int fator = fatorInicial;
		int total = 0;

		for (int ind = 0; ind < tam; ind++)
		{
			total += (Character.digit(cadeia.charAt(tam - ind - 1), 10) * fator);
			fator++;

			if (fator > fatorFinal)
				fator = fatorInicial;
		}
		return total;
	}

	/**
	 * Método que calcula o dígito verificador do módulo 11 de um número de IPTU
	 * ou ISS.
	 * 
	 * @param codigo -
	 *            Código ser calculado o dígito verificador.
	 * 
	 * @return Valor do dígito verificador.
	 */
	public static int calculateDVMod11IPTUISS(String codigo)
	{
		int total = 0, fator = 2;

		for (int ind = 0; ind < codigo.length(); ind++)
		{
			total += (Character.digit(codigo.charAt(codigo.length() - ind - 1),
					10) * fator);
			fator++;

			if (fator > 10)
				fator = 1;
		}
		total %= 11;

		if (total < 2)
			return 0;

		return (11 - total);
	}

	/**
	 * Método que calcula o dígito verificador do CEI.
	 * 
	 * @param cadeia -
	 *            String representando o CEI ser calculado o dígito verificador.
	 * 
	 * @return Dígito verificador do CEI.
	 */
	public static int calculateDvCei(String cadeia)
	{
		int total = 0;
		int[] fator = { 4, 7, 3, 6, 1, 2, 5, 8, 1, 4, 7 };

		// calcula DV para CEI
		for (int ind = 0; ind < 11; ind++)
		{
			total += (Character.digit(cadeia.charAt(10 - ind), 10) * fator[ind]);
		}

		total = ((total / 10) % 10) + (total % 10);
		// soma dígito da dezena com o da unidade

		total %= 10;

		return (10 - total) % 10;
	}

	/**
	 * Método que calcula o dígito verificador do módulo 10 de uma cadeia
	 * numérica.
	 * 
	 * @param cadeia -
	 *            String de dígitos decimais.
	 * @param tam -
	 *            Número de dígitos a serem utilizados no cálculo.
	 * 
	 * @return Dígito verificador da cadeia.
	 */
	public static int calculateDvMod10(String cadeia, int tam)
	{
		int total = 0;
		int parcial;
		int fator = 2;

		for (int ind = 0; ind < tam; ind++)
		{
			parcial = Character.digit(cadeia.charAt(tam - ind - 1), 10) * fator;
			total += ((parcial % 10) + (parcial / 10));
			fator = 3 - fator; // simula a sequência 2, 1, 2, ...
		}

		return (10 - (total % 10)) % 10;
	}

}
