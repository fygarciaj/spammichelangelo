package br.com.sispam.facade;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.sispam.dao.LoginDao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.Cripto;

public class LoginFacade {
	private LoginDao loginDao;
	private Usuario usuarioNew;
	
	/**
	 * : Efetua a pesquisa do usuário, apartir do login e senha.
	 * @param acesso
	 * @param senha
	 * @return
	 * @throws CampoInvalidoException
	 * @throws PersistenceException
	 */
	public Usuario pesquisaUsuario(String acesso, String senha) throws CampoInvalidoException, PersistenceException{

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
			}catch (PersistenceException e) {
				throw new CampoInvalidoException("Sem comunicação com Banco de Dados");
			}
		}
		return usuarioNew;
	}
	
	/**
	 * : Método para cripitografar a senha.
	 * @param senha
	 * @return
	 */
	public String criptografaSenha(String senha){
		Cripto cripto = new Cripto();
		return cripto.criptografar(senha);
	}
	
	/**
	 * : Método para formatar a data do acesso ao sistema.
	 * @return
	 */
	public String dataHoraLogin(){
		Locale locale = new Locale("pt","BR"); 
		GregorianCalendar calendar = new GregorianCalendar(); 
		SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' às 'HH':'mm'h'",locale); 
		return formatador.format(calendar.getTime());
	}
}
