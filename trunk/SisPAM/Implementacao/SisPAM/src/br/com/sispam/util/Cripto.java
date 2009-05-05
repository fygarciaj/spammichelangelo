package br.com.sispam.util;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class Cripto{
	//Algoritmo usado
	private static MessageDigest md = null;
	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}
	//Chave criptografica
	private static char[] hexCodes(byte[] text) {
		char[] hexOutput = new char[text.length * 2];
		String hexString;

		for (int i = 0; i < text.length; i++) {
			hexString = "SisPAM" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2, 
					hexString.length(), hexOutput, i * 2);
		}
		return hexOutput;
	}

	public static String criptografar(String senha) {
		if (md != null) {
			return new String(hexCodes(md.digest(senha.getBytes())));
		}
		return null;
	}
	
	public static void main(String[] args){
        String senha = "CCCCCC";
        System.out.println(Cripto.criptografar(senha));

}


}




