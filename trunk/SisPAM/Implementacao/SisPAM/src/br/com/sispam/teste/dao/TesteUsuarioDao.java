package br.com.sispam.teste.dao;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;

public class TesteUsuarioDao {
	private UsuarioDao usuarioDao;
	private MedicoDao medicoDao;
	
	
	@Before
	public void setup(){
		usuarioDao = new UsuarioDao();
		medicoDao = new MedicoDao();
		medicoDao.removerTodosTeste();
		usuarioDao.removerTodosTeste();
	}
	
	
	@Test
	public void salvarUsuario(){
		Usuario usuario = new Usuario();
		usuarioDao = new UsuarioDao();
		usuario.setCpf("9876554");
		usuario.setEndereco("rua xx casa yy");
		usuario.setNome("João cunha");
		usuario.setRg(123);
		usuario.setSexo(Sexo.MASCULINO.getSigla());
		usuario.setPerfil(Perfil.ATENDENTE.getCodigo());
		usuarioDao.salvarUsuario(usuario);
	}
	
	@Test
	public void recuperarUsuarioPeloCpf(){
		Usuario usuario = new Usuario();
		usuarioDao = new UsuarioDao();
		usuario.setCpf("1234567");
		usuario.setEndereco("Rua 15 lote 12 shis");
		usuario.setNome("Mauro da silva");
		usuario.setRg(1223);
		usuario.setSexo(Sexo.MASCULINO.getSigla());
		usuario.setPerfil(Perfil.ADMINISTRADOR.getCodigo());
		usuarioDao.salvarUsuario(usuario);
		
		//TESTE E VERIFICAÇÃO
		
		Usuario usuario2 = usuarioDao.recupera("1234567");
		assertNotNull(usuario2);
		assertEquals(usuario.getNome(), usuario2.getNome());
	}
	
	@Test
	public void listarUltimosCadastrados(){
		Usuario usuario = new Usuario();
		usuarioDao = new UsuarioDao();
		usuario.setCpf("12");
		usuario.setEndereco("Rua 15 lote 12 shis");
		usuario.setNome("Mauro da silva");
		usuario.setRg(1223);
		usuario.setSexo(Sexo.MASCULINO.getSigla());
		usuario.setPerfil(Perfil.ATENDENTE.getCodigo());
		usuarioDao.salvarUsuario(usuario);
		
		Usuario usuario2 = new Usuario();
		usuarioDao = new UsuarioDao();
		usuario2.setCpf("13");
		usuario2.setEndereco("Rua x casa y");
		usuario2.setNome("Maria da cunha");
		usuario2.setSexo(Sexo.FEMININO.getSigla());
		usuario2.setPerfil(Perfil.ATENDENTE.getCodigo());
		usuarioDao.salvarUsuario(usuario2);
		
		List<Usuario> lista = usuarioDao.recuperarUltimosCadastrados(Perfil.ATENDENTE.getCodigo());
		assertNotNull(lista);
		assertEquals(2, lista.size());
		assertEquals(usuario2.getCpf(), lista.get(0).getCpf());
		assertEquals(usuario.getCpf(), lista.get(1).getCpf());
				
	}

	
}
