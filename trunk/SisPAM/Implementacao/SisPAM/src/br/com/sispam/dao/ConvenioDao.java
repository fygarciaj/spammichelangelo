package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Convenio;
import br.com.sispam.dominio.Usuario;


public class ConvenioDao {
	
	private Conexao conexao;
	private EntityManager manager;
	
	public void incluirConvenio(Convenio convenio){
		System.out.println(convenio.getNome());
		
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		manager.persist(convenio);
		manager.getTransaction().commit();
		
		conexao.finalizaConexao();
	}
	
	public void alterarConvenio(Convenio convenio){
		
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		manager.merge(convenio);
		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}
	
	public Convenio consultarConvenio(long cnpj){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Convenio convenio = null;
		try{
			//cria uma queri para fazer a busca pelo cnpj
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
}
