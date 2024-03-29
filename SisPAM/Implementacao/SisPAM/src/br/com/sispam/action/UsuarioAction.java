package br.com.sispam.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sispam.dominio.Convenio;
import br.com.sispam.dominio.EspecialidadeMedica;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Paciente;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Acao;
import br.com.sispam.enums.Dia;
import br.com.sispam.enums.Funcionalidade;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import br.com.sispam.enums.Status;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.AuditoriaFacade;
import br.com.sispam.facade.ConvenioFacade;
import br.com.sispam.facade.EspecialidadeFacade;
import br.com.sispam.facade.MedicoFacade;
import br.com.sispam.facade.PacienteFacade;
import br.com.sispam.facade.UsuarioFacade;
import br.com.sispam.util.AuditoriaUtil;
import br.com.sispam.util.CampoUtil;
import br.com.sispam.util.DataUtil;

public class UsuarioAction extends Action{

	private Usuario usuario;
	private Medico medico;
	private UsuarioFacade usuarioFacade;
	private MedicoFacade medicoFacade;
	private PacienteFacade pacienteFacade;
	private ConvenioFacade convenioFacade;
	private AuditoriaFacade auditoriaFacade;
	private EspecialidadeFacade especialidadeFacade;
	private Perfil[] perfils;
	private Integer codigoPerfilSelecionado;
	private String codigoPerfilString;
	private Sexo[] sexos = Sexo.values();
	private char sexoSelecionado;
	private Date dataEntrada;
	private List<Usuario> usuariosCadastrados;
	private List<Medico> medicosCadastrados;
	private List<Paciente> pacientesCadastrados;
	private String telefoneAux;
	private String cepAux;
	private String rgAux;
	private String dddAux;
	private List<Dia>dias;
	private List<EspecialidadeMedica> especialidades;
	private List<Convenio> convenios;
	private String dataNascimentoAux;
	private boolean pacienteLogado;
	/**
	 * : carrega a tela inicial de cadastro
	 * @return
	 */
	public String carregarNovoUsuario(){
		limparCampos(true);
		this.perfils = Perfil.values(getUsuarioLogado().getPerfil());
		return CARREGAR_NOVO_USUARIO;
	}

	/**
	 * : define qual usuario será cadastrado no sistema.
	 * @return {@link String}
	 */
	public String definirTelaUsuario(){
		this.codigoPerfilSelecionado = Integer.parseInt(this.codigoPerfilString);

		//monta a lista de dias para o cadastro dos dias de trabalho do médico.
		if(this.codigoPerfilSelecionado == Perfil.MEDICO.getCodigo()){
			Dia[] diasVetor = Dia.values();
			this.dias = new ArrayList<Dia>();
			for(Dia dia: diasVetor){
				dias.add(dia);
			}
			this.especialidadeFacade = new EspecialidadeFacade();
			this.especialidades = this.especialidadeFacade.recuperarTodas();
		}
		else if(this.codigoPerfilSelecionado == Perfil.PACIENTE.getCodigo()){
			this.convenioFacade = new ConvenioFacade();
			this.convenios = this.convenioFacade.recuperarTodos();
		}
		limparCampos(false);
		this.perfils = Perfil.values(getUsuarioLogado().getPerfil());
		return TELA_SELECIONADA;
	}

