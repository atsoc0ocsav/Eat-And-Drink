package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static final boolean DEBUG_MODE = true;
	private static final String DBNAME = "eatdrink";
	private static final String USER = "dba";
	private static final String PASSWORD = "sql";
	private static final String HOST_NAME = "localhost";
	private static final String URL = "jdbc:sqlanywhere:Tds:" + HOST_NAME
			+ ":2638?eng=" + DBNAME;

	private Connection conn = null;

	/**
	 * Creates a new connection to the database, using the parameters specified
	 * has constants
	 */
	public void doConnections() {
		if(conn == null){
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			System.err
					.println("Erro ao estabelecer a liga��o � base de dados\n");
			printSQLException(e);
		}
		}
	}

	// Metodos estavam come�ados com maiuscula no relatorio
	// Coloquei os m�todos a receberem uma string mas se calhar podiam so
	// receber os argumentos necesarios

	public ResultSet select(String sql) {
		// poss�vel chamada a uma fun��o que prepara o commando select apenas
		// recebendo os seus argumentos

		ResultSet resultSet = null;

		try {
			doConnections();

			PreparedStatement prepStat = conn.prepareStatement(sql);
			resultSet = prepStat.executeQuery();
		} catch (SQLException e) {
			printSQLException(e);
		}

		return resultSet;
	}

	public ResultSet selectConcorrenciaPessimista(String sql) throws SQLException {
		// poss�vel chamada a uma fun��o que prepara o commando select apenas
		// recebendo os seus argumentos

		ResultSet resultSet = null;
		PreparedStatement prepStat = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		resultSet = prepStat.executeQuery();

		return resultSet;
	}
	
	public ResultSet selectConcorrenciaOptimista(String sql) throws SQLException {
		// poss�vel chamada a uma fun��o que prepara o commando select apenas
		// recebendo os seus argumentos

		doConnections();
		
		ResultSet resultSet = null;
		PreparedStatement prepStat = conn.prepareStatement(sql);
		resultSet = prepStat.executeQuery();

		return resultSet;
	}

	// No relatorio e void mas devia retornar um int...
	public void insert(String sql) {
		// poss�vel chamada a uma fun��o que prepara o commando insert apenas
		// recebendo os seus argumentos

		try {
			doConnections();

			PreparedStatement prepStat = conn.prepareStatement(sql);
			prepStat.executeUpdate();

			commit();
		} catch (SQLException e) {
			printSQLException(e);
		}

	}

	public int insertConcorrencia(String sql) throws SQLException {
		// poss�vel chamada a uma fun��o que prepara o commando insert apenas
		// recebendo os seus argumentos
		doConnections();
		
		PreparedStatement prepStat = conn.prepareStatement(sql);
		int updRows = prepStat.executeUpdate();

		commit();
		
		return updRows;
	}

	// No relatorio e void mas devia retornar um int...
	public void delete(String sql) {
		// poss�vel chamada a uma fun��o que prepara o commando delect apenas
		// recebendo os seus argumentos

		try {
			doConnections();

			PreparedStatement prepStat = conn.prepareStatement(sql);
			prepStat.executeUpdate();

			commit();
		} catch (SQLException e) {
			printSQLException(e);
		}

	}

	/**
	 * Prints data about the given SQL Exception
	 * 
	 * @param SQLException
	 */
	private void printSQLException(SQLException e) {
		if (DEBUG_MODE)
			e.printStackTrace();
		System.out.println("SQL Exception: " + e.getMessage());
		System.out.println("SQL State: " + e.getSQLState());
		System.out.println("Error Code: " + e.getErrorCode());
	}

	/**
	 * Commits the changes made to the Database
	 */
	private void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	/**
	 * Closes the connection to the database
	 */
	public void closeDBConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				printSQLException(e);
			} finally {
				conn = null;
			}
		}
	}

	public void setIsolationLevel(int i) {

		try {
			doConnections();

			PreparedStatement prepStat = conn
					.prepareStatement("SET TEMPORARY OPTION isolation_level = "
							+ Integer.toString(i) + ";");
			prepStat.execute();

		} catch (SQLException e) {
			printSQLException(e);
		}

	}

	public void begin() {

		try {
			// conn.commit();
			PreparedStatement prepStat;
			prepStat = conn.prepareStatement("BEGIN TRANSACTION");
			prepStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setTemporaryLockTimeout(int i) {
		try {
			// conn.commit();
			PreparedStatement prepStat;
			prepStat = conn
					.prepareStatement("SET TEMPORARY OPTION blocking_timeout = "
							+ Integer.toString(i) + ";");
			prepStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}