/*package model.utente;

import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;


public class UtenteServiceImplTest {

    @Mock
    private UtenteDAO uteDAO;
    private UtenteServiceImpl us;
    @Test
    public void loginTest(){
        String email = "alex.sa4ever@hotmail.it";
        String password = "totti10";
        uteDAO= Mockito.mock(UtenteDAO.class);
        when(uteDAO.doRetrieveUtenteByEmailPassword(email, password)).thenReturn(null);
        us=new UtenteServiceImpl(uteDAO);
        Assertions.assertEquals(null, us.login(email, password));
    }
}
*/