package br.com.sispam.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sispam.dominio.Convenio;

import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.ConvenioFacade;


public class ConvenioAction extends Action{

	private Convenio convenio;
	private ConvenioFacade convenioFacade;
	private int isExisteConvenio;
	private List<Convenio> conveniosCadastrados;
	private String codigoANSAux;
	private String telefoneAux;
	private String cepAux;
	private String cnpjAux;
	private String dddAux;


	public String incluirConvenio(){
		convenioFacade = new ConvenioFacade();
		//monta um mapa com todos os campos que devem ser inteiros.	
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("ddd", dddAux);
		mapa.put("telefone", telefoneAux);
		mapa.put("codigoANS", codigoANSAux);
		mapa.put("cep", cepAux);

		try {
			//verifica se os campos são inteiros
			convenioFacade.verificaCampoInteiro(mapa);

			//verifica se os campo obrigatorios foram preenchidos
			convenioFacade.validaCampos(convenio);

			//seta os valores das variváveis auxiliares.
			convenio.setCep(Long.parseLong(cepAux));
			convenio.setDdd(Integer.parseInt(dddAux));
			convenio.setCodigoANS(Integer.parseInt(codigoANSAux));
			convenio.setTelefone(Integer.parseInt(telefoneAux));

			//verifica se já existe convênio cadastrado com esses dados.
			convenioFacade.verificaExistencia(convenio);
			convenioFacade.salvaConvenio(convenio);
			mensagens.put("salvo", "Convênio cadastrado com sucesso!");

		}catch (CampoInvalidoException e) {
			e.printStackTrace();
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR_CONVENIO;
		}catch (CampoInteiroException e) {
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR_CONVENIO;
		}
		apresentaMensagens();
		this.limparCampos();
		return SUCESSO_INCLUIR_CONVENIO;
	}

	public String excluirConvenio(){
		this.convenioFacade = new ConvenioFacade();		
		try {
			this.convenioFacade.excluiConvenio(this.convenio);
			mensagens.put("excluido", "Convenio excluido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();					
			return FALHA_EXCLUIR_CONVENIO;
		}
		apresentaMensagens();		
		return SUCESSO_EXCLUIR_CONVENIO;
	}

	public String consultarConvenio(){
		convenioFacade = new ConvenioFacade();
		try {
			this.conveniosCadastrados = new ArrayList<Convenio>();
			this.convenio = convenioFacade.pesquisaConvenio(convenio);
			this.conveniosCadastrados.add(this.convenio);
		} catch (CampoInvalidoException e) {
			erros.put("erro", e.getMessage());
		}

		return LISTAR_CONVENIOS;

	}
	
	public String carregaEdicaoConvenio(){
		this.convenioFacade = new ConvenioFacade();
		this.convenio = this.convenioFacade.recuperarPeloId(convenio.getId());
		this.cepAux = String.valueOf(convenio.getCep());
		return SUCESSO_INCLUIR_CONVENIO;
	}


	/*Utilitário*/
	private void limparCampos(){
		this.convenio = null;
		this.cepAux = null;
		this.cnpjAux = null;
		this.codigoANSAux = null;
		this.dddAux = null;
		this.telefoneAux = null;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public ConvenioFacade getConvenioFacade() {
		return convenioFacade;
	}

	public void setConvenioFacade(ConvenioFacade convenioFacade) {
		this.convenioFacade = convenioFacade;
	}

	public int getIsExisteConvenio() {
		return isExisteConvenio;
	}

	public void setIsExisteConvenio(int isExisteConvenio) {
		this.isExisteConvenio = isExisteConvenio;
	}

	public List<Convenio> getConveniosCadastrados() {
		return conveniosCadastrados;
	}

	public void setConveniosCadastrados(List<Convenio> conveniosCadastrados) {
		this.conveniosCadastrados = conveniosCadastrados;
	}

	public String getCodigoANSAux() {
		return codigoANSAux;
	}

	public void setCodigoANSAux(String codigoANSAux) {
		this.codigoANSAux = codigoANSAux;
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

	public String getCnpjAux() {
		return cnpjAux;
	}

	public void setCnpjAux(String cnpjAux) {
		this.cnpjAux = cnpjAux;
	}

	public String getDddAux() {
		return dddAux;
	}

	public void setDddAux(String dddAux) {
		this.dddAux = dddAux;
	}


}