	/**
	 * : salva um objeto usuário no banco de dados.
	 * @return {@link String}
	 */
	public String salvarUsuario(){
		boolean isEdicao = false;
		if(this.usuario.getId() > 0){
			isEdicao = true;
		}
		usuarioFacade = new UsuarioFacade();
		
		//limpa os caracteres dos campos
		telefoneAux = CampoUtil.replaceCampo("-", telefoneAux);
		cepAux = CampoUtil.replaceCampo(".", cepAux);
		
		//monta um mapa com todos os campos que devem ser inteiros.	
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("ddd", dddAux);
		mapa.put("telefone", telefoneAux);
		mapa.put("Rg", rgAux);
		mapa.put("cep", cepAux);

		try {
			//verifica se os campos são inteiros
			usuarioFacade.verificaCampoInteiro(mapa);

			//verifica se os campo obrigatorios foram preenchidos
			usuarioFacade.validaCampos(usuario, dataNascimentoAux);
			usuario.setDataNascimento(DataUtil.stringToDate(dataNascimentoAux));
		
			//verifica se o cpf está sendo usado
			usuarioFacade.verificaCpfJaExistente(usuario.getCpf(), usuario.getId());

			//verifica se o login está sendo usado
			usuarioFacade.verificaLoginJaExistente(usuario.getAcesso(), usuario.getId());

			usuario.setPerfil(this.codigoPerfilSelecionado);
			usuario.setCep(Long.parseLong(cepAux));
			usuario.setDdd(Integer.parseInt(dddAux));
			usuario.setRg(Long.parseLong(rgAux));
			usuario.setTelefone(Long.parseLong(telefoneAux));
			usuario.setStatus(Status.ATIVO.getCodigo());
			usuarioFacade.salvarUsuario(usuario);
			if(isEdicao){
				mensagens.put("salvo", Perfil.getPerfil(usuario.getPerfil())+" alterado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.ALTERACAO, getUsuarioLogado()));
			}else{
				mensagens.put("salvo", Perfil.getPerfil(usuario.getPerfil())+" cadastrado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.INCLUSAO, getUsuarioLogado()));
			}
			
		} catch (CampoInvalidoException e) {
			erros.put("campoInvalido", e.getMessage());
			return FALHA_SALVAR_USUARIO;
		}
		catch (CampoInteiroException e) {
			erros.put("campoInvalido", e.getMessage());
			return FALHA_SALVAR_USUARIO;
		}catch (ParseException e) {
			erros.put("dataInvalida", e.getMessage());
			return FALHA_SALVAR_USUARIO;
		}
		finally{
			this.perfils = Perfil.values(getUsuarioLogado().getPerfil());
			apresentaErrors();
			apresentaMensagens();
		}
		limparCampos(true);
		this.perfils = Perfil.values(getUsuarioLogado().getPerfil());
		return SUCESSO_SALVAR_USUARIO;
	}

	/**
	 * : Carrega a tela de consulta de usuários.
	 * @return {@link String}
	 */
	public String carregarConsulta(){
		limparCampos(true);
		
		if(getUsuarioLogado().getPerfil() == 4){
			return SUCESSO_CARREGAR_EDICAO_PACIENTE;			
		}
		this.perfils = Perfil.values(getUsuarioLogado().getPerfil());
		return SUCESSO_CARREGAR_CONSULTA;
	}

	public String definirTelaConsulta(){

		this.codigoPerfilSelecionado = Integer.parseInt(codigoPerfilString);
		if(this.codigoPerfilSelecionado == Perfil.ADMINISTRADOR.getCodigo() || this.codigoPerfilSelecionado == Perfil.ATENDENTE.getCodigo()){
			this.usuarioFacade = new UsuarioFacade();
			this.usuariosCadastrados = this.usuarioFacade.recuperarUltimosCadastrados(this.codigoPerfilSelecionado);
		}
		else if(this.codigoPerfilSelecionado == Perfil.MEDICO.getCodigo()){
			this.medicoFacade = new MedicoFacade();
			this.medicosCadastrados = this.medicoFacade.recuperarUltimosCadastrados();
		}
		else if(this.codigoPerfilSelecionado == Perfil.PACIENTE.getCodigo()){
			this.pacienteFacade = new PacienteFacade();
			this.pacientesCadastrados = this.pacienteFacade.recuperaUltimosCadastrados();

		}
		apresentaMensagens();
		apresentaErrors();
		this.perfils = Perfil.values(getUsuarioLogado().getPerfil());
		return SUCESSO_TELA_CONSULTA;
	}

	/**
	 * : Consulta o usuário apartir dos dados informados.
	 * @return {@link String}
	 */
	public String consultarUsuario(){
		this.usuarioFacade = new UsuarioFacade();
		try{
			this.usuariosCadastrados = this.usuarioFacade.recuperarUsuario(usuario.getCpf(), usuario.getNome(), this.codigoPerfilSelecionado);
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.CONSULTA, getUsuarioLogado()));
		}catch (CampoInvalidoException e) {
			erros.put("campoInvalido", e.getMessage());
			this.codigoPerfilString = String.valueOf(this.codigoPerfilSelecionado);
			return FALHA_CONSULTAR_USUARIO;
		}
		
		if(this.usuariosCadastrados == null || this.usuariosCadastrados.size() < 1){
			mensagens.put("consulta", "Nenhum "+Perfil.getPerfil(this.codigoPerfilSelecionado)+" encontrado com esses dados!");
		}
		
		limparCampos(false);
		apresentaMensagens();
		this.perfils = Perfil.values(getUsuarioLogado().getPerfil());
		return SUCESSO_CARREGAR_CONSULTA;
	}
	
	/**
	 * : Carrea o usuário a ser alterado.
	 * @return
	 */
	public String carregarEdicao(){
		this.usuarioFacade = new UsuarioFacade();
		this.usuario = this.usuarioFacade.recuperarPeloId(this.usuario.getId());
		//seta os valores nas variáveis auxiliares
		this.cepAux = String.valueOf(this.usuario.getCep());
		this.dddAux = String.valueOf(this.usuario.getDdd());
		this.telefoneAux = String.valueOf(this.usuario.getTelefone());
		this.rgAux = String.valueOf(this.usuario.getRg());
		this.dataNascimentoAux = DataUtil.dateToString(this.usuario.getDataNascimento());
		this.perfils = Perfil.values(getUsuarioLogado().getPerfil());
		return TELA_SELECIONADA;
	}



