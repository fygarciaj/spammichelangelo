package br.com.sispam.dao;

import javax.persistence.EntityManager;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Usuario;

public class UsuarioDao {
	
	private Conexao conexao;
	private EntityManager manager;
	
	public void salvarUsuario(Usuario usuario){
		
		manager = conexao.getEntityManger();
		manager.persist(usuario);
		conexao.finalizaConexao();
	}
}
