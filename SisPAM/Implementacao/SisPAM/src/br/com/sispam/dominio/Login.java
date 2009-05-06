package br.com.sispam.dominio;

import javax.persistence.Column;
import br.com.sispam.util.Cripto;

public class Login {
	private String userAcess;
	private String senha;
	
	
	@Column(name = "userAcess")
	public String getUser() {
		return userAcess;
	}
	public void setUser(String userAcess) {
		this.userAcess = userAcess;
	}
	
	@Column(name = "usrsen")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha){
		this.senha = senha;
	}
}
