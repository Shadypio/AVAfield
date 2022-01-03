


import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UtenteServiceImplTest {
    @InjectMocks
    private UtenteServiceImpl us;
    @Mock
    private DataSource ds;
    @Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;


    @Mock
    private UtenteDAO uteDAO;
    @Mock
    private Utente ute;

    @Before
    public void setUp() throws Exception {
        Assertions.assertNotNull(ds);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(ds.getConnection()).thenReturn(c);
    }

    @Test
    public void createUtente() {
        assertEquals(uteDAO.addUtente(ute),true);
    }

    @Test
    public void login(){
        String email = "alex.sa4ever@hotmail.it";
        String password = "totti10";
        when(uteDAO.doRetrieveUtenteByEmailPassword(email, password)).thenReturn(ute);
        System.out.println(ute+" "+us.login(email,password));
        Assertions.assertEquals(ute, us.login(email, password));
    }
}
