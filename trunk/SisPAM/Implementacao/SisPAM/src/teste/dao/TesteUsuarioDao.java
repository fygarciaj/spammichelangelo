package teste.dao;


import org.junit.Test;
import static org.junit.Assert.*;

import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Sexo;

public class TesteUsuarioDao {
	private UsuarioDao usuarioDao;
	
	
	@Test
	public void salvarUsuario(){
		Usuario usuario = new Usuario();
		usuarioDao = new UsuarioDao();
		usuario.setCpf("9876554");
		usuario.setEndereco("rua xx casa yy");
		usuario.setNome("João cunha");
		usuario.setRg(123);
		usuario.setSexo(Sexo.MASCULINO);
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
		usuario.setSexo(Sexo.MASCULINO);
		usuarioDao.salvarUsuario(usuario);
		
		//TESTE E VERIFICAÇÃO
		
		Usuario usuario2 = usuarioDao.recupera("1234567");
		assertNotNull(usuario2);
		assertEquals(usuario.getNome(), usuario2.getNome());
	}

	
}
