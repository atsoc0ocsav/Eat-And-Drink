package sqlAnywareGenerator;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		try {
			DBActions dbAction = new DBActions();

			long time = System.currentTimeMillis();
			
			dbAction.createZoneFieldInUserTable();
			dbAction.removeAllDataFromDatabase();
			
			System.out.println("\nAdding data to database!");
			System.out.println("---------------------------------------");

			dbAction.addCitiesToDB();
			dbAction.addZonesToDB();
			dbAction.addEstablishmentTypesToDB();
			dbAction.addWeekDaysToDB();
			dbAction.addEventTypeToDB();
			dbAction.addMealTypeToDB();
			dbAction.addMealsToDB(2500);
			dbAction.addUsersToDB(1000);
			dbAction.addEstablishmentsToDB(1000);
			dbAction.addFollowersToDB(500);
			dbAction.addAvailableEventsToDB(3000);
			dbAction.addEstablishmentScheduleToDB();
			dbAction.addEstablishmentMenusToDB(10);
			dbAction.addRecomendedMealsToDB(5);
			dbAction.addMealsCommentsToDB(750);
			dbAction.addEstablishmentCommentsToDB(750);
			dbAction.addMealsPhotographies(1000);
			dbAction.addTicketsToDB();

			long milliseconds = System.currentTimeMillis() - time;

			int seconds = (int) (milliseconds / 1000) % 60;
			int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
			int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

			System.err.println("--->Duration Time: " + hours + "h" + minutes
					+ "m" + seconds + "s");

		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
