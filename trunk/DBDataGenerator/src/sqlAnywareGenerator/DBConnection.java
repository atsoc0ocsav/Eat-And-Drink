package sqlAnywareGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	// Connection Parameters
	private static final boolean DEBUG_MODE = false;
	private static final String DBNAME = "eatdrink";
	private static final String USER = "dba";
	private static final String PASSWORD = "sql";
	private static final String HOST_NAME = "localhost";
	private static final int PORT = 2638;

	private static final String URL = "jdbc:sqlanywhere:Tds:" + HOST_NAME + ":"
			+ PORT + "?eng=" + DBNAME;
	private Connection connection = null;
	private Statement stmt = null;

	/**
	 * Make the connection to the DB
	 */
	public DBConnection() {
		try {
			Class.forName("sybase.jdbc4.sqlanywhere.IDriver").newInstance();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException ex) {
			System.err.println("Driver \"sybase.jdbc4.sqlanywhere.IDriver\"");
		}

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			connection.setAutoCommit(false);
			System.out.println("Connection Succesfull Established!\n");
		} catch (SQLException e) {
			System.out.println("Erro ao estabelecer a ligação.");
			System.out.println("SQL Exception: " + e.getMessage());
			System.out.println("SQL State: " + e.getSQLState());
			System.out.println("Error Code: " + e.getErrorCode());
		}
	}

	/**
	 * Returns the connection instance (null in case no connection is already
	 * established)
	 * 
	 * @return connection instance
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Returns the database name used to create the connection
	 * 
	 * @return database name
	 */
	public static String getDatabaseName() {
		return DBNAME;
	}

	/**
	 * Prints information about a thrown exception
	 * 
	 * @param exception
	 */
	public void printSQLException(SQLException e) {
		if (DEBUG_MODE) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			if (e.getCause() != null) {
				System.out.println("Cause: " + e.getCause().toString());
			} else {
				System.out.println("Cause: null");
			}
		}
	}

	/**
	 * Release the resources used in database interactions
	 */
	private void releaseResources() {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqlEx) {
			} finally {
				stmt = null;
			}
		}
	}

	/**
	 * Runs the supplied select query in the database
	 * 
	 * @param sql
	 *            select query
	 * @return resultSet of the execution of the query
	 */
	public ResultSet executeQuery(String query) {
		releaseResources();
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			printSQLException(e);
		}
		return rs;
	}

	/**
	 * Runs the supplied INSERT, UPDATE or DELETE query in the database
	 * 
	 * @param sql
	 *            statment (INSERT, UPDATE or DELETE)
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 */
	public boolean executeUpdate(String query) {
		releaseResources();
		boolean i = false;
		try {
			stmt = connection.createStatement();
			i = stmt.execute(query);
		} catch (SQLException e) {
			printSQLException(e);
		}
		return i;
	}

	/**
	 * Commits the changes made to the Database
	 */
	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
}