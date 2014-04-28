package controlo;

import java.util.ArrayList;

import dados.Cidade;
import dados.Estabelecimento;
import dados.TipoDeEstablecimento;
import dados.TipoDeEvento;
import dados.TipoDePrato;
import dados.Zona;

public class ctrlConsultarEstabelecimentos {
	private Estabelecimento estabelecimento;
	private Cidade cidade;
	private Zona zona;
	private TipoDeEstablecimento tipoDeEstablecimento;
	private TipoDeEvento tipoDeEvento;
	private TipoDePrato tipoDePrato;

	public ctrlConsultarEstabelecimentos() {
		estabelecimento = new Estabelecimento();
		cidade = new Cidade();
		zona = new Zona();
		tipoDeEstablecimento = new TipoDeEstablecimento();
		tipoDeEvento = new TipoDeEvento();
		tipoDePrato = new TipoDePrato();
	}

	public ArrayList<Estabelecimento> consultarEstabelecimentos(String cidade,
			String zona, String tipo, double aval, String prato, String evento,
			String nome) {

		ArrayList<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();

		listaEstabelecimentos = estabelecimento.select(cidade, zona, tipo,
				aval, prato, evento, nome);

		return listaEstabelecimentos;
	}

	public ArrayList<Cidade> getCidades() {
		return cidade.getCidades();
	}

	public ArrayList<Zona> getZonas() {
		return zona.getZonas();
	}

	public ArrayList<TipoDeEstablecimento> getTiposDeEstablecimento() {
		return tipoDeEstablecimento.getTiposDeEstablecimento();
	}

	public ArrayList<TipoDeEvento> getTiposDeEventos() {
		return tipoDeEvento.getTiposDeEvento();
	}

	public ArrayList<TipoDePrato> getTiposDePratos() {
		return tipoDePrato.getTiposDePrato();
	}

}