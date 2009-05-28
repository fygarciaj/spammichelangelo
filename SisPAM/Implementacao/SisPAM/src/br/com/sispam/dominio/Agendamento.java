package br.com.sispam.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.sispam.enums.StatusAgendamento;
import br.com.sispam.enums.TipoAgendamento;

@Entity
public class Agendamento {
	private int id;
	private Date data;
	private Paciente paciente;
	private Medico medico;
	private EspecialidadeMedica especialidadeMedica;
	private int tipo;
	private int hora;
	private String observacao;
	private int status;
	
	private StatusAgendamento statusAgendamento;
	private TipoAgendamento tipoAgendamento;
	
	@Id
	@Column(name="agdcod")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "agddat")
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@ManyToOne
	@JoinColumn(name="pctidfseg")
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	@ManyToOne
	@JoinColumn(name="mdccod")
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	@ManyToOne
	@JoinColumn(name="emdcod")
	public EspecialidadeMedica getEspecialidadeMedica() {
		return especialidadeMedica;
	}
	public void setEspecialidadeMedica(EspecialidadeMedica especialidadeMedica) {
		this.especialidadeMedica = especialidadeMedica;
	}
	@Column(name = "agdtip")
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	@Column(name = "agdhor")
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	@Column(name = "agdobs")
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	@Column(name = "agdsta")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Transient
	public StatusAgendamento getStatusAgendamento() {
		return statusAgendamento;
	}
	public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}
	@Transient
	public TipoAgendamento getTipoAgendamento() {
		return tipoAgendamento;
	}
	public void setTipoAgendamento(TipoAgendamento tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}
	
	
}
