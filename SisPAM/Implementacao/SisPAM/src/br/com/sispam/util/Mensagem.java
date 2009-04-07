package br.com.sispam.util	;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;


public class Mensagem {
	static Properties mensagem;
	static Properties erro;
	static{
		mensagem = new Properties();
		erro = new Properties();
		try{
			//carrega as mensagens
			InputStream is = Mensagem.class.getResourceAsStream("/mensagem/mensagem_pt.properties");
			mensagem.load(is);
			//carrega as mensagens de erro
			InputStream isErro = Mensagem.class.getResourceAsStream("/mensagem/mensagem_erro_pt.properties");
			erro.load(isErro);
			
		}
		catch (FileNotFoundException e) {
			System.out.println("erro "+e.getMessage());
		}
		catch (IOException e) {
			System.out.println("error "+e.getMessage());
		}

	}
	
	/**
	 * @descricao: Retona uma mensagem de erro de acordo com a chave passada.
	 * @param chave
	 * @return
	 */
	public String getErros(String chave){
		String retorno = null;
		if(chave != null){
		retorno = erro.getProperty(chave);
		}
		return retorno;
	}
	
	/**
	 * @descricao: Retona uma mensagem de acordo com a chave passada.
	 * @param chave
	 * @return
	 */
	public String getMensagem(String chave){
		String retorno = null;
		if(chave != null){
		retorno = mensagem.getProperty(chave);
		}
		return retorno;
	}

}
