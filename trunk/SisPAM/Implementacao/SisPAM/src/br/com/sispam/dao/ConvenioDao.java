package br.com.sispam.dao;

import javax.persistence.EntityManager;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Convenio;

public class ConvenioDao {
	
	private Conexao conexao;
	private EntityManager manager;
	
	public void incluiConvenio(Convenio convenio){
		
		manager = conexao.getEntityManger();
		manager.persist(convenio);
		conexao.finalizaConexao();
	}
	
	public void alteraConvenio(Convenio convenio){
		
		manager = conexao.getEntityManger();
		//fazer tratamento para alteracao
		conexao.finalizaConexao();
	}
}
