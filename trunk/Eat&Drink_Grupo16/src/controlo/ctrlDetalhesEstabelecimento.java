package controlo;

import dados.Estabelecimento;
import dados.Prato;

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

	public void removePrato(int idPrato) {
		new Prato().remove(idPrato);
	}

}
