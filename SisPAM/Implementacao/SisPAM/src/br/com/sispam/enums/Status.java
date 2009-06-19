package br.com.sispam.enums;

public enum Status {
	ATIVO(1), INATIVO(2);
	
	private int codigo;
	
	private Status(int codigo){
		this.codigo = codigo;
	}
	
	public Status getStatus(int codigo){
		if(codigo == 1){
			return ATIVO;
		}else if(codigo == 2){
			return INATIVO;
		}else{
			return null;
		}
		
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
