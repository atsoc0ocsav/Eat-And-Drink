package sqlAnywareGenerator;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main {
	private static final int USERS_TO_ADD = 10000;
	private static final int ESTABLISHMENTS_TO_ADD = 1000;

	public static void main(String[] args) {
		try {
			Main main = new Main();
			
			main.doInsertTest();
			
			for(int i=0;i<3;i++){
				main.doSelectTest(i);
			}
		} catch (NoSuchAlgorithmException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void doInsertTest() throws SQLException, NoSuchAlgorithmException {
		DBActions dbAction = new DBActions();

		long time = System.currentTimeMillis();
		System.out.println("#######################################");
		System.out.println("Teste de Insereção - " + USERS_TO_ADD
				+ " utilizadores e " + ESTABLISHMENTS_TO_ADD
				+ " estabelecimentos");

		// initial modification to the default database
		dbAction.createZoneFieldInUserTable();

		// insert the data
		dbAction.addCitiesToDB();
		dbAction.addZonesToDB();
		dbAction.addEstablishmentTypesToDB();
		dbAction.addUsersToDB(USERS_TO_ADD);
		dbAction.addEstablishmentsToDB(ESTABLISHMENTS_TO_ADD);

		System.out.println("Duração: " + (System.currentTimeMillis() - time)
				+ " ms\n");
	}

	public void doSelectTest(int indicesQuantity) throws SQLException,
			NoSuchAlgorithmException {
		DBActions dbAction = new DBActions();
		dbAction.addIndices(indicesQuantity);

		long time = System.currentTimeMillis();
		System.out.println("#######################################");
		System.out.println("Teste de Select para " + indicesQuantity
				+ " indices:");

		dbAction.selectEstablishmentByTypeAndUserAndZone(type, userEmail, 1);

		System.out.println("Duração: " + (System.currentTimeMillis() - time)
				+ " ms\n");

		dbAction.removeIndices();
	}
}
