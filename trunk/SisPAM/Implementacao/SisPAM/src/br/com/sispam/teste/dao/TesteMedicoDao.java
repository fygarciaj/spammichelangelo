package br.com.sispam.teste.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.sispam.dao.EspecialidadeDao;
import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dao.UsuarioDao;
import br.com.sispam.dominio.EspecialidadeMedica;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import br.com.sispam.util.Cripto;
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
		EspecialidadeDao especialidadeDao = new EspecialidadeDao();
		Cripto cripto =new Cripto();
		usuario.setNome("Elizeu");
		usuario.setEmail("ddd@iii");
		usuario.setCpf("54454545");
		usuario.setPerfil(Perfil.MEDICO.getCodigo());
		usuario.setSexo(Sexo.MASCULINO.getSigla());
		usuario.setAcesso("elizeu");
		usuario.setSenha(cripto.criptografar("asdf"));
		usuario.setDataNascimento(new Date());

		EspecialidadeMedica especialidadeMedica = especialidadeDao.recuperarEspecialidade(3);
		EspecialidadeMedica especialidadeMedica2 = especialidadeDao.recuperarEspecialidade(4);

		List<EspecialidadeMedica> especialidades = new ArrayList<EspecialidadeMedica>();
		especialidades.add(especialidadeMedica);
		especialidades.add(especialidadeMedica2);

		Medico medico = new Medico();
		medicoDao = new MedicoDao();
		medico.setCrm(12345);
		medico.setCrmUf("DF");
		medico.setUsuario(usuario);
		medico.setEspecialidades(especialidades);
		medico.setHoraInicio(1200);
		medico.setHoraFim(9888);
		medico.setConsultorio(123);
		medico.setDataAtendimento("1-2");
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
		List<Medico> medicoRecuperado = medicoDao.recuperaPeloCrm(1234);
		assertNotNull(medicoRecuperado);
		assertEquals("Batista", medicoRecuperado.get(0).getUsuario().getNome());
		assertEquals("GO", medicoRecuperado.get(0).getCrmUf());
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
	
	@Test
	public void alterarMedicoComEspecialidade(){
		Usuario usuario = new Usuario();
		EspecialidadeDao especialidadeDao = new EspecialidadeDao();
		Cripto cripto =new Cripto();
		usuario.setNome("Elizeu");
		usuario.setEmail("ddd@iii");
		usuario.setCpf("54454545");
		usuario.setPerfil(Perfil.MEDICO.getCodigo());
		usuario.setSexo(Sexo.MASCULINO.getSigla());
		usuario.setAcesso("elizeu");
		usuario.setSenha(cripto.criptografar("asdf"));
		usuario.setDataNascimento(new Date());

		EspecialidadeMedica especialidadeMedica = especialidadeDao.recuperarEspecialidade(3);
		EspecialidadeMedica especialidadeMedica2 = especialidadeDao.recuperarEspecialidade(4);

		List<EspecialidadeMedica> especialidades = new ArrayList<EspecialidadeMedica>();
		especialidades.add(especialidadeMedica);
		especialidades.add(especialidadeMedica2);

		Medico medico = new Medico();
		medicoDao = new MedicoDao();
		medico.setCrm(12345);
		medico.setCrmUf("DF");
		medico.setUsuario(usuario);
		medico.setEspecialidades(especialidades);
		medico.setHoraInicio(1200);
		medico.setHoraFim(9888);
		medico.setConsultorio(123);
		medico.setDataAtendimento("1-2");
		medicoDao.salvarMedico(medico);
		
		Medico medico2 = medicoDao.recuperaPeloId(medico.getId());
		assertNotNull(medico2);
		
		medico2.getEspecialidades().remove(0);
		medicoDao.salvarMedico(medico2);
		
	}
	
	@Test
	public void excluirMedicoComEspecialidade(){
		Usuario usuario = new Usuario();
		EspecialidadeDao especialidadeDao = new EspecialidadeDao();
		Cripto cripto =new Cripto();
		usuario.setNome("Elizeu");
		usuario.setEmail("ddd@iii");
		usuario.setCpf("54454545");
		usuario.setPerfil(Perfil.MEDICO.getCodigo());
		usuario.setSexo(Sexo.MASCULINO.getSigla());
		usuario.setAcesso("elizeu");
		usuario.setSenha(cripto.criptografar("asdf"));
		usuario.setDataNascimento(new Date());

		EspecialidadeMedica especialidadeMedica = especialidadeDao.recuperarEspecialidade(3);
		EspecialidadeMedica especialidadeMedica2 = especialidadeDao.recuperarEspecialidade(4);

		List<EspecialidadeMedica> especialidades = new ArrayList<EspecialidadeMedica>();
		especialidades.add(especialidadeMedica);
		especialidades.add(especialidadeMedica2);

		Medico medico = new Medico();
		medicoDao = new MedicoDao();
		medico.setCrm(12345);
		medico.setCrmUf("DF");
		medico.setUsuario(usuario);
		medico.setEspecialidades(especialidades);
		medico.setHoraInicio(1200);
		medico.setHoraFim(9888);
		medico.setConsultorio(123);
		medico.setDataAtendimento("1-2");
		medicoDao.salvarMedico(medico);
		
		Medico medico2 = medicoDao.recuperaPeloId(medico.getId());
		
		this.medicoDao.remover(medico2);
		
	}

}
