package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class TipoDeEstablecimento {
	private DBConnection dbcConnection;
	private String tipoDeEstablecimento;

	public TipoDeEstablecimento(DBConnection dbcConnection) {
		super();
		this.dbcConnection = dbcConnection;
	}

	public TipoDeEstablecimento(String tipoDeEstablecimento) {
		super();
		this.tipoDeEstablecimento = tipoDeEstablecimento;
	}

	public ArrayList<TipoDeEstablecimento> getTiposDeEstablecimento() {

		ArrayList<TipoDeEstablecimento> tiposDeEstablecimento = new ArrayList<TipoDeEstablecimento>();

		ResultSet resultSet = dbcConnection
				.select("SELECT * FROM TipoDeEstabelecimento");

		try {
			while (resultSet.next()) {
				String tipoDeEstablecimento = resultSet.getString("tipoDoEstabelecimento");
				tiposDeEstablecimento.add(new TipoDeEstablecimento(
						tipoDeEstablecimento));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tiposDeEstablecimento;
	}

	public String getTipoDeEstablecimento() {
		return tipoDeEstablecimento;
	}

	public void setTipoDeEstablecimento(String tipoDeEstablecimento) {
		this.tipoDeEstablecimento = tipoDeEstablecimento;
	}

}
