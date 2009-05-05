package br.com.sispam.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;

public class MedicoDao {

	private Conexao conexao;
	private EntityManager manager;

	/**
	 * @descricao: Salva e atualiza o médico passado.
	 * @param usuario
	 */
	public void salvarMedico(Medico medico){
		conexao = new Conexao();
		manager = conexao.getEntityManger();

		manager.getTransaction().begin();
		//verifica se possui id caso possua apenas atualiza os dados no banco
		if(medico != null && medico.getId() > 0){
			manager.merge(medico);
		}
		//caso não salva um novo usuário
		else{
			manager.persist(medico);
		}
		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}


	/**
	 * @descricao: Recupera o médico apartir do id passado.
	 * @param id
	 * @return
	 */
	public Medico recuperaPeloId(int id){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		try{
			return manager.find(Medico.class,id);
		}catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @descricao: recupera o médico pelo seu crm.
	 * @param cpf
	 * @return
	 */
	public Medico recuperaPeloCrm(int crm){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Medico medico = null;
		try{
			//cria uma queri para fazer a busca pelo perfil
			Query query = manager.createQuery("from Medico where crm = :crm ");
			//seta o parametro
			query.setParameter("crm", crm);
			medico = (Medico) query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return medico;
	}

	/**
	 * @descricao: Remove o médico cadastrado
	 * @param medico
	 */
	public void remover(Medico medico){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		Query query = manager.createQuery("delete from Medico where id = :id");
		query.setParameter("id", medico.getId());
		query.executeUpdate();
		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}

	public void removerTodosTeste() {
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		Query query = manager.createQuery("delete from Medico where id > 0");
		query.executeUpdate();
		manager.getTransaction().commit();

	}
}
