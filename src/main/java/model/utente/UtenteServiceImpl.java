package model.utente;

import java.util.ArrayList;

public class UtenteServiceImpl implements UtenteService{
    private UtenteDAO uteDAO = new UtenteDAO();
    @Override
    public Utente login(String email, String pass) {
        Utente x= uteDAO.doRetrieveUtenteByEmailPassword(email,pass);
        return x;
    }

    @Override
    public void modificaDati(Utente u) {
        uteDAO.doChanges(u);
    }

    public void modificaDati2(Utente u) {
        uteDAO.doChangesWithPass(u);
    }

    @Override
    public void cancellazioneAccount(Utente u) {
        uteDAO.deleteById(u.getIdUtente());
    }

    @Override
    public ArrayList<Utente> visualizzaUtenti() {
        return uteDAO.doRetrieveAll();
    }

    @Override
    public void registrazione(Utente u) {
        uteDAO.addUtente(u);
    }

    @Override
    public Utente trovaUtente (int id){
        return uteDAO.doRetrieveById(id);
    }
}
