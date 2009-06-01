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
	
	public AuditoriaFacade(){
		this.auditoriaDao = new AuditoriaDao();
	}
	public void gravaAuditoria(Auditoria auditoria) throws CampoInvalidoException{

		try{
			auditoriaDao.gravaAuditoria(auditoria);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
