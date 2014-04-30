package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class Prato {
	
	private String nome;
	private float preco;
	private int idPrato;
	private String descricao ;
	private int tipoDePrato;
	
	private DBConnection dbConnection;
	
	public Prato() {
		dbConnection = new DBConnection();
	}
	
	public Prato(float preco, int idPrato, String descricao, int tipoDePrato) {
		dbConnection = new DBConnection();
		
		this.preco = preco;
		this.idPrato = idPrato;
		this.descricao = descricao;
		this.tipoDePrato = tipoDePrato;
	}
	
	public ArrayList<Prato> select(int idEstabelecimento) {

		String sqlExpression = "SELECT * FROM menuDoEstabelecimento, Prato" +
				" WHERE menuDoEstabelecimento.idEstabelecimento = " + idEstabelecimento + " " +
						"AND menuDoEstabelecimento.idPrato = Prato.idPrato;";
		
		ResultSet resultSet = dbConnection.select(sqlExpression);
		
		ArrayList<Prato> pratos = prepareResult(resultSet); 
		
		dbConnection.closeDBConnection();
		
		return pratos;
	}
	
	private ArrayList<Prato> prepareResult(ResultSet resultSet) {
		ArrayList<Prato> pratos = new ArrayList<Prato>();
		try {
			while (resultSet.next()) {
				float preco = resultSet.getFloat("preco");
				int idPrato = resultSet.getInt("idPrato");
				String descricao = resultSet.getString("descricao");
				int tipoDePrato = resultSet.getInt("tipoDePrato");

				Prato prato = new Prato(preco, idPrato, descricao, tipoDePrato);
				pratos.add(prato);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pratos;
	}

	public String getNome() {
		return nome;
	}

	public float getPreco() {
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
