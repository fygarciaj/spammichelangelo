package br.com.sispam.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Medico {
	
	private int id;
	private int crm;
	private String crmUf;
	private Usuario usuario;

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
	
}
