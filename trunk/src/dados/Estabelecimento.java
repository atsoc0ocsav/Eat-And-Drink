package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbc.DBConnection;

public class Estabelecimento {
	private String coordenadasGPS;
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

	public Estabelecimento(String coordenadasGPS, String designacao,
			String formaDeChegar, int idEstabelecimento,
			String informacoesHorario, String morada, double rating) {
		super();
		this.coordenadasGPS = coordenadasGPS;
		this.designacao = designacao;
		this.formaDeChegar = formaDeChegar;
		this.idEstabelecimento = idEstabelecimento;
		this.informacoesHorario = informacoesHorario;
		this.morada = morada;
		this.rating = rating;
	}

	public List<Estabelecimento> select(String cidade, String zona,
			String tipo, double rating, String prato, double preco,
			String evento, boolean foto, String nome) {

		List<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();

		if (cidade == null && zona == null && tipo == null && rating == 0.0
				&& prato == null && preco == 0.0 && evento == null && !foto
				&& nome == null) {

			ResultSet resultSet = dbcConnection.select("SELECT * FROM Estabelecimento");

			try {
				while (resultSet.next()) {
					coordenadasGPS = resultSet
							.getString("coordenadasGps");
					designacao = resultSet.getString("designacao");
					formaDeChegar = resultSet
							.getString("formaDeLaChegar");
					idEstabelecimento = resultSet
							.getInt("idEstabelecimento");
					informacoesHorario = resultSet
							.getString("informacoesHorario");
					morada = resultSet.getString("morada");
					rating = resultSet.getInt("rating");

					Estabelecimento estabelecimento = new Estabelecimento(
							coordenadasGPS, designacao, formaDeChegar,
							idEstabelecimento, informacoesHorario, morada,
							rating);
					listaEstabelecimentos.add(estabelecimento);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// Faz a pesquisa pelo filtro
		}
		return listaEstabelecimentos;
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

}
