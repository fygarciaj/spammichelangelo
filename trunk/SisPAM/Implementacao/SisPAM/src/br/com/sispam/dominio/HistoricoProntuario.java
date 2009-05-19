package br.com.sispam.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HistoricoProntuario {
	private int id;
	private CodigoDoenca codigoDoenca;
	private Paciente paciente;
	private String sintoma;
	private String laudo;
	private String prescricao;
	private String observacao;
	
	@Id
	@Column(name="htccod")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="cidcod")
	public CodigoDoenca getCodigoDoenca() {
		return codigoDoenca;
	}
	public void setCodigoDoenca(CodigoDoenca codigoDoenca) {
		this.codigoDoenca = codigoDoenca;
	}
	@ManyToOne
	@JoinColumn(name="pctidfseg")
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	@Column(name = "htcstm")
	public String getSintoma() {
		return sintoma;
	}
	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}
	@Column(name = "htcldo")
	public String getLaudo() {
		return laudo;
	}
	public void setLaudo(String laudo) {
		this.laudo = laudo;
	}
	@Column(name = "htcprc")
	public String getPrescricao() {
		return prescricao;
	}
	public void setPrescricao(String prescricao) {
		this.prescricao = prescricao;
	}
	@Column(name = "htcobs")
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
