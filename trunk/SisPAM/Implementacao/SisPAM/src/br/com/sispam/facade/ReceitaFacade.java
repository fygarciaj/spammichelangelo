package br.com.sispam.facade;

import br.com.sispam.dao.ConvenioDao;
import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.Convenio;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;

public class ReceitaFacade {
	private PacienteDao pacienteDao;
	
	private void emiteReceita(Paciente paciente){
		
		try {
			pacienteDao = new PacienteDao();
			pacienteDao.EmitirReceita(paciente.getCnpj());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		}
		
		
		return paciente;	
	}
	
	
	
	/**
	 * @descricao: valida os campos do objeto da tela passado.
	 * @param objeto
	 * @throws CampoInvalidoException 
	 */
	private void validaCampos(Paciente paciente) throws CampoInvalidoException{

			if(paciente.getCpf() == null || paciente.getCpf().isEmpty()) {
				throw new CampoInvalidoException("Campo cpf inválido");
			}			
			if(paciente.getNome() == null || paciente.getNome().length() == 0){
				throw new CampoInvalidoException("Campo nome inválido");
			}
		}

	

}
