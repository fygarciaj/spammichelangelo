package br.com.sispam.action;

import java.util.Date;
import java.util.List;

import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.HistoricoProntuario;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.StatusAgendamento;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.HistoricoProntuarioFacade;
import br.com.sispam.facade.MedicoFacade;
import br.com.sispam.facade.ReceitaFacade;

public class ReceitaAction extends Action{
	
	private Agendamento agendamento;
	private ReceitaFacade receitaFacade;
	private List<Agendamento> agendamentosCadastrados;
	private MedicoFacade medicoFacade;
	private String idAux;
	private Date dataAtendimentoAux;
	private int horaAtendimentoAux;
	
	
	/**
	 * : Carrega o agendamento do dia, caso seja médico recupera dele mesmo. 
	 * @return
	 */
	public String carregarAgendamentos(){
		this.receitaFacade = new ReceitaFacade();
		this.agendamento = new Agendamento();
		this.medicoFacade = new MedicoFacade();
		
		if(this.getUsuarioLogado().getPerfil() == Perfil.MEDICO.getCodigo()){
			this.agendamento.setMedico(this.medicoFacade.recuperar(getUsuarioLogado()));

			//Seta status CONCLUIDO(2) e data atual para buscar agendamentos
			this.agendamento.setStatus(StatusAgendamento.CONCLUIDO.getCodigo());
			this.agendamento.setData(new Date());
			this.agendamentosCadastrados = this.receitaFacade.recuperarAgendamentosDiaAtual(agendamento);
			
		}
		apresentaMensagens();
		return SUCESSO_CARREGAR_ATENDIMENTOS;
	}
	
	/**
	 * : carrega os dados necessário para a emissão da receita do paciente.
	 * @return
	 */
	public String carregaEmissaoReceita(){
		this.receitaFacade = new ReceitaFacade();
		this.agendamento = this.receitaFacade.recuperarAgendamento(agendamento.getId());
		return SUCESSO_EMISSAO_RECEITA;
	}

	/*Utilitário*/
	/**
	 * : Limpa os campos.
	 */
	private void limparCampos(){
		this.receitaFacade = null;
		
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public ReceitaFacade getReceitaFacade() {
		return receitaFacade;
	}

	public void setReceitaFacade(ReceitaFacade receitaFacade) {
		this.receitaFacade = receitaFacade;
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

	public String getIdAux() {
		return idAux;
	}

	public void setIdAux(String idAux) {
		this.idAux = idAux;
	}

	public Date getDataAtendimentoAux() {
		return dataAtendimentoAux;
	}

	public void setDataAtendimentoAux(Date dataAtendimentoAux) {
		this.dataAtendimentoAux = dataAtendimentoAux;
	}

	public int getHoraAtendimentoAux() {
		return horaAtendimentoAux;
	}

	public void setHoraAtendimentoAux(int horaAtendimentoAux) {
		this.horaAtendimentoAux = horaAtendimentoAux;
	}
	
	
}
