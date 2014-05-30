package main;

import gui.ReservaBilhete;
import controlo.GestorReservaBilhete.ConcorrencyLevel;

public class MainReservaDeBilhetes {

	public static void main(String[] args) {
		new ReservaBilhete(ConcorrencyLevel.PESSIMIST);
	}

}
