package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;

import Controller.LigacaoBaseDeDados;

public class DadosUtilizador {
	
	private LigacaoBaseDeDados database;
	private Vector<Utilizador> users = new Vector<Utilizador>();
	
	public DadosUtilizador(LigacaoBaseDeDados database){
		this.database = database;
	}
	
	public Vector<Utilizador> obterUtilizadores(String city, String school, String name, boolean isFollower){
		String query = "";
		try {
			if (city.equals("") && school.equals("") && name.equals(""))
				query = "SELECT * FROM Utilizador";
			else if(!city.equals("") && school.equals("") && name.equals(""))
				query = "SELECT * FROM Utilizador WHERE cidade = '" + city +"'";
			else if(city.equals("") && !school.equals("") && name.equals(""))
				query = "SELECT * FROM Utilizador WHERE escola = '" + school +"'";
			else if(city.equals("") && school.equals("") && !name.equals(""))
				query = "SELECT * FROM Utilizador WHERE nome LIKE '%" + name +"%'";
			else if(!city.equals("") && !school.equals("") && name.equals(""))
				query = "SELECT * FROM Utilizador WHERE cidade = '" + city +"' AND escola = '" + school +"'";
			else if(!city.equals("") && school.equals("") && !name.equals(""))
				query = "SELECT * FROM Utilizador WHERE cidade = '" + city +"' AND nome LIKE '%" + name +"%'";
			else if(city.equals("") && !school.equals("") && !name.equals(""))
				query = "SELECT * FROM Utilizador WHERE escola = '" + school +"' AND nome LIKE '%" + name +"%'";
			
			ResultSet rs = database.select(query);
			
			while (rs.next()){
				Utilizador user = new Utilizador(rs.getString("email"), 0, rs.getString("nome"), rs.getString("escola"), rs.getString("cidade"), " ");
				users.add(user); 
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database.closeStatement();
		return users;
	}
	
	public Utilizador obterInformacaoUtilizador(String userSelecionado){
		String query = "SELECT * FROM Utilizador WHERE nome LIKE '%" + userSelecionado +"%'"; 
		ResultSet rs;
		ResultSet rsTemp;
		Utilizador user = null;
		LinkedList<String> followUsers = new LinkedList<String>();
		try {
			rs = database.select(query);
			rs.next();
			user = new Utilizador(rs.getString("email"), 0, rs.getString("nome"), rs.getString("escola"), rs.getString("cidade"), " ");
			
			rs = database.select("SELECT * FROM follow WHERE emailsgu = '" + rs.getString("email") +"'");
			while(rs.next()){
				rsTemp = database.select("SELECT * FROM Utilizador WHERE email LIKE '%" + rs.getString("emailsguidor") +"%'");
				rsTemp.next();
				followUsers.add(rsTemp.getString("nome"));
			}
			user.setFollowers(followUsers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database.closeStatement();
		return user;
	}
	
	public boolean isFollower(String currentEmail, String email) {
		boolean valid = false;
		
		try {
			valid = database.select("SELECT * FROM follow WHERE emailsguidor = '" + currentEmail +"' AND emailsgu = '" + email +"';").next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database.closeStatement();
		return valid;
	}
	
	public boolean gravarAlteracoes(String nome, String email, String cidade, String escola){
		return database.update("UPDATE Utilizador SET nome = '" + nome + "', email = '" + email + "', cidade = '" + cidade + "' , escola = '" + escola + "' WHERE email = '" + email + "';");
	}

	public boolean seguirUtilizador(String currentEmail, String email) {
		return database.insert("INSERT INTO follow VALUES ('"+ currentEmail + "', '" + email + "');");
	}

	public boolean deixarSeguir(String currentEmail, String email) {
		return database.delete("DELETE FROM follow WHERE emailsguidor = '" + currentEmail +"' AND emailsgu = '" + email +"';");
	}
}
