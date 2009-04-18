package br.com.sispam.facade;

import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.Usuario;

public class UsuarioFacade {
	private UsuarioDao usuarioDao;
	public boolean verificaSexo(Usuario usuario){
		usuarioDao = new UsuarioDao();
		if(usuario.getNome().equals("carlos")){
			return false;
		}
		else{
			usuarioDao.salvarUsuario(usuario);
			return true;
		}
	
	}
}
