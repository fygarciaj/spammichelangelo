package br.com.sispam.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Compromisso;
import br.com.sispam.dominio.Convenio;

public class CompromissoDao {
	private Conexao conexao;
	private EntityManager manager;

	public void incluirCompromisso(Compromisso compromisso){		

		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();

		//verifica se possui id caso possua apenas atualiza os dados no banco
		if(compromisso != null && compromisso.getId() > 0){
			manager.merge(compromisso);
		}
		//caso não, salva um novo compromisso
		else{
			manager.persist(compromisso);
		}

		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}

	public void excluirCompromisso(Compromisso compromisso){

		conexao = new Conexao();
		manager = conexao.getEntityManger();
		try{
			manager.getTransaction().begin();

			Query query = manager.createQuery("delete from Compromisso where id = :id");
			//seta o parametro
			query.setParameter("id", compromisso.getId());
			query.executeUpdate();
			manager.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();

		}
		conexao.finalizaConexao();
	}

	public Compromisso recuperarPeloId(int id){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		return manager.find(Compromisso.class, id);
	}

	public List<Compromisso> consultarCompromissoUnico(Compromisso compromisso){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Compromisso> compromissos = null;
	try{
			//cria uma query para fazer a busca pelo cnpj
			Query query = manager.createQuery("from Compromisso where " +
					"((horaInicial >= :horaInicio and horaFinal < :horaFim) or (horaFinal > :horaInicio and horaInicial <= :horaFim)) " +
					"and medico.id = :idMedico and data = :data ");
			//seta o parametro
			query.setParameter("idMedico", compromisso.getMedico().getId());
			query.setParameter("data", compromisso.getData());
			query.setParameter("horaInicio", compromisso.getHoraInicial());
			query.setParameter("horaFim", compromisso.getHoraFinal());
			compromissos = query.getResultList();						
		}catch (NoResultException e) {
			compromisso = null;
		}
		conexao.finalizaConexao();		
		return compromissos;
	}

	public List<Compromisso> consultarCompromissos(Compromisso compromisso){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Compromisso> compromissos = null;
		try{
			//cria uma queri para fazer a busca pelo nome
			Query query = manager.createQuery("from Compromisso where medico.id = :medico and data = :data");
			//seta o parametro
			query.setParameter("medico", compromisso.getMedico().getId());
			query.setParameter("data", compromisso.getData());
			compromissos = query.getResultList();				
		}catch (NoResultException e) {
			e.printStackTrace();
			e.getMessage();
		}
		conexao.finalizaConexao();		
		return compromissos;
	}	

}
