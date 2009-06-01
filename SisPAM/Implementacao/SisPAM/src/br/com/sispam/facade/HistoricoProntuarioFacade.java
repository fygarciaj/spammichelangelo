package br.com.sispam.facade;

import java.util.List;


import br.com.sispam.dao.AgendamentoDao;
import br.com.sispam.dao.CodigoDoencaDao;
import br.com.sispam.dao.HistoricoProntuarioDao;
import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.HistoricoProntuario;
import br.com.sispam.enums.StatusAgendamento;
import br.com.sispam.excecao.CampoInvalidoException;


public class HistoricoProntuarioFacade {
	private HistoricoProntuarioDao historicoProntuarioDao;
	private AgendamentoDao agendamentoDao;
	
	/**
	 * : Recupera os agendamentos do médico.
	 * @param agendamento
	 * @return List<Agendamento> 
	 */
	public List<Agendamento> recuperarAgendamentosDiaAtual(Agendamento agendamento){
		this.historicoProntuarioDao = new HistoricoProntuarioDao();
		return this.historicoProntuarioDao.consultarAgendamento(agendamento);
	}
	
	/**
	 * : Salva historico de prontuario e atualiza o status do agendamento para CONCLUIDO(2)
	 * @param historicoProntuario, agendamento
	 * @throws CampoInvalidoException
	 */
	public void atualizaHistoricoProntuario(HistoricoProntuario historicoProntuario, Agendamento agendamento) throws CampoInvalidoException{

		try{
			historicoProntuarioDao = new HistoricoProntuarioDao();					
			agendamentoDao = new AgendamentoDao();
			CodigoDoencaDao codigoDoencaDao = new CodigoDoencaDao();
			agendamento = agendamentoDao.recuperarAgendamento(agendamento.getId());
			historicoProntuario.setCodigoDoenca(codigoDoencaDao.recuperarCodigoDoenca(historicoProntuario.getCodigoDoenca().getId()));
			historicoProntuarioDao.atualizarHistorioProntuario(historicoProntuario);

			//Seta status do agendamento para CONCLUIDO
			agendamento.setStatus(StatusAgendamento.CONCLUIDO.getCodigo());
			agendamentoDao.incluirAgendamento(agendamento);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * : Recupera o agendamento pelo seu id.
	 * @param id
	 */
	public Agendamento recuperarAgendamento(int id){
		this.agendamentoDao = new AgendamentoDao();
		Agendamento agendamento = null; 
		if(id != 0){			
		agendamento = this.agendamentoDao.recuperarAgendamento(id);
		}
		return agendamento;
	}
	
	/**
	 * : valida os campos do historico de prontuário
	 * @param historicoProntuario
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(HistoricoProntuario historicoProntuario) throws CampoInvalidoException{

		if(historicoProntuario != null){

			if(historicoProntuario.getSintoma() == null || historicoProntuario.getSintoma().length() == 0){
				throw new CampoInvalidoException("Campo Sintoma tem preenchimento obrigatório");
			}
			if(historicoProntuario.getLaudo() == null || historicoProntuario.getLaudo().length() == 0){
				throw new CampoInvalidoException("Campo Laudo tem preenchimento obrigatório");
			}
			if(historicoProntuario.getPrescricao() == null || historicoProntuario.getPrescricao().length() == 0){
				throw new CampoInvalidoException("Campo Prescrição tem preenchimento obrigatório");
			}							
			
		}
	}	
}
