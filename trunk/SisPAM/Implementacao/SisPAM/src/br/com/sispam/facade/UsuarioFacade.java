package br.com.sispam.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.util.Cripto;
import br.com.sispam.validation.IValidation;
import br.com.sispam.validation.ValidatorFactory;
import br.com.sispam.validation.cnpjcpf.CPFValidator;

public class UsuarioFacade {
	private UsuarioDao usuarioDao;
	private CPFValidator validador;

	public UsuarioFacade(){
		this.usuarioDao = new UsuarioDao();
	}

	/**
	 * Salva o usuário de todos os tipos.
	 * @param usuario
	 * @throws CampoInvalidoException
	 */
	public void salvarUsuario(Usuario usuario) throws CampoInvalidoException{
		if(usuario != null){
			Cripto cripto = new Cripto();
			usuario.setSenha(cripto.criptografar(usuario.getSenha()));
			this.usuarioDao.salvarUsuario(usuario);
		}

	}

	/**
	 * Recupera o usuário pelo seu id.
	 * @param id
	 */
	public Usuario recuperarPeloId(int id){
		Usuario usuario = this.usuarioDao.recuperarPeloId(id);
		return usuario;
	}

	/**
	 * Valida os campos que devem ser inteiros.
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
	 * Verifica se o cpf já está sendo usado.
	 * @param cpf
	 */
	public void verificaCpfJaExistente(String cpf, int id)throws CampoInvalidoException{
		Usuario usuario = this.usuarioDao.recupera(cpf);
		if(usuario != null && id != usuario.getId()){
			throw new CampoInvalidoException("Este CPF já está sendo usado!");
		}
	}
	/**
	 * Método genérico para recuperar usuários.
	 * @param cpf
	 * @param nome
	 * @param codigoPerfil
	 * @return
	 * @throws CampoInvalidoException
	 */
	public List<Usuario> recuperarUsuario(String cpf, String nome, int codigoPerfil) throws CampoInvalidoException{
		List<Usuario> lista = null;
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = null;
		if(cpf != null){
			cpf = cpf.replaceAll("[.]", "");
			cpf = cpf.replaceAll("[-]", "");
		}

		if((cpf == null || cpf.isEmpty()) && (nome == null || nome.isEmpty())){
			throw new CampoInvalidoException("Preencha um dos campos para realizar a pesquisa!");
		}
		else if(cpf != null && cpf.trim().length() > 0){
			lista = new ArrayList<Usuario>();
			usuario = usuarioDao.recupera(cpf);
			if(usuario != null){
				lista.add(usuario);
			}

		}else{
			lista = usuarioDao.recuperaPeloNome(nome, codigoPerfil);
		}

		return lista;
	}



	/**
	 * Recupera os últimos usuários cadastrados.
	 * @return
	 */
	public List<Usuario> recuperarUltimosCadastrados(int codigoPerfilSelecionado) {
		return this.usuarioDao.recuperarUltimosCadastrados(codigoPerfilSelecionado);
	}

	/**
	 * Remove o usuário do sistema.
	 * @param id
	 */
	public void removerUsuario(int id){
		Usuario usuario = this.usuarioDao.recuperarPeloId(id);
		this.usuarioDao.removerUsuario(usuario);
	}

	/**
	 * valida os campos do objeto da tela passado.
	 * @param objeto
	 * @throws CampoInvalidoException 
	 */
	public void validaCampos(Object objeto, String dataNascimento) throws CampoInvalidoException{
		validador = (CPFValidator) ValidatorFactory.getInstance()
		.getValidator(IValidation.VALIDATOR_CPF);		

		if(objeto != null && objeto instanceof Usuario){
			Usuario usuario = (Usuario)objeto;
			//retira a mascára do cpf
			if(usuario.getCpf() != null){
				usuario.setCpf(usuario.getCpf().replaceAll("[.]", ""));
				usuario.setCpf(usuario.getCpf().replaceAll("[-]", ""));
			}
			validador.setCpf(usuario.getCpf());
			if(usuario.getNome() == null || usuario.getNome().length() == 0){
				throw new CampoInvalidoException("Campo nome inválido");
			}
			if(usuario.getCpf() == null || usuario.getCpf().isEmpty() || usuario.getCpf().length() < 11 || !validador.isValid()) {
				throw new CampoInvalidoException("Campo cpf inválido");
			}
			if(usuario.getExpedidorRg() == null || usuario.getExpedidorRg().length() == 0){
				throw new CampoInvalidoException("Campo orgão expedidor inválido");
			}
			if(usuario.getSexo() == '0'){
				throw new CampoInvalidoException("Campo sexo inválido");
			}
			if(usuario.getEndereco() == null || usuario.getEndereco().length() == 0){
				throw new CampoInvalidoException("Campo endereço inválido");
			}
			if(usuario.getCidade() == null || usuario.getCidade().isEmpty()){
				throw new CampoInvalidoException("Campo cidade inválido");
			}
			if(usuario.getUf() ==  null || usuario.getUf().isEmpty()){
				throw new CampoInvalidoException("Campo Estado inválido");
			}
			if(usuario.getAcesso() == null || usuario.getAcesso().isEmpty()){
				throw new CampoInvalidoException("Campo Login inválido");
			}
			if(usuario.getSenha() == null || usuario.getSenha().length() == 0){
				throw new CampoInvalidoException("Campo senha inválido");
			}
			if(dataNascimento == null || dataNascimento.length() == 0){
				throw new CampoInvalidoException("Data de Nascimento inválida");
			}
		}

	}

	/**
	 * Verifica se já existe o login no banco de dados. 
	 * @param acesso
	 * @param id
	 * @throws CampoInvalidoException
	 */
	public void verificaLoginJaExistente(String acesso, int id) throws CampoInvalidoException {
		Usuario usuario = this.usuarioDao.recuperaPeloLogin(acesso);

		if(usuario != null && id != usuario.getId()){
			throw new CampoInvalidoException("Este Login já está em uso, digite outro Login!");
		}

	}


}
