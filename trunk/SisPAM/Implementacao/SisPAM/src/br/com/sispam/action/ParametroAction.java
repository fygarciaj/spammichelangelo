package br.com.sispam.action;

import java.util.List;

import br.com.sispam.dominio.Parametro;
import br.com.sispam.facade.ParametroFacade;

public class ParametroAction extends Action{

	private Parametro parametro;
	private ParametroFacade parametroFacade;
	private List<Parametro> parametros;
	
	public String atualizaParametro(){
		
		return ATUALIZAR_HISTORICO_PRONTUARIO;
	}
	
	public String carregarParametros(){
		
		return ATUALIZAR_HISTORICO_PRONTUARIO;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public ParametroFacade getParametroFacade() {
		return parametroFacade;
	}

	public void setParametroFacade(ParametroFacade parametroFacade) {
		this.parametroFacade = parametroFacade;
	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}
	
	
}
