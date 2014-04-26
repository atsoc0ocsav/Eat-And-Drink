package dados;

import gui.ecraConsultarEstabelecimentos;
import controlo.ctrlConsultarEstabelecimentos;


public class Main {

	public static void main(String[] args) {
		ecraConsultarEstabelecimentos guiConsulta = new ecraConsultarEstabelecimentos(new ctrlConsultarEstabelecimentos());
	}

}
