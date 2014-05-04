package dbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {

	public static void main(String[] Args) {
		DBConnection db;

		boolean works = false;
		try {
			db = new DBConnection();
			works = true;

			if (works) {
				System.out.println("It Works!");

				// db.insert("");
				// db.delete("");

				showResult(db.select(""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showResult(ResultSet result) {
		try {
			while (result.next()) {
				try {
					System.out.println(result.getString(0));
				} catch (SQLException e) {
					System.out.println("Falha ao ir buscar cada resultado");
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha ao ir buscar o próximo");
		}

	}

}
