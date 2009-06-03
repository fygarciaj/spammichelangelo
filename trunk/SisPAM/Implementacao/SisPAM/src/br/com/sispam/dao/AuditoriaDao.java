package br.com.sispam.dao;

import javax.persistence.EntityManager;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Auditoria;


public class AuditoriaDao {
	private Conexao conexao;
	private EntityManager manager;
	
	/**
	 * : insere o historico de prontuario
	 * @param historicoProntuario
	 */
	public void gravaAuditoria(Auditoria auditoria){		
		
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		
		//verifica se possui id caso possua apenas atualiza os dados no banco
		if(auditoria != null && auditoria.getId() > 0){
			manager.merge(auditoria);
		}
		//caso não, salva um novo historico
		else{
			manager.persist(auditoria);
		}
		
		manager.getTransaction().commit();
	}
}
