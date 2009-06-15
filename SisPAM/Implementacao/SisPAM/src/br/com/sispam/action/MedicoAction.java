package br.com.sispam.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import br.com.sispam.dominio.EspecialidadeMedica;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Acao;
import br.com.sispam.enums.Dia;
import br.com.sispam.enums.Funcionalidade;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.AuditoriaFacade;
import br.com.sispam.facade.EspecialidadeFacade;
import br.com.sispam.facade.MedicoFacade;
import br.com.sispam.facade.UsuarioFacade;
import br.com.sispam.util.AuditoriaUtil;
import br.com.sispam.util.CampoUtil;
import br.com.sispam.util.DataUtil;

public class MedicoAction extends Action{

	private Medico medico;
	private UsuarioFacade usuarioFacade;
	private MedicoFacade medicoFacade;
	private EspecialidadeFacade especialidadeFacade;
	private String telefoneAux;
	private String cepAux;
	private String rgAux;
	private String dddAux;
	private String crmAux;
	private String consultorioAux;
	private String horaIni;
	private String horaFim;
	private Integer codigoPerfilSelecionado;
	private List<EspecialidadeMedica> especialidades;
	private Perfil[] perfils = Perfil.values();
	private Sexo[] sexos = Sexo.values();
	private List<Dia>dias;
	private String codigoPerfilString;
	private String especialidadesSelecionadas;
	private List<String> diasString;
	private String dataNascimentoAux;
	private List<Medico> medicosCadastrados;
	private String crmUf;
	private AuditoriaFacade auditoriaFacade;
	
	/**
	 * : Recebe os dados do médico para ser incluído.
	 * @return
	 * @throws ParseException
	 */
	public String salvarMedico() throws ParseException{
		limparMapas();
		boolean isEdicao = false;
		if(medico.getId() > 0){
			isEdicao = true;
		}
		usuarioFacade = new UsuarioFacade();
		medicoFacade = new MedicoFacade();
		especialidadeFacade = new EspecialidadeFacade();
		
		//limpa os caracteres dos campos
		telefoneAux = CampoUtil.replaceCampo("-", telefoneAux);
		cepAux = CampoUtil.replaceCampo(".", cepAux);
		horaIni = CampoUtil.replaceCampo(":", horaIni);
		horaFim = CampoUtil.replaceCampo(":", horaFim);
		
		//monta um mapa com todos os campos que devem ser inteiros.	
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("ddd", dddAux);
		mapa.put("telefone", telefoneAux);
		mapa.put("Rg", rgAux);
		mapa.put("cep", cepAux);
		mapa.put("crm", crmAux);
		mapa.put("Hora início", horaIni);
		mapa.put("hora fim", horaFim);
		mapa.put("Consultório", consultorioAux);

		try {
			//verifica se os campos são inteiros
			usuarioFacade.verificaCampoInteiro(mapa);

			//valida os campos de médico e do objeto usuario.
			medicoFacade.validaCampos(medico, especialidadesSelecionadas, this.dataNascimentoAux);
			medico.getUsuario().setDataNascimento(DataUtil.stringToDate(dataNascimentoAux));

			//verifica se o campo dias de trabalho foi preenchido.
			String diasMarcados = getDiasMarcados().toString();
			medicoFacade.validaCampoDias(diasMarcados);

			//verifica se o login está sendo usado
			usuarioFacade.verificaLoginJaExistente(medico.getUsuario().getAcesso(), medico.getUsuario().getId());

			medico.getUsuario().setPerfil(this.codigoPerfilSelecionado);
			medico.getUsuario().setCep(Long.parseLong(cepAux));
			medico.getUsuario().setDdd(Integer.parseInt(dddAux));
			medico.getUsuario().setRg(Long.parseLong(rgAux));
			medico.getUsuario().setTelefone(Long.parseLong(telefoneAux));
			medico.setCrm(Integer.parseInt(crmAux));

			//verifica se o crm está sendo usado
			medicoFacade.verificaCrmExistente(medico.getCrm(), medico.getId(), medico.getCrmUf());

			//monta a lista de especialidades
			this.medicoFacade.validaEspecialidadesSalvas(medico, especialidadesSelecionadas);

			medico.setHoraInicio(Integer.parseInt(horaIni));
			medico.setHoraFim(Integer.parseInt(horaFim));
			medico.setConsultorio(Integer.parseInt(consultorioAux));
			medico.setDataAtendimento(diasMarcados);
			
			this.medicoFacade.salvarMedico(medico);

			if(isEdicao){
				mensagens.put("salvo", "Médico alterado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.ALTERACAO, getUsuarioLogado()));
			}else{
				mensagens.put("salvo", "Médico cadastrado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.INCLUSAO, getUsuarioLogado()));
			}

		} catch (CampoInvalidoException e) {
			e.printStackTrace();
			erros.put("campoInvalido", e.getMessage());
			if(this.medico != null && this.medico.getId() > 0){
				Medico medico2 = this.medicoFacade.recuperar(this.medico.getId());
				preparaListaDeExibicao(medico2);
				medico.setEspecialidades(medico2.getEspecialidades());
				}
			else{
				this.especialidades = this.especialidadeFacade.recuperarTodas();
			}
			medico.setDataAtendimento(getDiasMarcados().toString());
			diasString = this.medicoFacade.montaMedico(medico);
			getListaDias();
			apresentaErrors();
			return FALHA_SALVAR_MEDICO;
		}
		catch (CampoInteiroException e) {
			erros.put("campoInvalido", e.getMessage());

			if(this.medico != null && this.medico.getId() > 0){
				Medico medico2 = this.medicoFacade.recuperar(this.medico.getId());
				preparaListaDeExibicao(medico2);
				medico.setEspecialidades(medico2.getEspecialidades());
			}
			else{
				this.especialidades = this.especialidadeFacade.recuperarTodas();
			}
			medico.setDataAtendimento(getDiasMarcados().toString());
			diasString = this.medicoFacade.montaMedico(medico);
			getListaDias();
			apresentaErrors();
			return FALHA_SALVAR_MEDICO;
		}finally{
			this.perfils = Perfil.values(getUsuarioLogado().getPerfil());
		}


		apresentaMensagens();
		limparCampos(true);
		return SUCESSO_SALVAR_MEDICO;
	}
	
	/**
	 * : Realiza a consulta do médico.
	 * @return
	 */
	public String consultarMedico(){
		this.medicoFacade = new MedicoFacade();
		
		try{
			this.medicosCadastrados = this.medicoFacade.consultar(crmAux,crmUf,  medico.getUsuario().getNome());
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.CONSULTA, getUsuarioLogado()));
		}catch (CampoInvalidoException e) {
			erros.put("erro", e.getMessage());
			this.codigoPerfilString = String.valueOf(this.codigoPerfilSelecionado);
			return FALHA_CONSULTAR_MEDICO;
		}
		
		if(this.medicosCadastrados == null || this.medicosCadastrados.size() < 1){
			mensagens.put("consulta", "Nenhum médico encontrado para os dados informados!");
		}
		limparCampos(false);
		apresentaMensagens();
		return SUCESSO_CONSULTAR_MEDICO;
	}

