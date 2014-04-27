package mySQLGenerator;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Vasco Craveiro Costa
 */
public class DBInterface {

	// Interface Properties
	private Connection conn;
	private Statement stmt = null;
	private ResultSet rs = null;
	// User Generation Properties
	private final int numOfThreads = 16;
	private int userQnt;
	private SimulationWorker[] workers;
	private final String usersTable = "user";
	private final String userType = "Normal"; // Normal, Administrador de Grupo
												// ou Administrador Geral
	private final String[] names = { "Vasco", "Américo", "Rita", "Pedro",
			"Ana", "Paula", "Ana", "Joana", "Maria", "Bárbara", "Sara",
			"Teresa", "Filipa", "Mariana", "Vânia", "Inês", "Conceição",
			"Miguel", "Duarte", "Gonçalo", "Manuel", "David", "Paulo", "Pedro",
			"Marco", "Diogo", "Marcos", "João", "Rui", "Eduardo", "Margarida" };
	private final String[] surnames = { "Craveiro", "Vieira", "Teixeira",
			"Costa", "Dias", "Pedro", "Alves", "Ribeiro", "Bento", "Silva",
			"Tomás", "Borges", "Couto", "Pedreira", "Rebelo", "Pinto",
			"Marques", "Saldanha", "Viegas", "Marcelino", "Proença", "Azevedo",
			"Coelho", "Matos", "Sousa", "Sarmento", "Morais", "Mesquita" };
	private final String[] emailAlias = { "hotmail", "yahoo", "gmail",
			"facebook", "msn", "live", "space", "aol", "sapo", "zon", "oni",
			"clix" };
	private final String[] areaCode = { "com", "pt", "org", "mil", "ru", "so",
			"es", "br", "gk", "fr", "it" };

	// Connection Acquirement or Creation
	public DBInterface(Connection conn) {
		this.conn = conn;
	}

	public DBInterface() {
		this.conn = new DBConnector().connection();
	}

	public DBInterface(int userQnt) {
		generateSimpleUsersThreads(userQnt);
	}

	// SQL Query Funtions
	public ResultSet getAllDataInDB() {
		releaseResources();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user");
		} catch (SQLException e) {
			printSQLException(e);
		}
		return rs;
	}

	// SQL Insert Funtions
	private synchronized void executeInsertQuery(String string) {
		try {
			stmt = conn.createStatement();
			stmt.execute(string);
		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			releaseResources();
		}
	}

	// Auxiliary Funtions
	private void releaseResources() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqlEx) {
			}
			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqlEx) {
			}
			stmt = null;
		}
	}

	private void printSQLException(SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
		if (e.getCause() != null) {
			System.out.println("Cause: " + e.getCause().toString());
		} else {
			System.out.println("Cause: null");
		}
	}

	// Complex and Random Generator Function
	public void generateUsers(int qnt) {
		try {
			Random random = new Random();
			SecureRandom securerandom = new SecureRandom();
			for (int i = 0; i < qnt; i++) {

				String name = names[random.nextInt(names.length)] + " "
						+ surnames[random.nextInt(surnames.length)];
				// System.out.println("Name: " + name);

				String username = new BigInteger(130, securerandom)
						.toString(30);
				username = username.trim();
				username = username.substring(0, 8);
				// System.out.println("Username: " + username);

				String password = new BigInteger(130, securerandom)
						.toString(31);
				password = password.trim();
				password = password.substring(0, 25);
				password = md5Encode(password);
				// System.out.println("Password: " + password);

				String email = username + "@"
						+ emailAlias[random.nextInt(emailAlias.length)] + "."
						+ areaCode[random.nextInt(areaCode.length)];
				// System.out.println("Email: " + email);

				executeInsertQuery("INSERT INTO " + usersTable
						+ "(username,password,name,email,type) VALUES ('"
						+ username + "','" + password + "','" + name + "','"
						+ email + "','" + userType + "')");
				if (i % 200 == 0) {
					System.out.println("Users Already Added to Database: " + i);
				}
			}
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	// Predictable User Generator
	public void generateSimpleUsers(int qnt) {
		try {
			String password = "sql";
			String email = "simpleuser@pr-innovation.pt";
			String name = "Erwin SchrÃ¶dinger";
			String user = "erwin";
			for (int i = 0; i < qnt; i++) {

				password = md5Encode(password);
				String username = user + i;
				executeInsertQuery("INSERT INTO " + usersTable
						+ "(username,password,name,email,type) VALUES ('"
						+ username + "','" + password + "','" + name + "','"
						+ email + "','" + userType + "')");
				if (i % 200 == 0) {
					System.out.println("Users Already Added to Database: " + i);
				}
			}
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
	}

	// MD5 Encoder
	public String md5Encode(String string) throws NoSuchAlgorithmException {
		MessageDigest mdEnc;
		mdEnc = MessageDigest.getInstance("MD5");
		mdEnc.update(string.getBytes(), 0, string.length());
		return new BigInteger(1, mdEnc.digest()).toString(16);
	}

	public void generateSimpleUsersThreads(int qnt) {
		this.userQnt = qnt;
		workers = new SimulationWorker[numOfThreads];
		for (int i = 0; i < numOfThreads; i++) {
			SimulationWorker worker = new SimulationWorker();
			worker.start();
			workers[i] = worker;
		}

	}

	private class SimulationWorker extends Thread {

		@Override
		public void run() {
			try {
				String password = "sql";
				String email = "simpleuser@pr-innovation.pt";
				String name = "Erwin SchrÃ¶dinger";
				String user = "erwinn" + this.getId();
				Connection connection = new DBConnector().connection();
				Statement statement;
				for (int i = 0; i < userQnt / numOfThreads; i++) {
					password = md5Encode(password);
					String username = user + i;
					String query = "INSERT INTO " + usersTable
							+ "(username,password,name,email,type) VALUES ('"
							+ username + "','" + password + "','" + name
							+ "','" + email + "','" + userType + "')";
					statement = connection.createStatement();
					statement.execute(query);
					if (i % 200 == 0) {
						System.out.println("Users Already Added to Database: "
								+ i);
					}
				}
			} catch (NoSuchAlgorithmException ex) {
				System.err.println(ex);
			} catch (SQLException e) {
				printSQLException(e);
			}
		}

		public void stopThreads() {
			for (int i = 0; i < numOfThreads; i++) {
				workers[i].interrupt();
			}
		}
	}
}
