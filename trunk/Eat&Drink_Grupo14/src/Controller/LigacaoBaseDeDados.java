package Controller;

import java.sql.*;

public class LigacaoBaseDeDados {

	private Connection conn = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public LigacaoBaseDeDados(){ 
		String db = "eatdrink"; 
		String user = "dba"; 
		String pass = "sql"; 
		String dbUrl = "jdbc:sqlanywhere:Tds:localhost:2638?eng=" + db;

		try {
			conn = DriverManager.getConnection(dbUrl,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public ResultSet select(String query) throws SQLException{
		try {
			resultSet = null;
			statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return resultSet;
	}

	public boolean update(String command){
		try {
			statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			int state = statement.executeUpdate(command);
			statement.close();
			if(state == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insert(String command){
		try {
			statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			int state = statement.executeUpdate(command);
			statement.close();
			if(state == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(String command) {
		boolean state = false;
		try {
			statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			state = statement.execute(command);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	public void closeStatement(){
		try {
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}	
