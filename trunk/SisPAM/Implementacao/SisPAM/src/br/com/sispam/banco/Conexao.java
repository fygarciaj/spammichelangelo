package br.com.sispam.banco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

	private static EntityManagerFactory factory;
	private EntityManager manager;
	private static Conexao conexao;
	
	public Conexao(){
		if(factory == null){
			factory = Persistence.createEntityManagerFactory("sispam");
		}
	}

	/**
	 * Cria a conexão com o banco de dados
	 * @return 
	 */
	public EntityManager getEntityManger(){
			manager = factory.createEntityManager();
			return manager;
	}
}
