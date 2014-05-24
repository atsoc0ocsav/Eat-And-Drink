package controlo;

import java.util.ArrayList;

import dados.Evento;
import dados.ReservaDeBilhetes;


public class GestorReservaBilhete {

	private Evento evento;
	private ReservaDeBilhetes bilhete;

	public GestorReservaBilhete() {
		this.evento = new Evento();
		this.bilhete = new ReservaDeBilhetes();
	}

	public ArrayList<Evento> getEvento() {
		ArrayList<Evento> array = new ArrayList<>();

		array = evento.selectEventoOferecido();

		return array;
	}

	public ArrayList<ReservaDeBilhetes> getLugares(int id) {
		ArrayList<ReservaDeBilhetes> array = new ArrayList<>();

		array = bilhete.selectLugares(id);

		return array;
	}

	public void confirmarBilhete(int idEvento,  int lugar, String estado) {
		bilhete.updateEstado(idEvento, lugar, estado);
		
	}

}
