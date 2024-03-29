﻿package br.com.sispam.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import br.com.sispam.dao.ConvenioDao;
import br.com.sispam.dominio.Convenio;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.CampoUtil;
import br.com.sispam.validation.IValidation;
import br.com.sispam.validation.ValidatorFactory;
import br.com.sispam.validation.cnpjcpf.CNPJValidator;

public class ConvenioFacade {
	private ConvenioDao convenioDao; 
    private CNPJValidator validador;
    private String cnpjAux;
	/**
	 * Salva um convênio.
	 * @param convenio
	 * @throws CampoInvalidoException
	 */
	public void salvaConvenio(Convenio convenio) throws CampoInvalidoException{

		try{
			convenioDao = new ConvenioDao();		
			convenioDao.incluirConvenio(convenio);
		}catch(Exception e){
			e.getStackTrace();
		}
	}

	/**
	 * Exclui um convênio.
	 * @param convenio
	 * 
	 */
	public void excluiConvenio(Convenio convenio) throws EntityExistsException{

		convenioDao = new ConvenioDao();
		convenioDao.excluirConvenio(convenio.getCnpj());							
					
	}
	
	/**
	 * Recupera o convênio pelo ID.
	 * @param id
	 * @return
	 */
	public Convenio recuperarPeloId(int id){
		this.convenioDao = new ConvenioDao();
		Convenio convenio = null;
		if(id != 0){
			convenio = this.convenioDao.recuperarPeloId(id);
		}
		return convenio;
	}

	/**
	 * verifica existencia do convenio.
	 * @param convenio
	 * @throws CampoInvalidoException 
	 * 
	 */
	public void verificaExistencia(Convenio convenio) throws CampoInvalidoException{
		convenioDao = new ConvenioDao();				
		Convenio convenioNew = convenioDao.consultarConvenioPorCnpj(convenio.getCnpj());
		if(convenioNew != null && convenio.getId() != convenioNew.getId()){
			throw new CampoInvalidoException("Convênio já cadastrado com esse cnpj!");
		}

		List<Convenio> convenios = convenioDao.consultarConvenioPorDescricao(convenio.getNome());

		if(convenios != null && convenios.size() > 0){
			for (Convenio convenio2 : convenios) {
				if(convenio2 != null && convenio.getId() != convenio2.getId() ){
					throw new CampoInvalidoException("Convênio já cadastrado com esse nome!");
				}
			}
		}

	}


	/**
	 * Pesquisa convenio por CNPJ.
	 * @param convenio
	 * @throws CampoInvalidoException 
	 * 
	 */
	public List<Convenio> pesquisaConvenio(Convenio convenio) throws CampoInvalidoException{
		convenioDao = new ConvenioDao();
		Convenio convenioRetornado = null;
		List<Convenio> conveniosRetornados = null;
		try {
			if((convenio.getCnpj() == null || convenio.getCnpj().isEmpty()) && (convenio.getNome() == null || convenio.getNome().isEmpty())){
				throw new CampoInvalidoException("Preencha um dos campos para efetuar a pesquisa!");
			}else if(convenio.getCnpj() != null && !convenio.getCnpj().isEmpty()) {
				conveniosRetornados = new ArrayList<Convenio>();
				cnpjAux = convenio.getCnpj();
				cnpjAux = CampoUtil.replaceCampo(".", cnpjAux);
				cnpjAux = CampoUtil.replaceCampo("/", cnpjAux);
				cnpjAux = CampoUtil.replaceCampo("-", cnpjAux);
				convenio.setCnpj(cnpjAux);
				
				convenioRetornado = convenioDao.consultarConvenioPorCnpj(convenio.getCnpj());
				conveniosRetornados.add(convenioRetornado);
			}else{
				conveniosRetornados = convenioDao.consultarConvenioPorDescricao(convenio.getNome());
			}

		} catch (NoResultException e) {
			throw new CampoInvalidoException("Nenhum registro encontrado");
		}
		return conveniosRetornados;
	}

	/**
	 * Recupera os últimos convênios cadastrados.
	 * @return
	 */
	public List<Convenio> recuperarUltimosCadastrados() {
		this.convenioDao = new ConvenioDao();
		return this.convenioDao.recuperarUltimosCadastrados();
	}
	/**
	 * Valida os campos que devem ser inteiros.
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
	 * valida os campos do convenio da tela passado.
	 * @param convenio
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Convenio convenio) throws CampoInvalidoException{
	
		if(convenio != null){
			//retira a mascára do cpf
			if(convenio.getCnpj() != null){
				convenio.setCnpj(convenio.getCnpj().replaceAll("[.]", ""));
				convenio.setCnpj(convenio.getCnpj().replaceAll("[/]", ""));
				convenio.setCnpj(convenio.getCnpj().replaceAll("[-]", ""));
			}
			validador = (CNPJValidator) ValidatorFactory
			.getInstance().getValidator(IValidation.VALIDATOR_CNPJ);
			validador.setCnpj(convenio.getCnpj());
			if(convenio.getNome() == null || convenio.getNome().length() == 0){
				throw new CampoInvalidoException("Campo Nome do Convênio inválido");
			}						
			if(convenio.getCnpj() == null || convenio.getCnpj().isEmpty() || convenio.getCnpj().length() < 14 || !validador.isValid()) {
				throw new CampoInvalidoException("Campo CNPJ inválido");
			}
			if(convenio.getEndereco() == null || convenio.getEndereco().length() == 0){
				throw new CampoInvalidoException("Campo Endereço inválido");
			}			
			if(convenio.getCidade() == null || convenio.getCidade().length() == 0){
				throw new CampoInvalidoException("Campo Cidade inválido");
			}
			if(convenio.getEstado() ==  null || convenio.getEstado().isEmpty()){
				throw new CampoInvalidoException("Campo Estado inválido");
			}																
			
		}
	}
	
	/**
	 * Recupera todos os convênios cadastrados.
	 * @return
	 */
	public List<Convenio> recuperarTodos(){
		this.convenioDao = new ConvenioDao();
		return this.convenioDao.recuperarTodos();
	}
	
}
