package br.com.sispam.teste.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;



import br.com.sispam.dao.AgendamentoDao;
import br.com.sispam.dao.CodigoDoencaDao;

import br.com.sispam.dao.HistoricoProntuarioDao;
import br.com.sispam.dao.PacienteDao;
import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.CodigoDoenca;

import br.com.sispam.dominio.HistoricoProntuario;
import br.com.sispam.dominio.Paciente;

public class TesteHistoricoProntuarioDao {

	@Test
	public void consultaAgendamento(){
		Agendamento agendamento = new Agendamento();
		List<Agendamento> agendamentos = null;
		

		agendamento.setData(new Date());
		agendamento.setStatus(1);
		HistoricoProntuarioDao historicoProntuarioDao = new HistoricoProntuarioDao();
		
		agendamentos = historicoProntuarioDao.consultarAgendamento(agendamento);
		
		System.out.println(agendamentos.indexOf(agendamento));		
	}
	
	@Test
	public void atualizaHistoricoProntuario(){
				
		PacienteDao pacienteDao = new PacienteDao();
		Paciente paciente = pacienteDao.recuperarPeloId(2);				
		
		HistoricoProntuario historicoProntuario = new HistoricoProntuario();
		HistoricoProntuarioDao historicoProntuarioDao = new HistoricoProntuarioDao();
		
		CodigoDoencaDao codigoDoencaDao = new CodigoDoencaDao();		
		CodigoDoenca codigoDoenca = codigoDoencaDao.recuperarCodigoDoenca(46);
		
		AgendamentoDao agendamentoDao = new AgendamentoDao();
		Agendamento agendamentoRecuperado = agendamentoDao.recuperarAgendamento(2);
				
		agendamentoRecuperado.setStatus(2);
		agendamentoDao.incluirAgendamento(agendamentoRecuperado);

		historicoProntuario.setCodigoDoenca(codigoDoenca);
		historicoProntuario.setPaciente(paciente);
		historicoProntuario.setLaudo("LAUDO");
		historicoProntuario.setPrescricao("PRESCRICAO");
		historicoProntuario.setSintoma("SINTOMA");
		historicoProntuario.setObservacao("OBSERVACAO");
		
		historicoProntuarioDao.atualizarHistorioProntuario(historicoProntuario);
		
		
	}
	
	@Test
	public void carregaCodigosDoenca(){
		CodigoDoencaDao codigoDoencaDao = new CodigoDoencaDao();		
		List<CodigoDoenca> codigosDoencas = codigoDoencaDao.recuperarTodas();
		System.out.println(codigosDoencas.size());
	}
}
