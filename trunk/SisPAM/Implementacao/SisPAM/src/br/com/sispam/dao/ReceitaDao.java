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
	 * : Consulta os agendamentos com status = concluido
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
				
		return agendamentos;
	}	
	/**
	 * Recupera os agendamentos apartir dos dados passados.
	 * @param agendamento
	 * @return
	 */
	public List<Agendamento> consultar(Agendamento agendamento){
		StringBuilder builder = montaQuery(agendamento);
		List<Agendamento> agendamentos = null;
		this.conexao = new Conexao();
		this.manager = this.conexao.getEntityManger();
		Query query = this.manager.createQuery(builder.toString());
		if(agendamento.getMedico() != null && agendamento.getMedico().getId() > 0){
			query.setParameter("medico", agendamento.getMedico().getId());
		}if(agendamento.getData() != null){
			query.setParameter("data", agendamento.getData());
		}
		agendamentos = query.getResultList();
		return agendamentos;
	}
	
	/**
	 * Monta a query da consulta.
	 * @param agendamento
	 * @return
	 */
	private StringBuilder montaQuery(Agendamento agendamento){
		StringBuilder builder = new StringBuilder();
		boolean medico = false;
		boolean data = false;		
		builder.append("from Agendamento where ");

		if(agendamento.getMedico() != null && agendamento.getMedico().getId() > 0){
			medico = true;
			builder.append("medico.id = :medico ");
		}if(agendamento.getData() != null){
			data = true;
			if(medico == true){
				builder.append("and data = :data ");
			}else{
				builder.append("data = :data ");
			}
		}

		return builder;
	}
	
}
