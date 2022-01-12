/**
 * Questa classe funge da intermediario tra le servlet e gli oggetti DAO
 */

package model.utente;

import java.util.ArrayList;

public class UtenteServiceImpl implements UtenteService{
    private UtenteDAO uteDAO;

    public UtenteServiceImpl(UtenteDAO uteDAO) {
        this.uteDAO = uteDAO;
    }
    public UtenteServiceImpl(){
        this.uteDAO=new UtenteDAO();
    }

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

    public Boolean checkEmail (String email){
            ArrayList<Utente> result=uteDAO.doRetrieveAll();
            for (Utente u: result){
                if (u.getEmail().equals(email))
                    return true;
            }
            return false;
    }
    }
