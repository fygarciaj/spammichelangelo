package br.com.sispam.util;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class Cripto{
	//Algoritmo utilizado MD5
	private static MessageDigest md = null;
	public Cripto() {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @descricao: Método de criptografia.
	 * @param text
	 * @return
	 */
	private char[] hexCodes(byte[] text) {
		char[] hexOutput = new char[text.length * 2];
		String hexString;

		for (int i = 0; i < text.length; i++) {
			hexString = "$isP@m" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2, 
					hexString.length(), hexOutput, i * 2);
		}
		return hexOutput;
	}
	
	/**
	 * @descricao: Recebe a senha para ser criptografada.
	 * @param senha
	 * @return
	 */
	public String criptografar(String senha) {
		if (md != null) {
			return new String(hexCodes(md.digest(senha.getBytes())));
		}
		return null;
	}

}