	/**
	 * : Carrega a edição do médico selecionado.
	 * @return {@link String}
	 */
	public String carregarEdicao(){
		this.medicoFacade = new MedicoFacade();
		this.especialidadeFacade = new EspecialidadeFacade();
		//Recupera o médico
		this.medico = this.medicoFacade.recuperar(this.medico.getId());

		//monta os atributos da tela.
		this.cepAux = String.valueOf(this.medico.getUsuario().getCep());
		this.consultorioAux = String.valueOf(this.medico.getConsultorio());
		this.crmAux = String.valueOf(this.medico.getCrm());
		this.dddAux = String.valueOf(this.medico.getUsuario().getDdd());
		this.horaFim = String.valueOf(this.medico.getHoraFim());
		this.horaIni = String.valueOf(this.medico.getHoraInicio());
		this.telefoneAux = String.valueOf(this.medico.getUsuario().getTelefone());
		this.rgAux = String.valueOf(this.medico.getUsuario().getRg());
		this.dataNascimentoAux = DataUtil.dateToString(this.medico.getUsuario().getDataNascimento());

		preparaListaDeExibicao(this.medico);

		//carrega a lista de dias para exibir na tela.
		getListaDias();

		//prepara a lista de dias do médico cadastrado.
		this.diasString = this.medicoFacade.montaMedico(medico);

		apresentaErrors();
		return SUCESSO_CARREGAR_EDICAO;
	}

