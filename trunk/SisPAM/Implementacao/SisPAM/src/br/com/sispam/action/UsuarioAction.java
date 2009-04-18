package br.com.sispam.action;

import br.com.sispam.dominio.Usuario;
import br.com.sispam.facade.UsuarioFacade;

public class UsuarioAction extends Action{
	
	private Usuario usuario;
	private UsuarioFacade usuarioFacade;
	
	
	public String salvarUsuario(){
		usuarioFacade = new UsuarioFacade();
		if(usuarioFacade.verificaSexo(usuario)){
			this.usuario.setNome("viado");
		}else{
			this.usuario.setNome("birobas");
		}
		return SUCESSO;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public UsuarioFacade getUsuarioFacade() {
		return usuarioFacade;
	}


	public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}




	
	/*Get & Set*/
	

}
