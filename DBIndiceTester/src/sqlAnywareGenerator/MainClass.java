package sqlAnywareGenerator;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

public class MainClass {
	private static final int USERS_TO_ADD = 5;
	private static final int ESTABLISHMENTS_TO_ADD = 10000;
	private static final int INDEX_MAX_QUANTITY_TO_TEST = 3;
	private DBActions dbAction;

	public static void main(String[] args) {
		MainClass main = new MainClass();

		try {
			main.initialyConfigureDatabase();
			main.deleteAllDataFromDatabase();

			for (int i = 0; i <= INDEX_MAX_QUANTITY_TO_TEST; i++) {
				System.out
						.println("\n#######################################################");
				System.out.println("####### Test with " + i + " index, "
						+ ESTABLISHMENTS_TO_ADD + " establishments #######");

				main.addIndex(i);
				main.doInsertTest();
				main.doSelectTest();

				// main.removeIndex();

				main.deleteAllDataFromDatabase();
			}

		} catch (NoSuchAlgorithmException | SQLException e) {
			e.printStackTrace();
		}
	}

	public MainClass() {
		dbAction = new DBActions();
	}

	public void initialyConfigureDatabase() {
		dbAction.createZoneFieldInUserTable();
		// dbAction.removeIndices(true);
	}

	public void addIndex(int indexQnt) {
		for (int i = 0; i < indexQnt; i++) {
			dbAction.addIndices(i);
		}

		if (indexQnt == 0)
			System.out.println("No index added!");
	}

	public void removeIndex() {
		dbAction.removeIndices(false);
	}

	public void doInsertTest() throws SQLException, NoSuchAlgorithmException {
		long time = System.currentTimeMillis();

		// insert the data
		dbAction.addCitiesToDB();
		dbAction.addZonesToDB();
		dbAction.addEstablishmentTypesToDB();
		dbAction.addUsersToDB(USERS_TO_ADD);
		dbAction.addEstablishmentsToDB(ESTABLISHMENTS_TO_ADD);

		System.err.println("--->Insert Test Duration Time: "
				+ (System.currentTimeMillis() - time) + " ms");
	}

	public void doSelectTest() throws SQLException, NoSuchAlgorithmException {
		long time = System.currentTimeMillis();

		dbAction.selectEstablishmentByTypeAndUserAndZone("Snack-Bar",
				("EPRV_" + new Random().nextInt(USERS_TO_ADD)), 1);

		System.err.println("--->Select Test Duration Time: "
				+ (System.currentTimeMillis() - time) + " ms");
	}

	public void deleteAllDataFromDatabase() {
		dbAction.removeAllDataFromDatabase();
	}
}
