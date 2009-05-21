package br.com.sispam.teste.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.sispam.dao.HistoricoProntuarioDao;
import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.HistoricoProntuario;

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
		HistoricoProntuario historicoProntuario = new HistoricoProntuario();
		HistoricoProntuarioDao historicoProntuarioDao = new HistoricoProntuarioDao();
		
		//historicoProntuario.set
		
		
	}
}
