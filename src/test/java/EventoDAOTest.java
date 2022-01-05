import model.evento.Evento;
import model.evento.EventoDAO;
import org.junit.Before;
import org.junit.Test;

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
}
