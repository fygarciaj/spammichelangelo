package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.CodigoDoenca;



public class CodigoDoencaDao {
	
	private Conexao conexao;
	private EntityManager manager;

	
	/**
	 * @descricao: Recupera o codigo de doença a partir do id passado.
	 * @param id
	 * @return CodigoDoenca
	 */
	public CodigoDoenca recuperarCodigoDoenca(int id){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		return manager.find(CodigoDoenca.class, id) ;
	}
	/**
	 * @descricao: Recupera todas os codigos internacionais de doença cadastradas.
	 * @return
	 */
	public List<CodigoDoenca> recuperarTodas(){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Query query = manager.createQuery("from CodigoDoenca");
		return query.getResultList();
	}
	
}
