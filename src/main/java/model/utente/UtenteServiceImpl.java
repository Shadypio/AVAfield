package model.utente;

import java.util.ArrayList;

public class UtenteServiceImpl implements UtenteService{
    @Override
    public Utente login(String email, String pass) {
        UtenteDAO uteDAO=new UtenteDAO();
        Utente x= uteDAO.doRetrieveUtenteByEmailPassword(email,pass);
        return x;
    }

    @Override
    public void modificaDati(Utente u) {
        UtenteDAO uteDAO = new UtenteDAO();
        uteDAO.doChanges(u);
    }

    @Override
    public void cancellazioneAccount(Utente u) {
        UtenteDAO uteDAO = new UtenteDAO();
        uteDAO.deleteById(u.getIdUtente());
    }

    @Override
    public ArrayList<Utente> visualizzaUtenti() {
        UtenteDAO uteDAO = new UtenteDAO();
        return uteDAO.doRetrieveAll();
    }

    @Override
    public void registrazione(Utente u) {
        UtenteDAO uteDAO = new UtenteDAO();
        uteDAO.addUtente(u);
    }
}
