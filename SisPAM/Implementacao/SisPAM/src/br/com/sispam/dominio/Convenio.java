package br.com.sispam.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Convenio {
	
	private int id;
	private String nome;	 
	private String cnpj;	 
	private String endereco;	 
	private String cidade;	 
	private String estado;	 
	private long cep;
	private String site;	 
	private String email; 
	private int ddd; 
	private int telefone;	 
	private int codigoANS;
		
	@Id
	@Column(name = "cvncod")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "cvndes")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(name = "cvncpj")
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	@Column(name = "cvnend")
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Column(name = "cvncde")
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	@Column(name = "cvncep")
	public long getCep() {
		return cep;
	}
	public void setCep(long cep) {
		this.cep = cep;
	}
	@Column(name = "cvnetd")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Column(name = "cvnste")
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	@Column(name = "cvneml")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "cvnddd")
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	@Column(name = "cvntel")
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	@Column(name = "cvnanscod")
	public int getCodigoANS() {
		return codigoANS;
	}
	public void setCodigoANS(int codigoANS) {
		this.codigoANS = codigoANS;
	}	 

}