	/**
	 * : Remove o médico do sistema.
	 * @return
	 */
	public String excluirMedico(){
		this.medicoFacade = new MedicoFacade();
		this.medicoFacade.removerMedico(this.medico.getId());
		this.codigoPerfilString = String.valueOf(this.codigoPerfilSelecionado);
		mensagens.put("salvo", "Médico excluído com sucesso!");
		
		
		try {
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_USUARIO, Acao.EXCLUSAO, getUsuarioLogado()));
		} catch (CampoInvalidoException e) {
			e.printStackTrace();
		}
		return SUCESSO_EXCLUIR_MEDICO;
	}
	/**
	 * : Monta uma lista de dias da enum.
	 * @return
	 */
	public List<Dia> getListaDias(){
		//monta a lista de dias para o cadastro dos dias de trabalho do médico.
		if(this.codigoPerfilSelecionado == Perfil.MEDICO.getCodigo()){
			Dia[] diasVetor = Dia.values();
			this.dias = new ArrayList<Dia>();
			for(Dia dia: diasVetor){
				dias.add(dia);
			}
		}
		return this.dias;
	}
	
	/**
	 * : Recupera os dias marcados da tela.
	 * @return
	 */
	public StringBuilder getDiasMarcados(){
		StringBuilder builder = new StringBuilder();
		for(Object obj: ActionContext.getContext().getParameters().keySet()){

			if(String.valueOf(obj).startsWith("dia-")){
				String idDia = String.valueOf(obj).split("-")[1];

				Object valor = ActionContext.getContext().getParameters().get("dia-"+idDia);

				String[] opcao = null;

				if(valor instanceof String[]){
					opcao = (String[])valor;
				}
				if(opcao != null && opcao[0].equalsIgnoreCase("true")){
					if(builder.length() == 0){
						builder.append(idDia);
					}
					else{
						builder.append("-");
						builder.append(idDia);
					}
				}

			}
		}
		return builder;
	}

	/*Utilitário*/
	/**
	 * : Limpa os campos da tela.
	 */
	private void limparCampos(boolean limpaCodigoPerfilSelecionado){
		this.medico = null;
		this.crmAux = null;
		this.crmUf = null;
		if(limpaCodigoPerfilSelecionado == true){
			this.codigoPerfilSelecionado = null;
		}
		this.codigoPerfilString = null;

	}
	
	/**
	 * : Prepara a lista de dias de trabalho do médico.
	 * @param medico
	 */
	private void preparaListaDeExibicao(Medico medico){
		if(medico != null && medico.getEspecialidades() != null){
			List<EspecialidadeMedica> especialidades = medico.getEspecialidades();

			List<Integer> lista = new ArrayList<Integer>();
			for(EspecialidadeMedica esp: especialidades){
				lista.add(esp.getId());
			}
			this.especialidades = this.especialidadeFacade.recuperarTodas(lista);
		}else{
			this.especialidades = this.especialidadeFacade.recuperarTodas();
		}

	}
	
	/**
	 * : Limpa os mapas de mesnagem e erro.
	 */
	private void limparMapas(){
		erros.clear();
		mensagens.clear();
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public UsuarioFacade getUsuarioFacade() {
		return usuarioFacade;
	}

	public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
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

	public String getCrmAux() {
		return crmAux;
	}

	public void setCrmAux(String crmAux) {
		this.crmAux = crmAux;
	}

	public Integer getCodigoPerfilSelecionado() {
		return codigoPerfilSelecionado;
	}

	public void setCodigoPerfilSelecionado(Integer codigoPerfilSelecionado) {
		this.codigoPerfilSelecionado = codigoPerfilSelecionado;
	}

	public String getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public List<EspecialidadeMedica> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadeMedica> especialidades) {
		this.especialidades = especialidades;
	}

	public Sexo[] getSexos() {
		return sexos;
	}

	public void setSexos(Sexo[] sexos) {
		this.sexos = sexos;
	}
	public Perfil[] getPerfils() {
		return perfils;
	}

	public void setPerfils(Perfil[] perfils) {
		this.perfils = perfils;
	}

	public List<Dia> getDias() {
		return dias;
	}

	public void setDias(List<Dia> dias) {
		this.dias = dias;
	}

	public Usuario getPessoaLogada() {
		return getPessoaLogada();
	}

	public String getCodigoPerfilString() {
		return codigoPerfilString;
	}

	public void setCodigoPerfilString(String codigoPerfilString) {
		this.codigoPerfilString = codigoPerfilString;
	}

	public String getConsultorioAux() {
		return consultorioAux;
	}

	public void setConsultorioAux(String consultorioAux) {
		this.consultorioAux = consultorioAux;
	}

	public String getEspecialidadesSelecionadas() {
		return especialidadesSelecionadas;
	}

	public void setEspecialidadesSelecionadas(String especialidadesSelecionadas) {
		this.especialidadesSelecionadas = especialidadesSelecionadas;
	}

	public List<String> getDiasString() {
		return diasString;
	}

	public void setDiasString(List<String> diasString) {
		this.diasString = diasString;
	}
	public String getDataNascimentoAux() {
		return dataNascimentoAux;
	}
	public void setDataNascimentoAux(String dataNascimentoAux) {
		this.dataNascimentoAux = dataNascimentoAux;
	}
	public List<Medico> getMedicosCadastrados() {
		return medicosCadastrados;
	}
	public void setMedicosCadastrados(List<Medico> medicosCadastrados) {
		this.medicosCadastrados = medicosCadastrados;
	}

	public String getCrmUf() {
		return crmUf;
	}

	public void setCrmUf(String crmUf) {
		this.crmUf = crmUf;
	}

	public MedicoFacade getMedicoFacade() {
		return medicoFacade;
	}

	public void setMedicoFacade(MedicoFacade medicoFacade) {
		this.medicoFacade = medicoFacade;
	}

	public EspecialidadeFacade getEspecialidadeFacade() {
		return especialidadeFacade;
	}

	public void setEspecialidadeFacade(EspecialidadeFacade especialidadeFacade) {
		this.especialidadeFacade = especialidadeFacade;
	}

	public AuditoriaFacade getAuditoriaFacade() {
		return auditoriaFacade;
	}

	public void setAuditoriaFacade(AuditoriaFacade auditoriaFacade) {
		this.auditoriaFacade = auditoriaFacade;
	}
	
}
