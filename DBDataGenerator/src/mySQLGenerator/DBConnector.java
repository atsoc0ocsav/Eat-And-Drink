package mySQLGenerator;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connector for MySQL Database
 * @author Vasco Craveiro Costa
 */
public class DBConnector {

    private static final String db = "pr-innovation";
    private static final String user = "pr";
    private static final String password = "sql";
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost/"
            + db + "?" + "user=" + user + "&password=" + password;

    public DBConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.err.println("Driver \"com.mysql.jdbc.Driver\"");
        }
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection Succesfull Established!");
        } catch (SQLException ex) {
            System.out.println("Connection error!!");
            System.out.println("SQL Exception: " + ex.getMessage());
            System.out.println("SQL State: " + ex.getSQLState());
            System.out.println("Error Code: " + ex.getErrorCode());
        }
    }

    public Connection connection() {
        return connection;
    }

    public static String getDatabaseName() {
        return db;
    }
}
