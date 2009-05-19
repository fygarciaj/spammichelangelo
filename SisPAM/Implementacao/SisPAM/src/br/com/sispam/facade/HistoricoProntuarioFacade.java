package br.com.sispam.facade;

import java.util.List;


import br.com.sispam.dao.HistoricoProntuarioDao;
import br.com.sispam.dominio.Agendamento;


public class HistoricoProntuarioFacade {
	private HistoricoProntuarioDao historicoProntuarioDao;
	
	/**
	 * @descricao: Recupera os agendamentos do médico.
	 * @param agendamento
	 * @return List<Agendamento> 
	 */
	public List<Agendamento> recuperarAgendamentosDiaAtual(Agendamento agendamento){
		this.historicoProntuarioDao = new HistoricoProntuarioDao();
		return this.historicoProntuarioDao.consultarAgendamento(agendamento);
	}
}
