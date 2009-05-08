package br.com.sispam.action;

import javax.persistence.NoResultException;

import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.LoginFacade;
import br.com.sispam.util.Cripto;

public class LoginAction extends Action{

	private String acesso;
	private String senha;
	private LoginFacade loginFacade;
	private LoginAction login;
	private Usuario usr;

	public String logar(){
		login  = new LoginAction();
		usr = new Usuario();
		loginFacade = new LoginFacade();
		
		try{
			this.usr = this.loginFacade.pesquisaUsuario(acesso, senha);
			System.out.println(acesso);
			System.out.println(senha);
			System.out.println(usr.getAcesso());
			System.out.println(usr.getSenha());
			
		}catch(CampoInvalidoException ex) {
			erros.put("campoInvalido", ex.getMessage());
			apresentaErrors();
			return FALHA;
		}catch(NoResultException e){
			erros.put("Usuário não cadastrado!", e.getMessage());
			apresentaErrors();
			return FALHA;
		}
		
		if(acesso != null && acesso.equals(usr.getAcesso())
				&& senha != null && senha.equals(usr.getSenha())){
			return SUCESSO;
		}else{
			return FALHA;
		}
	}

	/*Get & Set*/
	public String getAcesso() {
		return acesso;
	}
	public void setAcesso(String acesso) {
		this.acesso = acesso;
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
