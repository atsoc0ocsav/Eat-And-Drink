package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class ReservaDeBilhetes {

	private int numeroLugar;
	private String estado;
	private int idEstabelecimento;
	private int idEvento;

	private DBConnection dbConnection;

	public ReservaDeBilhetes() {
		this.dbConnection = new DBConnection();
	}

	public ReservaDeBilhetes(int numeroLugar, String estado,
			int idEstabelecimento, int idEvento) {
		super();
		this.numeroLugar = numeroLugar;
		this.estado = estado;
		this.idEstabelecimento = idEstabelecimento;
		this.idEvento = idEvento;

		this.dbConnection = new DBConnection();
	}

	public ArrayList<ReservaDeBilhetes> selectLugaresOptimista(int idEvento)
			throws SQLException {
		dbConnection.closeDBConnection();

		String sqlExpression = "SELECT * " + "FROM ReservaDeBilhetes "
				+ "WHERE idEvento = " + idEvento + " AND estado = 'Livre'";

		ResultSet resultSet = dbConnection.selectConcorrenciaOptimista(sqlExpression);
		ArrayList<ReservaDeBilhetes> bilhetes = prepareResult(resultSet);

		return bilhetes;
	}

	public ArrayList<ReservaDeBilhetes> selectLugaresPessimista(int idEvento)
			throws SQLException {
		dbConnection.closeDBConnection();
		dbConnection.setIsolationLevel(3);
		dbConnection.setTemporaryLockTimeout(600);

		dbConnection.begin();

		String sqlExpression = "SELECT * " + "FROM ReservaDeBilhetes "
				+ "WHERE idEvento = " + idEvento
				+ " AND estado = 'Livre' FOR UPDATE BY LOCK";

		ResultSet resultSet = dbConnection.selectConcorrenciaPessimista(sqlExpression);
		ArrayList<ReservaDeBilhetes> bilhetes = prepareResult(resultSet);

		return bilhetes;
	}

	private ArrayList<ReservaDeBilhetes> prepareResult(ResultSet resultSet)
			throws SQLException {
		ArrayList<ReservaDeBilhetes> array = new ArrayList<ReservaDeBilhetes>();
		while (resultSet.next()) {
			int numeroLugar = resultSet.getInt("numeroLugar");
			String estado = resultSet.getString("estado");
			int idEstabelecimento = resultSet.getInt("idEstabelecimento");
			int idEvento = resultSet.getInt("idEvento");

			ReservaDeBilhetes bilhete = new ReservaDeBilhetes(numeroLugar,
					estado, idEstabelecimento, idEvento);
			array.add(bilhete);
		}

		return array;
	}

	public int updateEstado(int idEvento, int numeroLugar, String estado)
			throws SQLException {
		String sqlExpression = "UPDATE ReservaDeBilhetes SET estado = '"
				+ estado + "' WHERE " + "idEvento = " + idEvento
				+ " AND numeroLugar = " + numeroLugar + "AND estado = 'Livre'";

		int updRows = dbConnection.insertConcorrencia(sqlExpression);
		dbConnection.closeDBConnection();
		
		return updRows;

	}

	public int getNumeroLugar() {
		return numeroLugar;
	}

	public String getEstado() {
		return estado;
	}

	public int getIDEvento() {
		return idEvento;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
