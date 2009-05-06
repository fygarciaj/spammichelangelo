package br.com.sispam.action;

import br.com.sispam.dominio.Login;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.Cripto;

public class LoginAction extends Action{

	private String usuario;
	private String senha;


	public String logar(){
		Login user = new Login();
		System.out.println(user.getUser());
		System.out.println(user.getSenha());
		System.out.println(usuario);
		System.out.println(senha);
		
			if(usuario != null && usuario.equals(user.getUser())
				&& senha != null && senha.equals(user.getSenha())){
				return SUCESSO;
			}else{
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
		Cripto encript = new Cripto();
		this.senha = encript.criptografar(senha);
	}


}
