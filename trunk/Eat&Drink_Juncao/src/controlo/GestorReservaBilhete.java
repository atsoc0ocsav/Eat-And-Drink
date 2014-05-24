package controlo;

import gui.ReservaBilhete;

import java.util.ArrayList;

import dados.BilheteEvento;

public class GestorReservaBilhete {

	private BilheteEvento bilhete;

	public GestorReservaBilhete() {
		this.bilhete = new BilheteEvento();
	}

	public ArrayList<BilheteEvento> getBilheteEvento() {
		ArrayList<BilheteEvento> array = new ArrayList<>();

		array = bilhete.select();

		return array;
	}

}
