package br.com.sispam.action;

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
			
			if (convenioFacade.salvaConvenio(convenio) == false){
				convenio.setCep(Long.parseLong(cepAux));
				convenio.setDdd(Integer.parseInt(dddAux));
				convenio.setCodigoANS(Integer.parseInt(codigoANSAux));
				convenio.setTelefone(Integer.parseInt(telefoneAux));
				convenioFacade.salvaConvenio(convenio);
				mensagens.put("salvo", "Dados cadastrados com sucesso!");
			}
			else{
				mensagens.put("Já existe", "Convênio já existe!");
			}
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
		this.convenio = convenioFacade.pesquisaConvenio(convenio);
		
		if(this.convenio != null){
			this.isExisteConvenio = 2;
			return CARREGAR_CONVENIO_EXISTENTE;	
		}
		else{
			this.isExisteConvenio = 1;
			return FALHA_CARREGAR_CONVENIO;			
		}		
		
	}
	
	/**
	 * @descricao: Direciona para a tela de consulta, exibindo os últimos cadastros de convênios realizados 
	 * @return 
	 */
	public String listaUltimosConveniosCadastrados(){
		this.convenioFacade = new ConvenioFacade();
		this.conveniosCadastrados = this.convenioFacade.recuperarUltimosCadastrados();
		return LISTAR_CONVENIOS;
	}

	/*Utilitário*/
	private void limparCampos(){
		this.convenio = null;		
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
