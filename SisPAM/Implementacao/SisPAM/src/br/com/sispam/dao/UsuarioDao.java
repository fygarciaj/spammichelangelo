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
	public Usuario salvarUsuario(Usuario usuario){
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
		return usuario;
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
	 * @descricao: Remove o usuario do sistema.
	 * @param usuario
	 */
	public void removerUsuario(Usuario usuario){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		manager.getTransaction().begin();
		usuario = manager.merge(usuario);
		manager.remove(usuario);
		manager.getTransaction().commit();
		conexao.finalizaConexao();
	}

	/**
	 * @descricao: Recupera o usuário pelo Id.
	 * @param id
	 * @return
	 */
	public Usuario recuperarPeloId(int id){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		return manager.find(Usuario.class, id);
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


	/**
	 * @descricao: recupera o usuário pelo seu nome.
	 * @param nome, codigoPerfil
	 * @return
	 */
	public List<Usuario> recuperaPeloNome(String nome, int codigoPerfil){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Usuario> lista = null;
		try{
			//cria uma queri para fazer a busca pelo perfil
			Query query = manager.createQuery("from Usuario where nome like :nome and perfil = :perfil");
			//seta o parametro
			query.setParameter("nome", "%"+nome+"%");
			query.setParameter("perfil", codigoPerfil);
			lista = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return lista;
	}

	/**
	 * @descricao: Lista os últimos usuários cadastrados no sistema.
	 * @return
	 */
	public List<Usuario> recuperarUltimosCadastrados(int codigoPerfil) {
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		List<Usuario> lista = null;
		try{
			Query query = manager.createQuery("from Usuario where perfil = :perfil order by id desc");
			query.setParameter("perfil", codigoPerfil);
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

	/**
	 * @descricao: Recupera o usuário pelo Login.
	 * @param acesso
	 * @return
	 */
	public Usuario recuperaPeloLogin(String acesso) {
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Usuario usuario = null;
		try{
			//cria uma queri para fazer a busca pelo perfil
			Query query = manager.createQuery("from Usuario where acesso = :acesso ");
			//seta o parametro
			query.setParameter("acesso", acesso);
			usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		conexao.finalizaConexao();
		return usuario;
	}


}
