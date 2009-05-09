package br.com.sispam.facade;

import javax.persistence.NoResultException;

import br.com.sispam.dao.LoginDao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.Cripto;

public class LoginFacade {
	private LoginDao loginDao;
	private Usuario usuarioNew;

	public Usuario pesquisaUsuario(String acesso, String senha) throws CampoInvalidoException{

		loginDao = new LoginDao();
		if(acesso == null || acesso.isEmpty()){
			throw new CampoInvalidoException("Usuário é campo obrigatório!");
		}else if  (senha == null || senha.isEmpty()){
			throw new CampoInvalidoException("Senha é campo obrigatório!");
		}else{
			try{
			usuarioNew = loginDao.recuperaSenha(acesso);
			}catch (NoResultException e) {
				throw new CampoInvalidoException("Usuário ou Senha inválida!");
			}
		}
		return usuarioNew;
	}


	public String criptografaSenha(String senha){
		Cripto cripto = new Cripto();
		return cripto.criptografar(senha);
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
