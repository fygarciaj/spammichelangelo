package br.com.sispam.facade;

import java.util.List;

import br.com.sispam.dao.ConvenioDao;
import br.com.sispam.dao.UsuarioDao;


import br.com.sispam.dominio.Convenio;
import br.com.sispam.dominio.Usuario;



public class ConvenioFacade {
	private ConvenioDao convenioDao; 
		
	public boolean verificaCnpj(Convenio convenio){
		convenioDao = new ConvenioDao();
		//definir validacao
		convenioDao.incluirConvenio(convenio);
		return true;
	
	}
	
	public Convenio pesquisaConvenio(Convenio convenio){
		
		try {
			convenioDao = new ConvenioDao();
			convenioDao.consultarConvenio(convenio.getCnpj());
			System.out.println(convenio.getNome());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		}
		return convenioDao.consultarConvenio(convenio.getCnpj());
	}
	/**
	 * @descricao: Recupera os últimos usuários cadastrados.
	 * @return
	 */
	/*public List<Usuario> recuperarUltimosCadastrados() {
		this.usuarioDao = new UsuarioDao();
		return this.usuarioDao.recuperarUltimosCadastrados();
	}*/
}
