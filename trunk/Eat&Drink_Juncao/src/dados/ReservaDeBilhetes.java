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

	public ArrayList<ReservaDeBilhetes> selectLugares(int idEvento) {

		String sqlExpression = "SELECT * " + "FROM ReservaDeBilhetes "
				+ "WHERE idEvento = " + idEvento + " AND estado = 'Livre'";
	
		ResultSet resultSet = dbConnection.select(sqlExpression);
		ArrayList<ReservaDeBilhetes> bilhetes = prepareResult(resultSet);

		dbConnection.closeDBConnection();

		return bilhetes;
	}

	private ArrayList<ReservaDeBilhetes> prepareResult(ResultSet resultSet) {
		ArrayList<ReservaDeBilhetes> array = new ArrayList<ReservaDeBilhetes>();
		try {
			while (resultSet.next()) {
				int numeroLugar = resultSet.getInt("numeroLugar");
				String estado = resultSet.getString("estado");
				int idEstabelecimento = resultSet.getInt("idEstabelecimento");
				int idEvento = resultSet.getInt("idEvento");

				ReservaDeBilhetes bilhete = new ReservaDeBilhetes(numeroLugar,
						estado, idEstabelecimento, idEvento);
				array.add(bilhete);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return array;
	}

	public void updateEstado(int idEvento, int numeroLugar, String estado) {
		String sqlExpression = "UPDATE ReservaDeBilhetes SET estado = '"
				+ estado + "' WHERE " + "idEvento = " + idEvento
				+ " AND numeroLugar = " + numeroLugar;

		dbConnection.insert(sqlExpression);
		dbConnection.closeDBConnection();

	}
	
	public int getNumeroLugar() {
		return numeroLugar;
	}
	
	public String getEstado(){
		return estado;
	}
	
	public int getIDEvento(){
		return idEvento;
	}

	public void setEstado(String estado) {
		this.estado = estado;		
	}

}
