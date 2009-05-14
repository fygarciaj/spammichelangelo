package br.com.sispam.facade;

import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;

public class MedicoFacade {

	private UsuarioFacade usuarioFacade;
	private MedicoDao medicoDao;
	
	/**
	 * @descricao: valida os campos do medico da tela passado.
	 * @param Medico
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Medico medico) throws CampoInvalidoException{
		if(medico != null){
			this.usuarioFacade = new UsuarioFacade();
			this.usuarioFacade.validaCampos(medico.getUsuario());
			if(medico.getCrmUf() == null || medico.getCrmUf().length() == 0){
				throw new CampoInvalidoException("Campo UF-CRM inválido");
			}
		}
	}

	public void validaCampoDias(String builder) throws CampoInvalidoException{
		if(builder== null || builder.length() == 0){
			throw new CampoInvalidoException("Preencha os dias de Trabalho!");
		}
	}

	public void verificaCrmExistente(int crm, int id) throws CampoInvalidoException {
		this.medicoDao = new MedicoDao();
		Medico medico = this.medicoDao.recuperaPeloCrm(crm);
		if(medico != null && id != medico.getId()){
			throw new CampoInvalidoException("Este CRM está em uso!");
		}
	}

}

