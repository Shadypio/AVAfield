/**
 * Questa classe funge da intermediario tra le servlet e gli oggetti DAO
 */

package model.evento;

import model.evento_utente.EventoUtente;
import model.evento_utente.EventoUtenteDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;
import java.util.ArrayList;

public class EventoServiceImpl implements EventoService{
    private EventoUtenteDAO euDAO;
    private UtenteDAO uteDAO;
    private EventoDAO eveDAO;

    public EventoServiceImpl(){
        euDAO=new EventoUtenteDAO();
        uteDAO=new UtenteDAO();
        eveDAO=new EventoDAO();
    }

    public EventoServiceImpl(EventoDAO eveDAO){
        this.eveDAO=eveDAO;
        euDAO=new EventoUtenteDAO();
        uteDAO=new UtenteDAO();
    }

    public EventoServiceImpl(EventoDAO eveDAO, UtenteDAO uteDAO){
        this.eveDAO=eveDAO;
        euDAO=new EventoUtenteDAO();
        this.uteDAO=uteDAO;
    }

    public EventoServiceImpl(EventoDAO eveDAO, UtenteDAO uteDAO, EventoUtenteDAO euDAO){
        this.eveDAO=eveDAO;
        this.euDAO=euDAO;
        this.uteDAO=uteDAO;
    }

    /**
     * Invoca il metodo DAO che inserisce l'evento nella base di dati
     * @param e l'evento da inserire nella base di dati
     */
    @Override
    public void creaEvento(Evento e) {
        eveDAO.addEvento(e);
    }

    /**
     * Invoca il metodo DAO che elimina l'evento dalla base di dati
     * @param e l'evento da eliminare
     */
    @Override
    public void eliminaEvento(Evento e) {
        eveDAO.deleteById(e.getIdEvento());
    }

    /**
     * Invoca il metodo DAO che modifica l'evento nella base di dati
     * @param e l'evento da modificare
     */
    @Override
    public void modificaEvento(Evento e) {
        eveDAO.doChanges(e);
    }

    /**
     * Invoca il metodo DAO che associa ad un utente la partecipazione ad un evento
     * @param e l'evento a cui l'utente partecipa
     * @param u l'utente che partecipa
     */
    @Override
    public void partecipaEvento(Evento e, Utente u) {
        euDAO.addEventoUtente(e,u);
    }

    /**
     * Invoca il metodo DAO che si occupa del recupero di tutti gli eventi
     * @return la lista degli eventi
     */
    @Override
    public ArrayList<Evento> visualizzaEventi() {
        ArrayList<Evento> result=eveDAO.doRetrieveAll();
        for (Evento e:result){
            ArrayList<Utente> partecipanti=this.findAllUtenti(e);
            e.setListaUtenti(partecipanti);
        }
        return result;
    }

    /**
     * Trova tutti gli utenti a partire da un evento
     * @param e l'evento di cui si vogliono i partecipanti
     * @return la lista di utenti che partecipano all'evento
     */
    @Override
    public ArrayList<Utente> findAllUtenti(Evento e) {
        ArrayList<Utente> result=new ArrayList<Utente>();
        ArrayList<EventoUtente> parziale= euDAO.doRetrieveUtentiWithIdEvento(e.getIdEvento());
        for (EventoUtente eu:parziale){
            result.add(uteDAO.doRetrieveById(eu.getUtente().getIdUtente()));
        }
        return result;
    }

    /**
     * Trova tutti gli eventi a partire da un utente
     * @param e l'utente che partecipa agli eventi
     * @return la lista di eventi a cui partecipa l'utente
     */
    @Override
    public ArrayList<Evento> findAllEventi(Utente e){
        ArrayList<Evento> result=new ArrayList<Evento>();
        ArrayList<EventoUtente> parziale= euDAO.doRetrieveEventiWithIdUtente(e.getIdUtente());
        for (EventoUtente eu: parziale){
            result.add(eveDAO.doRetrieveById(eu.getEvento().getIdEvento()));
        }
        return result;
    }

    /**
     * Invoca il metodo DAO che si occupa del recupero di un evento a partire da un identificativo
     * @param id l'identificativo dell'evento
     * @return l'evento recuperato
     */
    @Override
    public Evento trovaEvento(int id){
        return eveDAO.doRetrieveById(id);
    }


    public Double calcolaMedia(Evento e){
        Double media,somma = 0.0;
        ArrayList<Utente> partecipanti=this.findAllUtenti(e);
        for (Utente u:partecipanti){
            somma+=u.getAutovalutazione();
        }
        media=somma/partecipanti.size();
        return media;
    }


}
