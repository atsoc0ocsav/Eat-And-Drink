package controlo;

import java.util.ArrayList;

import dados.Evento;


public class GestorReservaBilhete {

	private Evento evento;

	public GestorReservaBilhete() {
		this.evento = new Evento();
	}

	public ArrayList<Evento> getEvento() {
		ArrayList<Evento> array = new ArrayList<>();

		array = evento.selectEventoOferecido();

		return array;
	}

}
