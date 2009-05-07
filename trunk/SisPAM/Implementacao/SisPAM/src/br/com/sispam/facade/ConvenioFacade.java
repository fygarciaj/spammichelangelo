package br.com.sispam.facade;



import java.util.List;

import br.com.sispam.dao.ConvenioDao;




import br.com.sispam.dominio.Convenio;


import br.com.sispam.excecao.CampoInvalidoException;



public class ConvenioFacade {
	private ConvenioDao convenioDao; 
		
	public boolean salvaConvenio(Convenio convenio) throws CampoInvalidoException{
		validaCampos(convenio);
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
	
	public void excluiConvenio(Convenio convenio){
		
		convenioDao = new ConvenioDao();
		
		try{			
			convenioDao.excluirConvenio(convenio.getCnpj());							
		}catch(Exception e){
			e.getStackTrace();
		}					
	}
	
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
	 * @descricao: Recupera os últimos usuários cadastrados.
	 * @return
	 */
	public List<Convenio> recuperarUltimosCadastrados() {
		this.convenioDao = new ConvenioDao();
		return this.convenioDao.recuperarUltimosCadastrados();
	}
	
	/**
	 * @descricao: valida os campos do convenio da tela passado.
	 * @param convenio
	 * @throws CampoInvalidoException 
	 */
	private void validaCampos(Convenio convenio) throws CampoInvalidoException{

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
			if(convenio.getCodigoANS() == 0){
				throw new CampoInvalidoException("Campo Código ANS inválido");
			}
			if(convenio.getCidade() == null){
				throw new CampoInvalidoException("Campo Cidade inválido");
			}
			if(convenio.getEstado() ==  null || convenio.getEstado().isEmpty()){
				throw new CampoInvalidoException("Campo Estado inválido");
			}
			if(convenio.getCep() == 0){
				throw new CampoInvalidoException("Campo CEP inválido");
			}			
			if(convenio.getDdd() == 0){
				throw new CampoInvalidoException("Campo DDD inválido");
			}
			if(convenio.getTelefone() == 0){
				throw new CampoInvalidoException("Campo Telefone inválido");
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
