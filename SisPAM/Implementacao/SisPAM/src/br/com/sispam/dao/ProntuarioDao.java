package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Paciente;

public class ProntuarioDao {
	private Conexao conexao;
	private EntityManager manager;
	
	/**
	 * : Recupera todos os pacientes cadastrados.
	 * @return
	 */
	public List<Paciente> recuperarTodos(){
		this.conexao = new Conexao();
		this.manager = conexao.getEntityManger();
		List<Paciente> pacientes = null;
		try{
		Query query = this.manager.createQuery("from Paciente");
		pacientes = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
			pacientes = null;
		}
		return pacientes;
	}
}
