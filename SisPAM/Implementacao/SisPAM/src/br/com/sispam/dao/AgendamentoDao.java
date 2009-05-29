package br.com.sispam.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Agendamento;


public class AgendamentoDao {
	private Conexao conexao;
	private EntityManager manager;

	/**
	 * Inclui ou altera um agendamento
	 * @param compromisso
	 */
	public void incluirAgendamento(Agendamento agendamento){		

		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();

		//verifica se possui id caso possua apenas atualiza os dados no banco
		if(agendamento != null && agendamento.getId() > 0){
			manager.merge(agendamento);
		}
		//caso não, salva um novo compromisso
		else{
			manager.persist(agendamento);
		}

		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}

	/**
	 * Retorna os agendamentos do dia
	 * @return
	 */
	public List<Agendamento> recuperarAgendamentosDoDia(){
		List<Agendamento> agendamentos = null;
		this.conexao = new Conexao();
		this.manager = this.conexao.getEntityManger();
		Query query = this.manager.createQuery("from Agendamento where data = :data");
		query.setParameter("data", new Date());
		agendamentos = query.getResultList();
		return agendamentos;
	}

	/**
	 * Excluir o agendamento do banco de dados.
	 * @param agendamento
	 */
	public void excluir(Agendamento agendamento){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		Agendamento ag = manager.merge(agendamento);
		manager.remove(ag);
		manager.getTransaction().commit();
		conexao.finalizaConexao();
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
		}if(agendamento.getTipo() > 0){
			query.setParameter("idTipo", agendamento.getTipo());
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
		boolean tipo = false;
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
		}if(agendamento.getTipo() > 0){
			if(data == true || medico == true){
			builder.append("and tipo = :idTipo ");
			}else{
				builder.append("tipo = :idTipo ");
			}
		}

		return builder;
	}


	/**
	 * Recupera o agendamento a partir do id passado.
	 * @param id
	 * @return Agendamento
	 */
	public Agendamento recuperarAgendamento(int id){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		return manager.find(Agendamento.class, id) ;
	}
}
