package br.com.sispam.banco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

	private EntityManagerFactory factory;
	private EntityManager manager;

	/**
	 * @descricao: cria a conexão com o banco de dados
	 * @return
	 */
	public EntityManager getEntityManger(){
		this.factory = Persistence.createEntityManagerFactory("sispam");
		this.manager = factory.createEntityManager();
		return this.manager;
	}

	/**
	 * @descricao: Finaliza a conexão com o banco de dados.
	 */
	public void finalizaConexao(){
		if(this.factory != null){
			this.factory.close();
		}
		if(this.manager != null){
			this.manager.close();
		}
	}

}
