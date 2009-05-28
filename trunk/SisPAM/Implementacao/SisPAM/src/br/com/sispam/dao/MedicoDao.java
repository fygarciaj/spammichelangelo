package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.EspecialidadeMedica;
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
				
		if(medico != null && medico.getId() > 0){
			excluirEspecialidades(medico);
		}
		if(medico != null && medico.getId() > 0 && medico.getEspecialidades() != null && medico.getEspecialidades().size() > 0){
			inseriEspecialidades(medico);
		}
		
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
	 * @descricao: Recupera uma lista de médicos pelo nome.
	 * @param nome
	 * @return
	 */
	public List<Medico> recuperaPeloNome(String nome){
		conexao = new Conexao();
		List<Medico> lista = null;
		manager = conexao.getEntityManger();
		Medico medico = null;
		try{
			//cria uma queri para fazer a busca pelo perfil
			Query query = manager.createQuery("from Medico where usuario.nome like :nome ");
			//seta o parametro
			query.setParameter("nome", "%"+nome+"%");
			lista =query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
			lista = null;
		}
		conexao.finalizaConexao();
		return lista;
	}

	/**
	 * @descricao: Remove o médico cadastrado
	 * @param medico
	 */
	public void remover(Medico medico){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		medico = manager.merge(medico);
		manager.remove(medico);
		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}

	/**
	 * @descricao: Lista os últimos médicos cadastrados no sistema.
	 * @return
	 */
	public List<Medico> recuperarUltimosCadastrados() {
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Medico> lista = null;
		try{
			Query query = manager.createQuery("from Medico order by id desc");
			query.setMaxResults(8);
			lista = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return lista;
	}

	/**
	 * @descricao: Recuperar todos os médicos.
	 * @return
	 */
	public List<Medico> recuperarTodos(){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Medico> lista = null;
		try{
			Query query = manager.createQuery("from Medico order by usuario.nome");
			lista = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return lista;
	}

	/**
	 * @descricao: Recupera o médico apartir do usuário.
	 * @param usuario
	 * @return
	 */
	public Medico recuperar(Usuario usuario){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Medico medico =  null;
		try{
			Query query = manager.createQuery("from Medico where usuario.id = :idUsuario");
			query.setParameter("idUsuario", usuario.getId());
			medico = (Medico) query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		return medico;
	}

	/**
	 * @descricao: Exclui as especialidades do médico.
	 * @param medico
	 */
	public void excluirEspecialidades(Medico medico){
		conexao = new Conexao();
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("delete from medico_especialidade where mdccod = :id");
		query.setParameter("id", medico.getId());
		query.executeUpdate();
		manager.getTransaction().commit();
	}
	
	/**
	 * @descricao: inseri especialidades no médico.
	 * @param medico
	 */
	public void inseriEspecialidades(Medico medico){
		conexao = new Conexao();
		manager.getTransaction().begin();
		
		for(EspecialidadeMedica esp: medico.getEspecialidades()){
			Query query = manager.createNativeQuery("insert into medico_especialidade (mdccod, emdcod) " +
					"values (:idMedico, :idEsp)");
			query.setParameter("idMedico", medico.getId());
			query.setParameter("idEsp", esp.getId());
			query.executeUpdate();
		}
		manager.getTransaction().commit();
		
	}
	
	/**
	 * @descricao: Limpa as tabelas para testes.
	 */
	public void removerTodosTeste() {
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		Query query = manager.createQuery("delete from Medico where id > 0");
		query.executeUpdate();
		manager.getTransaction().commit();

	}
}
