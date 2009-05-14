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
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@Entity
public class Medico {
	
	private int id;
	private int crm;
	private String crmUf;
	private Usuario usuario;
	private AgendaMedica agendaMedica;
	private List<EspecialidadeMedica> especialidades;

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
	@Transient
	public AgendaMedica getAgendaMedica() {
		return agendaMedica;
	}
	public void setAgendaMedica(AgendaMedica agendaMedica) {
		this.agendaMedica = agendaMedica;
	}
	
	@ManyToMany
	@JoinTable(name="medico_especialidade", joinColumns=  @JoinColumn( name = "mdccod"),  inverseJoinColumns= @JoinColumn(name = "emdcod"))
	public List<EspecialidadeMedica> getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(List<EspecialidadeMedica> especialidades) {
		this.especialidades = especialidades;
	}
	
	
	
}
