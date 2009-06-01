package br.com.sispam.dao;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;


public class LoginDao {
	private Conexao conexao;
	private EntityManager manager;
	
	/**
	 * : Recupera o usuário pelo seu login.
	 * @param acesso
	 * @return
	 * @throws CampoInvalidoException
	 * @throws PersistenceException
	 */
	public Usuario recuperaSenha(String acesso)throws CampoInvalidoException, PersistenceException{
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		Usuario usuario = null;

		try{
			//cria uma query para fazer a busca pelo usuário de acesso
			Query query = manager.createQuery("from Usuario where acesso = :acesso ");
			//define o parametro
			query.setParameter("acesso", acesso);
			usuario = (Usuario) query.getSingleResult();
		
		}catch(NoResultException e) {
			e.printStackTrace();
		}catch(PersistenceException exc){
			exc.printStackTrace();
			throw new PersistenceException("Sem comunicação com Banco de Dados");
		}
		
		
		return usuario;
		
	}
}
