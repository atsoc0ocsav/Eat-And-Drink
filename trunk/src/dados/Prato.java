package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DBConnection;

public class Prato {
	
	private String nome;
	private double preco;
	private int idPrato;
	private String descricao ;
	private int tipoDePrato;
	
	private DBConnection dbConnection;
	
	public Prato() {
		dbConnection = new DBConnection();
	}
	
	public Prato(double prec, int idPrato, String descricao, int tipoDePrato) {
		dbConnection = new DBConnection();
		
		this.preco = prec;
		this.idPrato = idPrato;
		this.descricao = descricao;
		this.tipoDePrato = tipoDePrato;
	}
	
	public ArrayList<Prato> select(int idEstabelecimento) {

		String sqlExpression = "SELECT * FROM menuDoEstabelecimento, Prato " +
				"WHERE menuDoEstabelecimento.idEstabelecimento = " + idEstabelecimento +  
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

	public boolean insere(int estabelecimentoId, Prato p) {
		
		ResultSet rs = dbConnection
				.select("SELECT FIRST idPrato FROM Prato ORDER BY idPrato DESC");

		int currentID = 0;
		try {
			if (rs.next()) 
				currentID = rs.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sqlExpression = "INSERT INTO Prato (descricao, idPrato, preco, tipoDePrato) VALUES " +
				"( '" + p.getDescricao() + 
				"', " + currentID +
				", " + p.getPreco() + 
				", " + p.getTipoDePrato() + "); ";
				
		//System.out.println(sqlExpression);
		
		dbConnection.insert(sqlExpression);
		
		dbConnection.closeDBConnection();
		
		
		sqlExpression = "INSERT INTO MenuDoEstabelecimento (idEstabelecimento, idPrato) VALUES " +
				"( '" + estabelecimentoId + 
				"', " + currentID + "); ";
				
		//System.out.println(sqlExpression);
		
		dbConnection.insert(sqlExpression);
		
		dbConnection.closeDBConnection();
		
		return true;
		
	}

	

}
