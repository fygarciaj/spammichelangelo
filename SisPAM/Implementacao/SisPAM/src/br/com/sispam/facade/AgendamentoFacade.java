package br.com.sispam.facade;

import java.text.ParseException;
import java.util.List;

import javax.persistence.RollbackException;

import br.com.sispam.dao.AgendamentoDao;
import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.Compromisso;
import br.com.sispam.dominio.Medico;
import br.com.sispam.enums.StatusAgendamento;
import br.com.sispam.enums.TipoAgendamento;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.DataUtil;

public class AgendamentoFacade {
	private AgendamentoDao agendamentoDao; 

	public AgendamentoFacade() {
		this.agendamentoDao = new AgendamentoDao();
	}

	/**
	 * : Salva o agendamento.
	 * @param agendamento
	 */
	public void salvarAgendamento(Agendamento agendamento){
		this.agendamentoDao.incluirAgendamento(agendamento);
	}

	/**
	 * : Recupera o {@link Agendamento} pelo seu Id.
	 * @param id
	 * @return
	 */
	public Agendamento recuperarPeloId(int id){
		return this.agendamentoDao.recuperarAgendamento(id);
	}

	/**
	 * : Retorna todos os agendamentos do dia.
	 * @return
	 */
	public List<Agendamento>recuperarAgendamentosDoDia(){
		List<Agendamento> lista = agendamentoDao.recuperarAgendamentosDoDia();
		montarAgendamentos(lista);
		return lista;
	}

	/**
	 * : retorna a consulta realizada pelo paciente.
	 * @param agendamento
	 * @param data
	 * @return
	 * @throws CampoInvalidoException 
	 * @throws CampoInvalidoException
	 */
	public List<Agendamento> consultar(Agendamento agendamento, String data, int idUsuario) throws CampoInvalidoException{

		if(agendamento != null){
			try {
				agendamento.setData(DataUtil.stringToDate(data));
			} catch (ParseException e) {
				throw new CampoInvalidoException("Informe a data para a consulta!");
			}
			return this.agendamentoDao.consultar(agendamento, idUsuario);
		}else{
			return null;
		}

	}

	/**
	 * : retorna a consulta realizada pelo usuário.
	 * @param agendamento
	 * @param data
	 * @return
	 * @throws CampoInvalidoException 
	 * @throws CampoInvalidoException
	 */
	public List<Agendamento> consultar(Agendamento agendamento, String data) throws CampoInvalidoException{

		if(agendamento != null){
			try {
				agendamento.setData(DataUtil.stringToDate(data));
			} catch (ParseException e) {
				throw new CampoInvalidoException("Informe a data para a consulta!");
			}
			return this.agendamentoDao.consultar(agendamento);
		}else{
			return null;
		}

	}

	/**
	 * : preenche os objetos da lista de agendamento.
	 * @param agendamentos
	 */
	public void montarAgendamentos(List<Agendamento> agendamentos){
		if(agendamentos != null && agendamentos.size() > 0){

			for (Agendamento agendamento : agendamentos) {
				agendamento.setStatusAgendamento(StatusAgendamento.getStatusAgendamento(agendamento.getStatus()));
				agendamento.setTipoAgendamento(TipoAgendamento.getTipoAgendamento(agendamento.getTipo()));
			}

		}
	}

	public void excluir(Agendamento agendamento) throws CampoInvalidoException{
		Agendamento agendamento2 = this.agendamentoDao.recuperarAgendamento(agendamento.getId());
		try{
			this.agendamentoDao.excluir(agendamento2);
		}catch (RollbackException e) {
			throw new CampoInvalidoException("Agendamento já realizado não pode ser excluído!");
		}
	}

	/**
	 * : Valida os dados do agendamento passado.
	 * @param agendamento
	 * @param data
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Agendamento agendamento, String data) throws CampoInvalidoException{

		if(data == null || data.isEmpty()){
			throw new CampoInvalidoException("Data é campo obrigatório!");
		}
		if(agendamento.getPaciente() == null || agendamento.getPaciente().getId() == 0){
			throw new CampoInvalidoException("Selecione um paciente para o agendamento!");
		}
		if(agendamento.getMedico() == null || agendamento.getMedico().getId() == 0){
			throw new CampoInvalidoException("Selecione o médico para o agendamento!");
		}
		if(agendamento.getEspecialidadeMedica() == null || agendamento.getEspecialidadeMedica().getId() == 0){
			throw new CampoInvalidoException("Selecione a especialidade para o agendamento");
		}
		if(agendamento.getTipo() == 0){
			throw new CampoInvalidoException("Selecione o tipo de agendamento!");
		}
		try {
			agendamento.setData(DataUtil.stringToDate(data));
			agendamento.setStatus(StatusAgendamento.SOLICITADO.getCodigo());
		} catch (ParseException e) {
			new CampoInvalidoException("Data inválida! use o calendário ou digite no formato DD/MM/AAAA.");
		}

		if(agendamento.getHora() > 2359){
			throw new CampoInvalidoException("Hora Inicial deve ser menor ou igual a 23:59!");
		}
		
		MedicoFacade medicoFacade = new MedicoFacade();
		medicoFacade.verificaDiasDeTrabalhoDoMedico(agendamento);
	}
	
	

	/**
	 * Recupera os agendamentos do paciente.
	 * @param id
	 * @return
	 */
	public List<Agendamento> recuperaAgendamentosPaciente(int id){
		List<Agendamento> lista = this.agendamentoDao.recuperarAgendamentoPaciente(id);

		if(lista != null && lista.size() > 0){
			for(Agendamento ag: lista){
				ag.setTipoAgendamento(TipoAgendamento.getTipoAgendamento(ag.getTipo()));
			}
		}
		return lista;
	}

	public void verificaDisponivilidade(Agendamento agendamento)throws CampoInvalidoException{
		CompromissoFacade compromissoFacade = new CompromissoFacade();
		Compromisso compromisso = new Compromisso();
		if(agendamento != null){
			compromisso.setData(agendamento.getData());
			compromisso.setHoraInicial(agendamento.getHora());
			compromisso.setHoraFinal(agendamento.getHora()+15);
			compromisso.setMedico(agendamento.getMedico());
			compromissoFacade.verificaExistencia(compromisso);
		}
	}
}
