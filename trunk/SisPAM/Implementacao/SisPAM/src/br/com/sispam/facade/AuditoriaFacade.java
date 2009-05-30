package br.com.sispam.facade;


import br.com.sispam.dao.AuditoriaDao;
import br.com.sispam.dominio.Auditoria;
import br.com.sispam.excecao.CampoInvalidoException;

public class AuditoriaFacade {
	private AuditoriaDao auditoriaDao;
	
	/**
	 * : Grava log de auditoria
	 * @param auditoria
	 * @throws CampoInvalidoException
	 */
	public void gravaAuditoria(Auditoria auditoria) throws CampoInvalidoException{

		try{
			auditoriaDao = new AuditoriaDao();					
			auditoriaDao.gravaAuditoria(auditoria);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
