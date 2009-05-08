package br.com.sispam.facade;

import br.com.sispam.dao.LoginDao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;

public class LoginFacade {
	private LoginDao loginDao;
	private Usuario usuarioNew;
	
	public Usuario pesquisaUsuario(String acesso, String senha) throws CampoInvalidoException{
		try {
			loginDao = new LoginDao();
			if((acesso == null && acesso.trim().length() == 0) || (senha == null && senha.trim().length() == 0)){
				throw new CampoInvalidoException("Usuário e Senha são obrigatórios!");
			}else{
				usuarioNew = loginDao.recuperaSenha(acesso);
			}
			
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

			if(user.getAcesso() == null){
				throw new CampoInvalidoException("Usuário inválido.");
			}
			if(user.getSenha() == null){
				throw new CampoInvalidoException("Senha inválida.");
			}
		}
	}
}
