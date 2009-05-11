package br.com.sispam.action;

import java.util.HashMap;
import java.util.Map;

import br.com.sispam.dominio.Medico;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.MedicoFacade;
import br.com.sispam.facade.UsuarioFacade;

public class MedicoAction extends Action{
	
	private Medico medico;
	private UsuarioFacade usuarioFacade;
	private MedicoFacade medicoFacade;
	private String telefoneAux;
	private String cepAux;
	private String rgAux;
	private String dddAux;
	private String crmAux;
	private Integer codigoPerfilSelecionado;
	
	public String salvarMedico(){
		usuarioFacade = new UsuarioFacade();
		medicoFacade = new MedicoFacade();
		//monta um mapa com todos os campos que devem ser inteiros.	
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("ddd", dddAux);
		mapa.put("telefone", telefoneAux);
		mapa.put("Rg", rgAux);
		mapa.put("cep", cepAux);
		mapa.put("crm", crmAux);

		try {
			//verifica se os campos são inteiros
			usuarioFacade.verificaCampoInteiro(mapa);
			
			//valida os campos de médico e do objeto usuario.
			medicoFacade.validaCampos(medico);
			
			//verifica se o crm está sendo usado
			//usuarioFacade.verificaCpfJaExistente(usuario.getCpf(), usuario.getId());
			
			//verifica se o login está sendo usado
			//usuarioFacade.verificaLoginJaExistente(usuario.getAcesso(), usuario.getId());

			medico.getUsuario().setPerfil(this.codigoPerfilSelecionado);
			medico.getUsuario().setCep(Long.parseLong(cepAux));
			medico.getUsuario().setDdd(Integer.parseInt(dddAux));
			medico.getUsuario().setRg(Long.parseLong(rgAux));
			medico.getUsuario().setTelefone(Long.parseLong(telefoneAux));

			//usuarioFacade.salvarUsuario(usuario);
			mensagens.put("salvo", "Dados cadastrados com sucesso!");
		} catch (CampoInvalidoException e) {
			e.printStackTrace();
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR_USUARIO;
		}
		catch (CampoInteiroException e) {
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR_USUARIO;
		}

		apresentaMensagens();
		return SUCESSO_SALVAR_USUARIO;
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
}
