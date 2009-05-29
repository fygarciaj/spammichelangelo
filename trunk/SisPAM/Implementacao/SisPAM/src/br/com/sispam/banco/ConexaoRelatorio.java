package br.com.sispam.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoRelatorio {

	private Connection connection;
	/**
	 * : Cria uma conexão para gerar os relatórios em pdf.
	 * @return
	 */
	public Connection getConexao(){
		String url = "jdbc:postgresql://localhost:5432/sispam";
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, "postgres", "postgres");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Sem conexão");
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver não encontrado");
			return null;
		}
	}

}
