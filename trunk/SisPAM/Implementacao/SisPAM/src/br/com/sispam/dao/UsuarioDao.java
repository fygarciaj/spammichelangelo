package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
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
	public Usuario recupera(String cpf){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Usuario usuario = null;
		try{
			//cria uma queri para fazer a busca pelo perfil
			Query query = manager.createQuery("from Usuario where cpf = :cpf ");
			//seta o parametro
			query.setParameter("cpf", cpf);
			usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return usuario;
	}
	
	public Usuario recuperaSenha(String userAcess){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Usuario usuario = null;
		try{
			//cria uma queri para fazer a busca pelo perfil
			Query query = manager.createQuery("from Usuario where userAcess = :userAcess ");
			//seta o parametro
			query.setParameter("userAcess", userAcess);
			usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return usuario;
	}

	/**
	 * @descricao: Lista os últimos usuários cadastrados no sistema.
	 * @return
	 */
	public List<Usuario> recuperarUltimosCadastrados() {
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Usuario> lista = null;
		try{
			Query query = manager.createQuery("from Usuario order by id desc");
			query.setMaxResults(8);
			lista = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return lista;
	}

	public void removerTodosTeste() {
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		try{
			manager.getTransaction().begin();
			Query query = manager.createQuery("delete from Usuario where id > 0");
			query.executeUpdate();
			manager.getTransaction().commit();
		}catch (EntityExistsException e) {
			e.printStackTrace();
		}
	}


}
