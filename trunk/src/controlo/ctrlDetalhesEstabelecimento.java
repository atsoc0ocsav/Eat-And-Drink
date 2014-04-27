package controlo;

import dados.Estabelecimento;

public class ctrlDetalhesEstabelecimento {

	/**
	 * Creates the Establishment Details Controller
	 * 
	 * @param establishment
	 *            ID
	 */
	public ctrlDetalhesEstabelecimento(int establishmentID) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Return the establishment with the ID given on the constructor method
	 * 
	 * @return establishment with the given ID
	 */
	public Estabelecimento consultarDetalhes() {
		//TODO Estabelecimento de Teste.... Falta fazer o verdadeiro ;)
		return new Estabelecimento("38º44'52.11717\" 9º9'12.40631\"",
				0, "Estabelecimento de Teste", "Virá à esquerda e à direira! ",
				123, "Aberto 24 sobre 24", "Av. das Forças Armadas", 0);
	}

}
