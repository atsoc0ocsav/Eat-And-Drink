package sqlAnywareGenerator;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class MainClass {
	private static final int USERS_TO_ADD = 1000;
	private static final int ESTABLISHMENTS_TO_ADD = 10000;
	private DBActions dbAction;

	public static void main(String[] args) {
		try {
			MainClass main = new MainClass();
			main.initialyConfigureDatabase();

			for (int i = 0; i < 3; i++) {
				System.out.println("#######################################");
				System.out.println("####### Teste com " + i
						+ " indices #######");

				main.addIndex(i);
				main.doInsertTest();

				main.doSelectTest();

				main.removeIndex();
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
		dbAction.removeIndices(true);
	}

	public void addIndex(int indexQnt) {
		for (int i = 0; i < indexQnt; i++) {
			dbAction.addIndices(i);
		}
	}

	public void removeIndex() {
		dbAction.removeIndices(false);
	}

	public void doInsertTest() throws SQLException, NoSuchAlgorithmException {
		long time = System.currentTimeMillis();
		System.out.println("Teste de Inserção - " + USERS_TO_ADD
				+ " utilizadores e " + ESTABLISHMENTS_TO_ADD
				+ " estabelecimentos");

		// insert the data
		dbAction.addCitiesToDB();
		dbAction.addZonesToDB();
		dbAction.addEstablishmentTypesToDB();
		dbAction.addUsersToDB(USERS_TO_ADD);
		dbAction.addEstablishmentsToDB(ESTABLISHMENTS_TO_ADD);

		System.out.println("Duração: " + (System.currentTimeMillis() - time)
				+ " ms\n");
	}

	public void doSelectTest() throws SQLException, NoSuchAlgorithmException {
		long time = System.currentTimeMillis();
		System.out.println("Teste de Select:");

		// TODO
		// dbAction.selectEstablishmentByTypeAndUserAndZone(type, userEmail, 1);

		System.out.println("Duração: " + (System.currentTimeMillis() - time)
				+ " ms\n");

		dbAction.removeIndices(false);
	}
}
