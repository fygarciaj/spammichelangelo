package br.com.sispam.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Usuario;


public class LoginDao {
	private Conexao conexao;
	private EntityManager manager;
	
	public Usuario recuperaSenha(String userAcess){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Usuario usuario = null;

		try{
			//cria uma query para fazer a busca pelo usuário de acesso
			Query query = manager.createQuery("from Usuario where usracs = :usracs ");
			//define o parametro
			query.setParameter("usracs", userAcess);
			usuario = (Usuario) query.getSingleResult();
		
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		
		conexao.finalizaConexao();
		return usuario;
		
	}
}
