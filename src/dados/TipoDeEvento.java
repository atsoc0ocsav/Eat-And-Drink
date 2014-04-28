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

	public ArrayList<TipoDeEvento> getTiposDeEvento() {
		ArrayList<TipoDeEvento> tiposDeEvento = new ArrayList<TipoDeEvento>();

		ResultSet resultSet = dbcConnection
				.select("SELECT * FROM TipoDeEvento");

		try {
			while (resultSet.next()) {
				int tipoDeEvento = resultSet.getInt("tipoDoEvento");
				String descricao = resultSet.getString("descricao");
				tiposDeEvento.add(new TipoDeEvento(tipoDeEvento, descricao));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tiposDeEvento;
	}

	public int getTipoDeEvento() {
		return tipoDeEvento;
	}

	public void setTipoDeEvento(int tipoDeEvento) {
		this.tipoDeEvento = tipoDeEvento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
