package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Agendamento;

public class ReceitaDao {

	private Conexao conexao;
	private EntityManager manager;
	
	/**
	 * @descricao: Consulta os agendamentos com status = concluido
	 * @param agendamento
	 * @return agendamentos
	 */
	public List<Agendamento> consultarAgendamento(Agendamento agendamento){
		conexao = new Conexao();
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
		conexao.finalizaConexao();		
		return agendamentos;
	}	
	
	
}
