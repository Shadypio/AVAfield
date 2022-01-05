package model.struttura;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StrutturaDAOTest {
    private StrutturaDAO strutturaDAO;
    @Before
    public void setUp(){
        strutturaDAO=new StrutturaDAO();
    }

    @Test
    public void doRetrieveByIdTest(){
        int id=1;
        Struttura s=strutturaDAO.doRetrieveById(id);
        assertEquals(id,s.getIdStruttura());
    }
}
