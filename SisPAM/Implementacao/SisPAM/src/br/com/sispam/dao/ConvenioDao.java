package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Convenio;


public class ConvenioDao {
	
	private Conexao conexao;
	private EntityManager manager;
	
	/**
	 * : Incluir o convênio no banco de dados.
	 * @param convenio
	 */
	public void incluirConvenio(Convenio convenio){		
		
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		
		//verifica se possui id caso possua apenas atualiza os dados no banco
		if(convenio != null && convenio.getId() > 0){
			manager.merge(convenio);
		}
		//caso não, salva um novo convênio
		else{
			manager.persist(convenio);
		}
		
		manager.getTransaction().commit();

	}
	
	/**
	 * : excluir o convênio do banco de dados.
	 * @param cnpj
	 * @throws EntityExistsException
	 */
	public void excluirConvenio(String cnpj) throws EntityExistsException{
				
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		try{
			manager.getTransaction().begin();
			
			Query query = manager.createQuery("delete from Convenio where cnpj = :cnpj");
			//seta o parametro
			query.setParameter("cnpj", cnpj);
			query.executeUpdate();
			manager.getTransaction().commit();
		}catch(EntityExistsException e){
			e.printStackTrace();
			throw new EntityExistsException("Atenção! Pacientes vinculados a esse convênio, não permitindo a sua exclusao.");
		}
	
	}
	
	/**
	 * : Recupera o convênio pelo ID.
	 * @param id
	 * @return
	 */
	public Convenio recuperarPeloId(int id){
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		return manager.find(Convenio.class, id);
	}
	
	/**
	 * : Recupera o convênio pelo CNPJ.
	 * @param cnpj
	 * @return
	 */
	public Convenio consultarConvenioPorCnpj(String cnpj){
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		Convenio convenio = null;
		try{
			//cria uma query para fazer a busca pelo cnpj
			Query query = manager.createQuery("from Convenio where cnpj = :cnpj ");
			//seta o parametro
			query.setParameter("cnpj", cnpj);
			convenio = (Convenio) query.getSingleResult();						
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		return convenio;
	}
	
	/**
	 * : Consulta o convênio pelo nome.
	 * @param nome
	 * @return
	 */
	public List<Convenio> consultarConvenioPorDescricao(String nome){
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		List<Convenio> convenios = null;
		try{
			//cria uma queri para fazer a busca pelo nome
			Query query = manager.createQuery("from Convenio where nome like :nome ");
			//seta o parametro
			query.setParameter("nome", nome+"%");
			convenios = query.getResultList();				
		}catch (NoResultException e) {
			e.printStackTrace();
			e.getMessage();
		}
		return convenios;
	}	
	
	/**
	 * : Lista os últimos convênios cadastrados no sistema.
	 * @return
	 */
	public List<Convenio> recuperarUltimosCadastrados() {
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		List<Convenio> lista = null;
		try{
			Query query = manager.createQuery("from Convenio order by id desc");
			query.setMaxResults(8);
			lista = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/**
	 * : Recupera todos os convênios cadastrados.
	 * @return
	 */
	public List<Convenio> recuperarTodos(){
		conexao = Conexao.getConexao();
		manager = conexao.getEntityManger();
		List<Convenio> convenios = null;
		try{
			Query query = manager.createQuery("from Convenio order by nome asc");
			convenios = query.getResultList();
		}catch (NoResultException e) {
			convenios = null;
		}
		return convenios;
	}
}
