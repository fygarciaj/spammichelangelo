package br.com.sispam.util;

public class CampoUtil {

	/**
	 * Retira o carater indesejado do campo.
	 * @param caracter
	 * @param campo
	 * @return
	 */
	public static String replaceCampo(String caracter, String campo){
		if(campo != null){
			campo = campo.replaceAll("["+caracter+"]", "");
		}
		return campo;
	}

	public static String formataHora(int hora){
		String string = String.valueOf(hora);
		if(string.length() == 3){
			string = "0"+string.substring(0,1)+":"+string.substring(1, 3);
			return string;
		}
		else if(string.length() == 2){
			string = "00:"+string;
			return string;
		}
		else if(string.length() == 1){
			string = "00:0"+string;
			return string;
		}
		else{
			string = string.substring(0,2)+":"+string.substring(2, 4);
			return string;
		}
	}
}