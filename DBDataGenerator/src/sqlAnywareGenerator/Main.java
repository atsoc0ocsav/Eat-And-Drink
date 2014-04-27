package sqlAnywareGenerator;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		try {
			DBActions dbAction = new DBActions();

			long time = System.currentTimeMillis();
			System.out.println("Adding data to database!");
			System.out.println("---------------------------------------");

			// dbAction.addCitiesToDB();
			// dbAction.addZonesToDB();
			// dbAction.addEstablishmentTypesToDB();
			// dbAction.addWeekDaysToDB();
			// dbAction.addEventTypeToDB();
			// dbAction.addMealTypeToDB();
			// dbAction.addMealsToDB(100000);
			dbAction.addUsersToDB(2);

			System.out.println("\n---------------------------------------");
			System.out.println("Task Duration: "
					+ (System.currentTimeMillis() - time) + " ms");
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
