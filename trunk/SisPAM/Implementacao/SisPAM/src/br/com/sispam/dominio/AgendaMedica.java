package br.com.sispam.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="agendamedica")
public class AgendaMedica {
	
	private Medico medico;
	private String dataAtendimento;
	private int horaInicio;
	private int horaFim;
	private int id;
	private int consultorio;
	
	@Id
	@Column(name="agmcod")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name="mdccod")
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	@Column(name="agmdtaatd")
	public String getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	
	@Column(name="agmhorini")
	public int getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	@Column(name="agmhorfim")
	public int getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
	}
	
	@Column(name="agmcnsatd")
	public int getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(int consultorio) {
		this.consultorio = consultorio;
	}
}
