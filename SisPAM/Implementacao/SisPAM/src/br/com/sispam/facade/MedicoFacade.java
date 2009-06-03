package br.com.sispam.facade;

import java.util.ArrayList;
import java.util.List;
import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dominio.EspecialidadeMedica;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Dia;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.Cripto;

public class MedicoFacade {

	private UsuarioFacade usuarioFacade;
	private MedicoDao medicoDao;
	private EspecialidadeFacade especialidadeFacade;


	public MedicoFacade(){
		this.medicoDao = new MedicoDao();
	}


	/**
	 * : valida os campos do medico da tela passado.
	 * @param Medico
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Medico medico, String esp, String dataNascimento) throws CampoInvalidoException{
		if(medico != null){
			this.usuarioFacade = new UsuarioFacade();
			this.usuarioFacade.validaCampos(medico.getUsuario(), dataNascimento);
			if(medico.getCrmUf() == null || medico.getCrmUf().length() == 0){
				throw new CampoInvalidoException("Campo UF-CRM inválido");
			}
		}
		if(esp == null || esp.isEmpty()){
			throw new CampoInvalidoException("Adicione a especialidade do médico!");
		}

		//verifica se o cpf está sendo usado
		usuarioFacade.verificaCpfJaExistente(medico.getUsuario().getCpf(), medico.getUsuario().getId());
	}

	/**
	 * : Valida os campos dias de trabalho do medico.
	 * @param builder
	 * @throws CampoInvalidoException
	 */
	public void validaCampoDias(String builder) throws CampoInvalidoException{
		if(builder== null || builder.length() == 0){
			throw new CampoInvalidoException("Preencha os dias de Trabalho!");
		}
	}

	/**
	 * : Verifica se existe o crm passado cadastrado no banco de dados.
	 * @param crm
	 * @param id
	 * @throws CampoInvalidoException
	 */
	public void verificaCrmExistente(int crm, int id, String estado) throws CampoInvalidoException {
		Medico medico = this.medicoDao.recuperaPeloCrm(crm, estado);
		if(medico != null && id != medico.getId()){
			throw new CampoInvalidoException("Este CRM está em uso!");
		}
	}

	/**
	 * : Salva o médico no banco de dados.
	 * @param medico
	 */
	public void salvarMedico(Medico medico){
		Cripto cripto = new Cripto();
		medico.getUsuario().setSenha(cripto.criptografar(medico.getUsuario().getSenha()));
		this.medicoDao.salvarMedico(medico);
	}

	/**
	 * : Adiciona as especialidades no médico, caso seja edição apenas não deixa repetir os dados.
	 * @param medico
	 * @param esp
	 * @return
	 */
	public Medico validaEspecialidadesSalvas(Medico medico, String esp){
		this.especialidadeFacade = new EspecialidadeFacade();
		List<EspecialidadeMedica> lista = this.especialidadeFacade.recuperaEspecialidades(esp);
		medico.setEspecialidades(lista);
		return medico;
	}

	/**
	 * : Recupera os últimos médicos cadastrados.
	 * @return
	 */
	public List<Medico> recuperarUltimosCadastrados() {
		List<Medico> lista = this.medicoDao.recuperarUltimosCadastrados();
		return montaDiasMedico(lista);
	}

	/**
	 * : Monta os dias em enums da lista de médicos passada.
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
	 * : Monta as enums dos dias dos médicos.
	 * @param medico
	 * @return
	 */
	public List<String> montaMedico(Medico medico){
		List<String> diaString = new ArrayList<String>();
		List<Dia> diasList = new ArrayList<Dia>();
		if(medico != null){
			String diasMedico = medico.getDataAtendimento();
			diaString = new ArrayList<String>();

			char[] dias = diasMedico.toCharArray();
			for(char dia: dias){
				if(dia != '-'){
					diaString.add(Dia.newInstance(dia).getDescricao());	
					diasList.add(Dia.newInstance(dia));
				}
			}
			medico.setDias(diasList);

		}
		return diaString;
	}

	/**
	 * : Remove o médico do sistema.
	 * @param id
	 */
	public void removerMedico(int id){
		Medico medico = this.medicoDao.recuperaPeloId(id);
		this.medicoDao.remover(medico);
	}

	/**
	 * : Recupera todos os médicos do banco.
	 * @return
	 */
	public List<Medico> recuperarTodos(){
		return this.medicoDao.recuperarTodos();
	}

	/**
	 * : Recupera o Médico pelo ID.
	 * @param id
	 * @return
	 */
	public Medico recuperar(int id){
		return this.medicoDao.recuperaPeloId(id);
	}

	/**
	 * : Recupera o médico apartir do Usuário.
	 * @param usuario
	 * @return
	 */
	public Medico recuperar(Usuario usuario){
		if(usuario!= null){
			return this.medicoDao.recuperar(usuario);
		}else{
			return null;
		}
	}

	/**
	 * : Verifica campo inteiro.
	 * @param campo
	 * @throws CampoInvalidoException
	 */
	public void validaCampoInteiro(String campo) throws CampoInvalidoException{
		try{
			int valor = Integer.parseInt(campo);
		}catch (NumberFormatException e) {
			throw new CampoInvalidoException("CRM é campo numérico!");
		}
	}
	/**
	 * : Faz a consulta ou filtro de acordo com os campos preenchidos.
	 * @param medico
	 * @return
	 * @throws CampoInvalidoException 
	 */
	public List<Medico> consultar(String crm,String uf,  String nome) throws CampoInvalidoException{
		List<Medico>lista = null;
		Medico medicoRecuperado = null;
		
		if((crm == null || crm.isEmpty()) && (nome == null || nome.isEmpty())){
			throw new CampoInvalidoException("Preencha um dos campos para realizar a pesquisa!");
		}
		else if(crm != null && crm.trim().length() > 0){
			validaCampoInteiro(crm);
			int crmNumero = Integer.parseInt(crm);
			lista = new ArrayList<Medico>();
			if(uf != null && !uf.isEmpty()){
				medicoRecuperado = this.medicoDao.recuperaPeloCrm(crmNumero, uf);
				if(medicoRecuperado != null){
					lista.add(medicoRecuperado);
			}else{
				lista = this.medicoDao.recuperaPeloCrm(crmNumero);
			}
		}
		}else{
			lista = medicoDao.recuperaPeloNome(nome);
		}
		return lista;
	}
}

