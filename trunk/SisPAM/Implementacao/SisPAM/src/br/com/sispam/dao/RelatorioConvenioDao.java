package br.com.sispam.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


import br.com.sispam.banco.Conexao;


public class RelatorioConvenioDao {
	private Conexao conexao;
	private EntityManager manager;
	
	public void emitirRelatorioConvenio(String tipoRelatorio, String categoria){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		
		try{
			//cria uma query para fazer a busca pelo cnpj
									
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();		
		
	}
}
