/**
 * Questa classe modella la partecipazione di un utnte a un evento.
 */

package model.evento_utente;

import model.evento.Evento;
import model.utente.Utente;

public class EventoUtente {
    public EventoUtente(){ }
    public EventoUtente(Utente utente, Evento evento) {
        this.utente = utente;
        this.evento = evento;
    }

    public Utente getUtente() {
        return utente;
    }
    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "EventoUtente{" +
                "utente=" + utente +
                ", evento=" + evento +
                '}';
    }

    private Utente utente;
    private Evento evento;
}
