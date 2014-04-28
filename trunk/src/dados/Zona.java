package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class Zona {
	private DBConnection dbConnection;
	private int idZona;
	private String cidade;
	private String designacao;

	public Zona() {
		dbConnection = new DBConnection();
	}

	public Zona(int idZona, String cidade, String designacao) {
		super();
		this.idZona = idZona;
		this.cidade = cidade;
		this.designacao = designacao;
	}

	public ArrayList<Zona> getZonas() {

		ArrayList<Zona> zonas = new ArrayList<Zona>();

		ResultSet resultSet = dbConnection.select("SELECT * FROM Zona");

		try {
			while (resultSet.next()) {
				int idZona = resultSet.getInt("idZona");
				String cidade = resultSet.getString("cidade");
				String designacao = resultSet.getString("designacao");
				zonas.add(new Zona(idZona, cidade, designacao));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return zonas;
	}

	public int getIdZona() {
		return idZona;
	}

	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

}
