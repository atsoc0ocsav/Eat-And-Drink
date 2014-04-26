package controlo;

import java.util.ArrayList;

import dados.Estabelecimento;
import dbc.DBConnection;

public class CtrConsultarEstabelecimentos {

	private DBConnection dbc;
	private Estabelecimento estabelecimento;

	public CtrConsultarEstabelecimentos() {
		dbc = new DBConnection();
		estabelecimento = new Estabelecimento(dbc);
	}

	public ArrayList<Estabelecimento> consultarEstabelecimentos(String cidade,
			String zona, String tipo, double rating, String prato,
			double preco, String evento, boolean foto, String nome) {

		ArrayList<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();

		listaEstabelecimentos = estabelecimento.select(cidade, zona, tipo,
				rating, prato, preco, evento, foto, nome);

		return listaEstabelecimentos;
	}

}
