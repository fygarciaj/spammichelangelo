package br.com.sispam.facade;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.sispam.dao.PacienteDao;
import br.com.sispam.dominio.Paciente;
import br.com.sispam.dominio.Usuario;
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

	/**
	 * @descricao: Recupera vários pacientes pelo nome se existir..
	 * @param paciente
	 * @return
	 * @throws CampoInvalidoException
	 */
	public List<Paciente> consultar(Paciente paciente) throws CampoInvalidoException{
		List<Paciente> lista = null;
		Paciente pacienteRecuperado = null;
	
		if((paciente.getUsuario().getCpf() == null || paciente.getUsuario().getCpf().isEmpty())
				&& (paciente.getUsuario().getNome() == null || paciente.getUsuario().getNome().isEmpty())){
			throw new CampoInvalidoException("Preencha um dos campos para realizar a pesquisa!");
		}
		else if(paciente.getUsuario().getCpf() != null && paciente.getUsuario().getCpf().trim().length() > 0){
			lista = new ArrayList<Paciente>();
			pacienteRecuperado = pacienteDao.recuperarPeloCpf(paciente.getUsuario().getCpf());
			if(pacienteRecuperado != null){
				lista.add(pacienteRecuperado);
			}

		}else{
			lista = pacienteDao.recuperarPeloNome(paciente.getUsuario().getNome());
		}

		return lista;

	}

	/**
	 * @descricao: Valida os campos do paciente.
	 * @param paciente
	 * @param dataNascimento
	 * @throws CampoInvalidoException
	 * @throws ParseException
	 */
	public void validaCampos(Paciente paciente, String dataNascimento) throws CampoInvalidoException, ParseException{
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		//valida os campos de usuario
		usuarioFacade.validaCampos(paciente.getUsuario(), dataNascimento);

		if(paciente.getConvenio() != null && paciente.getConvenio().getId() == 0){
			paciente.setConvenio(null);
		}
		if(paciente.getDescricaoAcomodacao() == null || paciente.getDescricaoAcomodacao().isEmpty()){
			paciente.setDescricaoAcomodacao(null);
		}
		if(paciente.getPlano() == null || paciente.getPlano().isEmpty()){
			paciente.setPlano(null);
		}
		//verifica se o cpf está sendo usado
		usuarioFacade.verificaCpfJaExistente(paciente.getUsuario().getCpf(), paciente.getUsuario().getId());
	}

	/**
	 * @descricao: Recupera os últimos pacientes cadastrados no sistema.
	 * @return
	 */
	public List<Paciente> recuperaUltimosCadastrados(){
		return this.pacienteDao.recuperaUltimosCadastrados();
	}

	/**
	 * @descricao: Recupera o paciente pelo seu ID.
	 * @param id
	 * @return
	 */
	public Paciente recuperarPeloId(int id){
		return this.pacienteDao.recuperarPeloId(id);
	}
}
