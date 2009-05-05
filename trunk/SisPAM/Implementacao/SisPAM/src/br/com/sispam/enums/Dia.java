package br.com.sispam.enums;

public enum Dia {
	SEGUNDA(1, "SEG"), TERÇA(2, "TER"), QUARTA(3, "QUA"), QUINTA(4, "QUI"), SEXTA(5, "SEX");
	
	private int codigo;
	private String sigla;
	
	private Dia(int codigo, String sigla){
		this.codigo =  codigo;
		this.sigla =  sigla;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public static Dia newInstance(int codigo){
		switch (codigo) {
		case 1:
			return SEGUNDA;
		case 2:
			return TERÇA;
		case 3:
			return QUARTA;
		case 4:
			return QUINTA;
		case 5:
			return SEXTA;
		default:
			return null;
		}
	}
}
