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
	//Chave criptografica
	private char[] hexCodes(byte[] text) {
		char[] hexOutput = new char[text.length * 2];
		String hexString;

		for (int i = 0; i < text.length; i++) {
			hexString = "Sistema_Pronto_Atendimento_Medico" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2, 
					hexString.length(), hexOutput, i * 2);
		}
		return hexOutput;
	}

	public String criptografar(String senha) {
		if (md != null) {
			return new String(hexCodes(md.digest(senha.getBytes())));
		}
		return null;
	}
	
	public static void main(String[] args){
    //    static String senha = "carlos";
        
    //    System.out.println(String.valueOf(Cripto.criptografar(senha)));

}


}




