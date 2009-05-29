package br.com.sispam.facade;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import br.com.sispam.dao.CompromissoDao;
import br.com.sispam.dominio.Compromisso;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;

public class CompromissoFacade {
	private CompromissoDao compromissoDao; 

	/**
	 * @descricao: Salvar compromissos.
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
	 * @descricao: Excluir compromissos.
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
	 * @descricao: verifica existencia do compromisso.
	 * @param compromisso
	 * @throws CampoInvalidoException 
	 * 
	 */
	public void verificaExistencia(Compromisso compromisso) throws CampoInvalidoException{
		compromissoDao = new CompromissoDao();				
		List<Compromisso> compromissoNew = null;
		compromissoNew = (compromissoDao.consultarCompromissoUnico(compromisso));
		if(compromissoNew != null && compromissoNew.size() > 0){
			throw new CampoInvalidoException("Já existe Compromisso no período informado!");
		}
	}


	/**
	 * @descricao: Pesquisa compromisso
	 * @param compromisso
	 * @throws CampoInvalidoException 
	 * 
	 */
	public List<Compromisso> pesquisaCompromisso(Compromisso compromisso) throws CampoInvalidoException{
		compromissoDao = new CompromissoDao();
		Compromisso compromissoRetornado = null;
		List<Compromisso> compromissosRetornados = null;
		try {
			if((compromisso.getMedico().getId() == 0)  
					&& (compromisso.getData() == null)){
				throw new CampoInvalidoException("Preencha os campos \"Médico\" e \"Data\" para efetuar a pesquisa!");
			}else if((compromisso.getMedico().getId() != 0) && (compromisso.getData() != null)){
				compromissosRetornados = compromissoDao.consultarCompromissos(compromisso);
			}
		} catch (NoResultException e) {
			throw new CampoInvalidoException("Nenhum registro encontrado");
		}
		return compromissosRetornados;
	}

	/**
	 * @descricao: Recupera os últimos compromissos do médico.
	 * @param compromisso
	 * @return
	 */
	public List<Compromisso> recuperarCompromissosDiaAtual(Compromisso compromisso){
		this.compromissoDao = new CompromissoDao();
		return this.compromissoDao.consultarCompromissos(compromisso);
	}


	/**
	 * @descricao: Valida os campos que devem ser inteiros.
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
						throw new CampoInvalidoException("Campo "+nomeCampo+" inválido!");
					}
				}
				catch (NumberFormatException e) {
					throw new CampoInteiroException(nomeCampo+" é um campo inteiro!");
				}
			}
		}
	}

	/**
	 * @descricao: valida os campos do compromissos
	 * @param compromisso
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Compromisso compromisso) throws CampoInvalidoException{

		if(compromisso != null){

			if(compromisso.getMedico()== null){
				throw new CampoInvalidoException("Campo Nome do Médico inválido!");
			}						
			if(compromisso.getTipo().contains("0") || compromisso.getTipo().isEmpty()) {
				throw new CampoInvalidoException("Campo Tipo de Evento inválido!");
			}
			if(compromisso.getData() == null ){
				throw new CampoInvalidoException("Campo Data inválido!");
			}
			if(compromisso.getDescricao()== null || compromisso.getDescricao().length() == 0){
				throw new CampoInvalidoException("Campo Descrição inválido!");
			}						

		}
	}
	
	public void validaHora(Compromisso compromisso) throws CampoInvalidoException{

		if(compromisso != null){
			if(compromisso.getHoraInicial() >= compromisso.getHoraFinal()){
				throw new CampoInvalidoException("Hora Inicial deve ser menor que Hora Final!");
			}
		}
	}
}
