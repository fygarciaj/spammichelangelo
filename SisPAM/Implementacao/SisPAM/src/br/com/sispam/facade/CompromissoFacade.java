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
//	public void salvaCompromisso(Compromisso compromisso) throws CampoInvalidoException{
//
//		try{
//			compromissoDao = new CompromissoDao();		
//			compromissoDao.incluirCompromisso(compromisso);
//		}catch(Exception e){
//			e.getStackTrace();
//		}
//	}
//
//	/**
//	 * @descricao: Excluir compromissos.
//	 * @param compromisso
//	 * 
//	 */
//	public void excluiCompromisso(Compromisso compromisso){
//
//		compromissoDao = new CompromissoDao();
//
//		try{			
//			compromissoDao.excluirCompromisso(compromisso.getCodMedico(),
//											  compromisso.getData(), 
//											  compromisso.getHoraInicio(), 
//											  compromisso.getCodMedico());							
//		}catch(Exception e){
//			e.getStackTrace();
//		}					
//	}
//	
//	public Compromisso recuperarPeloId(int id){
//		this.compromissoDao = new CompromissoDao();
//		Compromisso compromisso = null;
//		if(id != 0){
//			compromisso = this.compromissoDao.recuperarPeloId(id);
//		}
//		return compromisso;
//	}
//
//	/**
//	 * @descricao: verifica existencia do compromisso.
//	 * @param compromisso
//	 * @throws CampoInvalidoException 
//	 * 
//	 */
//	public void verificaExistencia(Compromisso compromisso) throws CampoInvalidoException{
//		compromissoDao = new CompromissoDao();				
//		Compromisso compromissoNew = compromissoDao.consultarCompromissoGeral(compromisso.getCodMedico(),
//																			  compromisso.getData(),
//																			  compromisso.getHoraInicio(),
//																			  compromisso.getHoraFim());
//		if(compromissoNew != null){
//			throw new CampoInvalidoException("Compromisso já cadastrado!");
//		}
//	}
//
//
//	/**
//	 * @descricao: Pesquisa compromisso
//	 * @param compromisso
//	 * @throws CampoInvalidoException 
//	 * 
//	 */
//	public Compromisso pesquisaCompromisso(Compromisso compromisso) throws CampoInvalidoException{
//		compromissoDao = new CompromissoDao();
//		Compromisso convenioRetornado = null;
//		try {
//			if((compromisso.getCodMedico() == 0 || compromisso.getCodMedico().isEmpty()) 
//					&& (compromisso.getNome() == null || compromisso.getNome().isEmpty())
//					&& (compromisso.getCnpj() == null || compromisso.getCnpj().isEmpty()) 
//					&& (compromisso.getCnpj() == null || compromisso.getCnpj().isEmpty()) ){
//				throw new CampoInvalidoException("Preencha um dos campos para efetuar a pesquisa!");
//			}else if(compromisso.getCnpj() != null && !compromisso.getCnpj().isEmpty()) {
//				convenioRetornado = convenioDao.consultarConvenioPorCnpj(compromisso.getCnpj());
//			}else{
//				convenioRetornado = convenioDao.consultarConvenioPorDescricao(convenio.getNome());
//			}
//
//		} catch (NoResultException e) {
//			throw new CampoInvalidoException("Nenhum registro encontrado");
//		}
//		return convenioRetornado;
//	}
//	
//	
//
//	/**
//	 * @descricao: Recupera os últimos convênios cadastrados.
//	 * @return
//	 */
//	public List<Compromisso> recuperarUltimosCadastrados() {
//		this.convenioDao = new ConvenioDao();
//		return this.convenioDao.recuperarUltimosCadastrados();
//	}
//	/**
//	 * @descricao: Valida os campos que devem ser inteiros.
//	 * @param mapa
//	 * @throws CampoInvalidoException
//	 */
//	public void verificaCampoInteiro(Map<String, String> mapa) throws CampoInvalidoException{
//
//		if(mapa != null && mapa.size() > 0){
//			for(String nomeCampo: mapa.keySet()){
//				try{
//					String campoString = mapa.get(nomeCampo);
//
//					if(campoString != null && campoString.trim().length() > 0){
//						int valorCampo = Integer.parseInt(campoString);
//					}else{
//						throw new CampoInvalidoException("Campo "+nomeCampo+" inválido!");
//					}
//				}
//				catch (NumberFormatException e) {
//					throw new CampoInteiroException(nomeCampo+" é um campo inteiro!");
//				}
//			}
//		}
//	}
//
//	/**
//	 * @descricao: valida os campos do convenio da tela passado.
//	 * @param convenio
//	 * @throws CampoInvalidoException 
//	 */
//	public void validaCampos(Convenio convenio) throws CampoInvalidoException{
//
//		if(convenio != null){
//
//			if(convenio.getNome() == null || convenio.getNome().length() == 0){
//				throw new CampoInvalidoException("Campo Nome do Convênio inválido");
//			}						
//			if(convenio.getCnpj() == null || convenio.getCnpj().isEmpty()) {
//				throw new CampoInvalidoException("Campo CNPJ inválido");
//			}
//			if(convenio.getEndereco() == null || convenio.getEndereco().length() == 0){
//				throw new CampoInvalidoException("Campo Endereço inválido");
//			}			
//			if(convenio.getCidade() == null){
//				throw new CampoInvalidoException("Campo Cidade inválido");
//			}
//			if(convenio.getEstado() ==  null || convenio.getEstado().isEmpty()){
//				throw new CampoInvalidoException("Campo Estado inválido");
//			}												
//			if(convenio.getSite() == null || convenio.getSite().length() == 0){
//				throw new CampoInvalidoException("Campo Site inválido");
//			}
//			if(convenio.getEmail() == null || convenio.getEmail().length() == 0){
//				throw new CampoInvalidoException("Campo E-mail inválido");
//			}						
//
//		}
//	}	
}
