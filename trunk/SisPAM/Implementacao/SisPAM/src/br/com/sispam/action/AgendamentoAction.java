package br.com.sispam.action;


import java.util.List;

import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.EspecialidadeMedica;
import br.com.sispam.dominio.Medico;
import br.com.sispam.facade.EspecialidadeFacade;
import br.com.sispam.facade.MedicoFacade;

public class AgendamentoAction extends Action{
	
	private List<EspecialidadeMedica> especialidades;
	private List<Medico> medicos;
	private Agendamento agendamento;
	private String tipo;
	
	private MedicoFacade medicoFacade;
	private EspecialidadeFacade especialidadeFacade;
	
	/**
	 * @descricao: Carrega a tela de inclusão de agendamento com as especialidades para escolha.
	 * @return
	 */
	public String carregarInclusao(){
		this.especialidadeFacade = new EspecialidadeFacade();
		this.medicoFacade = new MedicoFacade();
		this.especialidades = this.especialidadeFacade.recuperarTodas();
		this.medicos = this.medicoFacade.recuperarTodos();
		return SUCESSO_CARREGAR_INCLUSAO_AGENDAMENTO;
	}
	
	/**
	 * @descricao: Prepara a inclusão do agendamento
	 * @return
	 */
	public String preparaInclusao(){
		
		return null;
	}

	public List<EspecialidadeMedica> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadeMedica> especialidades) {
		this.especialidades = especialidades;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
