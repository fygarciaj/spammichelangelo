package br.com.sispam.enums;

public enum Acao {
	
	INCLUSAO(1, "INCLUSÃO"), 
	EXCLUSAO(2, "EXCLUSÃO"), 
	CONSULTA(3, "CONSULTA"), 
	ALTERACAO(4, "ALTERAÇÃO"), 
	ATUALIZACAO(5, "ATUALIZAÇÃO"), 
	EMISSAO(6, "EMISSÃO");

	
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
