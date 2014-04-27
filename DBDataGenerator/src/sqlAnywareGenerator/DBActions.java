package sqlAnywareGenerator;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBActions {
	private FileParser parser = new FileParser();
	private DBConnection dbConnection;

	public DBActions() {
		this.dbConnection = new DBConnection();
	}

	public void addCitiesToDB() throws SQLDataException {
		ArrayList<String[]> cities = parser.cvsFileParser(
				"./data/Lista de Municípios.csv", ";");

		for (String[] city : cities) {
			dbConnection.executeUpdate("INSERT INTO Cidade(Cidade) VALUES ('"
					+ city[1] + "')");
		}

		dbConnection.commit();
		System.out.println(cities.size()
				+ " Cities Added with success to database");
	}

	public void addZonesToDB() throws SQLException {
		ArrayList<String[]> zones = parser.cvsFileParser(
				"./data/Lista de Freguesias.csv", ";");

		ResultSet test = dbConnection
				.executeQuery("SELECT FIRST idZona FROM Zona ORDER BY idZona DESC");

		int currentID;
		if (test.next())
			currentID = test.getInt(1) + 1;
		else
			currentID = 0;

		for (String[] zone : zones) {
			dbConnection
					.executeUpdate("INSERT INTO Zona(idZona,Cidade,Designação) VALUES ('"
							+ currentID
							+ "','"
							+ zone[0]
							+ "','"
							+ zone[1].split("[ (]")[0] + "')");
			currentID++;
		}
		
		dbConnection.commit();
		System.out.println(zones.size()
				+ " Zones Added with success to database");
	}
}
