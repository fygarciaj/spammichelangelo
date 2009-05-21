package br.com.sispam.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sispam.dominio.Convenio;
import br.com.sispam.dominio.Paciente;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.ConvenioFacade;
import br.com.sispam.facade.PacienteFacade;
import br.com.sispam.facade.UsuarioFacade;
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

	private UsuarioFacade usuarioFacade;
	private PacienteFacade pacienteFacade;
	private ConvenioFacade convenioFacade;


	/**
	 * @descricao: Salva o paciente no sistema.
	 * @return
	 */
	public String salvarPaciente(){

		this.usuarioFacade = new UsuarioFacade();
		this.pacienteFacade = new PacienteFacade();
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

			paciente.getUsuario().setCep(Integer.parseInt(this.cepAux));
			paciente.getUsuario().setDdd(Integer.parseInt(this.dddAux));
			paciente.getUsuario().setTelefone(Integer.parseInt(this.telefoneAux));
			paciente.getUsuario().setRg(Integer.parseInt(this.rgAux));
			paciente.getUsuario().setPerfil(this.codigoPerfilSelecionado);
			if(validaPlanoAux != null && !validaPlanoAux.isEmpty()){
				this.paciente.setValidadePlano(DataUtil.stringToDate(validaPlanoAux));
			}
			if(paciente.getConvenio() != null && paciente.getConvenio().getId() == 0){
				paciente.setConvenio(null);
			}

			this.pacienteFacade.salvar(paciente);
			mensagens.put("salvo", "Paciente cadastrado com sucesso!");
		} catch (CampoInvalidoException e) {
			erros.put("erros", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR__PACIENTE;
		}
		catch (ParseException e) {
			erros.put("erros", "Data inválida!");
			apresentaErrors();
			return FALHA_SALVAR__PACIENTE;
		}
		apresentaMensagens();
		limparCampos(true);
		return SUCESSO_SALVAR_PACIENTE;
	}
	
	/**
	 * @descricao: Remove o paciente do sistema.
	 * @return
	 */
	public String excluirPaciente(){
		this.pacienteFacade = new PacienteFacade();
		this.pacienteFacade.removerPaciente(this.paciente.getId());
		this.codigoPerfilString = String.valueOf(this.codigoPerfilSelecionado);
		return SUCESSO_EXCLUIR_PACIENTE;
	}



	/*Utilitarios*/

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
}
