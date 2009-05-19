package br.com.sispam.action;

import java.util.Date;
import java.util.List;

import br.com.sispam.dominio.Agendamento;
import br.com.sispam.enums.Perfil;
import br.com.sispam.facade.HistoricoProntuarioFacade;
import br.com.sispam.facade.MedicoFacade;

public class HistoricoProntuarioAction extends Action{
	
	private Agendamento agendamento;
	private HistoricoProntuarioFacade historicoProntuarioFacade;
	private List<Agendamento> agendamentosCadastrados;
	private MedicoFacade medicoFacade;
	
	public String carregarAgendamentos(){
		this.historicoProntuarioFacade = new HistoricoProntuarioFacade();
		this.agendamento = new Agendamento();
		this.medicoFacade = new MedicoFacade();
		
		if(this.getUsuarioLogado().getPerfil() == Perfil.MEDICO.getCodigo()){
			this.agendamento.setMedico(this.medicoFacade.recuperar(getUsuarioLogado()));
			//laurindo criar enum
			this.agendamento.setStatus(1);
			this.agendamento.setData(new Date());
			this.agendamentosCadastrados = historicoProntuarioFacade.recuperarAgendamentosDiaAtual(agendamento);
			
		}
		return CARREGAR_CONSULTA_AGENDAMENTO;
	}
	
	
}
