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

	public ArrayList<TipoDeEstablecimento> getTiposDeEstablecimentos() {

		ArrayList<TipoDeEstablecimento> tiposDeEstablecimentos = new ArrayList<TipoDeEstablecimento>();

		ResultSet resultSet = dbcConnection
				.select("SELECT * FROM TipoDeEstablecimento");

		try {
			while (resultSet.next()) {
				String tipoDeEstablecimento = resultSet.getString("designacao");
				tiposDeEstablecimentos.add(new TipoDeEstablecimento(
						tipoDeEstablecimento));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tiposDeEstablecimentos;
	}

}
