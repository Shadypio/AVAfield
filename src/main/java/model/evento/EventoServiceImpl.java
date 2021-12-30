package model.evento;

import model.evento_utente.EventoUtente;
import model.evento_utente.EventoUtenteDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;
import java.util.ArrayList;

public class EventoServiceImpl implements EventoService{
    private EventoUtenteDAO euDAO=new EventoUtenteDAO();
    private UtenteDAO uteDAO=new UtenteDAO();
    private EventoDAO eveDAO= new EventoDAO();
    @Override
    public void creaEvento(Evento e) {
        eveDAO.addEvento(e);
    }

    @Override
    public void eliminaEvento(Evento e) {
        eveDAO.deleteById(e.getIdEvento());
    }

    @Override
    public void modificaEvento(Evento e) {
        eveDAO.doChanges(e);
    }

    @Override
    public void partecipaEvento(Evento e, Utente u) {
        euDAO.addEventoUtente(e,u);
    }

    @Override
    public ArrayList<Evento> visualizzaEventi() {
        ArrayList<Evento> result=eveDAO.doRetrieveAll();
        return result;
    }

    @Override
    public ArrayList<Utente> findAllUtenti(Evento e) {
        ArrayList<Utente> result=new ArrayList<Utente>();
        ArrayList<EventoUtente> parziale= euDAO.doRetrieveUtentiWithIdEvento(e.getIdEvento());
        for (EventoUtente eu:parziale){
            result.add(uteDAO.doRetrieveById(eu.getUtente().getIdUtente()));
        }
        return result;
    }

    @Override
    public ArrayList<Evento> findAllEventi(Utente e){
        ArrayList<Evento> result=new ArrayList<Evento>();
        ArrayList<EventoUtente> parziale= euDAO.doRetrieveEventiWithIdUtente(e.getIdUtente());
        for (EventoUtente eu: parziale){
            result.add(eveDAO.doRetrieveById(eu.getEvento().getIdEvento()));
        }
        return result;
    }

}
