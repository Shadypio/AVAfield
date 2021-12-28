package model.evento;

import model.utente.Utente;

import java.util.ArrayList;

public interface EventoService {
    void creaEvento(Evento e);
    void eliminaEvento(Evento e);
    void modificaEvento(Evento e);
    void partecipaEvento(Evento e, Utente u);
    ArrayList<Evento> visualizzaEventi();
    ArrayList<Utente> findAllUtenti(Evento e);
}
