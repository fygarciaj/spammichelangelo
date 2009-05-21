package br.com.sispam.dao;

import javax.persistence.EntityManager;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.Compromisso;


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
