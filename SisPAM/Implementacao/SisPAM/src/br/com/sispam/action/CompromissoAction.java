package br.com.sispam.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.Compromisso;
import br.com.sispam.dominio.Medico;
import br.com.sispam.enums.Acao;
import br.com.sispam.enums.Funcionalidade;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.TipoAgendamento;
import br.com.sispam.enums.TipoCompromisso;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.AuditoriaFacade;
import br.com.sispam.facade.CompromissoFacade;
import br.com.sispam.facade.MedicoFacade;
import br.com.sispam.util.AuditoriaUtil;
import br.com.sispam.util.DataUtil;


public class CompromissoAction extends Action{

	private Compromisso compromisso;
	private CompromissoFacade compromissoFacade;
	private MedicoFacade medicoFacade;
	private List<Compromisso> compromissosCadastrados;
	private List<Agendamento> agendamentosCadastrados;
	private List<Medico> medicos;
	private String nomMedico;
	private String horaInicialAux;
	private String horaFinalAux;
	private String dataAux;
	private TipoCompromisso[] tipoCompromisso = TipoCompromisso.values();
	private TipoAgendamento[] tipoAgendamento = TipoAgendamento.values();
	private AuditoriaFacade auditoriaFacade;

	/**
	 * Recebe os dados que vem da tela de inclusão de compromisso.
	 * @return
	 * @throws ParseException 
	 */
	public String incluirCompromisso(){
		compromissoFacade = new CompromissoFacade();
		medicoFacade =  new MedicoFacade();

		try {
			//Remove o caracter ":" da hora
			horaInicialAux = horaInicialAux.replaceAll("[:]", "");
			horaFinalAux = horaFinalAux.replaceAll("[:]", "");

			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("Hora Final", horaFinalAux);
			mapa.put("Hora Inicial", horaInicialAux);
			this.compromisso.setMedico(this.medicoFacade.recuperar(compromisso.getMedico().getId()));

			//verifica se os campo obrigatorios foram preenchidos
			compromissoFacade.validaCampos(compromisso, dataAux);
			
			//verifica se os campos são inteiros
			compromissoFacade.verificaCampoInteiro(mapa);
			
			//Valida Hora inicial e Final
			compromissoFacade.validaHora(horaInicialAux, horaFinalAux);
			
			//seta os valores das variváveis auxiliares.
			compromisso.setHoraInicial(Integer.parseInt(horaInicialAux));
			compromisso.setHoraFinal(Integer.parseInt(horaFinalAux));
		
			//verifica se já existe compromisso cadastrado com esses dados.
			compromissoFacade.verificaExistencia(compromisso, 0);

			if(compromisso.getId() > 0){
				compromissoFacade.salvaCompromisso(compromisso);
				mensagens.put("salvo", "Compromisso alterado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_AGENDA_MEDICO, Acao.ALTERACAO, getUsuarioLogado()));
			}else{
				compromissoFacade.salvaCompromisso(compromisso);
				mensagens.put("salvo", "Compromisso cadastrado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_AGENDA_MEDICO, Acao.INCLUSAO, getUsuarioLogado()));
			}

		}catch (CampoInvalidoException e) {
			erros.put("campoInvalido", e.getMessage());
			return FALHA_SALVAR_COMPROMISSO;
		}catch (CampoInteiroException e) {
			erros.put("campoInvalido", e.getMessage());
			return FALHA_SALVAR_COMPROMISSO;
		}
		return SUCESSO_SALVAR_COMPROMISSO;
	}

	/**
	 * Carrega os objetos necessários para fazer a inclusão de compromisso.
	 * @return
	 */
	public String carregarInclusao(){

		if(erros.size() == 0 ){
			limparCampos();
		}
		apresentaErrors();
		apresentaMensagens();
		if(this.getUsuarioLogado().getPerfil() == Perfil.MEDICO.getCodigo()){
			this.medicos = new ArrayList<Medico>();
			this.medicoFacade = new MedicoFacade();
			this.medicos.add(this.medicoFacade.recuperar(getUsuarioLogado()));
		}else{
			this.medicoFacade = new MedicoFacade();
			this.medicos = this.medicoFacade.recuperarTodos();
		}
		return CARREGAR_INCLUSAO_COMPROMISSO;
	}

	/**
	 * Carrega os dados para realizar a consulta de compromissos.
	 * @return
	 */
	public String carregarConsulta(){

		this.medicoFacade = new MedicoFacade();
		this.compromissoFacade = new CompromissoFacade();
		this.compromisso = new Compromisso();
		
		if(this.getUsuarioLogado().getPerfil() == Perfil.MEDICO.getCodigo()){
			this.medicos = new ArrayList<Medico>();
			this.medicos.add(this.medicoFacade.recuperar(getUsuarioLogado()));
			this.compromisso.setMedico(this.medicoFacade.recuperar(getUsuarioLogado()));

			if((compromissosCadastrados == null || compromissosCadastrados.size() == 0)&& (agendamentosCadastrados == null || agendamentosCadastrados.size()== 0)){
				this.compromisso.setData(new Date());
				this.compromissosCadastrados = this.compromissoFacade.recuperarCompromissosDiaAtual(compromisso);
				this.agendamentosCadastrados = this.compromissoFacade.getAgendamentos();
			}
		}else{
			this.medicos = this.medicoFacade.recuperarTodos();
		}
		montaAgendamentos(agendamentosCadastrados);
		montaCompromissos(compromissosCadastrados);
		limparData();
		return CARREGAR_CONSULTA_COMPROMISSO;
	}

	/**
	 * Recebe o compromisso a ser excluído.
	 * @return
	 */
	public String excluirCompromisso(){
		this.medicoFacade = new MedicoFacade();
		this.compromissoFacade = new CompromissoFacade();		
		try {
			this.compromissoFacade.excluiCompromisso(this.compromisso);
			mensagens.put("excluido", "Compromisso excluido com sucesso!");
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_AGENDA_MEDICO, Acao.EXCLUSAO, getUsuarioLogado()));
		} catch (Exception e) {
			e.printStackTrace();					
			return FALHA_EXCLUIR_COMPROMISSO;
		}
		apresentaMensagens();
		this.medicos = this.medicoFacade.recuperarTodos();
		
		return FALHA_EXCLUIR_COMPROMISSO;
	}

