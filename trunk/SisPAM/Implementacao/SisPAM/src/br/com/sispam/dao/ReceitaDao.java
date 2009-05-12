package br.com.sispam.dao;

import javax.persistence.EntityManager;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Convenio;

public class ReceitaDao {

	private Conexao conexao;
	private EntityManager manager;
	
	public void emitirReceita(Paciente paciente){		
		
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		
		//verifica se possui id caso possua apenas atualiza os dados no banco
		if(convenio != null && convenio.getId() > 0){
			manager.merge(convenio);
		}
		//caso não, salva um novo convênio
		else{
			manager.persist(convenio);
		}
		
		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}
	
}
