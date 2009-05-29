package br.com.sispam.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.sispam.dao.EspecialidadeDao;
import br.com.sispam.dominio.EspecialidadeMedica;

public class EspecialidadeFacade {

	private EspecialidadeDao especialidadeDao;

	public EspecialidadeFacade(){
		this.especialidadeDao = new EspecialidadeDao();
	}

	/**
	 * : Recupera todas especialidades.
	 * @return
	 */
	public List<EspecialidadeMedica>recuperarTodas(){
		return especialidadeDao.recuperarTodas();
	}

	/**
	 * : Recupera todas especialidades.
	 * @return
	 */
	public List<EspecialidadeMedica>recuperarTodas(List<Integer> lista){
		if(lista != null && lista.size() > 0){
			return especialidadeDao.recuperarTodas(lista);
		}else{
			return null;
		}
	}



	/**
	 * : Recuperar a especialidade pelo id.
	 * @param id
	 * @return
	 */
	public EspecialidadeMedica recuperarPeloId(int id){
		return especialidadeDao.recuperarEspecialidade(id);
	}


	/**
	 * : Recupera uma lista de especialidades apartir dos caracteres passados.
	 * @param esp
	 * @return
	 */
	public List<EspecialidadeMedica> recuperaEspecialidades(String esp){
		this.especialidadeDao = new EspecialidadeDao();
		List<String> listaString = new ArrayList<String>();
		List<Integer> especialidades = new ArrayList<Integer>();
		char[] espCharacter = esp.toCharArray();

		for(char caracter: espCharacter){
			if(caracter != ',' && caracter != ' '){
				listaString.add(String.valueOf(caracter));
			}
		}

		for(String string: listaString){
			especialidades.add(Integer.parseInt(string));
		}

		return this.especialidadeDao.recuperarEspecialidades(especialidades);
	}


}
