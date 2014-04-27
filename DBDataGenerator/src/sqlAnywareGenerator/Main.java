package sqlAnywareGenerator;

import java.sql.SQLDataException;

public class Main {
	public static void main(String[] args) {
		try {
			DBActions dbAction = new DBActions();

			dbAction.addCitiesToDB();
			dbAction.addZonesToDB();
		} catch (SQLDataException e) {
			e.printStackTrace();
		}
	}
}
