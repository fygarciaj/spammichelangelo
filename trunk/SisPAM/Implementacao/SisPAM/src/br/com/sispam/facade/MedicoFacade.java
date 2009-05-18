package br.com.sispam.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Dia;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.Cripto;

public class MedicoFacade {

	private UsuarioFacade usuarioFacade;
	private MedicoDao medicoDao;
	
	/**
	 * @descricao: valida os campos do medico da tela passado.
	 * @param Medico
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Medico medico) throws CampoInvalidoException{
		if(medico != null){
			this.usuarioFacade = new UsuarioFacade();
			this.usuarioFacade.validaCampos(medico.getUsuario());
			if(medico.getCrmUf() == null || medico.getCrmUf().length() == 0){
				throw new CampoInvalidoException("Campo UF-CRM inválido");
			}
		}
	}

	public void validaCampoDias(String builder) throws CampoInvalidoException{
		if(builder== null || builder.length() == 0){
			throw new CampoInvalidoException("Preencha os dias de Trabalho!");
		}
	}

	public void verificaCrmExistente(int crm, int id) throws CampoInvalidoException {
		this.medicoDao = new MedicoDao();
		Medico medico = this.medicoDao.recuperaPeloCrm(crm);
		if(medico != null && id != medico.getId()){
			throw new CampoInvalidoException("Este CRM está em uso!");
		}
	}
	
	/**
	 * @descricao: Salva o médico no banco de dados.
	 * @param medico
	 */
	public void salvarMedico(Medico medico){
		Cripto cripto = new Cripto();
		medico.getUsuario().setSenha(cripto.criptografar(medico.getUsuario().getSenha()));
		this.medicoDao.salvarMedico(medico);
	}
	
	/**
	 * @descricao: Recupera os últimos médicos cadastrados.
	 * @return
	 */
	public List<Medico> recuperarUltimosCadastrados() {
		this.medicoDao = new MedicoDao();
		List<Medico> lista = this.medicoDao.recuperarUltimosCadastrados();
		return montaDiasMedico(lista);
	}

/**
 * @descricao: Monta os dias em enums da lista de médicos passada.
 * @param lista
 * @return
 */
public List<Medico> montaDiasMedico(List<Medico> lista){
	
	if(lista!= null && lista.size() > 0){
		for (Medico medico : lista) {
			montaMedico(medico);
		}
	}
	return lista;
	
}

/**
 * @descricao: Monta as enums dos dias dos médicos.
 * @param medico
 * @return
 */
public Medico montaMedico(Medico medico){
	if(medico != null){
		List<Dia> listaDias = new ArrayList<Dia>();
		String diasMedico = medico.getDataAtendimento();
		char[] dias = diasMedico.toCharArray();
		for(char dia: dias){
			if(dia != '-'){
				listaDias.add(Dia.newInstance(dia));	
			}
		}
		medico.setDias(listaDias);
	}
	return medico;
}
	
	/**
	 * @descricao: Remove o médico do sistema.
	 * @param id
	 */
	public void removerMedico(int id){
		this.medicoDao = new MedicoDao();
		Medico medico = this.medicoDao.recuperaPeloId(id);
		this.medicoDao.remover(medico);
	}
	
	/**
	 * @descricao: Recupera todos os médicos do banco.
	 * @return
	 */
	public List<Medico> recuperarTodos(){
		this.medicoDao = new MedicoDao();
		return this.medicoDao.recuperarTodos();
	}
	
	/**
	 * @descricao: Recupera o Médico pelo ID.
	 * @param id
	 * @return
	 */
	public Medico recuperar(int id){
		this.medicoDao = new MedicoDao();
		return this.medicoDao.recuperaPeloId(id);
	}
	

}

