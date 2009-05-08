package br.com.sispam.facade;



import java.util.List;
import java.util.Map;

import br.com.sispam.dao.ConvenioDao;




import br.com.sispam.dominio.Convenio;


import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;



public class ConvenioFacade {
	private ConvenioDao convenioDao; 
	
	/**
	 * @descricao: Salva um convênio.
	 * @param convenio
	 * @throws CampoInvalidoException
	 */
	public boolean salvaConvenio(Convenio convenio) throws CampoInvalidoException{
		
		boolean existencia = true;
		try{
			convenioDao = new ConvenioDao();		
			if (verificaExistencia(convenio) == false){
				convenioDao.incluirConvenio(convenio);
				existencia = false;
			}
							
		}catch(Exception e){
			e.getStackTrace();
		}
		return existencia;
	}
	
	/**
	 * @descricao: Exclui um convênio.
	 * @param convenio
	 * 
	 */
	public void excluiConvenio(Convenio convenio){
		
		convenioDao = new ConvenioDao();
		
		try{			
			convenioDao.excluirConvenio(convenio.getCnpj());							
		}catch(Exception e){
			e.getStackTrace();
		}					
	}
	
	/**
	 * @descricao: verifica existencia do convenio.
	 * @param convenio
	 * 
	 */
	public boolean verificaExistencia(Convenio convenio){
		convenioDao = new ConvenioDao();				
		boolean existencia = true;
		Convenio convenioNew = null;
		convenioNew = convenioDao.consultarConvenioPorCnpj(convenio.getCnpj());
		//convenioNew = convenioDao.consultarConvenioPorDescricao(convenio.getNome());

		if (convenioNew == null){
			existencia = false;
		}
		
		return existencia;
	}
	
	
	/**
	 * @descricao: Pesquisa convenio por CNPJ.
	 * @param convenio
	 * 
	 */
	public Convenio pesquisaConvenio(Convenio convenio){
		
		try {
			convenioDao = new ConvenioDao();
			convenioDao.consultarConvenioPorCnpj(convenio.getCnpj());			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		}
		return convenioDao.consultarConvenioPorCnpj(convenio.getCnpj());
	}
	
	/**
	 * @descricao: Recupera os últimos convênios cadastrados.
	 * @return
	 */
	public List<Convenio> recuperarUltimosCadastrados() {
		this.convenioDao = new ConvenioDao();
		return this.convenioDao.recuperarUltimosCadastrados();
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
	 * @descricao: valida os campos do convenio da tela passado.
	 * @param convenio
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Convenio convenio) throws CampoInvalidoException{

		if(convenio != null){
			
			if(convenio.getNome() == null || convenio.getNome().length() == 0){
				throw new CampoInvalidoException("Campo Nome do Convênio inválido");
			}						
			if(convenio.getCnpj() == null || convenio.getCnpj().isEmpty()) {
				throw new CampoInvalidoException("Campo CNPJ inválido");
			}
			if(convenio.getEndereco() == null || convenio.getEndereco().length() == 0){
				throw new CampoInvalidoException("Campo Endereço inválido");
			}			
			if(convenio.getCidade() == null){
				throw new CampoInvalidoException("Campo Cidade inválido");
			}
			if(convenio.getEstado() ==  null || convenio.getEstado().isEmpty()){
				throw new CampoInvalidoException("Campo Estado inválido");
			}												
			if(convenio.getSite() == null || convenio.getSite().length() == 0){
				throw new CampoInvalidoException("Campo Site inválido");
			}
			if(convenio.getEmail() == null || convenio.getEmail().length() == 0){
				throw new CampoInvalidoException("Campo E-mail inválido");
			}						
			
		}
	}	
}
