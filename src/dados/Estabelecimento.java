package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class Estabelecimento {
	private String coordenadasGPS;
	private int idZona;
	private String designacao;
	private String formaDeChegar;
	private int idEstabelecimento;
	private String informacoesHorario;
	private String morada;
	private double rating;
	private String email;
	private String tipoDoEstabelecimento;
	private String nomeZona;
	private String cidade;

	private DBConnection dbConnection;

	public Estabelecimento() {
		dbConnection = new DBConnection();
	}

	public Estabelecimento(String coordenadasGPS, int idZona,
			String designacao, String formaDeChegar, int idEstabelecimento,
			String informacoesHorario, String morada, double rating,
			String tipoDoEstabelecimento, String email, String nomeZona,
			String cidade) {
		dbConnection = new DBConnection();

		this.coordenadasGPS = coordenadasGPS;
		this.idZona = idZona;
		this.designacao = designacao;
		this.formaDeChegar = formaDeChegar;
		this.idEstabelecimento = idEstabelecimento;
		this.informacoesHorario = informacoesHorario;
		this.morada = morada;
		this.rating = rating;
		this.tipoDoEstabelecimento = tipoDoEstabelecimento;
		this.email = email;
		this.nomeZona = nomeZona;
		this.cidade = cidade;
	}

	public ArrayList<Estabelecimento> select(String cidade, String zona,
			String tipo, double aval, String pratos, String eventos, String nome) {

		String sqlExpression;
		String sqlZona;
		String sqlPrato;
		String sqlEvento;
		String sqlSelect = "SELECT * FROM Estabelecimento";

		if (cidade.equals("") && zona.equals("") && tipo.equals("")
				&& pratos.equals("") && eventos.equals("") && nome.equals("")) {

			sqlSelect = "SELECT * FROM Estabelecimento WHERE Estabelecimento.rating >= "
					+ aval;

		} else {

			sqlExpression = "";
			sqlZona = ", Zona";
			sqlPrato = ", menuDoEstabelecimento, Prato, TipoDePrato";
			sqlEvento = ", eventoOferecido, TipoDeEvento";

			if (!cidade.equals("")) {
				sqlExpression += " Estabelecimento.idZona = Zona.idZona AND Zona.cidade = '"
						+ cidade + "' AND";
			}

			if (!zona.equals("")) {
				sqlExpression += " Estabelecimento.idZona = Zona.idZona AND Zona.designacao = '"
						+ zona + "' AND";
			}

			if (!tipo.equals("")) {
				sqlExpression += " Estabelecimento.tipoDoEstabelecimento = '"
						+ tipo + "' AND";
			}

			if (!nome.equals("")) {
				sqlExpression += " Estabelecimento.designacao LIKE '%" + nome
						+ "%' AND";
			}

			if (!cidade.equals("") || !zona.equals("")) {
				sqlSelect += sqlZona;
			}

			if (!pratos.equals("")) {
				sqlSelect += sqlPrato;
				sqlExpression += " Estabelecimento.idEstabelecimento = menuDoEstabelecimento.idEstabelecimento AND "
						+ "menuDoEstabelecimento.idPrato = Prato.idPrato AND Prato.tipoDePrato = TipoDePrato.tipoDePrato AND "
						+ "( ";
				String[] strP = pratos.split(";");
				for (int i = 0; i < strP.length; i++) {
					sqlExpression += " TipoDePrato.descricao = '" + strP[i]
							+ "'";
					if (i + 1 < strP.length) {
						sqlExpression += "AND";
					}
				}
				sqlExpression += ") AND ";
			}

			if (!eventos.equals("")) {
				sqlSelect += sqlEvento;
				sqlExpression += " Estabelecimento.idEstabelecimento = eventoOferecido.idEstabelecimento AND "
						+ "eventoOferecido.tipoDoEvento = TipoDeEvento.tipoDoEvento AND "
						+ "(";
				String[] strE = eventos.split(";");
				for (int i = 0; i < strE.length; i++) {
					sqlExpression += " TipoDeEvento.descricao = '" + strE[i]
							+ "'";
					if (i + 1 < strE.length) {
						sqlExpression += "AND";
					}
				}
				sqlExpression += ") AND ";
			}

			sqlSelect += " WHERE" + sqlExpression
					+ " Estabelecimento.rating >= " + aval;

			System.out.println(sqlSelect);

		}

		ResultSet resultSet = dbConnection.select(sqlSelect);

		ArrayList<Estabelecimento> estabelecimentos = prepareResult(resultSet);

		dbConnection.closeDBConnection();

		return estabelecimentos;
	}

	private ArrayList<Estabelecimento> prepareResult(ResultSet resultSet) {
		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		try {
			while (resultSet.next()) {
				String coordenadasGPS = resultSet.getString("coordenadasGps");
				String designacao = resultSet.getString("designacao");
				String formaDeChegar = resultSet.getString("formaDeLaChegar");
				int idEstabelecimento = resultSet.getInt("idEstabelecimento");
				String informacoesHorario = resultSet
						.getString("informacoesHorario");
				String morada = resultSet.getString("morada");
				int idZona = resultSet.getInt("idZona");
				double rating = resultSet.getDouble("rating");

				Estabelecimento estabelecimento = new Estabelecimento(
						coordenadasGPS, idZona, designacao, formaDeChegar,
						idEstabelecimento, informacoesHorario, morada, rating,
						null, null, null, null);
				estabelecimentos.add(estabelecimento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estabelecimentos;
	}

	public Estabelecimento select(int establishmentID) {
		try {
			ResultSet resultSet = dbConnection
					.select("SELECT * FROM Estabelecimento WHERE idEstabelecimento="
							+ establishmentID);

			String coordenadasGPS;
			String designacao;
			String formaDeChegar;
			String informacoesHorario;
			String morada;
			String email;
			String tipoDoEstabelecimento;
			String nomeZona;
			String cidade;
			int idZona;
			double rating;

			if (resultSet.next()) {
				coordenadasGPS = resultSet.getString("coordenadasGps");
				designacao = resultSet.getString("designacao");
				formaDeChegar = resultSet.getString("formaDeLaChegar");
				informacoesHorario = resultSet.getString("informacoesHorario");
				morada = resultSet.getString("morada");
				email = resultSet.getString("email");
				tipoDoEstabelecimento = resultSet
						.getString("tipoDoEstabelecimento");
				idZona = resultSet.getInt("idZona");
				rating = resultSet.getDouble("rating");
			} else {
				throw new NullPointerException();
			}

			resultSet = dbConnection
					.select("SELECT designacao,cidade FROM Zona WHERE idZona="
							+ idZona);

			if (resultSet.next()) {
				nomeZona = resultSet.getString("designacao");
				cidade = resultSet.getString("cidade");
			} else {
				throw new NullPointerException();
			}

			return new Estabelecimento(coordenadasGPS, idZona, designacao,
					formaDeChegar, establishmentID, informacoesHorario, morada,
					rating, tipoDoEstabelecimento, email, nomeZona, cidade);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getCoordenadasGPS() {
		return coordenadasGPS;
	}

	public String getDesignacao() {
		return designacao;
	}

	public String getFormaDeChegar() {
		return formaDeChegar;
	}

	public int getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public String getInformacoesHorario() {
		return informacoesHorario;
	}

	public String getMorada() {
		return morada;
	}

	public double getRating() {
		return rating;
	}

	public int getIdZona() {
		return idZona;
	}

	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	public String getTipoDoEstabelecimento() {
		return tipoDoEstabelecimento;
	}

	public String getEmailUtilizadorQueRecomenda() {
		return email;
	}

	public String getNomeZona() {
		return nomeZona;
	}

	public String getCidade() {
		return cidade;
	}
}