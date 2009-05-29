package br.com.sispam.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	/**
	 * @descricao: Recebe os dados que vem da tela de inclusão de compromisso.
	 * @return
	 * @throws ParseException 
	 */
	public String incluirCompromisso(){
		compromissoFacade = new CompromissoFacade();
		medicoFacade =  new MedicoFacade();

		try {
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("horaInicial", horaInicialAux);
			mapa.put("horaFinal", horaFinalAux);
			this.compromisso.setMedico(this.medicoFacade.recuperar(compromisso.getMedico().getId()));
			
			if(dataAux != null && !dataAux.equals("")){
				try {
					compromisso.setData(DataUtil.stringToDate(dataAux));
				} catch (ParseException e) {
					erros.put("campoInvalido", "Data inválida!");
					apresentaErrors();
					return FALHA_SALVAR_COMPROMISSO;
				}
			}
			
			//verifica se os campo obrigatorios foram preenchidos
			compromissoFacade.validaCampos(compromisso);
			
			//seta os valores das variváveis auxiliares.
			compromisso.setHoraInicial(Integer.parseInt(horaInicialAux));
			compromisso.setHoraFinal(Integer.parseInt(horaFinalAux));
			
			//verifica se os campos são inteiros
			compromissoFacade.verificaCampoInteiro(mapa);

			compromissoFacade.validaHora(compromisso);
								
			//verifica se já existe compromisso cadastrado com esses dados.
			compromissoFacade.verificaExistencia(compromisso);
			
			compromissoFacade.salvaCompromisso(compromisso);
			mensagens.put("salvo", "Compromisso cadastrado com sucesso!");

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
	 * @descricao: Carrega os objetos necessários para fazer a inclusão de compromisso.
	 * @return
	 */
	public String carregarInclusao(){

		if(erros.size() == 0 ){
			limparCampos();
		}
		apresentaErrors();
		apresentaMensagens();
		this.medicoFacade = new MedicoFacade();
		this.medicos = this.medicoFacade.recuperarTodos();
		return CARREGAR_INCLUSAO_COMPROMISSO;
	}

	/**
	 * @descricao: Carrega os dados para realizar a consulta de compromissos.
	 * @return
	 */
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

	/**
	 * @descricao: Recebe o compromisso a ser excluído.
	 * @return
	 */
	public String excluirCompromisso(){
		this.medicoFacade = new MedicoFacade();
		this.compromissoFacade = new CompromissoFacade();		
		try {
			this.compromissoFacade.excluiCompromisso(this.compromisso);
			mensagens.put("excluido", "Compromisso excluido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();					
			return FALHA_EXCLUIR_COMPROMISSO;
		}
		apresentaMensagens();
		this.medicos = this.medicoFacade.recuperarTodos();
		return FALHA_EXCLUIR_COMPROMISSO;
	}

	/**
	 * @descricao: Efetuar a consulta do compromisso.
	 * @return
	 */
	public String consultarCompromisso(){
		compromissoFacade= new CompromissoFacade();
		this.medicoFacade = new MedicoFacade();
		try {
			this.compromissosCadastrados = new ArrayList<Compromisso>();
			this.compromissosCadastrados = compromissoFacade.pesquisaCompromisso(compromisso);
			this.medicos = this.medicoFacade.recuperarTodos();
		} catch (CampoInvalidoException e) {
			erros.put("erro", e.getMessage());
		}
		return LISTAR_COMPROMISSOS;

	}

	/**
	 * @descricao: Carrega o compromisso para ser alterado.
	 * @return
	 */
	public String carregaEdicaoCompromisso(){
		this.compromissoFacade = new CompromissoFacade();
		this.medicoFacade = new MedicoFacade();
		this.compromisso = this.compromissoFacade.recuperarPeloId(compromisso.getId());
		this.dataAux = DataUtil.dateToString(this.compromisso.getData());
		this.horaInicialAux = String.valueOf(this.compromisso.getHoraInicial());
		this.horaFinalAux = String.valueOf(this.compromisso.getHoraFinal());
		this.medicos = this.medicoFacade.recuperarTodos();
		return SUCESSO_EDICAO_COMPROMISSO;
	}


	/*Utilitário*/
	/**
	 * @descricao: Limpa os campos da tela.
	 */
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
