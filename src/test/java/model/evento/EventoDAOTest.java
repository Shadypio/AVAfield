package model.evento;



import model.struttura.Struttura;
import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EventoDAOTest {
    private EventoDAO eventoDAO;
    private EventoServiceImpl eventoService;

    @Before
    public void setUp(){
        eventoDAO=new EventoDAO();
        eventoService=new EventoServiceImpl();
    }

    @Test
    public void doRetrieveByIdTest(){
        int id=1;
        Evento e=eventoDAO.doRetrieveById(id);
        assertEquals(id,e.getIdEvento());
    }

    @Test
    public void addEventoTest() {
        Evento e=new Evento();
        e.setIdEvento(eventoService.visualizzaEventi().size()+1);
        e.setNome("Nuovo Evento");
        e.setNumeroPartecipanti(12);
        Date data=Date.valueOf("2021-12-25");
        e.setDataEvento(data);
        Time t=new Time(10,10,10);
        e.setOrario(t);
        Struttura s=new Struttura();
        s.setIdStruttura(1);
        e.setStruttura(s);
        eventoDAO.addEvento(e);

        Evento test= eventoDAO.doRetrieveById(eventoService.visualizzaEventi().size());
        assertEquals(test.getIdEvento(),e.getIdEvento());
        assertEquals(test.getNome(),e.getNome());
        assertEquals(test.getNumeroPartecipanti(),e.getNumeroPartecipanti());
        assertEquals(test.getDataEvento(),e.getDataEvento());
        assertEquals(test.getOrario(),e.getOrario());
        assertEquals(test.getStruttura().getIdStruttura(),e.getStruttura().getIdStruttura());
    }

    @Test
    public void doChangesTest(){
        Evento e=new Evento();
        e.setIdEvento(eventoService.visualizzaEventi().size());
        e.setNome("Nuovo Eventissimo");
        e.setNumeroPartecipanti(12);
        Date data=Date.valueOf("2021-12-25");
        e.setDataEvento(data);
        Time t=new Time(10,10,10);
        e.setOrario(t);
        Struttura s=new Struttura();
        s.setIdStruttura(1);
        e.setStruttura(s);
        eventoDAO.doChanges(e);

        Evento test= eventoDAO.doRetrieveById(eventoService.visualizzaEventi().size());
        assertEquals(test.getIdEvento(),e.getIdEvento());
        assertEquals(test.getNome(),e.getNome());
        assertEquals(test.getNumeroPartecipanti(),e.getNumeroPartecipanti());
        assertEquals(test.getDataEvento(),e.getDataEvento());
        assertEquals(test.getOrario(),e.getOrario());
        assertEquals(test.getStruttura().getIdStruttura(),e.getStruttura().getIdStruttura());
    }

    @Test
    public void deleteEventoTest(){
        int id=eventoService.visualizzaEventi().size();
        eventoDAO.deleteById(id);

        Evento test= eventoDAO.doRetrieveById(id);
        assertEquals(0,test.getIdEvento());
        assertNull(test.getNome());
        assertEquals(0,test.getNumeroPartecipanti());
        assertNull(test.getDataEvento());
        assertNull(test.getOrario());
        assertNull(test.getStruttura());
    }

    @Test
    public void doRetrieveByIdStrutturaTest(){
        int id=1;
        ArrayList<Evento> result = eventoDAO.doRetrieveByIdStruttura(1);
        for(Evento e: result){
            assertEquals(e.getStruttura().getIdStruttura(),id);
        }
    }


}
