package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class TipoDeEvento {
	private DBConnection dbcConnection;
	private int tipoDeEvento;
	private String descricao;

	public TipoDeEvento(DBConnection dbcConnection) {
		super();
		this.dbcConnection = dbcConnection;
	}

	public TipoDeEvento(int tipoDeEvento, String descricao) {
		super();
		this.tipoDeEvento = tipoDeEvento;
		this.descricao = descricao;
	}

	public ArrayList<TipoDeEvento> getTiposDeEventos() {
		ArrayList<TipoDeEvento> tiposDeEventos = new ArrayList<TipoDeEvento>();

		ResultSet resultSet = dbcConnection
				.select("SELECT * FROM TipoDeEvento");

		try {
			while (resultSet.next()) {
				int tipoDeEvento = resultSet.getInt("tipoDeEvento");
				String descricao = resultSet.getString("descricao");
				tiposDeEventos.add(new TipoDeEvento(tipoDeEvento, descricao));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tiposDeEventos;
	}

}
