package br.com.sispam.action;

import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.LoginFacade;
import br.com.sispam.util.Cripto;

public class LoginAction extends Action{

	private String usuario;
	private String senha;
	private LoginFacade loginFacade;
	private Usuario usr;

	public String logar(){
		usr = new Usuario();
		loginFacade = new LoginFacade();
		usr = loginFacade.pesquisaUsuario(usr);
		
/*		System.out.println(usr.getUsracs());
		System.out.println(usr.getSenha());
*/		System.out.println(usuario);
		System.out.println(senha);
		
			if(usuario != null && usuario.equals(usr.getUsracs())
				&& senha != null && senha.equals(usr.getSenha())){
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

	public LoginFacade getLoginFacade() {
		return loginFacade;
	}

	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}

	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		this.usr = usr;
	}

}
