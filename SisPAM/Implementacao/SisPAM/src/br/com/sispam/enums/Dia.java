package br.com.sispam.enums;

public enum Dia {
	SEGUNDA(1, "SEG", "Segunda"), TERÇA(2, "TER", "Terça"), QUARTA(3, "QUA", "Quarta"), QUINTA(4, "QUI", "Quinta"), SEXTA(5, "SEX", "Sexta");
	
	private int codigo;
	private String sigla;
	private String descricao;
	
	private Dia(int codigo, String sigla, String descricao){
		this.codigo =  codigo;
		this.sigla =  sigla;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
