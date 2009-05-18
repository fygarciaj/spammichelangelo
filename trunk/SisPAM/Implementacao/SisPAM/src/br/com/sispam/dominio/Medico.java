package br.com.sispam.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.sispam.enums.Dia;


@Entity
public class Medico {

	private int id;
	private int crm;
	private String crmUf;
	private Usuario usuario;
	private List<EspecialidadeMedica> especialidades;
	private String dataAtendimento;
	private int horaInicio;
	private int horaFim;
	private int consultorio;
	private List<Compromisso> compromissos;
	private List<Dia> dias;

	@Id
	@Column(name = "mdccod")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usrcod")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Column(name = "mdccrm")
	public int getCrm() {
		return crm;
	}
	public void setCrm(int crm) {
		this.crm = crm;
	}
	@Column(name = "mdccrmuf")
	public String getCrmUf() {
		return crmUf;
	}
	public void setCrmUf(String crmUf) {
		this.crmUf = crmUf;
	}
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name="medico_especialidade", joinColumns=  @JoinColumn( name = "mdccod"),  inverseJoinColumns= @JoinColumn(name = "emdcod"))
	public List<EspecialidadeMedica> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(List<EspecialidadeMedica> especialidades) {
		this.especialidades = especialidades;
	}

	@Column(name="mdcdiaatd")
	public String getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	@Column(name="mdchorini")
	public int getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Column(name="mdchorfim")
	public int getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
	}

	@Column(name="mdccnsatd")
	public int getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(int consultorio) {
		this.consultorio = consultorio;
	}
	
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	public List<Compromisso> getCompromissos() {
		return compromissos;
	}
	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}
	@Transient
	public List<Dia> getDias() {
		return dias;
	}
	public void setDias(List<Dia> dias) {
		this.dias = dias;
	}
	
	
}
