package br.com.sispam.action;

import java.util.List;

import br.com.sispam.dominio.Convenio;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.ConvenioFacade;
import br.com.sispam.facade.UsuarioFacade;

public class ConvenioAction extends Action{

	private Convenio convenio;
	private ConvenioFacade convenioFacade;
	private int isExisteConvenio;
	private List<Convenio> conveniosCadastrados;
	
	public String incluirConvenio(){
		convenioFacade = new ConvenioFacade();
		try {
			if (convenioFacade.salvaConvenio(convenio) == false){
				convenioFacade.salvaConvenio(convenio);
				mensagens.put("salvo", "Dados cadastrados com sucesso!");
			}
			else{
				mensagens.put("Já existe", "Convênio já existe!");
			}
		} catch (CampoInvalidoException e) {
			e.printStackTrace();
			erros.put("campoInavlido", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR_CONVENIO;
		}
		apresentaMensagens();
		limparCampos();
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
		}
		else{
			this.isExisteConvenio = 1;
		}		
		return CARREGAR_CONVENIO_EXISTENTE;
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

	public int isExisteConvenio() {
		return isExisteConvenio;
	}

	public void setExisteConvenio(int isExisteConvenio) {
		this.isExisteConvenio = isExisteConvenio;
	}

	public List<Convenio> getConveniosCadastrados() {
		return conveniosCadastrados;
	}

	public void setConveniosCadastrados(List<Convenio> conveniosCadastrados) {
		this.conveniosCadastrados = conveniosCadastrados;
	}
		
}
