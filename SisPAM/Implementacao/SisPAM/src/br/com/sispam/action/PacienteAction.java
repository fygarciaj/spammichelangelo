package br.com.sispam.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sispam.dominio.Convenio;
import br.com.sispam.dominio.Paciente;
import br.com.sispam.enums.Acao;
import br.com.sispam.enums.Funcionalidade;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.AuditoriaFacade;
import br.com.sispam.facade.ConvenioFacade;
import br.com.sispam.facade.PacienteFacade;
import br.com.sispam.facade.UsuarioFacade;
import br.com.sispam.util.AuditoriaUtil;
import br.com.sispam.util.CampoUtil;
import br.com.sispam.util.DataUtil;

public class PacienteAction extends Action {

	private Paciente paciente;
	private String telefoneAux;
	private String cepAux;
	private String rgAux;
	private String dddAux;
	private Integer codigoPerfilSelecionado;
	private List<Convenio> convenios;
	private Perfil[] perfils = Perfil.values();
	private Sexo[] sexos = Sexo.values();
	private String codigoPerfilString;
	private String validaPlanoAux;
	private String dataNascimentoAux;
	private List<Paciente> pacientesCadastrados;
	private boolean pacienteLogado;

	private UsuarioFacade usuarioFacade;
	private PacienteFacade pacienteFacade;
	private ConvenioFacade convenioFacade;
	private AuditoriaFacade auditoriaFacade;

