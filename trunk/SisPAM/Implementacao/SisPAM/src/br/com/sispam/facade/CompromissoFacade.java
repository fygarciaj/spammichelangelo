package br.com.sispam.facade;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import br.com.sispam.dao.AgendamentoDao;
import br.com.sispam.dao.CompromissoDao;
import br.com.sispam.dominio.Agendamento;
import br.com.sispam.dominio.Compromisso;
import br.com.sispam.enums.StatusAgendamento;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.DataUtil;

public class CompromissoFacade {
	private CompromissoDao compromissoDao; 
	private List<Agendamento> agendamentosRetornados = null;
	private AgendamentoDao agendamentoDao;
	private Agendamento agendamento;

	/**
	 * : Salvar compromissos.
	 * @param compromisso
	 * @throws CampoInvalidoException
	 */
	public void salvaCompromisso(Compromisso compromisso) throws CampoInvalidoException{

		try{
			compromissoDao = new CompromissoDao();		
			compromissoDao.incluirCompromisso(compromisso);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * : Excluir compromissos.
	 * @param compromisso
	 * 
	 */
	public void excluiCompromisso(Compromisso compromisso){

		compromissoDao = new CompromissoDao();

		try{			
			compromissoDao.excluirCompromisso(compromisso); 

		}catch(Exception e){
			e.printStackTrace();
		}					
	}

	public Compromisso recuperarPeloId(int id){
		this.compromissoDao = new CompromissoDao();
		Compromisso compromisso = null;
		if(id != 0){
			compromisso = this.compromissoDao.recuperarPeloId(id);
		}
		return compromisso;
	}

	/**
	 * : verifica existencia do compromisso ou agendamentos.
	 * @param compromisso
	 * @throws CampoInvalidoException 
	 * 
	 */
	public void verificaExistencia(Compromisso compromisso, int idAgendamento) throws CampoInvalidoException{
		compromissoDao = new CompromissoDao();				
		List<Compromisso> compromissoNew = null;
		List<Agendamento> agendamentosNew = null; 

		compromissoNew = (compromissoDao.consultarCompromissoUnico(compromisso));
		if(compromissoNew != null && compromissoNew.size() > 0){
			for(Compromisso comp: compromissoNew){
				if(comp.getId() != compromisso.getId()){
					throw new CampoInvalidoException("Já existe Compromisso no período informado!");
				}
			}
		}

		agendamentosNew = (compromissoDao.consultarAgendamentoUnico(compromisso));
		if(agendamentosNew != null && agendamentosNew.size() > 0){
			for(Agendamento agend: agendamentosNew){
				if(agend.getId() != 0 && agend.getId() != idAgendamento){
					throw new CampoInvalidoException("Já existe Agendamento no período informado!");
				}
			}
		}
	}


	/**
	 * : Pesquisa compromisso
	 * @param compromisso
	 * @throws CampoInvalidoException 
	 * 
	 */
	public List<Compromisso> pesquisaCompromisso(Compromisso compromisso) throws CampoInvalidoException{
		compromissoDao = new CompromissoDao();
		agendamentoDao = new AgendamentoDao();
		agendamento = new Agendamento();
		List<Compromisso> compromissosRetornados = null;

		try {
			if((compromisso.getMedico().getId() == 0)  
					&& (compromisso.getData() == null)){
				throw new CampoInvalidoException("Preencha os campos \"Médico\" e \"Data\" para efetuar a pesquisa!");

			}else if((compromisso.getMedico().getId() != 0) && (compromisso.getData() != null)){
				compromissosRetornados = compromissoDao.consultarCompromissos(compromisso);
				agendamento.setMedico(compromisso.getMedico());
				agendamento.setData(compromisso.getData());
				agendamentosRetornados = agendamentoDao.consultar(agendamento);

			}else if(compromisso.getMedico().getId() != 0 && compromisso.getData() == null){
				compromissosRetornados = compromissoDao.consultarCompromissosMedico(compromisso);
				agendamento.setMedico(compromisso.getMedico());
				agendamentosRetornados = agendamentoDao.consultar(agendamento);

			}else if((compromisso.getMedico().getId() == 0 && compromisso.getData() != null)){
				throw new CampoInvalidoException("Selecione o médico!");
			}

			if ((compromissosRetornados == null || compromissosRetornados.size() == 0)&& (agendamentosRetornados == null || agendamentosRetornados.size() == 0)){
				throw new CampoInvalidoException("Compromissos ou Agendamentos não encontrados.");
			}

		} catch (NoResultException e) {
			throw new CampoInvalidoException("Nenhum registro encontrado");
		}
		return compromissosRetornados;
	}

	public void setAgendamentos(List<Agendamento> agendamentosRetornados){
		this.agendamentosRetornados = agendamentosRetornados;
	}

	public List<Agendamento> getAgendamentos(){
		return agendamentosRetornados;
	}

	/**
	 * : Recupera os últimos compromissos do médico.
	 * @param compromisso
	 * @return
	 */
	public List<Compromisso> recuperarCompromissosDiaAtual(Compromisso compromisso){
		this.compromissoDao = new CompromissoDao();
		agendamentoDao = new AgendamentoDao();
		agendamento = new Agendamento();
		List<Compromisso> compromissosRetornados = null;

		compromissosRetornados = this.compromissoDao.consultarCompromissos(compromisso);
		agendamento.setMedico(compromisso.getMedico());
		agendamento.setData(compromisso.getData());
		agendamentosRetornados = agendamentoDao.consultar(agendamento);

		return compromissosRetornados;
	}


	/**
	 * : Valida os campos que devem ser inteiros.
	 * @param mapa
	 * @throws CampoInvalidoException
	 */
	public void verificaCampoInteiro(Map<String, String> mapa) throws CampoInvalidoException{

		if(mapa != null && mapa.size() > 0){
			for(String nomeCampo: mapa.keySet()){
				try{
					String campoString = mapa.get(nomeCampo);

					if(campoString != null && campoString.trim().length() > 0){
						int valorCampo = Integer.parseInt(campoString);
					}else{
						throw new CampoInvalidoException("Campo "+nomeCampo+" deve ser informado!");
					}
				}
				catch (NumberFormatException e) {
					throw new CampoInteiroException(nomeCampo+" é um campo inteiro!");
				}
			}
		}
	}

	/**
	 * : valida os campos do compromissos
	 * @param compromisso
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Compromisso compromisso, String data) throws CampoInvalidoException{

		if(compromisso != null){

			if(compromisso.getMedico()== null){
				throw new CampoInvalidoException("O campo Médico deve ser informado!");
			}						
			if(compromisso.getTipo().contains("0") || compromisso.getTipo().isEmpty()) {
				throw new CampoInvalidoException("O campo Tipo de Evento deve ser informado!");
			}
			
			try {
				compromisso.setData(DataUtil.stringToDate(data));
			} catch (ParseException e) {
				throw new CampoInvalidoException("Preencha o campo Data conforme o formato DD/MM/AAAA ou use o Calendário.");
			}
								
			if(compromisso.getDescricao()== null || compromisso.getDescricao().length() == 0){
				throw new CampoInvalidoException("Campo Descrição inválido!");
			}
		}
	}

	public void validaHora(String horaIni, String horaFin) throws CampoInvalidoException{
		if(Integer.parseInt(horaIni) > 2359){
			throw new CampoInvalidoException("Hora Inicial deve ser menor ou igual a 23:59!");
		}
		if(Integer.parseInt(horaFin) > 2359){
			throw new CampoInvalidoException("Hora Final deve ser menor ou igual a 23:59!");
		}

		if(Integer.parseInt(horaIni) >= Integer.parseInt(horaFin)){
			throw new CampoInvalidoException("Hora Inicial deve ser menor que Hora Final!");
		}
	}

}
