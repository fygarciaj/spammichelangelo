package br.com.sispam.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.Compromisso;
import br.com.sispam.enums.StatusAgendamento;


public class AgendamentoDao {
	private Conexao conexao;
	private EntityManager manager;

	/**
	 * Inclui ou altera um agendamento
	 * @param compromisso
	 */
	public void incluirAgendamento(Agendamento agendamento){		

		this.conexao = new Conexao();
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
		}

	/**
	 * Retorna os agendamentos do dia
	 * @return
	 */
	public List<Agendamento> recuperarAgendamentosDoDia(StatusAgendamento status){
		List<Agendamento> agendamentos = null;
		this.conexao = new Conexao();
		this.manager = this.conexao.getEntityManger();
		Query query = this.manager.createQuery("from Agendamento where data = :data and status = :status ");
		query.setParameter("data", new Date());
		query.setParameter("status", status.getCodigo());
		agendamentos = query.getResultList();
		return agendamentos;
	}
	
	/**
	 * : Verifica se a colisão de agendamento pela data e hora
	 * @param compromisso
	 * @return
	 */
	public List<Agendamento> consultarAgendamentoUnico(Agendamento agendamento){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Agendamento> agendamentos = null;
	try{
			Query query = manager.createQuery("from Agendamento where " +
					"((hora >= :horaInicio and hora+15 < :horaFim) or (hora+15 > :horaInicio and hora <= :horaFim)) " +
					"and medico.id = :idMedico and data = :data ");
			//seta o parametro
			query.setParameter("idMedico", agendamento.getMedico().getId());
			query.setParameter("data", agendamento.getData());
			agendamentos = query.getResultList();						
		}catch (NoResultException e) {
			agendamentos = null;
		}
	
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
		
	}
	
	/**
	 * Recupera os agendamentos do paciente.
	 * @param idPaciente
	 * @return
	 */
	public List<Agendamento> recuperarAgendamentoPaciente(int idPaciente){
		List<Agendamento> agendamentos = null;
		this.conexao = new Conexao();
		this.manager = this.conexao.getEntityManger();
		Query query = this.manager.createQuery("from Agendamento where paciente.usuario.id = :id and status = 2");
		query.setParameter("id", idPaciente);
		agendamentos = query.getResultList();
		return agendamentos;	
	}
	
	/**
	 * Recupera os agendamentos apartir dos dados passados.
	 * @param agendamento
	 * @return
	 */
	public List<Agendamento> consultar(Agendamento agendamento, int idUsuario){
		StringBuilder builder = montaQuery(agendamento, idUsuario);
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
		}if(idUsuario > 0){
			query.setParameter("usuario", idUsuario);
		}
		agendamentos = query.getResultList();
		return agendamentos;
	}
	
	/**
	 * Recupera os agendamentos apartir dos dados passados.
	 * @param agendamento
	 * @return
	 */
	public List<Agendamento> consultar(Agendamento agendamento){
		StringBuilder builder = montaQuery(agendamento, 0);
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
		}if(agendamento.getStatus() > 0){
			query.setParameter("status", agendamento.getStatus());
		}
		agendamentos = query.getResultList();
		return agendamentos;
	}
	
	/**
	 * Monta a query da consulta.
	 * @param agendamento
	 * @return
	 */
	private StringBuilder montaQuery(Agendamento agendamento, int idUsuario){
		StringBuilder builder = new StringBuilder();
		boolean medico = false;
		boolean data = false;
		boolean tipo = false;
		boolean usuario = false;
	
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
			tipo = true;
			if(data == true || medico == true){
			builder.append("and tipo = :idTipo ");
			}else{
				builder.append("tipo = :idTipo ");
			}
		}if(idUsuario > 0){
			usuario = true;
			if(data == true || medico == true || tipo == true){
				builder.append("and paciente.usuario.id = :usuario");
			}else{
				builder.append("paciente.usuario.id = :usuario");
			}
		}
		if(agendamento.getStatus() > 0){
			if(data == true || medico == true || tipo == true || usuario == true ){
				builder.append("and status = :status");
			}else{
				builder.append("status = :status");
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
