package controlo;

import java.sql.SQLException;
import java.util.ArrayList;

import dados.Evento;
import dados.ReservaDeBilhetes;

public class GestorReservaBilhete {

	private Evento evento;
	private ReservaDeBilhetes bilhete;

	public static enum ConcorrencyLevel {
		OPTIMIST, PESSIMIST
	};

	private ConcorrencyLevel CONCORRENCY_STATE = ConcorrencyLevel.PESSIMIST;

	public GestorReservaBilhete(ConcorrencyLevel concurrenceLevel) {
		this.evento = new Evento();
		this.bilhete = new ReservaDeBilhetes();
		this.CONCORRENCY_STATE = concurrenceLevel;
	}

	public ArrayList<Evento> getEvento() {
		ArrayList<Evento> array = new ArrayList<>();

		array = evento.selectEventoOferecido();

		return array;
	}

	public ArrayList<ReservaDeBilhetes> getLugares(int id) throws SQLException {
		ArrayList<ReservaDeBilhetes> array = new ArrayList<>();

		if (CONCORRENCY_STATE == ConcorrencyLevel.PESSIMIST) {
			array = bilhete.selectLugaresPessimista(id);
		} else {
			if (CONCORRENCY_STATE == ConcorrencyLevel.OPTIMIST) {
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
