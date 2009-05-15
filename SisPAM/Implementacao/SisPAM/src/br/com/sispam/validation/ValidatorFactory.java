package br.com.sispam.validation;

public class ValidatorFactory
{
	private static ValidatorFactory instance;

	private ValidatorFactory()
	{
	}

	public static ValidatorFactory getInstance()
	{
		if (instance == null)
			instance = new ValidatorFactory();

		return instance;
	}

	public Validator getValidator(String name)
	{
		Validator retorno = null;
		try
		{
			Class c = Class.forName("br.com.sispam.validation.".concat(name));
			retorno = (Validator) c.newInstance();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return retorno;
	}
}
