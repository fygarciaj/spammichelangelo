package br.com.sispam.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.sispam.facade.LoginFacade;

@Entity
public class Usuario {

	private int id;
	private String nome;
	private char sexo;
	private String cpf;
	private long rg;
	private String expedidorRg;
	private String endereco;
	private long cep;
	private String cidade;
	private String uf;
	private int ddd;
	private long telefone;
	private String email;
	private int perfil;
	private String senha;
	private String acesso;
	private String dtHoraAcesso;
	private Date dataNascimento;


	/*Get & Set*/
	@Id
	@Column(name = "usrcod")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	@Column(name = "usrcpf")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
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
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	@Column(name = "usruf")
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
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
	@Column(name = "usracs")
	public String getAcesso() {
		return acesso;
	}
	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}

	@Transient
	public String getDtHoraAcesso() {
		LoginFacade lf = new LoginFacade();
		return lf.dataHoraLogin();
	}
	public void setDtHoraAcesso(String dtHoraAcesso) {
		LoginFacade lf = new LoginFacade();
		this.dtHoraAcesso = lf.dataHoraLogin();
	}
	
	@Column(name="usrdatnsc")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	

}
