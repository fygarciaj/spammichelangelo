package br.com.sispam.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;



public class DataUtil {
	
	/**
	 * : converte date para string de acordo com o formato desejado.
	 * @param date
	 * @param formato
	 * @return
	 */
	public static String dateToString(Date date, String formato){
		SimpleDateFormat simpleDateFormat;
		String retorno = null;
		if(date != null && formato != null){
			simpleDateFormat = new SimpleDateFormat(formato);
			retorno = simpleDateFormat.format(date);
		}
		else if(date != null && formato == null){
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			retorno = simpleDateFormat.format(date);
		}
		return retorno;
	}
	
	/**
	 * descrição: formata um date para uma string no padrão dd/mm/yyyy
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		String dataFormatada = null;
		if(date != null){
			SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
			dataFormatada = formata.format(date);
		}
		return dataFormatada;
	}
		
	/**
	 * descrição: converte uma data em string para um objeto do tipo Date
	 * @param data
	 * @return
	 * @throws java.text.ParseException 
	 * @throws java.text.ParseException 
	 */
	public static Date stringToDate(String data) throws java.text.ParseException {
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date date = formata.parse(data);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
	
}