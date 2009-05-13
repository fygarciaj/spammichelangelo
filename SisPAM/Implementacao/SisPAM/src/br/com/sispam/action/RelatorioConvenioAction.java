package br.com.sispam.action;

import java.util.Date;

import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.RelatorioConvenioFacade;

public class RelatorioConvenioAction extends Action {

	private String tipoRelatorio;
	private String categoria;
	
	private RelatorioConvenioFacade relatorioConvenioFacade;
	
	public String emitirRelatorioConvenio(String tipoRelatorio, String categoria){
		relatorioConvenioFacade = new RelatorioConvenioFacade();
		try {
			relatorioConvenioFacade.emiteRelatorioConvenio(tipoRelatorio, categoria);
		} catch (CampoInvalidoException e) {
			
			e.printStackTrace();
		}
		
		return SUCESSO;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public RelatorioConvenioFacade getRelatorioConvenioFacade() {
		return relatorioConvenioFacade;
	}

	public void setRelatorioConvenioFacade(
			RelatorioConvenioFacade relatorioConvenioFacade) {
		this.relatorioConvenioFacade = relatorioConvenioFacade;
	}
	
	
}
