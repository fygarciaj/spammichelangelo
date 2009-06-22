package br.com.sispam.facade;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.RollbackException;

import br.com.sispam.dao.PacienteDao;
import br.com.sispam.dominio.Paciente;
import br.com.sispam.enums.Status;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.Cripto;

public class PacienteFacade {

	private PacienteDao pacienteDao;

	public PacienteFacade(){
		this.pacienteDao = new PacienteDao();
	}

	/**
	 * : Salva o paciente.
	 * @param paciente
	 * @return
	 */
	public Paciente salvar(Paciente paciente){
		Cripto cripto = new Cripto();
		String senha = paciente.getUsuario().getSenha();
		paciente.getUsuario().setSenha(cripto.criptografar(senha));
		paciente.getUsuario().setStatus(Status.ATIVO.getCodigo());
		return this.pacienteDao.salvar(paciente);
	}

	/**
	 * : Remove o paciente do sistema.
	 * @param id
	 * @throws CampoInvalidoException 
	 */
	public void removerPaciente(int id){
		Paciente paciente = null;
		paciente = this.pacienteDao.recuperarPeloId(id);
		paciente.getUsuario().setStatus(Status.INATIVO.getCodigo());
		this.pacienteDao.salvar(paciente);
	}

	public Paciente recuperarPeloUsuario(int idUsuario){
		return this.pacienteDao.recuperarPeloUsuario(idUsuario);
	}

	/**
	 * : Recupera vários pacientes pelo nome se existir..
	 * @param paciente
	 * @return
	 * @throws CampoInvalidoException
	 */
	public List<Paciente> consultar(Paciente paciente) throws CampoInvalidoException{
		List<Paciente> lista = null;
		Paciente pacienteRecuperado = null;

		if(paciente != null && paciente.getUsuario() != null && paciente.getUsuario().getCpf() != null){
			String cpf = paciente.getUsuario().getCpf().replaceAll("[.]", "");
			cpf = cpf.replaceAll("[-]", "");
			paciente.getUsuario().setCpf(cpf);
		}

		if((paciente.getUsuario().getCpf() == null || paciente.getUsuario().getCpf().isEmpty())
				&& (paciente.getUsuario().getNome() == null || paciente.getUsuario().getNome().isEmpty())){
			throw new CampoInvalidoException("Preencha um dos campos para realizar a pesquisa!");
		}
		else if(paciente.getUsuario().getCpf() != null && paciente.getUsuario().getCpf().trim().length() > 0){
			lista = new ArrayList<Paciente>();
			pacienteRecuperado = pacienteDao.recuperarPeloCpf(paciente.getUsuario().getCpf(), Status.ATIVO.getCodigo());
			if(pacienteRecuperado != null){
				lista.add(pacienteRecuperado);
			}

		}else{
			lista = pacienteDao.recuperarPeloNome(paciente.getUsuario().getNome(), Status.ATIVO.getCodigo());
		}

		return lista;

	}

	/**
	 * : Valida os campos do paciente.
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
	 * : Recupera os últimos pacientes cadastrados no sistema.
	 * @return
	 */
	public List<Paciente> recuperaUltimosCadastrados(){
		return this.pacienteDao.recuperaUltimosCadastrados();
	}

	/**
	 * : Recupera todos os pacientes do banco de dados.
	 * @return
	 */
	public List<Paciente> recuperarTodos(){
		return this.pacienteDao.recuperarTodos();
	}

	/**
	 * : Recupera o paciente pelo seu ID.
	 * @param id
	 * @return
	 */
	public Paciente recuperarPeloId(int id){
		return this.pacienteDao.recuperarPeloId(id);
	}
}
