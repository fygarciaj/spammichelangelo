package br.com.sispam.enums;

public enum Perfil {
	ADMINISTRADOR(1), ATENDENTE (2), MEDICO(3), PACIENTE(4);

	private int codigo;

	private Perfil(int codigo){
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public static Perfil getPerfil(int codigo){
		switch (codigo) {
		case 1:
			return ADMINISTRADOR;
		case 2:
			return ATENDENTE;
		case 3:
			return MEDICO;
		case 4:
			return PACIENTE;
		default:
			return null;
		}
	}
	
	public static Perfil[] values(int perfil){
		 if(perfil == ATENDENTE.getCodigo()){
		   Perfil[] perfils = new Perfil[]{ATENDENTE, MEDICO, PACIENTE};
		   return perfils;
		 }
		 else{
			 Perfil[] perfils = new Perfil[]{ADMINISTRADOR, ATENDENTE, MEDICO, PACIENTE};
			 return perfils;
		 }
	}
	
}
