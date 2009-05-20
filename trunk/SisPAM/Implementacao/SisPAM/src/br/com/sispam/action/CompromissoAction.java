package br.com.sispam.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dominio.Compromisso;
import br.com.sispam.dominio.Medico;
import br.com.sispam.enums.Perfil;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.CompromissoFacade;
import br.com.sispam.facade.MedicoFacade;
import br.com.sispam.util.DataUtil;


public class CompromissoAction extends Action{

	private Compromisso compromisso;
	private CompromissoFacade compromissoFacade;
	private MedicoFacade medicoFacade;
	private List<Compromisso> compromissosCadastrados;
	private List<Medico> medicos;
	private String horaInicialAux;
	private String horaFinalAux;
	private String dataAux;

	public String incluirCompromisso(){
		compromissoFacade = new CompromissoFacade();
		medicoFacade =  new MedicoFacade();

		try {
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("horaInicial", horaInicialAux);
			mapa.put("horaFinal", horaFinalAux);
			this.compromisso.setMedico(this.medicoFacade.recuperar(compromisso.getMedico().getId()));

			//verifica se os campos são inteiros
			compromissoFacade.verificaCampoInteiro(mapa);

			//seta os valores das variváveis auxiliares.
			compromisso.setHoraInicial(Integer.parseInt(horaInicialAux));
			compromisso.setHoraFinal(Integer.parseInt(horaFinalAux));
			try {
				compromisso.setData(DataUtil.stringToDate(dataAux));
			} catch (ParseException e) {
				erros.put("campoInvalido", "Data inválida!");
				apresentaErrors();
				return FALHA_SALVAR_COMPROMISSO;
			}

			//verifica se os campo obrigatorios foram preenchidos
			compromissoFacade.validaCampos(compromisso);

			//verifica se já existe compromisso cadastrado com esses dados.
	//		compromissoFacade.verificaExistencia(compromisso);

			compromissoFacade.salvaCompromisso(compromisso);
			mensagens.put("salvo", "Compromisso cadastrado com sucesso!");

		}catch (CampoInvalidoException e) {
			e.printStackTrace();
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR_COMPROMISSO;
		}catch (CampoInteiroException e) {
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR_COMPROMISSO;
		}
		apresentaMensagens();
		return SUCESSO_SALVAR_COMPROMISSO;
	}

	public String carregarInclusao(){
		this.medicoFacade = new MedicoFacade();
		this.medicos = this.medicoFacade.recuperarTodos();
		limparCampos();
		return CARREGAR_INCLUSAO_COMPROMISSO;
	}

	public String carregarConsulta(){
		this.medicoFacade = new MedicoFacade();
		this.compromissoFacade = new CompromissoFacade();
		this.medicos = this.medicoFacade.recuperarTodos();
		this.compromisso = new Compromisso();
		if(this.getUsuarioLogado().getPerfil() == Perfil.MEDICO.getCodigo()){
			this.compromisso.setMedico(this.medicoFacade.recuperar(getUsuarioLogado()));
			this.compromisso.setData(new Date());
			this.compromissosCadastrados = this.compromissoFacade.recuperarCompromissosDiaAtual(compromisso);
		}
		
		return CARREGAR_CONSULTA_COMPROMISSO;
	}

	public String excluirCompromisso(){

		this.compromissoFacade = new CompromissoFacade();		
		try {
			this.compromissoFacade.excluiCompromisso(this.compromisso);
			mensagens.put("excluido", "Compromisso excluido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();					
			return FALHA_EXCLUIR_COMPROMISSO;
		}
		apresentaMensagens();		
		return FALHA_EXCLUIR_COMPROMISSO;
	}

	public String consultarCompromisso(){
		compromissoFacade= new CompromissoFacade();
		try {
			this.compromissosCadastrados = new ArrayList<Compromisso>();
			this.compromissosCadastrados = compromissoFacade.pesquisaCompromisso(compromisso);
		} catch (CampoInvalidoException e) {
			erros.put("erro", e.getMessage());
		}

		return LISTAR_COMPROMISSOS;

	}

	public String carregaEdicaoCompromisso(){
		this.compromissoFacade = new CompromissoFacade();
		this.compromisso = this.compromissoFacade.recuperarPeloId(compromisso.getId());
		return SUCESSO_EDICAO_COMPROMISSO;
	}


	/*Utilitário*/
	private void limparCampos(){
		this.compromisso = null;
		horaFinalAux = null;
		horaInicialAux = null;
		this.dataAux = null;
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


}
