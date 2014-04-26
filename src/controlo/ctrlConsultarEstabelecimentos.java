package controlo;

import java.util.ArrayList;

import dados.Cidade;
import dados.Estabelecimento;
import dados.TipoDeEstablecimento;
import dados.TipoDeEvento;
import dados.TipoDePrato;
import dados.Zona;
import dbc.DBConnection;

public class ctrlConsultarEstabelecimentos {
	private DBConnection dbc;
	private Estabelecimento estabelecimento;
	private Cidade cidade;
	private Zona zona;
	private TipoDeEstablecimento tipoDeEstablecimento;
	private TipoDeEvento tipoDeEvento;
	private TipoDePrato tipoDePrato;

	public ctrlConsultarEstabelecimentos() {
		dbc = new DBConnection();
		estabelecimento = new Estabelecimento(dbc);
		cidade = new Cidade(dbc);
		zona = new Zona(dbc);
		tipoDeEstablecimento = new TipoDeEstablecimento(dbc);
		tipoDeEvento = new TipoDeEvento(dbc);
		tipoDePrato = new TipoDePrato(dbc);
	}

	public ArrayList<Estabelecimento> consultarEstabelecimentos(String cidade,
			String zona, String tipo, double rating, String prato,
			double preco, String evento, boolean foto, String nome) {

		ArrayList<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();

		listaEstabelecimentos = estabelecimento.select(cidade, zona, tipo,
				rating, prato, preco, evento, foto, nome);

		return listaEstabelecimentos;
	}

	public ArrayList<Cidade> getCidades() {
		return cidade.getCidades();
	}
	
	public ArrayList<Zona> getZonas(){
		return zona.getZonas();
	}
	
	public ArrayList<TipoDeEstablecimento> getTiposDeEstablecimento(){
		return tipoDeEstablecimento.getTiposDeEstablecimento();
	}
	
	public ArrayList<TipoDeEvento> getTiposDeEventos(){
		return tipoDeEvento.getTiposDeEvento(); 
	}
	
	public ArrayList<TipoDePrato> getTiposDePratos(){
		return tipoDePrato.getTiposDePrato();
	}

}