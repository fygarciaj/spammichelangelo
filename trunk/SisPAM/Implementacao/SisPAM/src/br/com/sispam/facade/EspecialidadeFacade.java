package br.com.sispam.facade;

import java.util.List;

import br.com.sispam.dao.EspecialidadeDao;
import br.com.sispam.dominio.EspecialidadeMedica;

public class EspecialidadeFacade {
	
	private EspecialidadeDao especialidadeDao;
	
	public EspecialidadeFacade(){
		this.especialidadeDao = new EspecialidadeDao();
	}
	
	/**
	 * @descricao: Recupera todas especialidades.
	 * @return
	 */
	public List<EspecialidadeMedica>recuperarTodas(){
		return especialidadeDao.recuperarTodas();
	}
	
	/**
	 * @descricao: Recuperar a especialidade pelo id.
	 * @param id
	 * @return
	 */
	public EspecialidadeMedica recuperarPeloId(int id){
		return especialidadeDao.recuperarEspecialidade(id);
	}
	
	
}
