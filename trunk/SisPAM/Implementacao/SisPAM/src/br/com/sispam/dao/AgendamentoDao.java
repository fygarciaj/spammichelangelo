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
	 * @descricao: inclui ou altera um agendamento
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
	 * @descricao: Retorna os agendamentos do dia
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
	 * @descricao: Excluir o agendamento do banco de dados.
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
	 * @descricao: Recupera os agendamentos apartir dos dados passados.
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
			query.setParameter("tipo", agendamento.getTipo());
		}
		agendamentos = query.getResultList();
		return agendamentos;
	}
	
	/**
	 * @descricao: Monta a query da consulta.
	 * @param agendamento
	 * @return
	 */
	private StringBuilder montaQuery(Agendamento agendamento){
		StringBuilder builder = new StringBuilder();
		builder.append("from Agendamento where ");

		if(agendamento.getMedico() != null && agendamento.getMedico().getId() > 0){
			builder.append("medico.id = :medico");
		}if(agendamento.getData() != null){
			builder.append("and data = :data");
		}if(agendamento.getTipo() > 0){
			builder.append("and tipo = :idTipo");
		}

		return builder;
	}


	/**
	 * @descricao: Recupera o agendamento a partir do id passado.
	 * @param id
	 * @return Agendamento
	 */
	public Agendamento recuperarAgendamento(int id){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		return manager.find(Agendamento.class, id) ;
	}
}
