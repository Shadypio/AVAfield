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

    /**
     * Invoca il metodo DAO che recupera l'utente a partire da
     * email e password
     * @param email l'email dell'utente
     * @param pass la password dell'utente
     * @return l'utente con email e password corretti
     */
    @Override
    public Utente login(String email, String pass) {
        Utente x= uteDAO.doRetrieveUtenteByEmailPassword(email,pass);
        return x;
    }

    /**
     * Invoca il metodo DAO che modifica l'utente nella base di dati
     * @param u l'utente da modificare
     */
    @Override
    public void modificaDati(Utente u) {
        uteDAO.doChanges(u);
    }

    /**
     * Invoca il metodo DAO che modifica l'utente nella base di dati
     * @param u l'utente da modificare
     */
    public void modificaDati2(Utente u) {
        uteDAO.doChangesWithPass(u);
    }

    /**
     * Invoca il metodo DAO che elimina l'utente dalla base di dati
     * @param u l'utente da eliminare
     */
    @Override
    public void cancellazioneAccount(Utente u) {
        uteDAO.deleteById(u.getIdUtente());
    }

    /**
     * Invoca il metodo DAO che si occupa del recupero di tutti gli utenti
     * @return la lista degli utenti
     */
    @Override
    public ArrayList<Utente> visualizzaUtenti() {
        return uteDAO.doRetrieveAll();
    }

    /**
     * Invoca il metodo DAO che inserisce l'utente nella base di dati
     * @param u l'utente da inserire nella base di dati
     */
    @Override
    public void registrazione(Utente u) {
        uteDAO.addUtente(u);
    }

    /**
     * Invoca il metodo DAO che si occupa del recupero di un utente a partire da un identificativo
     * @param id l'identificativo dell'utente
     * @return l'utente recuperato
     */
    @Override
    public Utente trovaUtente (int id){
        return uteDAO.doRetrieveById(id);
    }


    /**
     * Controlla se c'è un utente con la mail specificata
     * @param email la mail da considerare
     * @return l'esito della ricerca
     */
    public Boolean checkEmail (String email){
            ArrayList<Utente> result=uteDAO.doRetrieveAll();
            for (Utente u: result){
                if (u.getEmail().equals(email))
                    return true;
            }
            return false;
    }
    }