	/**
	 * : Salva o paciente no sistema.
	 * @return
	 */
	public String salvarPaciente(){
		boolean isEdicao = false;
		if(this.paciente.getId() > 0){
			isEdicao = true;
		}
		this.usuarioFacade = new UsuarioFacade();
		this.pacienteFacade = new PacienteFacade();
		
		//limpa os caracteres dos campos
		telefoneAux = CampoUtil.replaceCampo("-", telefoneAux);
		cepAux = CampoUtil.replaceCampo(".", cepAux);
		
		//monta um mapa com todos os campos que devem ser inteiros.	
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("ddd", dddAux);
		mapa.put("telefone", telefoneAux);
		mapa.put("Rg", rgAux);
		mapa.put("cep", cepAux);

		this.convenioFacade = new ConvenioFacade();
		this.convenios = this.convenioFacade.recuperarTodos();

		try {
			//valida os campos inteiros
			this.usuarioFacade.verificaCampoInteiro(mapa);
			//valida os campos do paciente e usuarios.
			this.pacienteFacade.validaCampos(paciente, dataNascimentoAux);
			this.paciente.getUsuario().setDataNascimento(DataUtil.stringToDate(dataNascimentoAux));

			paciente.getUsuario().setCep(Integer.parseInt(this.cepAux));
			paciente.getUsuario().setDdd(Integer.parseInt(this.dddAux));
			paciente.getUsuario().setTelefone(Integer.parseInt(this.telefoneAux));
			paciente.getUsuario().setRg(Integer.parseInt(this.rgAux));
			paciente.getUsuario().setPerfil(this.codigoPerfilSelecionado);
			if(validaPlanoAux != null && !validaPlanoAux.isEmpty()){
				this.paciente.setValidadePlano(DataUtil.stringToDate(validaPlanoAux));
			}
			
			this.pacienteFacade.salvar(paciente);
			if(isEdicao){
				mensagens.put("salvo", "Paciente alterado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.ALTERACAO, getUsuarioLogado()));
			}else{
				mensagens.put("salvo", "Paciente cadastrado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.INCLUSAO, getUsuarioLogado()));
			}
		} catch (CampoInvalidoException e) {
			erros.put("erros", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR__PACIENTE;
		}
		catch (ParseException e) {
			erros.put("erros", "Data inválida!");
			apresentaErrors();
			return FALHA_SALVAR__PACIENTE;
		}finally{
			if(getUsuarioLogado().getPerfil() == 4){
				this.pacienteLogado = true;
			}
		}
		apresentaMensagens();
		limparCampos(true);
		
		return SUCESSO_SALVAR_PACIENTE;
	}
	
	/**
	 * : Carrega o paciente para ser editado.
	 * @return
	 */
	public String carregarEdicao(){
		this.pacienteFacade = new PacienteFacade();
		this.convenioFacade = new ConvenioFacade();
		this.convenios = this.convenioFacade.recuperarTodos();
		if(getUsuarioLogado().getPerfil() == 4){
			this.paciente = this.pacienteFacade.recuperarPeloUsuario(getUsuarioLogado().getId());
			this.codigoPerfilSelecionado = 4;
			this.pacienteLogado = true;
		}else{
			this.paciente = this.pacienteFacade.recuperarPeloId(this.paciente.getId());
		}
		this.cepAux = String.valueOf(this.paciente.getUsuario().getCep());
		this.dataNascimentoAux = DataUtil.dateToString(this.paciente.getUsuario().getDataNascimento());
		this.dddAux = String.valueOf(this.paciente.getUsuario().getDdd());
		this.rgAux = String.valueOf(this.paciente.getUsuario().getRg());
		this.telefoneAux = String.valueOf(this.paciente.getUsuario().getTelefone());
		this.validaPlanoAux = DataUtil.dateToString(this.paciente.getValidadePlano());
		
		return SUCESSO_CARREGAR_EDICAO_PACIENTE;
	}
	
	/**
	 * : Efetua a consulta do paciente.
	 * @return
	 */
	public String consultarPaciente(){
		this.pacienteFacade = new PacienteFacade();
		
		try{
			this.pacientesCadastrados = this.pacienteFacade.consultar(paciente);
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.CONSULTA, getUsuarioLogado()));
		}catch (CampoInvalidoException e) {
			erros.put("erro", e.getMessage());
			this.codigoPerfilString = String.valueOf(this.codigoPerfilSelecionado);
			return FALHA_CONSULTAR_PACIENTE;
		}
		
		if(this.pacientesCadastrados == null || this.pacientesCadastrados.size() < 1){
			mensagens.put("consulta", "Nenhum paciente encontrado com esses dados!");
		}
		limparCampos(false);
		apresentaMensagens();
		return SUCESSO_CONSULTAR_PACIENTE;
	}

	
	/**
	 * : Remove o paciente do sistema.
	 * @return
	 */
	public String excluirPaciente(){
		this.pacienteFacade = new PacienteFacade();
		
		try {
			this.pacienteFacade.removerPaciente(this.paciente.getId());
			this.codigoPerfilString = String.valueOf(this.codigoPerfilSelecionado);
			mensagens.put("salvo", "Paciente excluído com sucesso!");
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.EXCLUSAO, getUsuarioLogado()));
		} catch (CampoInvalidoException e) {
			erros.put("erro", e.getMessage());
			this.codigoPerfilString = String.valueOf(this.codigoPerfilSelecionado);
			return FALHA_CONSULTAR_PACIENTE;
		}
		return SUCESSO_EXCLUIR_PACIENTE;
	}



	/*Utilitarios*/
	
	/**
	 * : Limpa os campos da tela.
	 */
	public void limparCampos(boolean codigoPerfil){

		if(codigoPerfil){
			this.codigoPerfilSelecionado = null;
		}
		this.paciente = null;
		this.cepAux = null;
		this.dddAux = null;
		this.rgAux = null;
		this.telefoneAux = null;
		this.paciente = null;
	}


	/*Get e Set*/

	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public String getTelefoneAux() {
		return telefoneAux;
	}
	public void setTelefoneAux(String telefoneAux) {
		this.telefoneAux = telefoneAux;
	}
	public String getCepAux() {
		return cepAux;
	}
	public void setCepAux(String cepAux) {
		this.cepAux = cepAux;
	}
	public String getRgAux() {
		return rgAux;
	}
	public void setRgAux(String rgAux) {
		this.rgAux = rgAux;
	}
	public String getDddAux() {
		return dddAux;
	}
	public void setDddAux(String dddAux) {
		this.dddAux = dddAux;
	}
	public Integer getCodigoPerfilSelecionado() {
		return codigoPerfilSelecionado;
	}
	public void setCodigoPerfilSelecionado(Integer codigoPerfilSelecionado) {
		this.codigoPerfilSelecionado = codigoPerfilSelecionado;
	}
	public List<Convenio> getConvenios() {
		return convenios;
	}
	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	public Perfil[] getPerfils() {
		return perfils;
	}
	public void setPerfils(Perfil[] perfils) {
		this.perfils = perfils;
	}
	public Sexo[] getSexos() {
		return sexos;
	}
	public void setSexos(Sexo[] sexos) {
		this.sexos = sexos;
	}
	public String getCodigoPerfilString() {
		return codigoPerfilString;
	}
	public void setCodigoPerfilString(String codigoPerfilString) {
		this.codigoPerfilString = codigoPerfilString;
	}
	public String getValidaPlanoAux() {
		return validaPlanoAux;
	}
	public void setValidaPlanoAux(String validaPlanoAux) {
		this.validaPlanoAux = validaPlanoAux;
	}
	public String getDataNascimentoAux() {
		return dataNascimentoAux;
	}
	public void setDataNascimentoAux(String dataNascimentoAux) {
		this.dataNascimentoAux = dataNascimentoAux;
	}
	public List<Paciente> getPacientesCadastrados() {
		return pacientesCadastrados;
	}
	public void setPacientesCadastrados(List<Paciente> pacientesCadastrados) {
		this.pacientesCadastrados = pacientesCadastrados;
	}

	public UsuarioFacade getUsuarioFacade() {
		return usuarioFacade;
	}

	public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}

	public PacienteFacade getPacienteFacade() {
		return pacienteFacade;
	}

	public void setPacienteFacade(PacienteFacade pacienteFacade) {
		this.pacienteFacade = pacienteFacade;
	}

	public ConvenioFacade getConvenioFacade() {
		return convenioFacade;
	}

	public void setConvenioFacade(ConvenioFacade convenioFacade) {
		this.convenioFacade = convenioFacade;
	}

	public AuditoriaFacade getAuditoriaFacade() {
		return auditoriaFacade;
	}

	public void setAuditoriaFacade(AuditoriaFacade auditoriaFacade) {
		this.auditoriaFacade = auditoriaFacade;
	}

	public boolean isPacienteLogado() {
		return pacienteLogado;
	}

	public void setPacienteLogado(boolean pacienteLogado) {
		this.pacienteLogado = pacienteLogado;
	}
}
