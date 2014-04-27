package sqlAnywareGenerator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class DBActions {
	// Files Locations
	private static final String CITIES_LIST = "./dataSource/Geographical/Lista de Municípios.csv";
	private static final String ZONES_LIST = "./dataSource/Geographical/Lista de Freguesias.csv";
	private static final String ESTABLISHMENT_TYPE_LIST = "./dataSource/Types/Tipos de Estabelecimento.txt";
	private static final String EVENT_TYPE_LIST = "./dataSource/Types/Tipos de Evento.txt";
	private static final String MEAL_TYPE_LIST = "./dataSource/Types/Tipos de Prato.txt";
	private static final String MEAL_DESCRIPTION = "./dataSource/Random Theme/Ditados Populares.txt";
	private static final String FAMILY_NAMES = "./dataSource/User/Family Names.txt";
	private static final String FIRST_NAMES = "./dataSource/User/First Names.txt";
	private static final String EMAIL_ALIAS = "./dataSource/User/Emails Alias.txt";
	private static final String UNIVESITY_LIST = "./dataSource/User/Lista de Universidades.txt";
	private FileParser parser = new FileParser();
	private DBConnection dbConnection;

	/**
	 * Default constructor which also creates a connection to the database
	 */
	public DBActions() {
		this.dbConnection = new DBConnection();
	}

	/**
	 * Adds the cities to the database
	 */
	public void addCitiesToDB() {
		ArrayList<String[]> cities = parser.cvsFileParser(CITIES_LIST, ";");

		for (String[] city : cities) {
			dbConnection.executeUpdate("INSERT INTO Cidade(Cidade) VALUES ('"
					+ city[1] + "')");
		}

		dbConnection.commit();
		System.out.println(cities.size()
				+ " Cities Added with success to database");
	}

	/**
	 * Adds the zones to the database (with incremental IDs)
	 * 
	 * @throws SQLException
	 */
	public void addZonesToDB() throws SQLException {
		ArrayList<String[]> zones = parser.cvsFileParser(ZONES_LIST, ";");

		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST idZona FROM Zona ORDER BY idZona DESC");

		int currentID;
		if (rs.next()) {
			currentID = rs.getInt(1) + 1;
		} else {
			currentID = 0;
		}

		for (String[] zone : zones) {
			dbConnection
					.executeUpdate("INSERT INTO Zona(idZona,Cidade,Designação) VALUES ('"
							+ currentID
							+ "','"
							+ zone[0]
							+ "','"
							+ zone[1].split("[ (]")[0] + "')");
			currentID++;
		}

		dbConnection.commit();
		System.out.println(zones.size()
				+ " Zones Added with success to database");
	}

	/**
	 * Adds the establishment types to the database
	 */
	public void addEstablishmentTypesToDB() {
		ArrayList<String> types = parser
				.plainTextFileParser(ESTABLISHMENT_TYPE_LIST);

		for (String type : types) {
			dbConnection
					.executeUpdate("INSERT INTO TipoDeEstabelecimento(tipoDoEstabelecimento) VALUES ('"
							+ type + "')");
		}

		dbConnection.commit();
		System.out.println(types.size()
				+ " Establishment Types Added with success to database");
	}

	/**
	 * Adds the week days to the database (incremental integer....)
	 */
	public void addWeekDaysToDB() {
		for (int i = 1; i <= 7; i++) {
			dbConnection
					.executeUpdate("INSERT INTO DiaSemana(diaDaSemana) VALUES ('"
							+ i + "')");
		}

		dbConnection.commit();
		System.out.println("7 Week days Added with success to database");
	}

	/**
	 * Adds the event types to database
	 * 
	 * @throws SQLException
	 */
	public void addEventTypeToDB() throws SQLException {
		ArrayList<String> types = parser.plainTextFileParser(EVENT_TYPE_LIST);

		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST tipoDoEvento FROM TipoDeEvento ORDER BY tipoDoEvento DESC");

		int currentID;
		if (rs.next()) {
			currentID = rs.getInt(1) + 1;
		} else {
			currentID = 0;
		}

		for (String type : types) {
			dbConnection
					.executeUpdate("INSERT INTO TipoDeEvento(tipoDoEvento,descricao) VALUES ('"
							+ currentID + "','" + type + "')");
			currentID++;
		}

		dbConnection.commit();
		System.out.println(types.size()
				+ " Event Types Added with success to database");
	}

	/**
	 * Adds the meals types to database
	 * 
	 * @throws SQLException
	 */
	public void addMealTypeToDB() throws SQLException {
		ArrayList<String> types = parser.plainTextFileParser(MEAL_TYPE_LIST);

		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST tipoDePrato FROM TipoDePrato ORDER BY tipoDePrato DESC");

		int currentID;
		if (rs.next()) {
			currentID = rs.getInt(1) + 1;
		} else {
			currentID = 0;
		}

		for (String type : types) {
			dbConnection
					.executeUpdate("INSERT INTO TipoDePrato(tipoDePrato,descricao) VALUES ('"
							+ currentID + "','" + type + "')");
			currentID++;
		}

		dbConnection.commit();
		System.out.println(types.size()
				+ " Meal Types Added with success to database");
	}

	/**
	 * Adds a specified quantity of meals to the database (with random
	 * parameters)
	 * 
	 * @throws SQLException
	 */
	public void addMealsToDB(int mealsQnt) throws SQLException {
		ArrayList<String> descriptions = parser
				.plainTextFileParser(MEAL_DESCRIPTION);
		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST idPrato FROM Prato ORDER BY idPrato DESC");
		Random random = new Random();
		int maxIndex = descriptions.size() - 1;

		int currentID;
		if (rs.next()) {
			currentID = rs.getInt(1) + 1;
		} else {
			currentID = 0;
		}

		rs = dbConnection
				.executeQuery("SELECT FIRST tipoDePrato FROM TipoDePrato ORDER BY tipoDePrato DESC");
		int mealTypesAvailable;
		if (rs.next()) {
			mealTypesAvailable = rs.getInt(1);
		} else {
			throw new NullPointerException("No meal types available");
		}

		for (int i = 0; i < mealsQnt; i++) {
			String description = descriptions.get(random.nextInt(maxIndex));
			double price = random.nextInt(10) + random.nextDouble() * 100;
			double rating = random.nextInt(10) + random.nextDouble() * 100;
			int mealType = random.nextInt(mealTypesAvailable);

			dbConnection
					.executeUpdate("INSERT INTO Prato(descricao,preco,idPrato,tipoDePrato,rating) VALUES ('"
							+ description
							+ "','"
							+ price
							+ "','"
							+ currentID
							+ "','" + mealType + "','" + rating + "')");
			currentID++;
		}

		dbConnection.commit();
		System.out.println(mealsQnt + " Meals Added with success to database");
	}

	/**
	 * Adds a specified quantity of user to the database (with random
	 * parameters)
	 * 
	 * @param usersQnt
	 * @throws NoSuchAlgorithmException
	 * @throws SQLException
	 */
	public void addUsersToDB(int usersQnt) throws NoSuchAlgorithmException,
			SQLException {
		ArrayList<String> firstNames = parser.plainTextFileParser(FIRST_NAMES);
		ArrayList<String> familyNames = parser
				.plainTextFileParser(FAMILY_NAMES);
		ArrayList<String> emailAlias = parser.plainTextFileParser(EMAIL_ALIAS);
		ArrayList<String> universities = parser
				.plainTextFileParser(UNIVESITY_LIST);

		int maxFirstName = firstNames.size() - 1;
		int maxFamilyName = familyNames.size() - 1;
		int maxEmailAlias = emailAlias.size() - 1;
		int maxUniversities = universities.size() - 1;

		Random random = new Random();
		SecureRandom secureRandom = new SecureRandom();

		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST idZona FROM Zona ORDER BY idZona DESC");
		int currentZoneID;
		if (rs.next()) {
			currentZoneID = rs.getInt(1);
		} else {
			throw new NullPointerException("No zones in the zones table");
		}

		for (int i = 0; i < usersQnt; i++) {
			String username = new BigInteger(130, secureRandom).toString(30);
			username = username.trim();
			username = username.substring(0, 10);

			String email = username
					+ emailAlias.get(random.nextInt(maxEmailAlias));
			String name = firstNames.get(random.nextInt(maxFirstName)) + " "
					+ familyNames.get(random.nextInt(maxFamilyName));

			String password = new BigInteger(130, secureRandom).toString(31);
			password = password.trim();
			password = password.substring(0, 14);
			password = md5Encode(password);

			String university = universities.get(random
					.nextInt(maxUniversities));

			int idPhoto = createUserPhoto();

			dbConnection
					.executeUpdate("INSERT INTO Utilizador(email,idFotografia,nome,escola,senha,zona) VALUES ('"
							+ email
							+ "','"
							+ idPhoto
							+ "','"
							+ name
							+ "','"
							+ university
							+ "','"
							+ password
							+ "','"
							+ random.nextInt(currentZoneID) + "')");

			dbConnection.commit();
			addEmailToPhoto(email, idPhoto);
			dbConnection.commit();
		}
		//dbConnection.commit();
	}

	/**
	 * Inserts a photograph for the specified email
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	private int createUserPhoto() throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST idFotografia FROM Fotografia ORDER BY idFotografia DESC");

		int currentID;
		if (rs.next()) {
			currentID = rs.getInt(1) + 1;
		} else {
			currentID = 0;
		}

		dbConnection
				.executeUpdate("INSERT INTO Fotografia(idFotografia) VALUES ('"
						+ currentID + "')");
		return currentID;
	}

	/**
	 * Update the email (therefore the user) associated to a photo
	 * 
	 * @param email
	 *            of the user
	 * @param idPhoto
	 *            of the photo to update
	 */
	private void addEmailToPhoto(String email, int idPhoto) {
		dbConnection.executeUpdate("UPDATE Fotografia SET emailutilizador='"
				+ email + "' WHERE idFotografia='" + idPhoto + "'");
	}

	/**
	 * Digest a String into a MD5 hashed String
	 * 
	 * @param String
	 * @return MD5 hashed String
	 * @throws NoSuchAlgorithmException
	 */
	public String md5Encode(String string) throws NoSuchAlgorithmException {
		MessageDigest mdEnc;
		mdEnc = MessageDigest.getInstance("MD5");
		mdEnc.update(string.getBytes(), 0, string.length());
		return new BigInteger(1, mdEnc.digest()).toString(16);
	}

}
