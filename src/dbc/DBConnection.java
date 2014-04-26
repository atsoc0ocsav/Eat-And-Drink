package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static final String DB = "eatdrink";
	private static final String USER = "dba";
	private static final String PASSWORD = "sql";
	private static final String HOST_NAME = "localhost";
	private static final String URL = "jdbc:sqlanywhere:Tds:" + HOST_NAME
			+ ":2638?eng=" + DB;
	// private static final String url =
	// "jdbc:sqlanywhere:Tds:localhost:2638?eng=" + db;

	private Connection conn = null;

	// Acho q tou a ter problemas com o porto!
	public DBConnection() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Erro ao estabelecer a ligação.");
			System.out.println("SQL Exception: " + e.getMessage());
			System.out.println("SQL State: " + e.getSQLState());
			System.out.println("Error Code: " + e.getErrorCode());
		}
	}

	// Metodos estavam começados com maiuscula no relatorio
	// Coloquei os métodos a receberem uma string mas se calhar podiam so
	// receber os argumentos necesarios

	public ResultSet select(String sql) {
		// possível chamada a uma função que prepara o commando select apenas
		// recebendo os seus argumentos

		ResultSet resultSet = null;

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;

	}

	// No relatorio e void mas devia retornar um int...
	public void insert(String sql) {
		// possível chamada a uma função que prepara o commando insert apenas
		// recebendo os seus argumentos

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception: " + e.getMessage());
			System.out.println("SQL State: " + e.getSQLState());
			System.out.println("Error Code: " + e.getErrorCode());
		}

	}

	// No relatorio e void mas devia retornar um int...
	public void delete(String sql) {
		// possível chamada a uma função que prepara o commando delect apenas
		// recebendo os seus argumentos

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception: " + e.getMessage());
			System.out.println("SQL State: " + e.getSQLState());
			System.out.println("Error Code: " + e.getErrorCode());
		}

	}

}