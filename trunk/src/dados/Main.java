package dados;

import controlo.CtrConsultarEstabelecimentos;
import gui.Consulta;

public class Main {

	public static void main(String[] args) {
		Consulta guiConsulta = new Consulta(new CtrConsultarEstabelecimentos());
	}

}
