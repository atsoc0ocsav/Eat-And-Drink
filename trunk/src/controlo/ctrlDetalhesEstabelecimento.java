package controlo;

import dados.Estabelecimento;

public class ctrlDetalhesEstabelecimento {
	/**
	 * Return the establishment with the given ID
	 * 
	 * @return establishment ID
	 */
	public Estabelecimento consultarDetalhesEstabelecimento(int establishmentID) {
		Estabelecimento establishmentDataObject = new Estabelecimento();
		return establishmentDataObject.select(establishmentID);
	}

}
