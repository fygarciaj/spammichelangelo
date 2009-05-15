package br.com.sispam.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import br.com.sispam.dominio.AgendaMedica;
import br.com.sispam.dominio.EspecialidadeMedica;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Dia;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.AgendaMedicaFacade;
import br.com.sispam.facade.EspecialidadeFacade;
import br.com.sispam.facade.MedicoFacade;
import br.com.sispam.facade.UsuarioFacade;

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
	private Usuario pessoaLogada;
	private String codigoPerfilString;
	

	public String salvarMedico(){
		limparMapas();
		getParametros();
		usuarioFacade = new UsuarioFacade();
		medicoFacade = new MedicoFacade();
		especialidadeFacade = new EspecialidadeFacade();
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
			medicoFacade.validaCampos(medico);
			
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
			medicoFacade.verificaCrmExistente(medico.getCrm(), medico.getId());
							
			//salva a agenda do médico.
			AgendaMedica agendaMedica = new AgendaMedica();
			agendaMedica.setHoraInicio(Integer.parseInt(horaIni));
			agendaMedica.setHoraFim(Integer.parseInt(horaFim));
			agendaMedica.setDataAtendimento(diasMarcados);
			agendaMedica.setConsultorio(Integer.parseInt(consultorioAux));
			agendaMedica.setMedico(medico);
			medico.setAgendaMedica(agendaMedica);
			
			this.medicoFacade.salvarMedico(medico);
						

			//usuarioFacade.salvarUsuario(usuario);
			mensagens.put("salvo", "Médico cadastrado com sucesso!");
		} catch (CampoInvalidoException e) {
			e.printStackTrace();
			erros.put("campoInvalido", e.getMessage());
			this.especialidades = this.especialidadeFacade.recuperarTodas();
			getListaDias();
			apresentaErrors();
			return FALHA_SALVAR_MEDICO;
		}
		catch (CampoInteiroException e) {
			erros.put("campoInvalido", e.getMessage());
			this.especialidades = this.especialidadeFacade.recuperarTodas();
			getListaDias();
			apresentaErrors();
			return FALHA_SALVAR_USUARIO;
		}
	

		apresentaMensagens();
		limparCampos(true);
		return SUCESSO_SALVAR_MEDICO;
	}
	
	/**
	 * @descricao: Remove o médico do sistema.
	 * @return
	 */
	public String excluirMedico(){
		this.medicoFacade = new MedicoFacade();
		this.medicoFacade.removerMedico(this.medico.getId());
		this.codigoPerfilString = String.valueOf(this.codigoPerfilSelecionado);
		return SUCESSO_EXCLUIR_MEDICO;
	}

	
	public void getParametros(){
		System.out.println(ActionContext.getContext().getParameters().toString());
		
	}
	
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
	private void limparCampos(boolean limpaCodigoPerfilSelecionado){
		this.medico = null;
		if(limpaCodigoPerfilSelecionado == true){
			this.codigoPerfilSelecionado = null;
		}
		this.codigoPerfilString = null;

	}
	
	private void limparMapas(){
		erros.clear();
		mensagens.clear();
	}
	
	public String carregarEdicaoMedico(){
		return null;
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

}
