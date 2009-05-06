package br.com.sispam.dominio;


public class AgendaMedica {
	
	private Medico medico;
	private String dataAtendimento;
	private int horaInicio;
	private int horaFim;
	private int id;
	private int codigoAlgumaCoisa;
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	

	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public String getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	

	public int getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public int getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
	}
	
	
	public int getCodigoAlgumaCoisa() {
		return codigoAlgumaCoisa;
	}
	public void setCodigoAlgumaCoisa(int codigoAlgumaCoisa) {
		this.codigoAlgumaCoisa = codigoAlgumaCoisa;
	}
}
