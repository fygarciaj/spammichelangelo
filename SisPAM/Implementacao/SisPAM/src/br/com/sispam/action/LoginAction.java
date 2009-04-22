package br.com.sispam.action;

public class LoginAction extends Action{

	private String usuario;
	private String senha;


	public String logar(){
		if(usuario != null && usuario.equals("admin")
				&& senha != null && senha.equals("admin")){
			return SUCESSO;
		}
		else{
			return FALHA;
		}
	}


	/*Get & Set*/
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


}
