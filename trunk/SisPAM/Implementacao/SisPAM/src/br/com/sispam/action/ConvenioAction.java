package br.com.sispam.action;

import br.com.sispam.dominio.Convenio;
import br.com.sispam.facade.ConvenioFacade;

public class ConvenioAction extends Action{

	private Convenio convenio;
	private ConvenioFacade convenioFacade;
	
	public String incluirConvenio(){
		convenioFacade = new ConvenioFacade();
		
		return SUCESSO_INCLUIR_CONVENIO;
	}
	
	public String consultarConvenio(){
		convenioFacade = new ConvenioFacade();
		
		return CARREGAR_CONVENIO_EXISTENTE;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public ConvenioFacade getConvenioFacade() {
		return convenioFacade;
	}

	public void setConvenioFacade(ConvenioFacade convenioFacade) {
		this.convenioFacade = convenioFacade;
	}
	
}
