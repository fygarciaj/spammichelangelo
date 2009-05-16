package br.com.sispam.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Compromisso {
	private int id;
	private Medico medico;
	private Date data;
	private String descricao;
	private int horaInicio;
	private int horaFim;
	private String tipo;
	
	@Id
	@Column(name="cpmcod")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "cpmdta")
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Column(name="cpmhorini")
	public int getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}
	@Column(name="cpmhorfim")
	public int getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
	}
	@Column(name="cpmdes")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Column(name="cpmtip")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@ManyToOne
	@JoinColumn(name="mdccod")
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	
	
}