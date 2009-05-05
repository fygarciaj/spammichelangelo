package br.com.sispam.action;

import java.util.Date;
import java.util.List;

import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
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

	/**
	 * @descricao: carrega a tela inicial de cadastro
	 * @return
	 */
	public String carregarNovoUsuario(){
		limparCampos();
		return CARREGAR_NOVO_USUARIO;
	}

	/**
	 * @descricao: define qual usuario será cadastrado
	 * @return
	 */
	public String definirTelaUsuario(){
		this.codigoPerfilSelecionado = Integer.parseInt(this.codigoPerfilString);
		limparCampos();
		return TELA_SELECIONADA;
	}

	/**
	 * @descricao: salva um objeto usuário
	 * @return
	 */
	public String salvarUsuario(){
		usuarioFacade = new UsuarioFacade();
		if(this.codigoPerfilSelecionado == Perfil.ADMINISTRADOR.getCodigo() || 
				this.codigoPerfilSelecionado == Perfil.ATENDENTE.getCodigo() ){
			usuario.setPerfil(this.codigoPerfilSelecionado);
			try {
				usuarioFacade.salvarUsuario(usuario);
				mensagens.put("salvo", "Dados cadastrados com sucesso!");
			} catch (CampoInvalidoException e) {
				e.printStackTrace();
				erros.put("campoInavlido", e.getMessage());
				apresentaErrors();
				return FALHA_SALVAR_USUARIO;

			}
		}

		apresentaMensagens();
		limparCampos();
		return SUCESSO_SALVAR_USUARIO;
	}

	/**
	 * @descricao: Direciona para a tela de consulta, exibindo os últimos cadastros de usuários realizados 
	 * @return
	 */
	public String listaUltimosUsuarioCadastrados(){
		this.usuarioFacade = new UsuarioFacade();
		this.usuariosCadastrados = this.usuarioFacade.recuperarUltimosCadastrados();
		return LISTAR_USUARIOS;
	}
	
	/**
	 * @descricao: Consulta o usuário apartir dos dados informados.
	 * @return
	 */
	public String consultarUsuario(){
		this.usuario.setPerfil(this.codigoPerfilSelecionado);
		
		return null;
	}

	public String carregarEdicao(){
		return null;
	}


	/*Utilitário*/
	private void limparCampos(){
		this.usuario = null;
		this.medico = null;
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

}