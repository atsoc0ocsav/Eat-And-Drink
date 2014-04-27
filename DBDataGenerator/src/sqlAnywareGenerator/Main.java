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
			dbAction.addMealsToDB(1000);
			dbAction.addUsersToDB(10000);
			dbAction.addEstablishmentsToDB(1000);
			dbAction.addFollowersToDB(500);
			dbAction.addAvailableEventsToDB(500);
			dbAction.addEstablishmentScheduleToDB();
			dbAction.addEstablishmentMenusToDB(10);
			dbAction.addRecomendedMealsToDB(500);
			dbAction.addMealsCommentsToDB(500);
			dbAction.addEstablishmentCommentsToDB(500);
			dbAction.addMealsPhotographies(500);

			System.out.println("\n---------------------------------------");
			System.out.println("Task Duration: "
					+ (System.currentTimeMillis() - time) + " ms");
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
