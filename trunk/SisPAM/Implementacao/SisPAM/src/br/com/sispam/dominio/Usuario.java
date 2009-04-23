package br.com.sispam.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sispam.enums.Sexo;
@Entity
@Table(name = "usr")
public class Usuario {
	
	private int id;
	private String nome;
	private Sexo sexo;
	private long cpf;
	private long rg;
	private String expedidorRg;
	private String endereco;
	private long cep;
	private int cidade;
	private int uf;
	private int ddd;
	private long telefone;
	private String email;
	private String senha;
	private int perfil;
	
		
	/*Get & Set*/
	@Id
	@Column(name = "usrcod")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "usrnom")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(name = "usrsex")
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	@Column(name = "usrcpf")
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	@Column(name = "usrrg")
	public long getRg() {
		return rg;
	}
	public void setRg(long rg) {
		this.rg = rg;
	}
	@Column(name = "usrorgexp")
	public String getExpedidorRg() {
		return expedidorRg;
	}
	public void setExpedidorRg(String expedidorRg) {
		this.expedidorRg = expedidorRg;
	}
	@Column(name = "usrend")
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Column(name = "usrcep")
	public long getCep() {
		return cep;
	}
	public void setCep(long cep) {
		this.cep = cep;
	}
	@Column(name = "usrcde")
	public int getCidade() {
		return cidade;
	}
	public void setCidade(int cidade) {
		this.cidade = cidade;
	}
	@Column(name = "usruf")
	public int getUf() {
		return uf;
	}
	public void setUf(int uf) {
		this.uf = uf;
	}
	@Column(name = "usrddd")
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	@Column(name = "usrtel")
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	@Column(name = "usreml")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "usrsen")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Column(name = "usrpfl")
	public int getPerfil() {
		return perfil;
	}
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}
	
	
}
