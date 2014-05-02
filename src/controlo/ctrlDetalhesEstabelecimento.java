package controlo;

import dados.Cidade;
import dados.Estabelecimento;
import dados.Prato;
import dados.TipoDeEstablecimento;
import dados.TipoDeEvento;
import dados.TipoDePrato;
import dados.Zona;

public class ctrlDetalhesEstabelecimento {
	
	private Prato prato;
	
	public ctrlDetalhesEstabelecimento() {
		prato = new Prato();
	}
	
	/**
	 * Return the establishment with the given ID
	 * 
	 * @return establishment ID
	 */
	public Estabelecimento consultarDetalhesEstabelecimento(int establishmentID) {
		Estabelecimento establishmentDataObject = new Estabelecimento();
		return establishmentDataObject.select(establishmentID);
	}

	public boolean insere(int idEstabelecimento, Prato p) {
		
		return prato.insere(idEstabelecimento, p);
		
		
		
	}

}
