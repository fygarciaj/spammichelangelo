package br.com.sispam.action;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.EspecialidadeMedica;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Paciente;
import br.com.sispam.enums.TipoAgendamento;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.AgendamentoFacade;
import br.com.sispam.facade.EspecialidadeFacade;
import br.com.sispam.facade.MedicoFacade;
import br.com.sispam.facade.PacienteFacade;
import br.com.sispam.facade.UsuarioFacade;
import br.com.sispam.util.DataUtil;

/**
 * Classe que recebe as informações da página referente aos Agendamentos.
 * @author laurindo
 *
 */
public class AgendamentoAction extends Action{

	private List<EspecialidadeMedica> especialidades;
	private List<Medico> medicos;
	private Agendamento agendamento;
	private String tipo;
	private TipoAgendamento[] tipoAgendamento = TipoAgendamento.values();
	private String dataAgendamento;
	private List<Paciente> pacientes;
	private List<Agendamento> agendamentos;

	private MedicoFacade medicoFacade;
	private EspecialidadeFacade especialidadeFacade;
	private AgendamentoFacade agendamentoFacade;
	private PacienteFacade pacienteFacade;
	private UsuarioFacade usuarioFacade;
	private Medico medico;
	private EspecialidadeMedica especialidadeMedica;
	private int isAgenda;
	private boolean formulario = false;
	private String horario;
	private String agendamentoRetornado;

	/**
	 * Define o tipo de agendamento.
	 * @return
	 */
	public String definirTipo(){

		if(this.tipo != null && this.tipo.equals("med")){
			this.medicoFacade = new MedicoFacade();
			this.medicos = this.medicoFacade.recuperarTodos();
		}else{
			this.especialidadeFacade = new EspecialidadeFacade();
			this.especialidades = this.especialidadeFacade.recuperarTodas();
		}

		return SUCESSO_CARREGAR_INCLUSAO_AGENDAMENTO;

	}
	
	/**
	 * Salva o agendamento.
	 * @return
	 */
	public String salvarAgendamento(){
		this.agendamentoFacade = new AgendamentoFacade();
		this.usuarioFacade = new UsuarioFacade();
		//monta um mapa com os campos que devem ser inteiros
		Map<String, String> camposInteiros = new HashMap<String, String>();
		if(horario != null){
			horario.split(":");
		}
		camposInteiros.put("horário", horario);

		try {
			this.agendamentoFacade.validaCampos(agendamento, dataAgendamento);
			this.agendamentoFacade.verificaDisponivilidade();
			this.usuarioFacade.verificaCampoInteiro(camposInteiros);
			this.agendamento.setHora(Integer.parseInt(horario));
			this.agendamentoFacade.salvarAgendamento(agendamento);
			this.mensagens.put("sucesso", "Agedamento solicitado com sucesso!");
		}catch (CampoInvalidoException e) {
			this.erros.put("erro", e.getMessage());
			return FALHA_SALVAR_AGENDMENTO;
		}
		limparCampos();
		apresentaMensagens();
		return SUCESSO_SALVAR_AGENDAMENTO;
	}
	
	/**
	 * Carrega a edição do agendamento.
	 * @return
	 */
	public String carregaEdicaoAgendamento(){
		this.agendamentoFacade = new AgendamentoFacade();
		this.pacienteFacade = new PacienteFacade();
		this.medicoFacade = new MedicoFacade();
		this.especialidadeFacade = new EspecialidadeFacade();
		
		this.agendamento = this.agendamentoFacade.recuperarPeloId(this.agendamento.getId());
		this.dataAgendamento = DataUtil.dateToString(this.agendamento.getData());
		this.horario = String.valueOf(this.agendamento.getHora());
		
		//monta as listas de pacientes, medicos e especialidades
		this.especialidades = this.especialidadeFacade.recuperarTodas();
		this.medicos = this.medicoFacade.recuperarTodos();
		this.pacientes = this.pacienteFacade.recuperarTodos();
		
		return SUCESSO_CARREGAR_EDICAO;
	}
	
