package br.com.sispam.dao;

import javax.persistence.EntityManager;

import br.com.sispam.banco.Conexao;

public class ReceitaDao {

	private Conexao conexao;
	private EntityManager manager;
	
	/**
	 * @descricao: Emite a receita.
	 * @param id
	 */
	public void emitirReceita(String id){		
		
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		
		
		
		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}
	
}
