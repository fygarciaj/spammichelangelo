package br.com.sispam.banco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

	private EntityManagerFactory factory;
	private EntityManager manager;
	private static Conexao conexao;

	/**
	 * Cria a conexão com o banco de dados
	 * @return 
	 */
	public EntityManager getEntityManger(){
		if(manager == null){
			this.factory = Persistence.createEntityManagerFactory("sispam");
			this.manager = factory.createEntityManager();
		}
		return this.manager;
	}

	public static Conexao getConexao(){
		if(conexao == null){
			conexao = new Conexao();
			return conexao;
		}else{
			return conexao;
		}

	}

	private Conexao(){
		
	}

}
