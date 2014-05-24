package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class BilheteEvento {
	private int idEvento;
	private String nomeEvento;
	private String data;
	private String hora;
	private int numerLugar;
	private String disponivel;

	private DBConnection dbConnection;

	public BilheteEvento(int idEvento, String nomeEvento, String data,
			String hora, int numerLugar, String disponivel) {
		super();
		this.idEvento = idEvento;
		this.nomeEvento = nomeEvento;
		this.data = data;
		this.hora = hora;
		this.numerLugar = numerLugar;
		this.disponivel = disponivel;
		this.dbConnection = new DBConnection();
	}

	public BilheteEvento() {
		this.dbConnection = new DBConnection();
	}

	public String getEvento() {
		return nomeEvento;
	}

	public ArrayList<BilheteEvento> select() {

		ResultSet resultSet = dbConnection
				.select("SELECT * FROM ReservaDeBilhetes");

		ArrayList<BilheteEvento> array = prepareResult(resultSet);

		dbConnection.closeDBConnection();

		return array;
	}
	
	private ArrayList<BilheteEvento> prepareResult(ResultSet resultSet) {
		ArrayList<BilheteEvento> array = new ArrayList<BilheteEvento>();
		try {
			while (resultSet.next()) {
								
				int idEvento = resultSet.getInt("idEvento");
				String nomeEvento = resultSet.getString("nomeEvento");
				String data = resultSet.getString("data");
				String hora = resultSet.getString("hora");
				int numeroLugar = resultSet.getInt("numeroLugar");
				String estado = resultSet.getString("estado");				

				BilheteEvento bilhete = new BilheteEvento(idEvento, nomeEvento, data, hora, numeroLugar, estado);
				array.add(bilhete);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}

}
