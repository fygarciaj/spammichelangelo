package br.com.sispam.facade;

import java.util.List;

import br.com.sispam.dao.AgendamentoDao;
import br.com.sispam.dao.HistoricoProntuarioDao;
import br.com.sispam.dao.ReceitaDao;
import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;

public class ReceitaFacade {
	
	private ReceitaDao receitaDao;
	private AgendamentoDao agendamentoDao;
	
	/**
	 * @descricao: Recupera os agendamentos do médico que foram finalizados.
	 * @param agendamento
	 * @return List<Agendamento> 
	 */
	public List<Agendamento> recuperarAgendamentosDiaAtual(Agendamento agendamento){
		this.receitaDao = new ReceitaDao();
		return this.receitaDao.consultarAgendamento(agendamento);
	}
	
	/**
	 * @descricao: Recupera o agendamento pelo seu id.
	 * @param id
	 */
	public Agendamento recuperarAgendamento(int id){
		this.agendamentoDao = new AgendamentoDao();
		Agendamento agendamento = null; 
		if(id != 0){			
		agendamento = this.agendamentoDao.recuperarAgendamento(id);
		}
		return agendamento;
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
