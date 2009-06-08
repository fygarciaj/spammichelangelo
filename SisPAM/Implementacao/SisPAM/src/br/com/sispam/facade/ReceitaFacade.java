package br.com.sispam.facade;

import java.text.ParseException;
import java.util.List;

import br.com.sispam.dao.AgendamentoDao;
import br.com.sispam.dao.HistoricoProntuarioDao;
import br.com.sispam.dao.ReceitaDao;
import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.DataUtil;

public class ReceitaFacade {
	
	private ReceitaDao receitaDao;
	private AgendamentoDao agendamentoDao;
	
	/**
	 * : Recupera os agendamentos do médico que foram finalizados.
	 * @param agendamento
	 * @return List<Agendamento> 
	 */
	public List<Agendamento> recuperarAgendamentosDiaAtual(Agendamento agendamento){
		this.receitaDao = new ReceitaDao();
		return this.receitaDao.consultarAgendamento(agendamento);
	}
	
	/**
	 * : Recupera o agendamento pelo seu id.
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
	 * : retorna a consulta realizada pelo usuário.
	 * @param agendamento
	 * @param data
	 * @return
	 * @throws CampoInvalidoException
	 */
	public List<Agendamento> consultar(Agendamento agendamento, String data){
		
		if(agendamento != null){
			try {
				agendamento.setData(DataUtil.stringToDate(data));
			} catch (ParseException e) {
				agendamento.setData(null);
			}
			return this.receitaDao.consultar(agendamento);
		}else{
			return null;
		}
		
	}
		
	/**
	 * : valida os campos do objeto da tela passado.
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
