package br.com.sispam.action;

public class TesteAction extends Action {
	
	private String stringPagina;
	
	
	public String sucesso(){
		this.stringPagina = "Bem-vindo com sucesso";
		return SUCESSO;
	}
	
	public String falha(){
		this.stringPagina = "Bem-vindo com falha";
		return FALHA;
	}

	public String getStringPagina() {
		return stringPagina;
	}

	public void setStringPagina(String stringPagina) {
		this.stringPagina = stringPagina;
	}
	
	
}
