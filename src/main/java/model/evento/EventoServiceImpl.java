package model.evento;

import model.evento_utente.EventoUtente;
import model.evento_utente.EventoUtenteDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;
import java.util.ArrayList;

public class EventoServiceImpl implements EventoService{
    @Override
    public void creaEvento(Evento e) {
        EventoDAO eveDAO=new EventoDAO();
        eveDAO.addEvento(e);
    }

    @Override
    public void eliminaEvento(Evento e) {
        EventoDAO eveDAO=new EventoDAO();
        eveDAO.deleteById(e.getIdEvento());
    }

    @Override
    public void modificaEvento(Evento e) {
        EventoDAO eveDAO=new EventoDAO();
        eveDAO.doChanges(e);
    }

    @Override
    public void partecipaEvento(Evento e, Utente u) {
        EventoUtenteDAO euDAO=new EventoUtenteDAO();
        euDAO.addEventoUtente(e,u);
    }

    @Override
    public ArrayList<Evento> visualizzaEventi() {
        EventoDAO eveDAO=new EventoDAO();
        ArrayList<Evento> result=eveDAO.doRetrieveAll();
        return result;
    }

    @Override
    public ArrayList<Utente> findAllUtenti(Evento e) {
        ArrayList<Utente> result=new ArrayList<Utente>();
        EventoUtenteDAO euDAO=new EventoUtenteDAO();
        UtenteDAO uteDAO=new UtenteDAO();
        ArrayList<EventoUtente> parziale= euDAO.doRetrieveUtentiWithIdEvento(e.getIdEvento());
        for (EventoUtente eu:parziale){
            result.add(uteDAO.doRetrieveById(eu.getUtente().getIdUtente()));
        }
        return result;
    }
}
