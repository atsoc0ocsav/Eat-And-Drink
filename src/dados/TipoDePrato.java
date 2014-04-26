package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class TipoDePrato {
	private DBConnection dbcConnection;
	private int tipoDePrato;
	private String descricao;

	public TipoDePrato(DBConnection dbcConnection) {
		super();
		this.dbcConnection = dbcConnection;
	}

	public TipoDePrato(int tipoDePrato, String descricao) {
		super();
		this.tipoDePrato = tipoDePrato;
		this.descricao = descricao;
	}

	public ArrayList<TipoDePrato> getTiposDePratos() {
		ArrayList<TipoDePrato> tiposDePratos = new ArrayList<TipoDePrato>();

		ResultSet resultSet = dbcConnection.select("SELECT * FROM TipoDePrato");

		try {
			while (resultSet.next()) {
				int tipoDePrato = resultSet.getInt("tipoDePrato");
				String descricao = resultSet.getString("descricao");
				tiposDePratos.add(new TipoDePrato(tipoDePrato, descricao));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tiposDePratos;
	}

}
