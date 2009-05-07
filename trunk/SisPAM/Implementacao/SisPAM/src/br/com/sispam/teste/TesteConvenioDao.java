package br.com.sispam.teste;

import org.junit.Test;

import br.com.sispam.dao.ConvenioDao;
import br.com.sispam.dominio.Convenio;


public class TesteConvenioDao {
	private ConvenioDao convenioDao;
	
	@Test
	public void salvaConvenio(){
		Convenio convenio = new Convenio();
		convenioDao = new ConvenioDao();
		
		convenio.setNome("TESTE");
		convenio.setCep(73);
		convenio.setCidade("cidade");
		convenio.setCnpj("28022174000128");
		convenio.setDdd(61);
		convenio.setEmail("email");
		convenio.setEndereco("endereco");
		convenio.setEstado("estado");
		convenio.setSite("site");
		convenio.setTelefone(85444);
		convenio.setCodigoANS(5400);
		
		convenioDao.incluirConvenio(convenio);
		
	}
	
	@Test
	public void recuperarConvenio(){
		Convenio convenio = new Convenio();
		convenioDao = new ConvenioDao();
		convenio.setNome("TESTE");
		convenio.setCnpj("28022174000128");
		
		convenio = convenioDao.consultarConvenioPorCnpj(convenio.getCnpj());
		convenio = convenioDao.consultarConvenioPorDescricao(convenio.getNome());
		System.out.println(convenio.getCnpj() + convenio.getNome());
	}

	/*@Test
	public void excluirConvenio(){
		Convenio convenio = new Convenio();
		convenioDao = new ConvenioDao();
		
		convenio.setCnpj("28022174000128");
		
		convenioDao.excluirConvenio(convenio.getCnpj());
	}*/
}
