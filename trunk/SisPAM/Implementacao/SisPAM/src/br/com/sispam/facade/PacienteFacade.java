package br.com.sispam.facade;

import java.text.ParseException;
import java.util.List;

import br.com.sispam.dao.PacienteDao;
import br.com.sispam.dominio.Paciente;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.Cripto;
import br.com.sispam.util.DataUtil;

public class PacienteFacade {

	private PacienteDao pacienteDao;

	public PacienteFacade(){
		this.pacienteDao = new PacienteDao();
	}

	/**
	 * @descricao: Salva o paciente.
	 * @param paciente
	 * @return
	 */
	public Paciente salvar(Paciente paciente){
		Cripto cripto = new Cripto();
		String senha = paciente.getUsuario().getSenha();
		paciente.getUsuario().setSenha(cripto.criptografar(senha));
		return this.pacienteDao.salvar(paciente);
	}
	
	/**
	 * @descricao: Remove o paciente do sistema.
	 * @param id
	 */
	public void removerPaciente(int id){
		Paciente paciente = this.pacienteDao.recuperarPeloId(id);
		this.pacienteDao.removerPaciente(paciente);
	}


	public void validaCampos(Paciente paciente, String data) throws CampoInvalidoException, ParseException{
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		//valida os campos de usuario
		usuarioFacade.validaCampos(paciente.getUsuario());
		
		if(paciente.getConvenio().getId() == 0){
			paciente.setConvenio(null);
		}
		if(data == null || data.trim().length() == 0){
			throw new CampoInvalidoException("Data de assinatura é obrigatória!");
		}
			
		paciente.getUsuario().setDataNascimento(DataUtil.stringToDate(data));
		//verifica se o cpf está sendo usado
		usuarioFacade.verificaCpfJaExistente(paciente.getUsuario().getCpf(), paciente.getUsuario().getId());
	}
	
	public List<Paciente> recuperaUltimosCadastrados(){
		return this.pacienteDao.recuperaUltimosCadastrados();
	}
}
