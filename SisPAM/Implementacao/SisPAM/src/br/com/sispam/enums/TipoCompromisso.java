package br.com.sispam.enums;

public enum TipoCompromisso {
	Cirurgia(1), Palestra(2), Perícia(3), Reunião(4), Seminário(5), Congresso(6);
	
	private int codigo;
	
	private TipoCompromisso(int codigo){
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public static TipoCompromisso getTipoCompromisso(int codigo){
		switch (codigo) {
		case 1:
			return Cirurgia;
		case 2:
			return Palestra;
		case 3:
			return Perícia;		
		case 4:
			return Reunião;		
		case 5:
			return Seminário;	
		case 6:
			return Congresso;
		default:
			return null;
		}
	}
}
