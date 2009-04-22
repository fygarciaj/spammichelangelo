package br.com.sispam.enums;

public enum Sexo {
	MASCULINO('M'), FEMININO('F');
	private char sigla;

	private Sexo(char sigla){
		this.sigla = sigla;
	}

	public char getSigla() {
		return sigla;
	}

	public void setSigla(char sigla) {
		this.sigla = sigla;
	}
}
