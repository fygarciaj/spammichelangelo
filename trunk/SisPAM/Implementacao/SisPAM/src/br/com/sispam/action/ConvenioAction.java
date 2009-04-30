package br.com.sispam.action;

import br.com.sispam.dominio.Convenio;
import br.com.sispam.facade.ConvenioFacade;

public class ConvenioAction extends Action{

	public Convenio convenio;
	public ConvenioFacade convenioFacade;
	
	public String incluirConvenio(){
		convenioFacade = new ConvenioFacade();
		
		return SUCESSO_INCLUIR_CONVENIO;
	}
	
	public String consultarConvenio(){
		convenioFacade = new ConvenioFacade();
		
		return CARREGAR_CONVENIO_EXISTENTE;
	}
}
