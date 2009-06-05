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
}
