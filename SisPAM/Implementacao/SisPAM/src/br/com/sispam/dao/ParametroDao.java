package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.HistoricoProntuario;
import br.com.sispam.dominio.Parametro;


public class ParametroDao {

	private Conexao conexao;
	private EntityManager manager;
	
	/**
	 * @descricao: Recupera todos os parâmetros cadastrados.
	 * @return
	 */
	public List<Parametro> recuperarTodos(){
		this.conexao = Conexao.getConexao();
		this.manager = conexao.getEntityManger();
		List<Parametro> parametros = null;
		try{
		Query query = this.manager.createQuery("from Parametro");
		parametros = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
			parametros = null;
		}
		return parametros;
	}
	
	/**
	 * @descricao: atualiza parametro
	 * @param parametro
	 */
	public void atualizarParametro(Parametro parametro){		
		
		
	}
}
