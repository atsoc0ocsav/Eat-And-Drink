package sqlAnywareGenerator;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class DBActions {
	public enum Operation_Type {
		ADD_CITY, ADD_ZONE
	};

	private FileParser parser = new FileParser();
	private DBConnection dbConnection;

	public DBActions() {
		this.dbConnection = new DBConnection();
	}

	public void addCitiesToDB() throws SQLDataException {
		ArrayList<String> cities = (ArrayList<String>) parser
				.plainTextFileParser("./data/Lista de Cidades.txt");

		for (String city : cities) {
			if (dbConnection
					.executeUpdate("INSERT INTO Cidade(cidade) VALUES ('"
							+ city + "')") < 0)
				throw new SQLDataException("Error on city inserting method");
		}

		System.out.println(cities.size()
				+ " Cities Added with success to database");
	}

	public void addZonesToDB() {

	}
}
