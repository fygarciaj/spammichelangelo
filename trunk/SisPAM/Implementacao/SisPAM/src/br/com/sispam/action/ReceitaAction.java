package br.com.sispam.action;

import br.com.sispam.facade.ConvenioFacade;
import br.com.sispam.facade.ReceitaFacade;

public class ReceitaAction extends Action{

	private String id;
	private ReceitaFacade receitaFacade;
	
	public String emitirReceita(String id){
		receitaFacade = new ReceitaFacade();
		
		
		return CARREGAR_CONVENIO_EXISTENTE;
	}

	
}