	/**
	 * Carrega os agendamentos do dia.
	 * @return
	 */
	public String carregarAgendamentos(){
		this.agendamentoFacade = new AgendamentoFacade();
		this.medicoFacade = new MedicoFacade();
		this.agendamentos = this.agendamentoFacade.recuperarAgendamentosDoDia();
		this.agendamentoRetornado = DataUtil.dateToString(new Date());
		this.medicos = this.medicoFacade.recuperarTodos();
		return SUCESSO_CARREGAR_AGENDAMENTOS;
	}
	
	/**
	 * Exclui o agendamento passado
	 * @return
	 */
	public String excluirAgendamento(){
		this.agendamentoFacade = new AgendamentoFacade();
		this.agendamentoFacade.excluir(agendamento);
		return SUCESSO_EXCLUIR_AGENDAMENTO;
	}
	
	/**
	 * Realiza a consulta dos agendamentos.
	 * @return
	 */
	public String consultarAgendamento(){
		this.agendamentoFacade = new AgendamentoFacade();
		this.medicoFacade = new MedicoFacade();
		this.agendamentos = this.agendamentoFacade.consultar(this.agendamento, dataAgendamento);
		this.agendamentoFacade.montarAgendamentos(agendamentos);
		this.medicos = this.medicoFacade.recuperarTodos();
		limpaCamposConsulta();
		return SUCESSO_CARREGAR_AGENDAMENTOS;
	}
	
	/**
	 * Limpa os campos da consulta.
	 */
	private void limpaCamposConsulta(){
		this.agendamento = null;
		this.dataAgendamento = null;
	}

	/**
	 * Prepara a inclusão do agendamento
	 * @return
	 */
	public String preparaInclusao(){
		apresentaErrors();
		if(tipo.equals("med")){
			this.medicoFacade = new MedicoFacade();
			this.medico = this.medicoFacade.recuperar(this.agendamento.getMedico().getId());
			this.medicos = this.medicoFacade.recuperarTodos();
			this.medicoFacade.montaMedico(medico);
			this.agendamento.setMedico(this.medico);
			this.pacienteFacade = new PacienteFacade();
			this.pacientes = this.pacienteFacade.recuperarTodos();
			this.isAgenda = 1;
			this.formulario = true;
		}else{
			this.especialidadeFacade = new EspecialidadeFacade();
			this.especialidadeMedica =this.especialidadeFacade.recuperarPeloId(this.agendamento.getEspecialidadeMedica().getId());
			if(this.agendamento.getMedico() != null && this.agendamento.getMedico().getId() > 0){
				this.medicoFacade = new MedicoFacade();
				this.pacienteFacade = new PacienteFacade();
				this.pacientes = this.pacienteFacade.recuperarTodos();
				this.agendamento.setMedico(this.medicoFacade.recuperar(this.agendamento.getMedico().getId()));
				this.medicoFacade.montaMedico(this.agendamento.getMedico());
				this.isAgenda = 1;
				this.formulario = true;
			}
			this.agendamento.setEspecialidadeMedica(especialidadeMedica);
			this.especialidades = this.especialidadeFacade.recuperarTodas();
		}
		return SUCESSO_CARREGAR_INCLUSAO_AGENDAMENTO;
	}
	
	/**
	 * Limpa os campos da tela.
	 */
	private void limparCampos(){
		this.medico = null;
		this.pacientes = null;
		this.medicos = null;
		this.especialidades = null;
		this.especialidadeMedica = null;
		this.dataAgendamento = null;
		this.horario = null;
		this.tipo = null;
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
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public TipoAgendamento[] getTipoAgendamento() {
		return tipoAgendamento;
	}
	public void setTipoAgendamento(TipoAgendamento[] tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}
	public String getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public EspecialidadeMedica getEspecialidadeMedica() {
		return especialidadeMedica;
	}
	public void setEspecialidadeMedica(EspecialidadeMedica especialidadeMedica) {
		this.especialidadeMedica = especialidadeMedica;
	}
	public int getIsAgenda() {
		return isAgenda;
	}
	public void setIsAgenda(int isAgenda) {
		this.isAgenda = isAgenda;
	}
	public boolean isFormulario() {
		return formulario;
	}
	public void setFormulario(boolean formulario) {
		this.formulario = formulario;
	}
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}
	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public String getAgendamentoRetornado() {
		return agendamentoRetornado;
	}

	public void setAgendamentoRetornado(String agendamentoRetornado) {
		this.agendamentoRetornado = agendamentoRetornado;
	}
	
}
