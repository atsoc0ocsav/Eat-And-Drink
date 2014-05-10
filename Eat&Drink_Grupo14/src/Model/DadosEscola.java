package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Controller.LigacaoBaseDeDados;

public class DadosEscola {

	private LigacaoBaseDeDados database;
	private Vector<String> schools = new Vector<String>();

	public DadosEscola(LigacaoBaseDeDados database){
		this.database = database;
	}

	public Vector<String> obterEscolas(){
		try {
			ResultSet rs = database.select("SELECT DISTINCT Escola FROM Utilizador");
			while (rs.next()) 
				schools.add(rs.getString("Escola"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database.closeStatement();
		return schools;
	}
}
