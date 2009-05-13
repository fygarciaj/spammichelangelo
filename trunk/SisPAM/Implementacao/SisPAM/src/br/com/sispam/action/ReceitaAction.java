package br.com.sispam.action;

import java.util.Date;

import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.ConvenioFacade;
import br.com.sispam.facade.ReceitaFacade;

public class ReceitaAction extends Action{

	private String idAux;
	private Date dataAtendimentoAux;
	private int horaAtendimentoAux;
	private ReceitaFacade receitaFacade;
	
	public String emitirReceita(String id, Date dataAtendimentoAux, int horaAtendimentoAux){
		receitaFacade = new ReceitaFacade();
		try {
			receitaFacade.emiteReceita(id);
		} catch (CampoInvalidoException e) {
			
			e.printStackTrace();
		}
		
		return SUCESSO;
	}

	public String getIdAux() {
		return idAux;
	}

	public void setIdAux(String idAux) {
		this.idAux = idAux;
	}

	public Date getDataAtendimentoAux() {
		return dataAtendimentoAux;
	}

	public void setDataAtendimentoAux(Date dataAtendimentoAux) {
		this.dataAtendimentoAux = dataAtendimentoAux;
	}

	public int getHoraAtendimentoAux() {
		return horaAtendimentoAux;
	}

	public void setHoraAtendimentoAux(int horaAtendimentoAux) {
		this.horaAtendimentoAux = horaAtendimentoAux;
	}

	public ReceitaFacade getReceitaFacade() {
		return receitaFacade;
	}

	public void setReceitaFacade(ReceitaFacade receitaFacade) {
		this.receitaFacade = receitaFacade;
	}

	
}
