package br.com.sispam.action;

import java.util.List;

import br.com.sispam.dominio.Paciente;

import br.com.sispam.facade.ProntuarioFacade;


public class ProntuarioAction extends Action{

	private List<Paciente> pacientes;
	private ProntuarioFacade prontuarioFacade;
	
	/**
	 * Carrega Pacientes.
	 * @return
	 */
	public String carregarPacientes(){
		
		this.prontuarioFacade = new ProntuarioFacade();
		//monta a listas de pacientes		
		this.pacientes = this.prontuarioFacade.recuperarTodos();

		return SUCESSO_CARREGAR_PACIENTES;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public ProntuarioFacade getProntuarioFacade() {
		return prontuarioFacade;
	}

	public void setProntuarioFacade(ProntuarioFacade prontuarioFacade) {
		this.prontuarioFacade = prontuarioFacade;
	}
	
	
}
