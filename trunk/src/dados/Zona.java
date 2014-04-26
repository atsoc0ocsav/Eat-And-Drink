package dados;

import java.sql.ResultSet;
import java.util.ArrayList;

import dbc.DBConnection;

public class Zona {
	private DBConnection dbcConnection;

	public Zona(DBConnection dbcConnection) {
		super();
		this.dbcConnection = dbcConnection;
	}

	public ArrayList<Zona> getZonas() {
		
		ArrayList<Zona> zonas = new ArrayList<Zona>();
		
		ResultSet resultSet = dbcConnection.select("SELECT * FROM Zona");

		
		return null;
	}
	
	

}
