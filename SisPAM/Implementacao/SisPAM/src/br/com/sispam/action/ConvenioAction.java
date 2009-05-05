package br.com.sispam.action;

import br.com.sispam.dominio.Convenio;
import br.com.sispam.facade.ConvenioFacade;

public class ConvenioAction extends Action{

	private Convenio convenio;
	private ConvenioFacade convenioFacade;
	private int isExisteConvenio;
	
	public String incluirConvenio(){
		convenioFacade = new ConvenioFacade();
		convenioFacade.verificaCnpj(convenio);
		return SUCESSO_INCLUIR_CONVENIO;
	}
	
	public String consultarConvenio(){
		convenioFacade = new ConvenioFacade();
		this.convenio = convenioFacade.pesquisaConvenio(convenio);
		if(this.convenio != null){
			this.isExisteConvenio = 2;
		}
		else{
			this.isExisteConvenio = 1;
		}
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

	public int isExisteConvenio() {
		return isExisteConvenio;
	}

	public void setExisteConvenio(int isExisteConvenio) {
		this.isExisteConvenio = isExisteConvenio;
	}
	
	
}
