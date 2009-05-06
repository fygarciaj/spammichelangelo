package br.com.sispam.facade;

import br.com.sispam.dao.LoginDao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;

public class LoginFacade {
	private LoginDao loginDao;
	private Usuario usuarioNew;
	
	public Usuario pesquisaUsuario(Usuario usuario){
		try {
			loginDao = new LoginDao();
			usuarioNew = loginDao.recuperaSenha(usuario.getUser());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		}
		return usuarioNew;
	}
	private void validaCampos(Object objeto) throws CampoInvalidoException{

		if(objeto != null && objeto instanceof Usuario){
			Usuario user = (Usuario)objeto;

			if(user.getUser() == null){
				throw new CampoInvalidoException("Usuário deve ser informado.");
			}
			if(user.getSenha() == null){
				throw new CampoInvalidoException("Senha deve ser informada.");
			}
		}
	}
}
