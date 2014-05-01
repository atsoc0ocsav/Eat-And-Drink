package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class TipoDePrato {
	private DBConnection dbConnection;
	private int tipoDePrato;
	private String descricao;

	public TipoDePrato() {
		dbConnection = new DBConnection();
	}

	public TipoDePrato(int tipoDePrato, String descricao) {
		super();
		this.tipoDePrato = tipoDePrato;
		this.descricao = descricao;
	}

	public ArrayList<TipoDePrato> getTiposDePrato() {
		ArrayList<TipoDePrato> tiposDePrato = new ArrayList<TipoDePrato>();

		ResultSet resultSet = dbConnection.select("SELECT * FROM TipoDePrato ORDER BY descricao");

		try {
			while (resultSet.next()) {
				int tipoDePrato = resultSet.getInt("tipoDePrato");
				String descricao = resultSet.getString("descricao");
				tiposDePrato.add(new TipoDePrato(tipoDePrato, descricao));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbConnection.closeDBConnection();

		return tiposDePrato;
	}

	public int tipoDePrato() {
		return tipoDePrato;
	}
	
	public int getTipoDePrato() {
		return tipoDePrato;
	}

	public void setTipoDePrato(int tipoDePrato) {
		this.tipoDePrato = tipoDePrato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
