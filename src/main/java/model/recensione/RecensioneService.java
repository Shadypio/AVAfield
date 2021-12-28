package model.recensione;

import model.struttura.Struttura;

import java.util.ArrayList;

public interface RecensioneService {
    void inserisciRecensione(Recensione r);
    void eliminaRecensione(Recensione r);
    void modificaRecensione(Recensione r);
    ArrayList<Recensione> visualizzaRecensioni();
    ArrayList<Recensione> visualizzaRecensioniByIdStruttura(Struttura s);
}
