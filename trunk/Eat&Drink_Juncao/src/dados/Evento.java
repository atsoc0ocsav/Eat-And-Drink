package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class Evento {
	private String descricao;
	private int idEvento;

	private String data;
	private String hora;

	private DBConnection dbConnection;

	public Evento() {
		dbConnection = new DBConnection();
	}

	public Evento(int idEvento, String descricao) {
		dbConnection = new DBConnection();
		this.idEvento = idEvento;
		this.descricao = descricao;
	}

	public Evento(int idEvento, String descricao, String data, String hora) {
		this.idEvento = idEvento;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
	}

	public ArrayList<Evento> select(int idEstabelecimento) {

		String sqlExpression = "SELECT TipoDeEvento.tipoDoEvento,descricao "
				+ "FROM TipoDeEvento,eventoOferecido "
				+ "WHERE eventoOferecido.tipoDoEvento=TipoDeEvento.tipoDoEvento "
				+ "AND eventoOferecido.idEstabelecimento=" + idEstabelecimento;

		ResultSet resultSet = dbConnection.select(sqlExpression);

		ArrayList<Evento> eventos = prepareResult(resultSet, 1);

		dbConnection.closeDBConnection();

		return eventos;
	}

	public ArrayList<Evento> selectEventoOferecido() {

		String sqlExpression = "SELECT * " + "FROM eventoOferecido ";

		ResultSet resultSet = dbConnection.select(sqlExpression);

		ArrayList<Evento> eventos = prepareResult(resultSet, 2);

		dbConnection.closeDBConnection();

		return eventos;
	}

	private ArrayList<Evento> prepareResult(ResultSet resultSet, int escolha) {
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		if (escolha == 1) {
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
		} else if (escolha == 2) {
			try {
				while (resultSet.next()) {
					String descricao = resultSet.getString("nomeEvento");
					int idEvento = resultSet.getInt("idEvento");
					String data = resultSet.getString("data");
					String hora = resultSet.getString("hora");

					Evento evento = new Evento(idEvento, descricao, data,
							hora);
					eventos.add(evento);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eventos;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getIdEvento() {
		return idEvento;
	}
	
	public String getHora(){
		return hora;
	}
	
	public String getData(){
		return data;
	}

	public DBConnection getDbConnection() {
		return dbConnection;
	}

}
