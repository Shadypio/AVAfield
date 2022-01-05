package model.evento;


import model.struttura.Struttura;
import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import java.sql.Time;
import static org.junit.Assert.assertEquals;

public class EventoDAOTest {

    private EventoDAO eventoDAO;
    @Before
    public void setUp(){
        eventoDAO=new EventoDAO();
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
        e.setIdEvento(100);
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

        Evento test= eventoDAO.doRetrieveById(100);
        assertEquals(test.getIdEvento(),e.getIdEvento());
        assertEquals(test.getNome(),e.getNome());
        assertEquals(test.getNumeroPartecipanti(),e.getNumeroPartecipanti());
        assertEquals(test.getDataEvento(),e.getDataEvento());
        assertEquals(test.getOrario(),e.getOrario());
        assertEquals(test.getStruttura().getIdStruttura(),e.getStruttura().getIdStruttura());
    }
}
