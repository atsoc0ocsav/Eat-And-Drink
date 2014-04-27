package main;

import gui.ecraConsultarEstabelecimentos;
import controlo.ctrlConsultarEstabelecimentos;


public class Main {

	public static void main(String[] args) {
		ctrlConsultarEstabelecimentos controlador = new ctrlConsultarEstabelecimentos();
		ecraConsultarEstabelecimentos guiConsulta = new ecraConsultarEstabelecimentos(controlador);
	}

}
