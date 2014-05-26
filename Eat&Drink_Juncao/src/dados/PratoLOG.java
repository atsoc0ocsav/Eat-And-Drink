package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dbc.DBConnection;

public class PratoLOG {

	private String nome;
	private double preco;
	private int idPrato;
	private String descricao;
	private int tipoDePrato;
	private Date data;
	private String tipoDeOperacao;
	private String utilizador;

	private DBConnection dbConnection;

	public PratoLOG() {
		dbConnection = new DBConnection();
	}

	public PratoLOG(double prec, int idPrato, String descricao, int tipoDePrato, 
			Date data, String tipoDeOperacao, String utilizador) {
		dbConnection = new DBConnection();

		this.preco = prec;
		this.idPrato = idPrato;
		this.descricao = descricao;
		this.tipoDePrato = tipoDePrato;
		this.data = data;
		this.tipoDeOperacao = tipoDeOperacao;
		this.utilizador = utilizador;
	}

	public ArrayList<PratoLOG> select() {

		String sqlExpression = "SELECT * FROM LogPrato ";

		ResultSet resultSet = dbConnection.select(sqlExpression);

		ArrayList<PratoLOG> logs = prepareResult(resultSet);

		dbConnection.closeDBConnection();

		return logs;
	}

	private ArrayList<PratoLOG> prepareResult(ResultSet resultSet) {
		ArrayList<PratoLOG> pratos = new ArrayList<PratoLOG>();
		try {
			while (resultSet.next()) {
				float preco = resultSet.getFloat("preco");
				int idPrato = resultSet.getInt("idPrato");
				String descricao = resultSet.getString("descricao");
				int tipoDePrato = resultSet.getInt("tipoDePrato");
				Date data = resultSet.getDate("data");
				String tipoDeOperacao = resultSet.getString("operacao");
				String utilizador = resultSet.getString("utilizador");

				PratoLOG prato = new PratoLOG(preco, idPrato, descricao, tipoDePrato,
						data, tipoDeOperacao, utilizador);
				pratos.add(prato);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pratos;
	}

	public Date getData() {
		return data;
	}

	public String getTipoDeOperacao() {
		return tipoDeOperacao;
	}

	public String getUtilizador() {
		return utilizador;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public int getIdPrato() {
		return idPrato;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getTipoDePrato() {
		return tipoDePrato;
	}

	public DBConnection getDbConnection() {
		return dbConnection;
	}

	

}
