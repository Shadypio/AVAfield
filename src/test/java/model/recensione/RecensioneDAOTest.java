package model.recensione;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecensioneDAOTest {
    private RecensioneDAO recensioneDAO;
    @Before
    public void setUp(){
        recensioneDAO=new RecensioneDAO();
    }

    @Test
    public void doRetrieveByIdTest(){
        int id=1;
        Recensione r=recensioneDAO.doRetrieveById(id);
        assertEquals(id,r.getIdRecensione());
    }
}
