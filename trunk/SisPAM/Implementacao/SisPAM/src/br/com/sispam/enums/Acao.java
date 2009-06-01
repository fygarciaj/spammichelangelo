package br.com.sispam.enums;

public enum Acao {
	
	SALVAR_MEDICO(1, "Inclusão de médico"), SALVAR_PACIENTE(2, "Inclusão de paciente");
	
	private int codigo;
	private String descricao;
	
	private Acao(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
