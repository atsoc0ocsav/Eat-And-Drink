package dados;

import controlo.CtrConsultarEstabelecimentos;
import gui.Consulta;

public class Main {
	
	public static void main(String[] args) {
		CtrConsultarEstabelecimentos ctrConsulta = new CtrConsultarEstabelecimentos();
		Consulta guiConsulta = new Consulta(ctrConsulta);
		
	}

}
