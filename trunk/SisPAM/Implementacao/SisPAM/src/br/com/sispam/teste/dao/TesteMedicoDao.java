package br.com.sispam.teste.dao;

import org.junit.Before;
import org.junit.Test;

import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.AgendaMedica;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import static org.junit.Assert.*;

public class TesteMedicoDao {
	private MedicoDao medicoDao;
	private UsuarioDao usuarioDao;
	
	@Before
	public void setup(){
		medicoDao = new MedicoDao();
		usuarioDao = new UsuarioDao();
		medicoDao.removerTodosTeste();
		usuarioDao.removerTodosTeste();
	}
	
	@Test
	public void salvarMedico(){
		Usuario usuario = new Usuario();
		usuario.setNome("Elizeu");
		usuario.setEmail("ddd@iii");
		usuario.setCpf("54454545");
		usuario.setPerfil(Perfil.MEDICO.getCodigo());
		usuario.setSexo(Sexo.MASCULINO.getSigla());
		
		Medico medico = new Medico();
		medicoDao = new MedicoDao();
		medico.setCrm(12345);
		medico.setCrmUf("DF");
		medico.setUsuario(usuario);
		medicoDao.salvarMedico(medico);
	}
	
	@Test
	public void recuperarMedicoPeloCrm(){
		//salva um usuario médico
		Usuario usuario = new Usuario();
		usuario.setNome("Batista");
		usuario.setEmail("ddd@iii");
		usuario.setCpf("54454545");
		usuario.setPerfil(Perfil.MEDICO.getCodigo());
		usuario.setSexo(Sexo.MASCULINO.getSigla());
		
		Medico medico = new Medico();
		medicoDao = new MedicoDao();
		medico.setCrm(1234);
		medico.setCrmUf("GO");
		medico.setUsuario(usuario);
		medicoDao.salvarMedico(medico);
		
		//realiza a consulta do medico cadastrado
		Medico medicoRecuperado = medicoDao.recuperaPeloCrm(1234);
		assertNotNull(medicoRecuperado);
		assertEquals("Batista", medicoRecuperado.getUsuario().getNome());
		assertEquals("GO", medicoRecuperado.getCrmUf());
	}
	
	@Test
	public void excluirMedico(){
		//salva um usuario médico
		Usuario usuario = new Usuario();
		usuario.setNome("Batista");
		usuario.setEmail("ddd@iii");
		usuario.setCpf("54454545");
		usuario.setPerfil(Perfil.MEDICO.getCodigo());
		usuario.setSexo(Sexo.MASCULINO.getSigla());
				
		Medico medico = new Medico();
		medicoDao = new MedicoDao();
		medico.setCrm(1234);
		medico.setCrmUf("GO");
		medico.setUsuario(usuario);
		medicoDao.salvarMedico(medico);
		
		
		int idMedicoSalvo = medico.getId();
		
		//excluir o medico criado
		medicoDao.remover(medico);
		
		Medico medicoRecuperado = medicoDao.recuperaPeloId(idMedicoSalvo);
		//verifica se objeto está nulo (médico excluído)
		assertNull(medicoRecuperado);
		
	}
	
}
