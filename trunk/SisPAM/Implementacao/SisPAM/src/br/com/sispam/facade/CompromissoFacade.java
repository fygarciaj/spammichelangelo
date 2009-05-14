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
			e.getStackTrace();
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
			compromissoDao.excluirCompromisso(compromisso.getMedico().getUsuario().getId(),
											  compromisso.getData(), 
											  compromisso.getHoraInicio()); 
											  							
		}catch(Exception e){
			e.getStackTrace();
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
		Compromisso compromissoNew = compromissoDao.consultarCompromissoUnico(compromisso.getMedico().getUsuario().getId(),
																			  compromisso.getData(),
																			  compromisso.getHoraInicio(),
																			  compromisso.getHoraFim());
		if(compromissoNew != null){
			throw new CampoInvalidoException("Compromisso já cadastrado!");
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
			if((compromisso.getMedico().getUsuario().getNome() == null)  
					&& (compromisso.getData() == null || compromisso.getData().isEmpty())
					&& compromisso.getTipo() == null || compromisso.getTipo().isEmpty()){
				throw new CampoInvalidoException("Preencha os campos \"Médico\" e \"Data\" ou \"Tipo de Evento\" para efetuar a pesquisa!");
			}else if(){
			
			}else{
				compromissoRetornado = compromissoDao.consultarCompromissos(compromisso.getMedico().getUsuario().getId(), compromisso.getData());
				compromissosRetornados.add(compromissoRetornado);
			}

		} catch (NoResultException e) {
			throw new CampoInvalidoException("Nenhum registro encontrado");
		}
		return compromissosRetornados;
	}
	
	

	/**
	 * @descricao: Recupera os Compromissos por dia
	 * @return
	 */
	public List<Compromisso> recuperarCompromissosDiaAtual() {
		this.compromissoDao = new CompromissoDao();
		return this.compromissoDao.recuperarCompromissosHoje();
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

			if(compromisso.getMedico().getUsuario().getNome()== null || compromisso.getMedico().getUsuario().getNome().isEmpty()){
				throw new CampoInvalidoException("Campo Nome do Médico inválido");
			}						
			if(compromisso.getTipo() == null || compromisso.getTipo().isEmpty()) {
				throw new CampoInvalidoException("Campo Tipo de Evento inválido");
			}
			if(compromisso.getData() == null || compromisso.getData().isEmpty()){
				throw new CampoInvalidoException("Campo Data inválido");
			}
			if(compromisso.getDescricao()== null || compromisso.getDescricao().length() == 0){
				throw new CampoInvalidoException("Campo E-mail inválido");
			}						

		}
	}	
}
