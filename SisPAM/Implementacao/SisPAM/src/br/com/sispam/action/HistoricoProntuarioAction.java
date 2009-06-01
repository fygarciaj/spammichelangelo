package br.com.sispam.action;

import java.util.Date;
import java.util.List;

import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.CodigoDoenca;
import br.com.sispam.dominio.HistoricoProntuario;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.StatusAgendamento;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.CodigoDoencaFacade;
import br.com.sispam.facade.HistoricoProntuarioFacade;
import br.com.sispam.facade.MedicoFacade;

public class HistoricoProntuarioAction extends Action{
	private HistoricoProntuario historicoProntuario;
	private Agendamento agendamento;
	private HistoricoProntuarioFacade historicoProntuarioFacade;
	private List<Agendamento> agendamentosCadastrados;
	private MedicoFacade medicoFacade;
	private List<CodigoDoenca> codigosDoencas;
	private CodigoDoencaFacade codigoDoencaFacade;
	
	/**
	 * : Carrega o agendamento do dia, caso seja médico recupera dele mesmo. 
	 * @return
	 */
	public String carregarAgendamentos(){
		this.historicoProntuarioFacade = new HistoricoProntuarioFacade();
		this.agendamento = new Agendamento();
		this.medicoFacade = new MedicoFacade();
		
		if(this.getUsuarioLogado().getPerfil() == Perfil.MEDICO.getCodigo()){
			this.agendamento.setMedico(this.medicoFacade.recuperar(getUsuarioLogado()));

			//Seta status SOLICITADO(0) e data atual para buscar agendamentos
			this.agendamento.setStatus(StatusAgendamento.SOLICITADO.getCodigo());
			this.agendamento.setData(new Date());
			this.agendamentosCadastrados = this.historicoProntuarioFacade.recuperarAgendamentosDiaAtual(agendamento);
			if (agendamentosCadastrados.size() == 0){
				erros.put("vazio", "Desculpe Dr., Sem agendamentos para atender!");
			}
		}
		apresentaMensagens();
		return CARREGAR_CONSULTA_AGENDAMENTO;
	}
	
	/**
	 * @descriao: Atualiza o prontuário do paciente.
	 * @return
	 */
	public String atualizarHistoricoProntuario(){
		
		
		try {
			this.historicoProntuarioFacade = new HistoricoProntuarioFacade();
			
			//verifica campos obrigatorios
			historicoProntuarioFacade.validaCampos(historicoProntuario);
			
			historicoProntuarioFacade.atualizaHistoricoProntuario(historicoProntuario, agendamento);
			mensagens.put("salvo", "Histórico de Prontuário atualizado com sucesso!");
		} catch (CampoInvalidoException e) {			
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			this.agendamento.setId(this.agendamento.getId());
			return carregaAtualizacaoHistorico();
		}
		
	
		limparCampos();
		return ATUALIZAR_HISTORICO_PRONTUARIO;
	}
	
	/**
	 * : carrega os dados necessário para a atualização do prontuário do paciente.
	 * @return
	 */
	public String carregaAtualizacaoHistorico(){
		this.historicoProntuarioFacade = new HistoricoProntuarioFacade();
		this.codigoDoencaFacade = new CodigoDoencaFacade();
		this.agendamento = this.historicoProntuarioFacade.recuperarAgendamento(agendamento.getId());
		this.codigosDoencas = this.codigoDoencaFacade.recuperarTodas();
		return SUCESSO_ATUALIZACAO_HISTORICO_PRONTUARIO;
	}

	/*Utilitário*/
	/**
	 * : Limpa os campos.
	 */
	private void limparCampos(){
		this.historicoProntuario = null;
		
	}
	
	public HistoricoProntuario getHistoricoProntuario() {
		return historicoProntuario;
	}

	public void setHistoricoProntuario(HistoricoProntuario historicoProntuario) {
		this.historicoProntuario = historicoProntuario;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public HistoricoProntuarioFacade getHistoricoProntuarioFacade() {
		return historicoProntuarioFacade;
	}

	public void setHistoricoProntuarioFacade(
			HistoricoProntuarioFacade historicoProntuarioFacade) {
		this.historicoProntuarioFacade = historicoProntuarioFacade;
	}

	public List<Agendamento> getAgendamentosCadastrados() {
		return agendamentosCadastrados;
	}

	public void setAgendamentosCadastrados(List<Agendamento> agendamentosCadastrados) {
		this.agendamentosCadastrados = agendamentosCadastrados;
	}

	public MedicoFacade getMedicoFacade() {
		return medicoFacade;
	}

	public void setMedicoFacade(MedicoFacade medicoFacade) {
		this.medicoFacade = medicoFacade;
	}



	public List<CodigoDoenca> getCodigosDoencas() {
		return codigosDoencas;
	}

	public void setCodigosDoencas(List<CodigoDoenca> codigosDoencas) {
		this.codigosDoencas = codigosDoencas;
	}

	public CodigoDoencaFacade getCodigoDoencaFacade() {
		return codigoDoencaFacade;
	}

	public void setCodigoDoencaFacade(CodigoDoencaFacade codigoDoencaFacade) {
		this.codigoDoencaFacade = codigoDoencaFacade;
	}
	
	
	
}
