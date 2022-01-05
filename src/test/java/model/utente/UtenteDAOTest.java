package model.utente;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UtenteDAOTest {

    private UtenteDAO utenteDAO;
    @Before
    public void setUp(){
        utenteDAO=new UtenteDAO();
    }

    @Test
    public void doRetrieveByIdTest(){
        int id=1;
        Utente u=utenteDAO.doRetrieveById(id);
        assertEquals(id,u.getIdUtente());
    }
}