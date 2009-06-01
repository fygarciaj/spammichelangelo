package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Agendamento;

import br.com.sispam.dominio.HistoricoProntuario;


public class HistoricoProntuarioDao {
	private Conexao conexao;
	private EntityManager manager;
	
	/**
	 * : Consulta os agendamentos com status = solicitado
	 * @param agendamento
	 * @return agendamentos
	 */
	public List<Agendamento> consultarAgendamento(Agendamento agendamento){
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		List<Agendamento> agendamentos = null;
		try{
			//cria uma queri para fazer a busca por data e status
			Query query = manager.createQuery("from Agendamento where status = :status and data = :data and medico.id = :medico");
			//seta o parametro
			query.setParameter("medico", agendamento.getMedico().getId());
			query.setParameter("status", agendamento.getStatus());
			query.setParameter("data", agendamento.getData());
			agendamentos = query.getResultList();				
		}catch (NoResultException e) {
			e.printStackTrace();
			e.getMessage();
		}
		return agendamentos;
	}	
	
	
	/**
	 * : insere o historico de prontuario
	 * @param historicoProntuario
	 */
	public void atualizarHistorioProntuario(HistoricoProntuario historicoProntuario){		
		
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		
		//verifica se possui id caso possua apenas atualiza os dados no banco
		if(historicoProntuario != null && historicoProntuario.getId() > 0){
			manager.merge(historicoProntuario);
		}
		//caso não, salva um novo historico
		else{
			manager.persist(historicoProntuario);
		}
		manager.getTransaction().commit();
	}
}
