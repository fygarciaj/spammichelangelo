package br.com.sispam.dao;

import javax.persistence.EntityManager;

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
}
