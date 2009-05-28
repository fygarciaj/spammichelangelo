package br.com.sispam.teste.dao;

import java.util.Date;
import org.junit.Test;

import br.com.sispam.dao.CompromissoDao;
import br.com.sispam.dao.MedicoDao;
import br.com.sispam.dominio.Compromisso;
import br.com.sispam.dominio.Medico;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Perfil;
import br.com.sispam.enums.Sexo;
import br.com.sispam.util.Cripto;

public class TesteCompromissoDao {

	@Test
	public void salvarCompromissos(){
		Usuario usuario = new Usuario();
		Cripto cripto = new Cripto();
		usuario.setEmail("ddd@iii");
		usuario.setCpf("28391900797");
		usuario.setPerfil(Perfil.MEDICO.getCodigo());
		usuario.setSexo(Sexo.FEMININO.getSigla());
		usuario.setAcesso("maria");
		usuario.setSenha(cripto.criptografar("maria"));
		usuario.setNome("Maria Madalena");
		usuario.setDataNascimento(new Date());
		
		Medico medico = new Medico();
		medico.setUsuario(usuario);
		medico.setCrm(123);
		medico.setConsultorio(12);
		medico.setCrmUf("DF");
		
		MedicoDao dao = new MedicoDao();
		dao.salvarMedico(medico);
		
		//salva o medico com um compromisso
		Compromisso compromisso = new Compromisso();
		compromisso.setData(new Date());
		compromisso.setDescricao("Aculpuntura");
		compromisso.setTipo("Consulta");
		compromisso.setMedico(medico);
		
		CompromissoDao compromissoDao = new CompromissoDao();
		compromissoDao.incluirCompromisso(compromisso);
	}
	
}