	/**
	 * Efetuar a consulta do compromisso.
	 * @return
	 */
	public String consultarCompromisso(){
		compromissoFacade= new CompromissoFacade();
		nomMedico = null;
		this.medicoFacade = new MedicoFacade();

		try {
			this.compromissosCadastrados = new ArrayList<Compromisso>();
			this.compromissosCadastrados = compromissoFacade.pesquisaCompromisso(compromisso);
			this.agendamentosCadastrados = compromissoFacade.getAgendamentos();
			this.nomMedico = this.medicoFacade.recuperar(compromisso.getMedico().getId()).getUsuario().getNome();		
			
			if(this.getUsuarioLogado().getPerfil() == Perfil.MEDICO.getCodigo()){
				this.medicos = new ArrayList<Medico>();
				this.medicos.add(this.medicoFacade.recuperar(getUsuarioLogado()));
			}else{
				this.medicos = this.medicoFacade.recuperarTodos();
			}
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_AGENDA_MEDICO, Acao.CONSULTA, getUsuarioLogado()));
		} catch (CampoInvalidoException e) {
			erros.put("erro", e.getMessage());		
		}
		apresentaErrors();
		apresentaMensagens();
		limparData();
		return carregarConsulta();

	}

	/**
	 * Carrega o compromisso para ser alterado.
	 * @return
	 */
	public String carregaEdicaoCompromisso(){
		this.compromissoFacade = new CompromissoFacade();
		this.medicoFacade = new MedicoFacade();
		this.compromisso = this.compromissoFacade.recuperarPeloId(compromisso.getId());
		this.dataAux = DataUtil.dateToString(this.compromisso.getData());
		this.horaInicialAux = this.compromisso.getHoraInicialFormatada();
		this.horaFinalAux = this.compromisso.getHoraFinalFormatada();
		if(this.getUsuarioLogado().getPerfil() == Perfil.MEDICO.getCodigo()){
			this.medicos = new ArrayList<Medico>();
			this.medicos.add(this.medicoFacade.recuperar(getUsuarioLogado()));
		}else{
			this.medicos = this.medicoFacade.recuperarTodos();
		}
		return SUCESSO_EDICAO_COMPROMISSO;
	}


	/*Utilitário*/
	/**
	 * Limpa os campos da tela.
	 */
	private void limparCampos(){
		this.compromisso = null;
		horaFinalAux = null;
		horaInicialAux = null;
		this.dataAux = null;
	}
	
	/**
	 * monta a exbição do agendamento.
	 * @param lista
	 * @return
	 */
	private List<Agendamento> montaAgendamentos(List<Agendamento> lista){
		if(lista != null && lista.size() > 0){
			for(Agendamento ag: lista){
				ag.setTipoAgendamento(TipoAgendamento.getTipoAgendamento(ag.getTipo()));
			}
		}
		return lista;
	}
	
	/**
	 * monta a exbição do compromisso.
	 * @param lista
	 * @return
	 */
	private List<Compromisso> montaCompromissos(List<Compromisso> lista){
		if(lista != null && lista.size() > 0){
			for(Compromisso comp: lista){
				comp.setTipoCompromisso(TipoCompromisso.getTipoCompromisso(Integer.parseInt(comp.getTipo())));
			}
		}
		return lista;
	}
	
	private void limparData(){
		this.compromisso.setData(null);
	}
	
	public Compromisso getCompromisso() {
		return compromisso;
	}
	public void setCompromisso(Compromisso compromisso) {
		this.compromisso = compromisso;
	}
	public CompromissoFacade getCompromissoFacade() {
		return compromissoFacade;
	}
	public void setCompromissoFacade(CompromissoFacade compromissoFacade) {
		this.compromissoFacade = compromissoFacade;
	}
	public List<Compromisso> getCompromissosCadastrados() {
		return compromissosCadastrados;
	}
	public void setCompromissosCadastrados(List<Compromisso> compromissosCadastrados) {
		this.compromissosCadastrados = compromissosCadastrados;
	}
	public List<Medico> getMedicos() {
		return medicos;
	}
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
	public String getHoraInicialAux() {
		return horaInicialAux;
	}
	public void setHoraInicialAux(String horaInicialAux) {
		this.horaInicialAux = horaInicialAux;
	}
	public String getHoraFinalAux() {
		return horaFinalAux;
	}
	public void setHoraFinalAux(String horaFinalAux) {
		this.horaFinalAux = horaFinalAux;
	}
	public String getDataAux() {
		return dataAux;
	}
	public void setDataAux(String dataAux) {
		this.dataAux = dataAux;
	}

	public TipoCompromisso[] getTipoCompromisso() {
		return tipoCompromisso;
	}

	public void setTipoCompromisso(TipoCompromisso[] tipoCompromisso) {
		this.tipoCompromisso = tipoCompromisso;
	}

	public MedicoFacade getMedicoFacade() {
		return medicoFacade;
	}

	public void setMedicoFacade(MedicoFacade medicoFacade) {
		this.medicoFacade = medicoFacade;
	}

	public AuditoriaFacade getAuditoriaFacade() {
		return auditoriaFacade;
	}

	public void setAuditoriaFacade(AuditoriaFacade auditoriaFacade) {
		this.auditoriaFacade = auditoriaFacade;
	}

	public List<Agendamento> getAgendamentosCadastrados() {
		return agendamentosCadastrados;
	}

	public void setAgendamentosCadastrados(List<Agendamento> agendamentosCadastrados) {
		this.agendamentosCadastrados = agendamentosCadastrados;
	}

	public TipoAgendamento[] getTipoAgendamento() {
		return tipoAgendamento;
	}

	public void setTipoAgendamento(TipoAgendamento[] tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}

	public String getNomMedico() {
		return nomMedico;
	}

	public void setNomMedico(String nomMedico) {
		this.nomMedico = nomMedico;
	}
	
	
}
