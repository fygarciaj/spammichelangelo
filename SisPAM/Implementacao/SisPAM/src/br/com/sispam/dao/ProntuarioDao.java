package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;

import br.com.sispam.dominio.Paciente;
import br.com.sispam.dominio.Usuario;

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
	
	/**
	 * : Recupera o paciente a partir do usuário.
	 * @param usuario
	 * @return
	 */
	public Paciente recuperar(Usuario usuario){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Paciente paciente =  null;
		try{
			Query query = manager.createQuery("from Paciente where usuario.id = :idUsuario");
			query.setParameter("idUsuario", usuario.getId());
			paciente = (Paciente) query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		return paciente;
	}
}
