package br.com.sispam.facade;

import javax.persistence.NoResultException;

import br.com.sispam.dao.ConvenioDao;
import br.com.sispam.dao.RelatorioConvenioDao;
import br.com.sispam.dominio.Convenio;
import br.com.sispam.excecao.CampoInvalidoException;

public class RelatorioConvenioFacade {
	private RelatorioConvenioDao relatorioConvenioDao; 
	
	public Convenio emiteRelatorioConvenio(String tipoRelatorio, String categoria) throws CampoInvalidoException{
		relatorioConvenioDao = new RelatorioConvenioDao();
		Convenio convenioRetornado = null;
		try {
			

		} catch (NoResultException e) {
			throw new CampoInvalidoException("Nenhum registro encontrado");
		}
		return convenioRetornado;
	}
}
