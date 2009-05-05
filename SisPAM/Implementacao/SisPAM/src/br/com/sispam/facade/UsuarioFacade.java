package br.com.sispam.facade;

import java.util.List;

import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.excecao.CampoInvalidoException;

public class UsuarioFacade {
	private UsuarioDao usuarioDao;
	private MedicoDao medicoDao;

	public void salvarUsuario(Object objeto) throws CampoInvalidoException{
		validaCampos(objeto);
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
	 * @descricao: Recupera os últimos usuários cadastrados.
	 * @return
	 */
	public List<Usuario> recuperarUltimosCadastrados() {
		this.usuarioDao = new UsuarioDao();
		return this.usuarioDao.recuperarUltimosCadastrados();
	}

	/**
	 * @descricao: valida os campos do objeto da tela passado.
	 * @param objeto
	 * @throws CampoInvalidoException 
	 */
	private void validaCampos(Object objeto) throws CampoInvalidoException{

		if(objeto != null && objeto instanceof Usuario){
			Usuario usuario = (Usuario)objeto;

			if(usuario.getCep() == 0){
				throw new CampoInvalidoException("Campo cep inválido");
			}
			if(usuario.getCidade() == null){
				throw new CampoInvalidoException("Campo cidade inválido");
			}
			if(usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
				throw new CampoInvalidoException("Campo cpf inválido");
			}
			if(usuario.getDdd() == 0){
				throw new CampoInvalidoException("Campo ddd inválido");
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
			if(usuario.getRg() == 0){
				throw new CampoInvalidoException("Campo Rg inválido");
			}
			if(usuario.getSenha() == null || usuario.getSenha().length() == 0){
				throw new CampoInvalidoException("Campo senha inválido");
			}
			if(usuario.getSexo() == '\0'){
				throw new CampoInvalidoException("Campo sexo inválido");
			}
			if(usuario.getTelefone() == 0){
				throw new CampoInvalidoException("Campo telefone inválido");
			}
			if(usuario.getUf() ==  null || usuario.getUf().isEmpty()){
				throw new CampoInvalidoException("Campo UF inválido");
			}


		}

	}


}
