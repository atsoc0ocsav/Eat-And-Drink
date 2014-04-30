package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class Evento {
	private String descricao;
	private int idEvento;

	private DBConnection dbConnection;

	public Evento() {
		dbConnection = new DBConnection();
	}

	public Evento(int idEvento, String descricao) {
		dbConnection = new DBConnection();

		this.idEvento = idEvento;
		this.descricao = descricao;
	}

	public ArrayList<Evento> select(int idEstabelecimento) {

		String sqlExpression = "SELECT TipoDeEvento.tipoDoEvento,descricao "
				+ "FROM TipoDeEvento,eventoOferecido "
				+ "WHERE eventoOferecido.tipoDoEvento=TipoDeEvento.tipoDoEvento "
				+ "AND eventoOferecido.idEstabelecimento=" + idEstabelecimento;

		ResultSet resultSet = dbConnection.select(sqlExpression);

		ArrayList<Evento> eventos = prepareResult(resultSet);

		dbConnection.closeDBConnection();

		return eventos;
	}

	private ArrayList<Evento> prepareResult(ResultSet resultSet) {
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		try {
			while (resultSet.next()) {
				String descricao = resultSet.getString("descricao");
				int tipoDoEvento = resultSet.getInt("tipoDoEvento");

				Evento evento = new Evento(tipoDoEvento, descricao);
				eventos.add(evento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventos;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public DBConnection getDbConnection() {
		return dbConnection;
	}

}
