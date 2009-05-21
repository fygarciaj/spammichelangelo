package br.com.sispam.enums;



	public enum TipoAgendamento {
		EXAME(1), CONSULTA(2);

		private int codigo;

		private TipoAgendamento(int codigo){
			this.codigo = codigo;
		}

		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}
		
		public static TipoAgendamento getTipoAgendamento(int codigo){
			switch (codigo) {
			case 1:
				return EXAME;
			case 2:
				return CONSULTA;			
			default:
				return null;
			}
		}
		
	}