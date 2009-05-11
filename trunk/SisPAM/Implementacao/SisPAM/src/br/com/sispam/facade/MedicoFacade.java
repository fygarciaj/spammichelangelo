package br.com.sispam.facade;

import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;

public class MedicoFacade {
	
	private UsuarioFacade usuarioFacade;

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

}

