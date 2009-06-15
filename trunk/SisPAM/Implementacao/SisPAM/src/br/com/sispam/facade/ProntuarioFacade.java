package br.com.sispam.facade;

import java.util.List;

import br.com.sispam.dao.PacienteDao;
import br.com.sispam.dao.ProntuarioDao;
import br.com.sispam.dominio.Paciente;

public class ProntuarioFacade {
	private ProntuarioDao prontuarioDao;

	public ProntuarioFacade(){
		this.prontuarioDao = new ProntuarioDao();
	}
	/**
	 * : Recupera todos os pacientes do banco de dados.
	 * @return
	 */
	public List<Paciente> recuperarTodos(){
		return this.prontuarioDao.recuperarTodos();
	}
}
