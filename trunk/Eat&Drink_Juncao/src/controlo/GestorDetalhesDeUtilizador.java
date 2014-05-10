package controlo;

import dados.DadosUtilizador;
import dados.Fotografias;
import dados.Utilizador;
import dbc.LigacaoBaseDeDados;

public class GestorDetalhesDeUtilizador {

	private LigacaoBaseDeDados database;
	private DadosUtilizador userData;

	public GestorDetalhesDeUtilizador(){
		database = new LigacaoBaseDeDados();
		userData = new DadosUtilizador(database);
	}
	
	public Utilizador carregarDados(String userSelecionado){
		return userData.obterInformacaoUtilizador(userSelecionado);
	}
	
	public void alterarFoto(String userSelecionado){
		new Fotografias().alterarFotografia(userSelecionado);
	}
	
	public void gravarAlteracoes(String nome, String email, String cidade, String escola){
		userData.gravarAlteracoes(nome, email, cidade, escola);
	}
	
	public Utilizador obterDetalhesPorBotao(String userSelecionado){
		return carregarDados(userSelecionado);
	}
	
}