	/**
	 * : Remove o usuário do sistema.
	 * @return {@link String}
	 */
	public String excluirUsuario(){
		this.usuarioFacade = new UsuarioFacade();
		this.usuarioFacade.removerUsuario(this.usuario.getId());
		this.codigoPerfilString = String.valueOf(this.codigoPerfilSelecionado);
		mensagens.put("exclusao", Perfil.getPerfil(this.codigoPerfilSelecionado)+" excluído com sucesso!");
		
		try {
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.EXCLUSAO, getUsuarioLogado()));
		} catch (CampoInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return definirTelaConsulta();
	}


	/*Utilitário*/
	/**
	 * : Limpa os campos da tela.
	 */
	private void limparCampos(boolean limpaCodigoPerfilSelecionado){
		this.usuario = null;
		this.medico = null;
		if(limpaCodigoPerfilSelecionado == true){
			this.codigoPerfilSelecionado = null;
		}
		this.codigoPerfilString = null;
		this.cepAux = null;
		this.dddAux = null;
		this.rgAux = null;
		this.telefoneAux = null;


	}


	/*Get & Set*/

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public UsuarioFacade getUsuarioFacade() {
		return usuarioFacade;
	}


	public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}

	public Perfil[] getPerfils() {
		return perfils;
	}

	public void setPerfils(Perfil[] perfils) {
		this.perfils = perfils;
	}

	public Integer getCodigoPerfilSelecionado() {
		return codigoPerfilSelecionado;
	}

	public void setCodigoPerfilSelecionado(Integer codigoPerfilSelecionado) {
		this.codigoPerfilSelecionado = codigoPerfilSelecionado;
	}

	public Sexo[] getSexos() {
		return sexos;
	}

	public void setSexos(Sexo[] sexos) {
		this.sexos = sexos;
	}

	public char getSexoSelecionado() {
		return sexoSelecionado;
	}

	public void setSexoSelecionado(char sexoSelecionado) {
		this.sexoSelecionado = sexoSelecionado;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getCodigoPerfilString() {
		return codigoPerfilString;
	}

	public void setCodigoPerfilString(String codigoPerfilString) {
		this.codigoPerfilString = codigoPerfilString;
	}

	public List<Usuario> getUsuariosCadastrados() {
		return usuariosCadastrados;
	}

	public void setUsuariosCadastrados(List<Usuario> usuariosCadastrados) {
		this.usuariosCadastrados = usuariosCadastrados;
	}

	public String getTelefoneAux() {
		return telefoneAux;
	}

	public void setTelefoneAux(String telefoneAux) {
		this.telefoneAux = telefoneAux;
	}

	public String getCepAux() {
		return cepAux;
	}

	public void setCepAux(String cepAux) {
		this.cepAux = cepAux;
	}

	public String getRgAux() {
		return rgAux;
	}

	public void setRgAux(String rgAux) {
		this.rgAux = rgAux;
	}

	public String getDddAux() {
		return dddAux;
	}

	public void setDddAux(String dddAux) {
		this.dddAux = dddAux;
	}

	public List<Dia> getDias() {
		return dias;
	}

	public void setDias(List<Dia> dias) {
		this.dias = dias;
	}

	public List<EspecialidadeMedica> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadeMedica> especialidades) {
		this.especialidades = especialidades;
	}

	public List<Medico> getMedicosCadastrados() {
		return medicosCadastrados;
	}

	public void setMedicosCadastrados(List<Medico> medicosCadastrados) {
		this.medicosCadastrados = medicosCadastrados;
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	public List<Paciente> getPacientesCadastrados() {
		return pacientesCadastrados;
	}
	public void setPacientesCadastrados(List<Paciente> pacientesCadastrados) {
		this.pacientesCadastrados = pacientesCadastrados;
	}
	public String getDataNascimentoAux() {
		return dataNascimentoAux;
	}
	public void setDataNascimentoAux(String dataNascimentoAux) {
		this.dataNascimentoAux = dataNascimentoAux;
	}

	public MedicoFacade getMedicoFacade() {
		return medicoFacade;
	}

	public void setMedicoFacade(MedicoFacade medicoFacade) {
		this.medicoFacade = medicoFacade;
	}

	public PacienteFacade getPacienteFacade() {
		return pacienteFacade;
	}

	public void setPacienteFacade(PacienteFacade pacienteFacade) {
		this.pacienteFacade = pacienteFacade;
	}

	public ConvenioFacade getConvenioFacade() {
		return convenioFacade;
	}

	public void setConvenioFacade(ConvenioFacade convenioFacade) {
		this.convenioFacade = convenioFacade;
	}

	public AuditoriaFacade getAuditoriaFacade() {
		return auditoriaFacade;
	}

	public void setAuditoriaFacade(AuditoriaFacade auditoriaFacade) {
		this.auditoriaFacade = auditoriaFacade;
	}

	public EspecialidadeFacade getEspecialidadeFacade() {
		return especialidadeFacade;
	}

	public void setEspecialidadeFacade(EspecialidadeFacade especialidadeFacade) {
		this.especialidadeFacade = especialidadeFacade;
	}

	public boolean isPacienteLogado() {
		return pacienteLogado;
	}

	public void setPacienteLogado(boolean pacienteLogado) {
		this.pacienteLogado = pacienteLogado;
	}

}