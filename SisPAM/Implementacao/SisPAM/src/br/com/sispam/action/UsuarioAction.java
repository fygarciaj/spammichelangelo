package br.com.sispam.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.UsuarioFacade;

public class UsuarioAction extends Action{

	private Usuario usuario;
	private Medico medico;
	private UsuarioFacade usuarioFacade;
	private Perfil[] perfils = Perfil.values();
	private Integer codigoPerfilSelecionado;
	private String codigoPerfilString;
	private Sexo[] sexos = Sexo.values();
	private char sexoSelecionado;
	private Date dataEntrada;
	private List<Usuario> usuariosCadastrados;
	private String telefoneAux;
	private String cepAux;
	private String rgAux;
	private String dddAux;



	/**
	 * @descricao: carrega a tela inicial de cadastro
	 * @return
	 */
	public String carregarNovoUsuario(){
		limparCampos(true);
		return CARREGAR_NOVO_USUARIO;
	}

	/**
	 * @descricao: define qual usuario será cadastrado
	 * @return
	 */
	public String definirTelaUsuario(){
		this.codigoPerfilSelecionado = Integer.parseInt(this.codigoPerfilString);
		limparCampos(false);
		return TELA_SELECIONADA;
	}

	/**
	 * @descricao: salva um objeto usuário
	 * @return
	 */
	public String salvarUsuario(){
		usuarioFacade = new UsuarioFacade();
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
			usuarioFacade.validaCampos(usuario);

			usuario.setPerfil(this.codigoPerfilSelecionado);
			usuario.setCep(Long.parseLong(cepAux));
			usuario.setDdd(Integer.parseInt(dddAux));
			usuario.setRg(Long.parseLong(rgAux));
			usuario.setTelefone(Long.parseLong(telefoneAux));

			usuarioFacade.salvarUsuario(usuario);
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
		limparCampos(true);
		return SUCESSO_SALVAR_USUARIO;
	}


	/**
	 * @descricao: Carrega a tela de consulta de usuários.
	 * @return
	 */
	public String carregarConsulta(){
		limparCampos(true);
		return SUCESSO_CARREGAR_CONSULTA;
	}

	public String definirTelaConsulta(){
		this.usuarioFacade = new UsuarioFacade();
		this.codigoPerfilSelecionado = Integer.parseInt(codigoPerfilString);
		this.usuariosCadastrados = this.usuarioFacade.recuperarUltimosCadastrados(this.codigoPerfilSelecionado);
		return SUCESSO_TELA_CONSULTA;
	}

	/**
	 * @descricao: Consulta o usuário apartir dos dados informados.
	 * @return
	 */
	public String consultarUsuario(){
		this.usuarioFacade = new UsuarioFacade();
		try{
			this.usuario = this.usuarioFacade.recuperarUsuario(usuario.getCpf(), usuario.getNome());
		}catch (CampoInvalidoException e) {
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			return FALHA_CONSULTAR_USUARIO;
		}
		this.usuariosCadastrados = new ArrayList<Usuario>();
		this.usuariosCadastrados.add(this.usuario);
		
		limparCampos(false);
		return SUCESSO_CARREGAR_CONSULTA;
	}

	public String carregarEdicao(){
		return null;
	}
	
	/**
	 * @descricao: Remove o usuário do sistema.
	 * @return
	 */
	public String excluirUsuario(){
		this.usuarioFacade = new UsuarioFacade();
		this.usuarioFacade.removerUsuario(this.usuario.getId());
		this.codigoPerfilString.valueOf(this.codigoPerfilSelecionado);
		return definirTelaConsulta();
	}


	/*Utilitário*/
	private void limparCampos(boolean limpaCodigoPerfilSelecionado){
		this.usuario = null;
		this.medico = null;
		if(limpaCodigoPerfilSelecionado == true){
			this.codigoPerfilSelecionado = null;
		}
		this.codigoPerfilString = null;

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



}