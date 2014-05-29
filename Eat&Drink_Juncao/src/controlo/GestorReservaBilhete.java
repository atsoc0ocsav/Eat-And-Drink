package controlo;

import java.sql.SQLException;
import java.util.ArrayList;

import dados.Evento;
import dados.ReservaDeBilhetes;

public class GestorReservaBilhete {

	private Evento evento;
	private ReservaDeBilhetes bilhete;

	public enum ConcorrencyLevel {
		OPTIMIST, PESSIMIST
	};

	private final ConcorrencyLevel CONCORRENCY_STATE = ConcorrencyLevel.PESSIMIST;

	public GestorReservaBilhete() {
		this.evento = new Evento();
		this.bilhete = new ReservaDeBilhetes();
	}

	public ArrayList<Evento> getEvento() {
		ArrayList<Evento> array = new ArrayList<>();

		array = evento.selectEventoOferecido();

		return array;
	}

	public ArrayList<ReservaDeBilhetes> getLugares(int id) throws SQLException {
		ArrayList<ReservaDeBilhetes> array = new ArrayList<>();

		if (CONCORRENCY_STATE == CONCORRENCY_STATE.PESSIMIST) {
			array = bilhete.selectLugaresPessimista(id);
		} else {
			if (CONCORRENCY_STATE == CONCORRENCY_STATE.OPTIMIST) {
				array = bilhete.selectLugaresOptimista(id);
			}
		}

		return array;
	}

	public void confirmarBilhete(int idEvento, int lugar, String estado)
			throws SQLException {
		bilhete.updateEstado(idEvento, lugar, estado);

	}

	public ConcorrencyLevel getCONCORRENCY_STATE() {
		return CONCORRENCY_STATE;
	}

}
