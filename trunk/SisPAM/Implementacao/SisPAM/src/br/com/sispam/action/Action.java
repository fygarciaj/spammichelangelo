package br.com.sispam.action;

import java.util.HashMap;
import java.util.Map;

import br.com.sispam.dominio.Usuario;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Action extends ActionSupport{

	public final String SUCESSO = "sucesso";	
	public final String FALHA = "falha";
	public final String SUCESSO_SALVAR_USUARIO = "sucessoSalvarUsuario";
	public final String CARREGAR_NOVO_USUARIO = "carregarNovoUsuario";
	public final String FALHA_SALVAR_USUARIO = "falhaSalvarUsuario";
	public final String TELA_SELECIONADA = "telaSelecionada";
	public final String LISTAR_USUARIOS = "listaUsuarios";
	public final String SUCESSO_CARREGAR_CONSULTA = "sucessoCarregarConsulta";
	public final String FALHA_CONSULTAR_USUARIO = "falhaConsultarUsuario";
	public final String SUCESSO_TELA_CONSULTA = "sucessoTelaConsulta";
	
	public final String SUCESSO_EXCLUIR_MEDICO = "sucessoExcluirMedico";
	public final String SUCESSO_SALVAR_MEDICO = "sucessoSalvarMedico";
	public final String FALHA_SALVAR_MEDICO = "falhaSalvarMedico";
	public final String SUCESSO_CARREGAR_EDICAO = "sucessoCarregarEdicao";
	public final String FALHA_CONSULTAR_MEDICO = "falhaConsultarMedico";
	public final String SUCESSO_CONSULTAR_MEDICO = "sucessoConsultarMedico";
	
	public final String FALHA_SALVAR__PACIENTE = "falhaSalvarPaciente";
	public final String SUCESSO_SALVAR_PACIENTE = "sucessoSalvarPaciente";
	public final String SUCESSO_EXCLUIR_PACIENTE = "sucessoRemoverPaciente";
	public final String SUCESSO_CARREGAR_EDICAO_PACIENTE = "sucessoCarregarEdicaoPaciente";
	public final String FALHA_CONSULTAR_PACIENTE = "falhaConsultarPaciente";
	public final String SUCESSO_CONSULTAR_PACIENTE = "sucessoConsultarPaciente";

	public final String SUCESSO_INCLUIR_CONVENIO = "sucessoIncluirConvenio";
	public final String CARREGAR_CONVENIO_EXISTENTE = "carregarConvenioExistente";	
	public final String FALHA_CARREGAR_CONVENIO = "falhaCarregarConvenio";
	public final String LISTAR_CONVENIOS = "listaConvenios";	
	public final String FALHA_SALVAR_CONVENIO = "falhaSalvarConvenio";
	public final String FALHA_EXCLUIR_CONVENIO = "falhaExcluirConvenio";
	public final String SUCESSO_EXCLUIR_CONVENIO = "sucessoExcluirConvenio";	
	public final String USUARIO_LOGADO = "usuarioLogado";
	public final String DATA_HORA_ACESSO = "dtHrAcess";
	public final String SUSCESSO_DESLOGAR = "sucessoDeslogar";
	
	public final String SUCESSO_SALVAR_COMPROMISSO = "sucessoSalvarCompromisso";
	public final String SUCESSO_EDICAO_COMPROMISSO = "sucessoEdicaoCompromisso";
	public final String FALHA_SALVAR_COMPROMISSO = "falhaSalvarCompromisso";
	public final String FALHA_EXCLUIR_COMPROMISSO = "falhaExcluirCompromisso";
	public final String LISTAR_COMPROMISSOS = "listarCompromissos";
	public final String CARREGAR_INCLUSAO_COMPROMISSO = "carregarInclusaoCompromisso";
	public final String CARREGAR_CONSULTA_COMPROMISSO = "carregarConsultaCompromisso";

	public final String CARREGAR_CONSULTA_AGENDAMENTO = "carregarConsultaAgendamento";
	public final String	ATUALIZAR_HISTORICO_PRONTUARIO = "atualizarHistoricoProntuario";
	public final String SUCESSO_ATUALIZACAO_HISTORICO_PRONTUARIO = "sucessoAtualizacaoHistoricoProntuario";
	public final String FALHA_ATUALIZAR_HISTORICO_PRONTUARIO = "falhaAtualizarHistoricoProntuario";
	
	protected Map<String, String> erros = new HashMap<String, String>();
	protected Map<String, String> mensagens = new HashMap<String, String>();


	public Usuario getUsuarioLogado(){
		return (Usuario) ActionContext.getContext().getSession().get(USUARIO_LOGADO);
	}
	
	public Usuario getDataHoraAcesso(){
		return (Usuario) ActionContext.getContext().getSession().get(DATA_HORA_ACESSO);
	}

	public void apresentaErrors(){
		if(erros != null && erros.size() > 0){
			for(String chave: erros.keySet()){
				addFieldError(chave, erros.get(chave));
			}
		}
	}

	public void apresentaMensagens(){
		if(mensagens != null && mensagens.size() > 0){
			for(String chave: mensagens.keySet()){
				addActionMessage(mensagens.get(chave));
			}
		}
	}

	public void apresentaTodasMensagens(){
		apresentaErrors();
		apresentaMensagens();
	}

	public Map<String, String> getErros() {
		return erros;
	}


	public void setErros(Map<String, String> erros) {
		this.erros = erros;
	}


	public Map<String, String> getMensagens() {
		return mensagens;
	}


	public void setMensagens(Map<String, String> mensagens) {
		this.mensagens = mensagens;
	}

}
