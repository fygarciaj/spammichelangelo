package br.com.sispam.enums;

public enum StatusAgendamento {
	SOLICITADO(1), CONCLUIDO(2);

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
		case 1:
			return SOLICITADO;
		case 2:
			return CONCLUIDO;			
		default:
			return null;
		}
	}
}
