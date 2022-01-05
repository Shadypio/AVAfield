package model.recensione;

import model.struttura.Struttura;
import model.utente.Utente;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RecensioneDAOTest {
    private RecensioneDAO recensioneDAO;
    private RecensioneServiceImpl recensioneService;

    @Before
    public void setUp(){
        recensioneDAO=new RecensioneDAO();
        recensioneService=new RecensioneServiceImpl();
    }

    @Test
    public void doRetrieveByIdTest(){
        int id=1;
        Recensione r=recensioneDAO.doRetrieveById(id);
        assertEquals(id,r.getIdRecensione());
    }

    @Test
    public void addRecensioneTest() {
        Recensione r = new Recensione();
        r.setIdRecensione(recensioneService.visualizzaRecensioni().size()+1);
        r.setTitolo("TitoloDiTest");
        r.setTesto("TestoDiTest");
        r.setNumeroStelle(5);

        Utente u = new Utente();
        u.setIdUtente(1);
        r.setUtente(u);

        Struttura s = new Struttura();
        s.setIdStruttura(1);
        r.setStruttura(s);

        recensioneDAO.addRecensione(r);

        Recensione test = recensioneDAO.doRetrieveById(recensioneService.visualizzaRecensioni().size());
        assertEquals(test.getIdRecensione(), r.getIdRecensione());
        assertEquals(test.getTesto(), r.getTesto());
        assertEquals(test.getTitolo(), r.getTitolo());
        assertEquals(test.getNumeroStelle(), r.getNumeroStelle());
        assertEquals(test.getUtente().getIdUtente(), r.getUtente().getIdUtente());
        assertEquals(test.getStruttura().getIdStruttura(), test.getStruttura().getIdStruttura());
    }

    @Test
    public void doChangesRecensioneTest() {
        Recensione r = new Recensione();
        r.setIdRecensione(recensioneService.visualizzaRecensioni().size());
        r.setTitolo("TitoloDiTest");
        r.setTesto("TestoDiTest");
        r.setNumeroStelle(5);

        Utente u = new Utente();
        u.setIdUtente(1);
        r.setUtente(u);

        Struttura s = new Struttura();
        s.setIdStruttura(1);
        r.setStruttura(s);

        recensioneDAO.doChanges(r);

        Recensione test = recensioneDAO.doRetrieveById(recensioneService.visualizzaRecensioni().size());
        assertEquals(test.getIdRecensione(), r.getIdRecensione());
        assertEquals(test.getTesto(), r.getTesto());
        assertEquals(test.getTitolo(), r.getTitolo());
        assertEquals(test.getNumeroStelle(), r.getNumeroStelle());
        assertEquals(test.getUtente().getIdUtente(), r.getUtente().getIdUtente());
        assertEquals(test.getStruttura().getIdStruttura(), test.getStruttura().getIdStruttura());
    }

    @Test
    public void deleteByIdTest() {
        int id = recensioneService.visualizzaRecensioni().size();
        recensioneDAO.deleteById(id);

        Recensione test = recensioneDAO.doRetrieveById(id);
        assertEquals(test.getIdRecensione(), 0);
        assertNull(test.getTitolo());
        assertNull(test.getTesto());
        assertEquals(test.getNumeroStelle(), 0);
        assertNull(test.getUtente());
        assertNull(test.getStruttura());

    }
}
