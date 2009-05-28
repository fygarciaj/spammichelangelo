package br.com.sispam.facade;

import br.com.sispam.dao.ReceitaDao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;

public class ReceitaFacade {
	private Usuario paciente;
	private ReceitaDao receitaDao;
	
	/**
	 * @descricao: Emite Receita Médica.
	 * @param String
	 * @throws CampoInvalidoException 
	 *  
	 */
	public void emiteReceita(String id) throws CampoInvalidoException{
		validaCampos(paciente);
		try {
			receitaDao = new ReceitaDao();
			receitaDao.emitirReceita(id);
		} catch (Exception e) {
			
			e.getMessage();
			e.printStackTrace();
		}
						
	}
	
		
	/**
	 * @descricao: valida os campos do objeto da tela passado.
	 * @param objeto
	 * @throws CampoInvalidoException 
	 */
	private void validaCampos(Usuario paciente) throws CampoInvalidoException{

			if(paciente.getCpf() == null || paciente.getCpf().isEmpty()) {
				throw new CampoInvalidoException("Campo cpf inválido");
			}			
			if(paciente.getNome() == null || paciente.getNome().length() == 0){
				throw new CampoInvalidoException("Campo nome inválido");
			}
		}

	

}
