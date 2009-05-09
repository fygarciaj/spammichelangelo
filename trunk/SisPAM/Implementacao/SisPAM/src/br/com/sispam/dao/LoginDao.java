package br.com.sispam.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Usuario;


public class LoginDao {
	private Conexao conexao;
	private EntityManager manager;
	
	public Usuario recuperaSenha(String acesso){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Usuario usuario = null;

		try{
			//cria uma query para fazer a busca pelo usuário de acesso
			Query query = manager.createQuery("from Usuario where acesso = :acesso ");
			//define o parametro
			query.setParameter("acesso", acesso);
			usuario = (Usuario) query.getSingleResult();
		
		}catch (NoResultException e) {
			 System.out.println("teste1");
			 usuario = null;
			e.printStackTrace();
		}catch (NonUniqueResultException e) {   
		      e.printStackTrace();   
		      System.out.println("teste2");
		} 
		
		conexao.finalizaConexao();
		return usuario;
		
	}
}
