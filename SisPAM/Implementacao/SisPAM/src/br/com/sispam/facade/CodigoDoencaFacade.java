package br.com.sispam.facade;

import java.util.List;

import br.com.sispam.dao.CodigoDoencaDao;
import br.com.sispam.dominio.CodigoDoenca;


public class CodigoDoencaFacade {
	private CodigoDoencaDao codigoDoencaDao;
	
	public CodigoDoencaFacade(){
		this.codigoDoencaDao = new CodigoDoencaDao();
	}
	
	/**
	 * @descricao: Recupera todos os CIDs.
	 * @return
	 */
	public List<CodigoDoenca>recuperarTodas(){
		return codigoDoencaDao.recuperarTodas();
	}
}
