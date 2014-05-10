package controlo;

import java.util.Vector;

import dados.DadosCidade;
import dados.DadosEscola;
import dados.DadosUtilizador;
import dados.Utilizador;
import dbc.LigacaoBaseDeDados;

public class GestorConsultaDeUtilizadores {
	
	private LigacaoBaseDeDados database;
	private DadosCidade cityData;
	private DadosEscola schoolData;
	private DadosUtilizador userData;

	public GestorConsultaDeUtilizadores(){
		database = new LigacaoBaseDeDados();
		cityData = new DadosCidade(database);
		schoolData = new DadosEscola(database);
		userData = new DadosUtilizador(database);
	}
	
	public Vector<Utilizador> consulta(String city, String school, String name, boolean isFollower){
		return userData.obterUtilizadores(city, school, name, isFollower);
	}
	
	public Vector<String> obterCidades() {
		return cityData.obterCidades();
	}
	
	public Vector<String> obterEscolas() {
		return schoolData.obterEscolas();
	}

	public boolean isFollower(String currentEmail, String email) {
		return userData.isFollower(currentEmail, email);
	}

	public void seguir(String currentEmail, String email) {
		userData.seguirUtilizador(currentEmail, email);
	}

	public void deixarSeguir(String currentEmail, String email) {
		userData.deixarSeguir(currentEmail, email);
	}
}
