package br.com.sispam.facade;

import br.com.sispam.dao.ConvenioDao;


import br.com.sispam.dominio.Convenio;



public class ConvenioFacade {
	private ConvenioDao convenioDao; 
		
	public boolean verificaCnpj(Convenio convenio){
		convenioDao = new ConvenioDao();
		//definir validacao
		convenioDao.incluirConvenio(convenio);
		return true;
	
	}
}
