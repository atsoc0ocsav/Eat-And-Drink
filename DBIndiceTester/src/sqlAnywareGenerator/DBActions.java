package sqlAnywareGenerator;

import java.math.BigInteger;
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
	private static final String PROVERB_LIST = "./dataSource/Random Theme/Lista de Ditados Populares.txt";
	private static final String MANTRA_LIST = "./dataSource/Random Theme/Lista de Mantras.txt";
	private static final String RESTAURANT_LIST = "./dataSource/Random Theme/Lista de Restaurantes.txt";

	private FileParser parser;
	private DBConnection dbConnection;

	private boolean[] activeIndexes;
	private static final String[] INDEX_NAMES = { "PK_ESTABELECIMENTO",
			"FK_ESTABELE_TIPODOEST_TIPODEES", "FK_ESTABELE_SUGESTAO_UTILIZAD",
			"FK_ESTABELE_LOCALDOES_ZONA" };

	/**
	 * Default constructor which also creates a connection to the database
	 */
	public DBActions() {
		this.dbConnection = new DBConnection();

		parser = new FileParser();
		activeIndexes = new boolean[INDEX_NAMES.length];

		for (int i = 0; i < activeIndexes.length; i++) {
			activeIndexes[i] = false;
		}
	}

	/**
	 * Creates the missing Zone column in user table and creates the foreign key
	 */
	public void createZoneFieldInUserTable() {
		dbConnection
				.executeUpdate("ALTER TABLE Utilizador ADD newcol INTEGER NOT NULL");
		dbConnection.commit();

		dbConnection
				.executeUpdate("ALTER TABLE Utilizador RENAME newcol TO idZona");
		dbConnection.commit();

		dbConnection
				.executeUpdate("ALTER TABLE Utilizador ADD CONSTRAINT \"FK_UTILIZADOR_ZONA\" NOT NULL FOREIGN KEY (\"idZona\" ASC ) "
						+ "REFERENCES Zona (\"idZona\") ON UPDATE CASCADE;");
		dbConnection.commit();

		System.out.println("Changes to the database made with sucess");
	}

	public void addIndices(int index) {
		switch (index) {
		case 0:
			dbConnection.executeUpdate("CREATE INDEX \"" + INDEX_NAMES[0]
					+ "\" ON Estabelecimento (idEstabelecimento)");
			dbConnection.commit();
			activeIndexes[0] = true;
			break;

		case 1:
			// Insert index on establishment type column
			dbConnection.executeUpdate("CREATE INDEX \"" + INDEX_NAMES[1]
					+ "\" ON Estabelecimento (tipoDoEstabelecimento)");
			dbConnection.commit();
			activeIndexes[1] = true;
			break;

		case 2:
			// Insert index on user column
			dbConnection.executeUpdate("CREATE INDEX \"" + INDEX_NAMES[2]
					+ "\" ON Estabelecimento (email)");
			dbConnection.commit();
			activeIndexes[2] = true;
			break;

		case 3:
			// Insert index on zone column
			dbConnection.executeUpdate("CREATE INDEX \"" + INDEX_NAMES[3]
					+ "\" ON Estabelecimento (idZona)");
			dbConnection.commit();
			activeIndexes[3] = true;
			break;

		default:
			throw new IllegalArgumentException("Invalid index!");
		}
	}

	public void removeIndices(boolean force) {
		for (int i = 0; i < activeIndexes.length; i++) {
			if (activeIndexes[i] || force) {
				dbConnection.executeQuery("DROP INDEX " + INDEX_NAMES[i]);
				dbConnection.commit();
			}
		}
	}

	// Database information insert/create functions
	/**
	 * Adds the cities to the database
	 */
	public void addCitiesToDB() {
		ArrayList<String[]> cities = parser.cvsFileParser(CITIES_LIST, ";");

		for (String[] city : cities) {
			StringBuilder cityName = new StringBuilder(city[1]);

			cityName.setCharAt(0, city[1].charAt(0));
			for (int index = 1; index < city[1].length(); index++) {
				char c = city[1].charAt(index);
				cityName.setCharAt(index, Character.toLowerCase(c));
			}

			dbConnection.executeUpdate("INSERT INTO Cidade(Cidade) VALUES ('"
					+ cityName.toString() + "')");
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
			try {
				StringBuilder zoneName = new StringBuilder(zone[1]);

				zoneName.setCharAt(0, zone[1].charAt(0));

				for (int index = 1; index < zone[1].length(); index++) {
					char c = zone[1].charAt(index);
					zoneName.setCharAt(index, Character.toLowerCase(c));
				}

				StringBuilder cityName = new StringBuilder(zone[0]);
				cityName.setCharAt(0, zone[0].charAt(0));

				for (int index = 1; index < zone[0].length(); index++) {
					char c = zone[0].charAt(index);
					cityName.setCharAt(index, Character.toLowerCase(c));
				}

				dbConnection
						.executeUpdate("INSERT INTO Zona(idZona,Cidade,Designação) VALUES ('"
								+ currentID
								+ "','"
								+ cityName.toString()
								+ "','" + zoneName.toString() + "')");
				currentID++;
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				System.err.println("Zone[0]:" + zone[0]);

				System.exit(1);
			}

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
	 * Adds a specified quantity of user to the database (with random
	 * parameters)
	 * 
	 * @param usersQnt
	 * @throws NoSuchAlgorithmException
	 * @throws SQLException
	 */
	public void addUsersToDB(int usersQnt) throws NoSuchAlgorithmException,
			SQLException {
		Random random = new Random();

		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST idZona FROM Zona ORDER BY idZona DESC");

		int currentZoneID;
		if (rs.next()) {
			currentZoneID = rs.getInt(1);
		} else {
			throw new NullPointerException("No zones in the zones table");
		}

		for (int i = 0; i < usersQnt; i++) {
			String name = "EPRV_" + i;
			String email = name + "@iscte.pt";
			String password = Integer.toString(random.nextInt(100000000));
			String university = "ISCTE";

			int idPhoto = createUserPhoto();

			dbConnection
					.executeUpdate("INSERT INTO Utilizador(email,idFotografia,nome,escola,senha,idZona) VALUES ('"
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
		}
		dbConnection.commit();
		System.out.println(usersQnt + " Users Added with success to database");
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
		dbConnection.commit();
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
		dbConnection.commit();
	}

	/**
	 * Adds a specified quantity of establishments to the database (with random
	 * parameters) to Database
	 * 
	 * @param establishemnts
	 *            Quantity
	 * @throws SQLException
	 */
	public void addEstablishmentsToDB(int estabQnt) throws SQLException {
		ArrayList<String> addressList = parser.plainTextFileParser(MANTRA_LIST);
		ArrayList<String> waysToPlaceList = parser
				.plainTextFileParser(PROVERB_LIST);
		ArrayList<String> restaurantList = parser
				.plainTextFileParser(RESTAURANT_LIST);

		Random random = new Random();
		SecureRandom secureRandom = new SecureRandom();

		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST idZona FROM Zona ORDER BY idZona DESC");
		int maxZone;
		if (rs.next()) {
			maxZone = rs.getInt(1) + 1;
		} else {
			throw new NullPointerException("No zones in zones table");
		}

		ArrayList<String> establishmentTypes = getEstablishmentTypes();
		ArrayList<String> usersEmails = getUserEmails();

		rs = dbConnection
				.executeQuery("SELECT FIRST idEstabelecimento FROM Estabelecimento ORDER BY idEstabelecimento DESC");
		int currentEstablishemntID;
		if (rs.next()) {
			currentEstablishemntID = rs.getInt(1) + 1;
		} else {
			currentEstablishemntID = 0;
		}

		for (int i = 0; i < estabQnt; i++) {
			double rating = random.nextInt(10) + random.nextDouble() * 100;
			int zone = random.nextInt(maxZone);
			String establishmentType = establishmentTypes.get(random
					.nextInt(establishmentTypes.size()));
			String email = usersEmails.get(random.nextInt(usersEmails.size()));
			String address = addressList
					.get(random.nextInt(addressList.size()));
			String wayToPlace = waysToPlaceList.get(random
					.nextInt(waysToPlaceList.size()));
			String restaurantName = restaurantList.get(random
					.nextInt(restaurantList.size()));

			char latit = random.nextBoolean() ? 'N' : 'S';
			char longit = random.nextBoolean() ? 'E' : 'O';

			String coordinates = random.nextInt(179) + "" + random.nextInt(60)
					+ "" + random.nextInt(60) + "" + latit + " "
					+ random.nextInt(179) + "" + random.nextInt(60) + ""
					+ +random.nextInt(60) + "" + longit;

			String schedule = new BigInteger(130, secureRandom).toString(15);
			schedule = schedule.trim();
			schedule = schedule.substring(0, 20);

			// System.out.println("Horario=" + schedule);
			// System.out.println("Forma de Chegar=" + wayToPlace);
			// System.out.println("Coordenadas=" + coordinates);
			// System.out.println("Morada=" + address);
			// System.out.println("ID=" + currentEstablishemntID);
			// System.out.println("Email=" + email);
			// System.out.println("idZona=" + zone);
			// System.out.println("Tipo de estab=" + establishmentType);
			// System.out.println("Design=" + restaurantName);
			// System.out.println("Rating=" + rating);

			dbConnection
					.executeUpdate("INSERT INTO Estabelecimento(informacoesHorario,formaDeLaChegar,coordenadasGps,morada,idEstabelecimento,email,idZona,tipoDoEstabelecimento,designacao,rating) VALUES "
							+ "('"
							+ schedule
							+ "','"
							+ wayToPlace
							+ "','"
							+ coordinates
							+ "','"
							+ address
							+ "','"
							+ currentEstablishemntID
							+ "','"
							+ email
							+ "','"
							+ zone
							+ "','"
							+ establishmentType
							+ "','"
							+ restaurantName + "','" + rating + "')");

			currentEstablishemntID++;
		}

		dbConnection.commit();
		System.out.println(estabQnt
				+ " Establishments Added with success to database");
	}

	// Database information dummy getters (Selects)
	public void selectEstablishmentByID(int id) throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT idEstabelecimento FROM Estabelecimento WHERE idEstabelecimento='"
						+ id + "'");
		ArrayList<String> establishments = new ArrayList<>();

		while (rs.next()) {
			establishments.add(rs.getString(1));
		}
	}

	public void selectEstablishmentByType(String type) throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT idEstabelecimento FROM Estabelecimento WHERE tipoDoEstabelecimento='"
						+ type + "'");
		ArrayList<String> establishments = new ArrayList<>();

		while (rs.next()) {
			establishments.add(rs.getString(1));
		}
	}

	public void selectEstablishmentByUser(String userEmail) throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT idEstabelecimento FROM Estabelecimento WHERE email='"
						+ userEmail + "'");
		ArrayList<String> establishments = new ArrayList<>();

		while (rs.next()) {
			establishments.add(rs.getString(1));
		}
	}

	public void selectEstablishmentByZone(int zone) throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT idEstabelecimento FROM Estabelecimento WHERE idZona='"
						+ zone + "'");
		ArrayList<String> establishments = new ArrayList<>();

		while (rs.next()) {
			establishments.add(rs.getString(1));
		}
	}

	public void selectEstablishmentByTypeAndUser(String type, String userEmail)
			throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT idEstabelecimento FROM Estabelecimento WHERE tipoDoEstabelecimento='"
						+ type + "' AND email='" + userEmail + "'");
		ArrayList<String> establishments = new ArrayList<>();

		while (rs.next()) {
			establishments.add(rs.getString(1));
		}
	}

	public void selectEstablishmentByTypeAndUserAndZone(String type,
			String userEmail, int zone) throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT idEstabelecimento FROM Estabelecimento WHERE tipoDoEstabelecimento='"
						+ type
						+ "' AND email='"
						+ userEmail
						+ "' AND idZona='"
						+ zone + "'");
		ArrayList<String> establishments = new ArrayList<>();

		while (rs.next()) {
			establishments.add(rs.getString(1));
		}
	}

	// Auxiliary Database Interactors
	private ArrayList<String> getUserEmails() throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT email FROM Utilizador");
		ArrayList<String> usersEmails = new ArrayList<>();

		while (rs.next()) {
			usersEmails.add(rs.getString(1));
		}
		return usersEmails;
	}

	private ArrayList<String> getEstablishmentTypes() throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT tipoDoEstabelecimento FROM TipoDeEstabelecimento");
		ArrayList<String> establishmentTypes = new ArrayList<>();
		while (rs.next()) {
			establishmentTypes.add(rs.getString(1));
		}
		return establishmentTypes;
	}

	@SuppressWarnings("unused")
	private ArrayList<String> getCitiesList() throws SQLException {
		ResultSet rs = dbConnection.executeQuery("SELECT cidade FROM Cidade");
		ArrayList<String> cityNames = new ArrayList<>();
		while (rs.next()) {
			cityNames.add(rs.getString(1));
		}
		return cityNames;
	}
}