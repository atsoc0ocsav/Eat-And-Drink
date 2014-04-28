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

	private DBConnection dbcConnection;

	public Estabelecimento(DBConnection dbcConnection) {
		this.dbcConnection = dbcConnection;
	}

	public Estabelecimento(String coordenadasGPS, int idZona,
			String designacao, String formaDeChegar, int idEstabelecimento,
			String informacoesHorario, String morada, double rating) {
		super();
		this.coordenadasGPS = coordenadasGPS;
		this.idZona = idZona;
		this.designacao = designacao;
		this.formaDeChegar = formaDeChegar;
		this.idEstabelecimento = idEstabelecimento;
		this.informacoesHorario = informacoesHorario;
		this.morada = morada;
		this.rating = rating;
	}

	public ArrayList<Estabelecimento> select(String cidade, String zona,
			String tipo, double aval, String prato, String evento, String nome) {

		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();

		if (cidade == null && zona == null && tipo == null && aval == 0.0
				&& prato == null && evento == null && nome == null) {

			ResultSet resultSet = dbcConnection
					.select("SELECT * FROM Estabelecimento");

			try {
				while (resultSet.next()) {
					String coordenadasGPS = resultSet
							.getString("coordenadasGps");
					String designacao = resultSet.getString("designacao");
					String formaDeChegar = resultSet
							.getString("formaDeLaChegar");
					int idEstabelecimento = resultSet
							.getInt("idEstabelecimento");
					String informacoesHorario = resultSet
							.getString("informacoesHorario");
					String morada = resultSet.getString("morada");
					int idZona = resultSet.getInt("idZona");
					double rating = resultSet.getDouble("rating");

					Estabelecimento estabelecimento = new Estabelecimento(
							coordenadasGPS, idZona, designacao, formaDeChegar,
							idEstabelecimento, informacoesHorario, morada,
							rating);
					estabelecimentos.add(estabelecimento);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			ResultSet resultSet = dbcConnection
					.select("SELECT * "
							+ "FROM Estabelecimento, Zona "
							+ "WHERE Estabelecimento.idZona = Zona.idZona AND Zona.cidade = '"+ cidade + "' OR "
							+ "Estabelecimento.idZona = Zona.idZona AND Zona.designacao = '" + zona + "' OR "
							+ "Estabelecimento.tipoDoEstabelecimento = '" + tipo + "' OR "
							+ "Estabelecimento.rating >= '" + aval + "' OR "
							+ "Estabelecimento.designacao = '" + nome + "'");			
			try {
				while (resultSet.next()) {
					String coordenadasGPS = resultSet
							.getString("coordenadasGps");
					String designacao = resultSet.getString("designacao");
					String formaDeChegar = resultSet
							.getString("formaDeLaChegar");
					int idEstabelecimento = resultSet
							.getInt("idEstabelecimento");
					String informacoesHorario = resultSet
							.getString("informacoesHorario");
					String morada = resultSet.getString("morada");
					int idZona = resultSet.getInt("idZona");
					double rating = resultSet.getDouble("rating");

					Estabelecimento estabelecimento = new Estabelecimento(
							coordenadasGPS, idZona, designacao, formaDeChegar,
							idEstabelecimento, informacoesHorario, morada,
							rating);
					estabelecimentos.add(estabelecimento);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return estabelecimentos;
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

}
