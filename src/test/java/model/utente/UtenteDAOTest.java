package model.utente;


import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UtenteDAOTest {

    private UtenteDAO utenteDAO;
    private UtenteServiceImpl utenteService;

    @Before
    public void setUp(){
        utenteDAO=new UtenteDAO();
        utenteService = new UtenteServiceImpl();
    }

    @Test
    public void doRetrieveByIdTest(){
        int id=1;
        Utente u=utenteDAO.doRetrieveById(id);
        assertEquals(id,u.getIdUtente());
    }

    @Test
    public void addUtenteTest() {
        Utente u = new Utente();
        u.setIdUtente(utenteService.visualizzaUtenti().size()+1);
        u.setNome("NomeTest");
        u.setCognome("CognomeTest");
        u.setUsername("UsernameTest");
        u.setEmail("EmailTest");
        u.setPassword("PasswordTest");
        u.setAdmin(false);
        u.setNumeroTelefono("1234567890");
        u.setAutovalutazione(4);

        utenteDAO.addUtente(u);

        Utente test = utenteDAO.doRetrieveById(utenteService.visualizzaUtenti().size());
        assertEquals(test.getIdUtente(), u.getIdUtente());
        assertEquals(test.getNome(), u.getNome());
        assertEquals(test.getCognome(), u.getCognome());
        assertEquals(test.getEmail(), u.getEmail());
        assertEquals(test.getUsername(), u.getUsername());
        //assertEquals(test.getPassword(), u.getPassword());
        assertEquals(test.isAdmin(), u.isAdmin());
        assertEquals(test.getNumeroTelefono(), u.getNumeroTelefono());
        assertEquals(test.getAutovalutazione(), u.getAutovalutazione());
    }

    @Test
    public void doChangesUtenteTest() {
        Utente u = new Utente();
        u.setIdUtente(utenteService.visualizzaUtenti().size());
        u.setNome("NomeTest");
        u.setCognome("CognomeTest");
        u.setUsername("UsernameTest");
        u.setEmail("EmailTest");
        u.setPassword("PasswordTest");
        u.setAdmin(false);
        u.setNumeroTelefono("1234567890");
        u.setAutovalutazione(4);

        utenteDAO.doChanges(u);

        Utente test = utenteDAO.doRetrieveById(utenteService.visualizzaUtenti().size());
        assertEquals(test.getIdUtente(), u.getIdUtente());
        assertEquals(test.getNome(), u.getNome());
        assertEquals(test.getCognome(), u.getCognome());
        assertEquals(test.getEmail(), u.getEmail());
        assertEquals(test.getUsername(), u.getUsername());
        //assertEquals(test.getPassword(), u.getPassword());
        assertEquals(test.isAdmin(), u.isAdmin());
        assertEquals(test.getNumeroTelefono(), u.getNumeroTelefono());
        assertEquals(test.getAutovalutazione(), u.getAutovalutazione());
    }

    @Test
    public void deleteByIdTest() {
        int id = utenteService.visualizzaUtenti().size();
        utenteDAO.deleteById(id);

        Utente test = utenteDAO.doRetrieveById(id);
        assertEquals(test.getIdUtente(), 0);
        assertNull(test.getNome());
        assertNull(test.getCognome());
        assertNull(test.getEmail());
        assertNull(test.getPassword());
        assertNull(test.getUsername());
        assertEquals(test.isAdmin(), false);
        assertNull(test.getNumeroTelefono());
        assertEquals(test.getAutovalutazione(), 0);
    }

    @Test
    public void doRetrieveByEmailPassTest() throws NoSuchAlgorithmException {
        String email="minaadmin80@gmail.com";
        String pass="admin";
        Utente u=utenteDAO.doRetrieveUtenteByEmailPassword(email,pass);
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(pass.getBytes(StandardCharsets.UTF_8));
        pass = String.format("%040x", new BigInteger(1, digest.digest()));
        assertEquals(u.getEmail(),email);
        assertEquals(u.getPassword(),pass);
    }
}