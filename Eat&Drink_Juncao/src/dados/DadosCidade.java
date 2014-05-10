package dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dbc.LigacaoBaseDeDados;

public class DadosCidade {
	
	private LigacaoBaseDeDados database;
	private Vector<String> cities = new Vector<String>();
	
	public DadosCidade(LigacaoBaseDeDados database){
		this.database = database;
	}
	
	public Vector<String> obterCidades(){
		try {
			ResultSet rs = database.select("SELECT * FROM Cidade");
			while (rs.next()) 
				cities.add(rs.getString("Cidade")); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database.closeStatement();
		return cities;
	}
}
