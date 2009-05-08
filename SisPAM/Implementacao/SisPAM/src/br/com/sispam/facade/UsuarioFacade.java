package br.com.sispam.facade;

import java.util.List;
import java.util.Map;

import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;

public class UsuarioFacade {
	private UsuarioDao usuarioDao;
	private MedicoDao medicoDao;

	/**
	 * @descricao: Salva o usuário de todos os tipos.
	 * @param objeto
	 * @throws CampoInvalidoException
	 */
	public void salvarUsuario(Object objeto) throws CampoInvalidoException{

		if(objeto != null && objeto instanceof Usuario){
			Usuario usuario = (Usuario)objeto;
			this.usuarioDao = new UsuarioDao();
			this.usuarioDao.salvarUsuario(usuario);
		}
		else if(objeto != null && objeto instanceof Medico){
			Medico medico = (Medico)objeto;
			this.medicoDao = new MedicoDao();
			this.medicoDao.salvarMedico(medico);
		}
	}

	/**
	 * @descricao: Recupera o usuário pelo seu id.
	 * @param id
	 */
	public Usuario recuperarPeloId(int id){
		this.usuarioDao = new UsuarioDao();
		Usuario usuario = this.usuarioDao.recuperarPeloId(id);
		return usuario;
	}
	
	/**
	 * @descricao: Valida os campos que devem ser inteiros.
	 * @param mapa
	 * @throws CampoInvalidoException
	 */
	public void verificaCampoInteiro(Map<String, String> mapa) throws CampoInvalidoException{

		if(mapa != null && mapa.size() > 0){
			for(String nomeCampo: mapa.keySet()){
				try{
					String campoString = mapa.get(nomeCampo);

					if(campoString != null && campoString.trim().length() > 0){
						int valorCampo = Integer.parseInt(campoString);
					}else{
						throw new CampoInvalidoException("Campo "+nomeCampo+" inválido!");
					}
				}
				catch (NumberFormatException e) {
					throw new CampoInteiroException(nomeCampo+" é um campo inteiro!");
				}
			}
		}
	}
	
	/**
	 * @descicao: Verifica se o cpf já está sendo usado.
	 * @param cpf
	 */
	public void verificaCpfJaExistente(String cpf, int id)throws CampoInvalidoException{
		this.usuarioDao = new UsuarioDao();
		Usuario usuario = this.usuarioDao.recupera(cpf);
		if(usuario != null && id != usuario.getId()){
			throw new CampoInvalidoException("Este CPF já está sendo usado!");
		}
	}

	public Usuario recuperarUsuario(String cpf, String nome) throws CampoInvalidoException{

		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = null;

		if((cpf == null && cpf.trim().length() == 0) || (nome == null && nome.trim().length() == 0)){
			throw new CampoInvalidoException("Preencha um dos campos para realizar a pesquisa!");
		}
		else if(cpf != null && cpf.trim().length() > 0){
			usuario = usuarioDao.recupera(cpf);
		}else{
			usuario = usuarioDao.recuperaPeloNome(nome);
		}
		
		return usuario;
	}



	/**
	 * @descricao: Recupera os últimos usuários cadastrados.
	 * @return
	 */
	public List<Usuario> recuperarUltimosCadastrados(int codigoPerfilSelecionado) {
		this.usuarioDao = new UsuarioDao();
		return this.usuarioDao.recuperarUltimosCadastrados(codigoPerfilSelecionado);
	}
	
	/**
	 * @descricao: Remove o usuário do sistema.
	 * @param id
	 */
	public void removerUsuario(int id){
		this.usuarioDao = new UsuarioDao();
		Usuario usuario = this.usuarioDao.recuperarPeloId(id);
		this.usuarioDao.removerUsuario(usuario);
	}

	/**
	 * @descricao: valida os campos do objeto da tela passado.
	 * @param objeto
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Object objeto) throws CampoInvalidoException{

		if(objeto != null && objeto instanceof Usuario){
			Usuario usuario = (Usuario)objeto;

			if(usuario.getCidade() == null || usuario.getCidade().isEmpty()){
				throw new CampoInvalidoException("Campo cidade inválido");
			}
			if(usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
				throw new CampoInvalidoException("Campo cpf inválido");
			}
			if(usuario.getEmail() == null || usuario.getEmail().length() == 0){
				throw new CampoInvalidoException("Campo e-mail inválido");
			}
			if(usuario.getEndereco() == null || usuario.getEndereco().length() == 0){
				throw new CampoInvalidoException("Campo endereço inválido");
			}
			if(usuario.getExpedidorRg() == null || usuario.getExpedidorRg().length() == 0){
				throw new CampoInvalidoException("Campo orgão expedidor inválido");
			}
			if(usuario.getNome() == null || usuario.getNome().length() == 0){
				throw new CampoInvalidoException("Campo nome inválido");
			}
			if(usuario.getSenha() == null || usuario.getSenha().length() == 0){
				throw new CampoInvalidoException("Campo senha inválido");
			}
			if(usuario.getSexo() == '\0'){
				throw new CampoInvalidoException("Campo sexo inválido");
			}
			if(usuario.getUf() ==  null || usuario.getUf().isEmpty()){
				throw new CampoInvalidoException("Campo UF inválido");
			}
		}

	}


}
