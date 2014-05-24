package sqlAnywareGenerator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class DBActions {
	// Files Locations
	private static final String CITIES_LIST = "./dataSource/Geographical/Lista de Municípios.csv";
	private static final String ZONES_LIST = "./dataSource/Geographical/Lista de Freguesias.csv";
	private static final String ESTABLISHMENT_TYPE_LIST = "./dataSource/Types/Tipos de Estabelecimento.txt";
	private static final String EVENT_TYPE_LIST = "./dataSource/Types/Tipos de Evento.txt";
	private static final String MEAL_TYPE_LIST = "./dataSource/Types/Tipos de Prato.txt";
	private static final String PROVERB_LIST = "./dataSource/Random Theme/Lista de Ditados Populares.txt";
	private static final String FAMILY_NAMES = "./dataSource/User/Family Names.txt";
	private static final String FIRST_NAMES = "./dataSource/User/First Names.txt";
	private static final String EMAIL_ALIAS = "./dataSource/User/Emails Alias.txt";
	private static final String UNIVESITY_LIST = "./dataSource/User/Lista de Universidades.txt";
	private static final String MANTRA_LIST = "./dataSource/Random Theme/Lista de Mantras.txt";
	private static final String RESTAURANT_LIST = "./dataSource/Random Theme/Lista de Restaurantes.txt";
	private static final String EVENTS_NAMES = "./dataSource/Random Theme/Nomes de Eventos.txt";

	private FileParser parser = new FileParser();
	private DBConnection dbConnection;

	/**
	 * Creates the missing Zone column in user table and creates the foreign key
	 */
	public void createZoneFieldInUserTable() {
		try {
			ResultSet rs = dbConnection
					.executeQuery("select column_name from syscolumn where table_id=(select table_id  from systable where table_name='Utilizador')");

			boolean exists = false;
			while (rs.next()) {
				if (rs.getString(1).equals("idZona"))
					exists = true;
			}

			if (!exists) {
				dbConnection
						.execute("ALTER TABLE Utilizador ADD \"newcol\" INTEGER NOT NULL");
				dbConnection.commit();

				dbConnection
						.execute("ALTER TABLE Utilizador RENAME \"newcol\" TO \"idZona\"");
				dbConnection.commit();

				dbConnection
						.execute("ALTER TABLE Utilizador ADD CONSTRAINT \"FK_UTILIZADOR_ZONA\" NOT NULL FOREIGN KEY (\"idZona\" ASC ) "
								+ "REFERENCES Zona (\"idZona\") ON UPDATE CASCADE;");
				dbConnection.commit();

				System.out.println("Changes to the database made with sucess!");
			} else {
				System.out
						.println("No Changes needed to be made to the database!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeAllDataFromDatabase() {
		dbConnection.executeUpdate("DELETE FROM follow");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM pratoRecomendado");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM menuDoEstabelecimento");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM ComentarioAoEstabelecimento");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM ComentarioAoPrato");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM HorarioEstabelecimento");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM ReservaDeBilhetes");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM eventoOferecido");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM DiaSemana");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM TipoDePrato");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM TipoDeEvento");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM Estabelecimento");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM TipoDeEstabelecimento");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM Utilizador");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM Fotografia");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM Zona");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM Cidade");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM Prato");
		dbConnection.commit();

		dbConnection.executeUpdate("DELETE FROM LogPrato");
		dbConnection.commit();

		System.out.println("Information from database removed with success!");
	}

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

		int currentID = getEventTypeMaxID() + 1;

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
				.plainTextFileParser(PROVERB_LIST);
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
		ArrayList<String[]> firstNames = parser.cvsFileParser(FIRST_NAMES, " ");
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
			// String username = new BigInteger(130, secureRandom).toString(30);
			// username = username.trim();
			// username = username.substring(0, 10);

			StringBuilder firstName = new StringBuilder(firstNames.get(random
					.nextInt(maxFirstName))[0]);
			for (int index = 1; index < firstName.length(); index++) {
				char c = firstName.charAt(index);
				firstName.setCharAt(index, Character.toLowerCase(c));
			}
			String familyName = familyNames.get(random.nextInt(maxFamilyName));

			String email = firstName.toString() + "_" + familyName
					+Integer.toString(random.nextInt(1000000))+emailAlias.get(random.nextInt(maxEmailAlias));
			String name = firstName.toString() + " " + familyName;

			String password = new BigInteger(130, secureRandom).toString(15);
			password = password.trim();
			password = password.substring(0, 14);

			String university = universities.get(random
					.nextInt(maxUniversities));

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
				.plainTextFileParserWithEscape(RESTAURANT_LIST);

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

	/**
	 * Adds user followers to Database. Doesn't prevent trying to add existing
	 * follows....
	 * 
	 * @param followersQnt
	 * @throws SQLException
	 */
	public void addFollowersToDB(int followersQnt) throws SQLException {
		ArrayList<String> usersEmails = getUserEmails();
		Random random = new Random();

		for (int i = 0; i < followersQnt; i++) {
			String follower = null;
			String toFollow = null;

			do {
				follower = usersEmails.get(random.nextInt(usersEmails.size()));
				toFollow = usersEmails.get(random.nextInt(usersEmails.size()));
			} while (follower == null || toFollow == null
					|| follower.equals(toFollow));

			dbConnection
					.executeUpdate("INSERT INTO follow(emailsguidor,emailsgu) VALUES ('"
							+ follower + "','" + toFollow + "')");
		}
		dbConnection.commit();
		System.out.println(followersQnt
				+ " Followers Added with success to database");
	}

	/**
	 * Add the schedule of each establishment (all possible week days per each)
	 * to Database
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public void addEstablishmentScheduleToDB() throws SQLException {
		ArrayList<Integer> establishmentIDs = getEstablishmentsIDs();
		ArrayList<String> weekDays = getWeekDays();

		Random random = new Random();
		Time openHour;
		Time closeHour;
		for (int establishmentID : establishmentIDs) {
			for (String weekDay : weekDays) {
				do {
					openHour = new Time(random.nextInt(23), random.nextInt(59),
							random.nextInt(59));
					closeHour = new Time(random.nextInt(23),
							random.nextInt(59), random.nextInt(59));
				} while (openHour.after(closeHour)
						|| openHour.equals(closeHour));

				dbConnection
						.executeUpdate("INSERT INTO HorarioEstabelecimento(idEstabelecimento,diaDaSemana,horaAbertura,horaFecho) VALUES ('"
								+ establishmentID
								+ "','"
								+ weekDay
								+ "','"
								+ openHour + "','" + closeHour + "')");
			}
		}

		dbConnection.commit();
		System.out.println(establishmentIDs.size() * weekDays.size()
				+ " Schedules Added with success to database");
	}

	/**
	 * Adds the menu for each establishment, with the indicated dish quantity,
	 * to Database
	 * 
	 * @param dishQnt
	 * @throws SQLException
	 */
	public void addEstablishmentMenusToDB(int dishQnt) throws SQLException {
		ArrayList<Integer> establishmentIDs = getEstablishmentsIDs();
		ArrayList<String> dishesList = getMealsList();
		Random random = new Random();

		for (int establishmentID : establishmentIDs) {
			for (int i = 0; i < dishQnt; i++) {
				String dish = dishesList.get(random.nextInt(dishesList.size()));

				dbConnection
						.executeUpdate("INSERT INTO menuDoEstabelecimento(idEstabelecimento,idPrato) VALUES ('"
								+ establishmentID + "','" + dish + "')");
			}
		}

		dbConnection.commit();
		System.out.println(dishQnt * establishmentIDs.size()
				+ " Dishes on Menus Added with success to database");
	}

	/**
	 * Add the user recommended dishes to Database
	 * 
	 * @param recomendeQnt
	 * @throws SQLException
	 */
	public void addRecomendedMealsToDB(int recomendeQnt) throws SQLException {
		ArrayList<String> usersEmails = getUserEmails();
		ArrayList<String> dishesList = getMealsList();
		Random random = new Random();

		for (int i = 0; i < recomendeQnt; i++) {
			String email = usersEmails.get(random.nextInt(usersEmails.size()));
			String dish = dishesList.get(random.nextInt(dishesList.size()));

			dbConnection
					.executeUpdate("INSERT INTO pratoRecomendado(email,idPrato) VALUES ('"
							+ email + "','" + dish + "')");
		}

		dbConnection.commit();
		System.out.println(recomendeQnt
				+ " Dishes Recomendations Added with success to database");
	}

	/**
	 * Add the indicated dish comments to database
	 * 
	 * @param commentsQnt
	 * @throws SQLException
	 */
	public void addMealsCommentsToDB(int commentsQnt) throws SQLException {
		ArrayList<String> commentsList = parser
				.plainTextFileParser(PROVERB_LIST);
		ArrayList<String> usersEmails = getUserEmails();
		ArrayList<String> dishesList = getMealsList();
		Random random = new Random();

		for (int i = 0; i < commentsQnt; i++) {
			String email = usersEmails.get(random.nextInt(usersEmails.size()));
			String dish = dishesList.get(random.nextInt(dishesList.size()));
			String comment = commentsList.get(random.nextInt(commentsList
					.size()));
			int classification = random.nextInt(100);

			dbConnection
					.executeUpdate("INSERT INTO ComentarioAoPrato(email,idPrato,comentario,nota) VALUES ('"
							+ email
							+ "','"
							+ dish
							+ "','"
							+ comment
							+ "','"
							+ classification + "')");
		}

		dbConnection.commit();
		System.out.println(commentsQnt
				+ " Meals Comments Added with success to database");
	}

	/**
	 * Add the indicated establishment comments
	 * 
	 * @param commentsQnt
	 * @throws SQLException
	 */
	public void addEstablishmentCommentsToDB(int commentsQnt)
			throws SQLException {
		ArrayList<String> commentsList = parser
				.plainTextFileParser(PROVERB_LIST);
		ArrayList<Integer> establishmentIDs = getEstablishmentsIDs();
		ArrayList<String> usersEmails = getUserEmails();
		Random random = new Random();

		for (int i = 0; i < commentsQnt; i++) {
			String email = usersEmails.get(random.nextInt(usersEmails.size()));
			String comment = commentsList.get(random.nextInt(commentsList
					.size()));
			int establishmentID = establishmentIDs.get(random
					.nextInt(establishmentIDs.size()));
			int grade = random.nextInt(100);
			int status = random.nextInt(10);

			dbConnection
					.executeUpdate("INSERT INTO ComentarioAoEstabelecimento(idEstabelecimento,email,comentario,nota,estado) VALUES"
							+ " ('"
							+ establishmentID
							+ "','"
							+ email
							+ "','"
							+ comment + "','" + grade + "','" + status + "')");
		}

		dbConnection.commit();
		System.out.println(commentsQnt
				+ " Establishments comments Added with success to database");
	}

	/**
	 * Adds meals photographs to the database
	 * 
	 * @param photographsQnt
	 * @throws SQLException
	 */
	public void addMealsPhotographies(int photographsQnt) throws SQLException {
		ArrayList<Integer> establishmentIDs = getEstablishmentsIDs();
		ArrayList<String> usersEmails = getUserEmails();
		ArrayList<String> mealsList = getMealsList();

		Random random = new Random();

		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST idFotografia FROM Fotografia ORDER BY idFotografia DESC");

		int currentID;
		if (rs.next()) {
			currentID = rs.getInt(1) + 1;
		} else {
			currentID = 0;
		}

		for (int i = 0; i < photographsQnt; i++) {
			int establishmentID = establishmentIDs.get(random
					.nextInt(establishmentIDs.size()));
			String email = usersEmails.get(random.nextInt(usersEmails.size()));
			String meal = mealsList.get(random.nextInt(mealsList.size()));

			dbConnection
					.executeUpdate("INSERT INTO Fotografia(idFotografia,idEstabelecimento,emailutilizador,idPrato) VALUES"
							+ " ('"
							+ currentID
							+ "','"
							+ establishmentID
							+ "','" + email + "','" + meal + "')");

			currentID++;
		}

		dbConnection.commit();
		System.out.println(photographsQnt
				+ " Photographs Added with success to database");
	}

	/**
	 * Add available events for random establishment, to Database
	 * 
	 * @param eventQnt
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public void addAvailableEventsToDB(int eventQnt) throws SQLException {
		ArrayList<String> eventTypes = getEventTypes();
		ArrayList<Integer> establishmentIDs = getEstablishmentsIDs();
		ArrayList<String> eventsNames = parser
				.plainTextFileParserWithEscape(EVENTS_NAMES);

		Random random = new Random();
		int currentID = getEventTypeMaxID() + 1;

		for (int i = 0; i < eventQnt; i++) {
			String type = eventTypes.get(random.nextInt(eventTypes.size()));
			int establishmentID = establishmentIDs.get(random
					.nextInt(establishmentIDs.size()));
			String eventName = eventsNames.get(random.nextInt(eventsNames
					.size()));

			Date date = new Date(2013 + random.nextInt(5),
					random.nextInt(11) + 1, random.nextInt(27) + 1);
			Time time = new Time(random.nextInt(23), random.nextInt(59),
					random.nextInt(59));

			dbConnection
					.executeUpdate("INSERT INTO eventoOferecido(idEstabelecimento,tipoDoEvento,nomeEvento,Data,Hora,idEvento) VALUES ('"
							+ establishmentID
							+ "','"
							+ type
							+ "','"
							+ eventName
							+ "','"
							+ date
							+ "','"
							+ time
							+ "','"
							+ currentID + "')");

			currentID++;
		}
		dbConnection.commit();
		System.out.println(eventQnt
				+ " Establishment Events Added with success to database");
	}

	public void addTicketsToDB() throws SQLException {
		ArrayList<String> types = parser.plainTextFileParser(EVENT_TYPE_LIST);
		ArrayList<Integer> establishmentIDs = getEstablishmentsIDs();

		Random random = new Random();

		for (int establishmentID : establishmentIDs) {
			ArrayList<Integer> establishmentEvents = getEstablishmentEvents(establishmentID);

			for (int eventID : establishmentEvents) {
				int capacity = random.nextInt(10) + 1;

				for (int i = 1; i < capacity + 1; i++) {
					dbConnection
							.executeUpdate("INSERT INTO ReservaDeBilhetes(numeroLugar,estado,idEstabelecimento,idEvento) VALUES ('"
									+ i
									+ "','Livre','"
									+ establishmentID
									+ "','" + eventID + "')");
					dbConnection.commit();
				}
			}
		}

		dbConnection.commit();
		System.out.println(types.size()
				+ " Event Types Added with success to database");
	}

	// Auxiliary Database Getters
	private ArrayList<String> getMealsList() throws SQLException {
		ResultSet rs = dbConnection.executeQuery("SELECT idPrato FROM Prato");
		ArrayList<String> dishesList = new ArrayList<>();
		while (rs.next()) {
			dishesList.add(rs.getString(1));
		}
		return dishesList;
	}

	private ArrayList<String> getUserEmails() throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT email FROM Utilizador");
		ArrayList<String> usersEmails = new ArrayList<>();

		while (rs.next()) {
			usersEmails.add(rs.getString(1));
		}
		return usersEmails;
	}

	private ArrayList<Integer> getEstablishmentsIDs() throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT idEstabelecimento FROM Estabelecimento");
		ArrayList<Integer> establishmentIDs = new ArrayList<>();
		while (rs.next()) {
			establishmentIDs.add(rs.getInt(1));
		}
		return establishmentIDs;
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

	private ArrayList<String> getEventTypes() throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT tipoDoEvento FROM TipoDeEvento");
		ArrayList<String> eventTypes = new ArrayList<>();
		while (rs.next()) {
			eventTypes.add(rs.getString(1));
		}
		return eventTypes;
	}

	private ArrayList<String> getWeekDays() throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT diaDaSemana FROM DiaSemana");
		ArrayList<String> weekDays = new ArrayList<>();
		while (rs.next()) {
			weekDays.add(rs.getString(1));
		}
		return weekDays;
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

	private int getEventTypeMaxID() throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT FIRST tipoDoEvento FROM TipoDeEvento ORDER BY tipoDoEvento DESC");

		int currentID;
		if (rs.next()) {
			currentID = rs.getInt(1);
		} else {
			currentID = 0;
		}
		return currentID;
	}

	private ArrayList<Integer> getEstablishmentEvents(int establishmentID)
			throws SQLException {
		ResultSet rs = dbConnection
				.executeQuery("SELECT idEvento FROM eventoOferecido WHERE idEstabelecimento="
						+ establishmentID);
		ArrayList<Integer> events = new ArrayList<>();
		while (rs.next()) {
			events.add(rs.getInt(1));
		}
		return events;

	}
}