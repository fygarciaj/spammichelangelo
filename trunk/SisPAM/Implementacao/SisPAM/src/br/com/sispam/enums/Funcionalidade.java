package br.com.sispam.enums;

public enum Funcionalidade {
	MANTER_USUARIO(1, "MANTER USUÁRIO"), 
	MANTER_CONVENIO(2, "MANTER CONVÊNIO"), 
	MANTER_AGENDA_MEDICO(3, "MANTER AGENDA MÉDICO"), 
	MANTER_AGENDAMENTO(4, "MANTER AGENDAMENTO"),
	LOGIN(5, "REALIZAR LOGIN"),
	ATUALIZA_PRONTUARIO(6, "ATUALIZA PRONTUÁRIO"),
	EMITE_RECEITA(7, "EMITE RECEITA"),
	RELATORIO_CONVENIO(8, "EMITIR RELATÓRIO CONVÊNIO"),
	RELATORIO_USUARIO(9, "EMITIR RELATÓRIO USUÁRIO"),
	RELATORIO_LOG(10, "EMITIR RELATÓRIO LOG" ),
	RELATORIO_PRONTUARIO(11, "EMITIR RELATÓRIO PRONTUÁRIO"),
	ATUALIZA_PARAMETRO(12, "ATUALIZA PARAMETRO"),
	CONSULTA_REALIZADO(13, "CONSULTA AGENDAMENTO REALIZADO");
	
	private int codigo;
	private String descricao;

	private Funcionalidade(int codigo, String descricao){
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
