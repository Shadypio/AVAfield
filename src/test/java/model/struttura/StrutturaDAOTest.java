package model.struttura;

import model.evento.Evento;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StrutturaDAOTest {
    private StrutturaDAO strutturaDAO;
    private StrutturaServiceImpl strutturaService;

    @Before
    public void setUp(){
        strutturaDAO=new StrutturaDAO();
        strutturaService=new StrutturaServiceImpl();
    }

    @Test
    public void doRetrieveByIdTest(){
        int id=1;
        Struttura s=strutturaDAO.doRetrieveById(id);
        assertEquals(id,s.getIdStruttura());
    }

    @Test
    public void addStrutturaTest(){
        Struttura s=new Struttura();
        s.setIdStruttura(strutturaService.visualizzaStrutture().size()+1);
        s.setNome("Struttura");
        s.setIndirizzo("via roma");
        s.setTelefono("089331029");
        s.setDescrizione("descrizione");
        s.setCapienza(100);
        s.setNumeroSpogliatoi(2);
        s.setParcheggio(true);
        s.setCategoria("Calcio");
        strutturaDAO.addStruttura(s);

        Struttura test=strutturaDAO.doRetrieveById(strutturaService.visualizzaStrutture().size());
        assertEquals(test.getIdStruttura(),s.getIdStruttura());
        assertEquals(test.getNome(),s.getNome());
        assertEquals(test.getIndirizzo(),s.getIndirizzo());
        assertEquals(test.getTelefono(),s.getTelefono());
        assertEquals(test.getDescrizione(),s.getDescrizione());
        assertEquals(test.getCapienza(),s.getCapienza());
        assertEquals(test.getNumeroSpogliatoi(),s.getNumeroSpogliatoi());
        assertEquals(test.isParcheggio(),s.isParcheggio());
        assertEquals(test.getCategoria(),s.getCategoria());
    }

    @Test
    public void doChangesTest(){
        Struttura s=new Struttura();
        s.setIdStruttura(strutturaService.visualizzaStrutture().size());
        s.setNome("Strutturona");
        s.setIndirizzo("via roma");
        s.setTelefono("089331029");
        s.setDescrizione("descrizione");
        s.setCapienza(100);
        s.setNumeroSpogliatoi(2);
        s.setParcheggio(true);
        s.setCategoria("Calcio");
        strutturaDAO.doChanges(s);

        Struttura test=strutturaDAO.doRetrieveById(strutturaService.visualizzaStrutture().size());
        assertEquals(test.getIdStruttura(),s.getIdStruttura());
        assertEquals(test.getNome(),s.getNome());
        assertEquals(test.getIndirizzo(),s.getIndirizzo());
        assertEquals(test.getTelefono(),s.getTelefono());
        assertEquals(test.getDescrizione(),s.getDescrizione());
        assertEquals(test.getCapienza(),s.getCapienza());
        assertEquals(test.getNumeroSpogliatoi(),s.getNumeroSpogliatoi());
        assertEquals(test.isParcheggio(),s.isParcheggio());
        assertEquals(test.getCategoria(),s.getCategoria());
    }

    @Test
    public void deleteStrutturaTest(){
        int id=strutturaService.visualizzaStrutture().size();
        strutturaDAO.deleteById(id);

        Struttura test= strutturaDAO.doRetrieveById(id);
        assertNull(test.getNome());
        assertNull(test.getDescrizione());
        assertNull(test.getCategoria());
        assertNull(test.getTelefono());
        assertNull(test.getIndirizzo());
        assertEquals(0,test.getCapienza());
        assertEquals(0,test.getIdStruttura());
        assertEquals(0,test.getNumeroSpogliatoi());
    }


}
