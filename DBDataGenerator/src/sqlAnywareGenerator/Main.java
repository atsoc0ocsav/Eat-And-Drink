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

			dbAction.createZoneFieldInUserTable();

			dbAction.addCitiesToDB();
			dbAction.addZonesToDB();
			dbAction.addEstablishmentTypesToDB();
			dbAction.addWeekDaysToDB();
			dbAction.addEventTypeToDB();
			dbAction.addMealTypeToDB();
			dbAction.addMealsToDB(2500);
			dbAction.addUsersToDB(10000);
			dbAction.addEstablishmentsToDB(1000);
			dbAction.addFollowersToDB(750);
			dbAction.addAvailableEventsToDB(3000);
			dbAction.addEstablishmentScheduleToDB();
			dbAction.addEstablishmentMenusToDB(15);
			dbAction.addRecomendedMealsToDB(500);
			dbAction.addMealsCommentsToDB(750);
			dbAction.addEstablishmentCommentsToDB(750);
			dbAction.addMealsPhotographies(1000);

			System.out.println("\n---------------------------------------");
			System.out.println("Task Duration: "
					+ (System.currentTimeMillis() - time) + " ms");
		} catch (SQLException | NoSuchAlgorithmException  e) {
			e.printStackTrace();
		}
	}
}
