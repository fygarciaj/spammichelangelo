package br.com.sispam.enums;

public enum StatusAgendamento {
	SOLICITADO(0), ATENDENDO(1), CONCLUIDO(2);

	private int codigo;

	private StatusAgendamento(int codigo){
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public static StatusAgendamento getStatusAgendamento(int codigo){
		switch (codigo) {
		case 0:
			return SOLICITADO;
		case 1:
			return ATENDENDO;
		case 2:
			return CONCLUIDO;			
		default:
			return null;
		}
	}
}
