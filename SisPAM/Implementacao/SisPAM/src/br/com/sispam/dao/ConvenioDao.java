package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Convenio;
import br.com.sispam.dominio.Usuario;


public class ConvenioDao {
	
	private Conexao conexao;
	private EntityManager manager;
	
	public void incluirConvenio(Convenio convenio){		
		
		conexao = new Conexao();
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
		conexao.finalizaConexao();
	}
	
	public void excluirConvenio(String cnpj) throws EntityExistsException{
				
		conexao = new Conexao();
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
		conexao.finalizaConexao();
	}
	
	public Convenio recuperarPeloId(int id){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		return manager.find(Convenio.class, id);
	}
	
	public Convenio consultarConvenioPorCnpj(String cnpj){
		conexao = new Conexao();
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
		conexao.finalizaConexao();		
		return convenio;
	}
	
	public List<Convenio> consultarConvenioPorDescricao(String nome){
		conexao = new Conexao();
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
		conexao.finalizaConexao();		
		return convenios;
	}	
	
	/**
	 * @descricao: Lista os últimos convênios cadastrados no sistema.
	 * @return
	 */
	public List<Convenio> recuperarUltimosCadastrados() {
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Convenio> lista = null;
		try{
			Query query = manager.createQuery("from Convenio order by id desc");
			query.setMaxResults(8);
			lista = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return lista;
	}
	
	/**
	 * @descricao: Recupera todos os convênios cadastrados.
	 * @return
	 */
	public List<Convenio> recuperarTodos(){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Convenio> convenios = null;
		try{
			Query query = manager.createQuery("from Convenio order by nome asc");
			convenios = query.getResultList();
		}catch (NoResultException e) {
			convenios = null;
		}
		conexao.finalizaConexao();
		return convenios;
	}
}
