package controlo;

import java.util.ArrayList;

import dados.Estabelecimento;

public class ctrlDetalhesEstabelecimento {
	/**
	 * Return the establishment with the given ID
	 * 
	 * @return establishment ID
	 */
	public Estabelecimento consultarDetalhesEstabelecimento(int establishmentID) {
		Estabelecimento establishmentDataObject = new Estabelecimento();
		ArrayList<Estabelecimento> establishment = establishmentDataObject
				.select(establishmentID);

		if (establishment != null && establishment.size() > 1)
			return establishment.get(0);
		else
			return null;
	}

}
