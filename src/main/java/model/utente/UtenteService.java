package model.utente;

import java.util.ArrayList;

public interface UtenteService {
    Utente login(String email,String pass);
    void modificaDati(Utente u);
    void cancellazioneAccount(Utente u);
    ArrayList<Utente> visualizzaUtenti();
    void registrazione(Utente u);
    Utente trovaUtente (int id);
}
