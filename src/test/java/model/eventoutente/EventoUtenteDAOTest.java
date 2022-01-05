package model.eventoutente;

import model.evento.Evento;
import model.evento.EventoDAO;
import model.evento.EventoServiceImpl;
import model.evento_utente.EventoUtente;
import model.evento_utente.EventoUtenteDAO;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EventoUtenteDAOTest {
    private EventoUtenteDAO eventoUtenteDAO;

    @Before
    public void setUp(){
        eventoUtenteDAO= new EventoUtenteDAO();
    }

    @Test
    public void addEventoUtenteByIdUtenteTest() {
        Evento e=new Evento();
        Utente u=new Utente();

        e.setIdEvento(1);
        u.setIdUtente(1);
        eventoUtenteDAO.addEventoUtente(e,u);

        ArrayList<EventoUtente> result=eventoUtenteDAO.doRetrieveEventiWithIdUtente(1);
        for(EventoUtente eu: result){
            assertEquals(eu.getEvento().getIdEvento(),e.getIdEvento());
        }
    }
    @Test
    public void addEventoUtenteByIdEventoTest() {
        Evento e=new Evento();
        Utente u=new Utente();

        e.setIdEvento(2);
        u.setIdUtente(2);
        eventoUtenteDAO.addEventoUtente(e,u);

        ArrayList<EventoUtente> result=eventoUtenteDAO.doRetrieveUtentiWithIdEvento(1);
        for(EventoUtente eu: result){
            assertEquals(eu.getUtente().getIdUtente(),u.getIdUtente());
        }
    }
}
