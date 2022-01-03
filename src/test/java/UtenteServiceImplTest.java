

import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteServiceImpl;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UtenteServiceImplTest {
    private UtenteServiceImpl us = new UtenteServiceImpl();
    private UtenteDAO uteDAO = new UtenteDAO();

    @Test
    public void loginUtente() {
        String email = "alex.sa4ever@hotmail.it";
        String password = "totti10";
        Utente ute=new Utente();
        when(uteDAO.doRetrieveUtenteByEmailPassword(email, password)).thenReturn(ute);
        System.out.println(ute+" "+us.login(email,password));
        assertEquals(ute, us.login(email, password));
    }
}
