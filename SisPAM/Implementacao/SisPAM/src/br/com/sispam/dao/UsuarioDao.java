package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Perfil;

public class UsuarioDao {

	private Conexao conexao;
	private EntityManager manager;

	/**
	 * @descricao: Salva e atualiza o usuário passado.
	 * @param usuario
	 */
	public void salvarUsuario(Usuario usuario){
		conexao = new Conexao();
		manager = conexao.getEntityManger();

		manager.getTransaction().begin();
		//verifica se possui id caso possua apenas atualiza os dados no banco
		if(usuario != null && usuario.getId() > 0){
			manager.merge(usuario);
		}
		//caso não salva um novo usuário
		else{
			manager.persist(usuario);
		}
		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}

	/**
	 * @descricao: Lista todos usuários apartir do perfil passado.
	 * @param perfil
	 */
	public List<Usuario> recuperaLista(Perfil perfil){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		//cria uma queri para fazer a busca pelo perfil
		Query query = manager.createQuery("from Usuario u where u.perfil = :perfil ");
		//seta o parametro
		query.setParameter("perfil", perfil);
		List<Usuario> lista = query.getResultList();
		conexao.finalizaConexao();
		return lista;
	}
	
	/**
	 * @descricao: recupera o usuário pelo seu cpf.
	 * @param cpf
	 * @return
	 */
	public Usuario recupera(long cpf){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Usuario usuario = null;
		try{
			//cria uma queri para fazer a busca pelo perfil
			Query query = manager.createQuery("select u from Usuario u where u.cpf = :cpf ");
			//seta o parametro
			query.setParameter("cpf", cpf);
			usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return usuario;
	}


}
