package br.com.sispam.action;

import java.util.Date;

import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import br.com.sispam.facade.UsuarioFacade;

public class UsuarioAction extends Action{
	
	private Usuario usuario;
	private UsuarioFacade usuarioFacade;
	private Perfil[] perfils = Perfil.values();
	private Integer codigoPerfilSelecionado;
	private Sexo[] sexos = Sexo.values();
	private char sexoSelecionado;
	private Date dataEntrada;
	
	
	public String carregarNovoUsuario(){
		return CARREGAR_NOVO_USUARIO;
	}
	
	/**
	 * @descricao: salva um objeto usuário
	 * @return
	 */
	public String salvarUsuario(){
		usuarioFacade = new UsuarioFacade();
		if(usuarioFacade.verificaSexo(usuario)){
			this.usuario.setNome("viado");
		}else{
			this.usuario.setNome("birobas");
		}
		return SUCESSO;
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
	
	
	
}
