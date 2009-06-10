package br.com.sispam.action;

import java.util.HashMap;
import java.util.Map;

import br.com.sispam.dominio.Usuario;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Classe que possui as constantes do projeto.
 * @author laurindo
 *
 */
public class Action extends ActionSupport{
	
	//USUÁRIO
	public final String SUCESSO_SALVAR_USUARIO = "sucessoSalvarUsuario";
	public final String CARREGAR_NOVO_USUARIO = "carregarNovoUsuario";
	public final String FALHA_SALVAR_USUARIO = "falhaSalvarUsuario";
	public final String TELA_SELECIONADA = "telaSelecionada";
	public final String LISTAR_USUARIOS = "listaUsuarios";
	public final String SUCESSO_CARREGAR_CONSULTA = "sucessoCarregarConsulta";
	public final String FALHA_CONSULTAR_USUARIO = "falhaConsultarUsuario";
	public final String SUCESSO_TELA_CONSULTA = "sucessoTelaConsulta";
	
	//MÉDICO
	public final String SUCESSO_EXCLUIR_MEDICO = "sucessoExcluirMedico";
	public final String SUCESSO_SALVAR_MEDICO = "sucessoSalvarMedico";
	public final String FALHA_SALVAR_MEDICO = "falhaSalvarMedico";
	public final String SUCESSO_CARREGAR_EDICAO = "sucessoCarregarEdicao";
	public final String FALHA_CONSULTAR_MEDICO = "falhaConsultarMedico";
	public final String SUCESSO_CONSULTAR_MEDICO = "sucessoConsultarMedico";
	
	//PACIENTE
	public final String FALHA_SALVAR__PACIENTE = "falhaSalvarPaciente";
	public final String SUCESSO_SALVAR_PACIENTE = "sucessoSalvarPaciente";
	public final String SUCESSO_EXCLUIR_PACIENTE = "sucessoRemoverPaciente";
	public final String SUCESSO_CARREGAR_EDICAO_PACIENTE = "sucessoCarregarEdicaoPaciente";
	public final String FALHA_CONSULTAR_PACIENTE = "falhaConsultarPaciente";
	public final String SUCESSO_CONSULTAR_PACIENTE = "sucessoConsultarPaciente";
	
	//CONVÊNIO
	public final String SUCESSO_INCLUIR_CONVENIO = "sucessoIncluirConvenio";
	public final String CARREGAR_CONVENIO_EXISTENTE = "carregarConvenioExistente";	
	public final String FALHA_CARREGAR_CONVENIO = "falhaCarregarConvenio";
	public final String LISTAR_CONVENIOS = "listaConvenios";	
	public final String FALHA_SALVAR_CONVENIO = "falhaSalvarConvenio";
	public final String FALHA_EXCLUIR_CONVENIO = "falhaExcluirConvenio";
	public final String SUCESSO_EXCLUIR_CONVENIO = "sucessoExcluirConvenio";	
	
	//LOGIN
	public final String SUCESSO = "sucesso";	
	public final String FALHA = "falha";
	public final String USUARIO_LOGADO = "usuarioLogado";
	public final String DATA_HORA_ACESSO = "dtHrAcess";
	public final String SUCESSO_DESLOGAR = "sucessoDeslogar";
	public final String SUCESSO_ALTERACAO_SENHA = "sucessoAlteracaoSenha";
	public final String FALHA_ALTERACAO_SENHA = "falhaAlteracaoSenha";
	
	//COMPROMISSO 
	public final String SUCESSO_SALVAR_COMPROMISSO = "sucessoSalvarCompromisso";
	public final String SUCESSO_EDICAO_COMPROMISSO = "sucessoEdicaoCompromisso";
	public final String FALHA_SALVAR_COMPROMISSO = "falhaSalvarCompromisso";
	public final String FALHA_EXCLUIR_COMPROMISSO = "falhaExcluirCompromisso";
	public final String LISTAR_COMPROMISSOS = "listarCompromissos";
	public final String CARREGAR_INCLUSAO_COMPROMISSO = "carregarInclusaoCompromisso";
	public final String CARREGAR_CONSULTA_COMPROMISSO = "carregarConsultaCompromisso";
	public final String FALHA_CONSULTAR_COMPROMISSO = "falhaConsultarCompromisso";
	//PRONTUÁRIO
	public final String CARREGAR_CONSULTA_AGENDAMENTO = "carregarConsultaAgendamento";
	public final String	ATUALIZAR_HISTORICO_PRONTUARIO = "atualizarHistoricoProntuario";
	public final String SUCESSO_ATUALIZACAO_HISTORICO_PRONTUARIO = "sucessoAtualizacaoHistoricoProntuario";
	public final String FALHA_ATUALIZAR_HISTORICO_PRONTUARIO = "falhaAtualizarHistoricoProntuario";
	
	//AGENDAMENTO
	public final String SUCESSO_CARREGAR_INCLUSAO_AGENDAMENTO = "sucessoCarregarInclusaoAgendamento";
	public final String SUCESSO_SALVAR_AGENDAMENTO = "sucessoSalvarAgendamento";
	public final String FALHA_SALVAR_AGENDMENTO = "falharSalvarAgendamento";
	public final String SUCESSO_CARREGAR_AGENDAMENTOS = "sucessoCarregarAgendamentos";
	public final String SUCESSO_EXCLUIR_AGENDAMENTO = "sucessoExcluirAgendamento";
	public final String CONSULTA_AGENDAMENTOS_REALIZADOS = "consultaAgendamentosRealizados";
	public final String FALHA_CONSULTAR_AGENDAMENTO_PACIENTE = "falhaConsultarAgendamentoPaciente";
	//RECEITA
	public final String SUCESSO_EMISSAO_RECEITA = "sucessoEmissaoReceita";
	public final String SUCESSO_CARREGAR_ATENDIMENTOS = "sucessoCarregarAtendimentos";
	public final String FALHA_CONSULTAR_AGENDAMENTO= "falhaConsultarAgendamento";
	//PRONTUARIO
	public final String SUCESSO_CARREGAR_PACIENTES = "sucessoCarregarPacientes";
	
	protected Map<String, String> erros = new HashMap<String, String>();
	protected Map<String, String> mensagens = new HashMap<String, String>();

	/**
	 * Recupera o usuário logado da sessão.
	 * @return
	 */
	public Usuario getUsuarioLogado(){
		return (Usuario) ActionContext.getContext().getSession().get(USUARIO_LOGADO);
	}
	
	/**
	 * Recupera a hora que foi efetuado o Login.
	 * @return
	 */
	public Usuario getDataHoraAcesso(){
		return (Usuario) ActionContext.getContext().getSession().get(DATA_HORA_ACESSO);
	}
	
	/**
	 * Apresenta os erros na tela.
	 */
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
	/**
	 * Apresenta as Mensagens na tela.
	 */
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
