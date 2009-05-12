package br.com.sispam.action;

import br.com.sispam.facade.ConvenioFacade;

public class ReceitaAction extends Action{

	private Paciente paciente;
	private ReceitaFacade receitaFacade;
	
	public String emitirReceita(paciente){
		receitaFacade = new ReceitaFacade();
		this.paciente = receitaFacade.emiteReceita(paciente);
		
		return CARREGAR_CONVENIO_EXISTENTE;
	}

	
}
