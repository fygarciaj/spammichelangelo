package br.com.sispam.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class Action extends ActionSupport{
		
	public final String SUCESSO = "sucesso";
	public final String CARREGAR_NOVO_USUARIO = "carregarNovoUsuario";
	public final String FALHA = "falha";
	public final String CARREGAR_CONVENIO_EXISTENTE = "carregarConvenioExistente";
	public  final String FALHA_SALVAR_USUARIO = "falhaSalvarUsuario";
	public  final String SUCESSO_SALVAR_USUARIO = "sucessoSalvarUsuario";
	public  final String TELA_SELECIONADA = "telaSelecionada";
	public 	final String LISTAR_USUARIOS = "listaUsuarios";
	public 	final String LISTAR_CONVENIOS = "listaConvenios";
	public final String SUCESSO_INCLUIR_CONVENIO = "sucessoIncluirConvenio";;
	public  final String FALHA_SALVAR_CONVENIO = "falhaSalvarConvenio";
	
	protected Map<String, String> erros = new HashMap<String, String>();
	protected Map<String, String> mensagens = new HashMap<String, String>();

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
