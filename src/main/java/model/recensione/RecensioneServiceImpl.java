package model.recensione;

import model.struttura.Struttura;

import java.util.ArrayList;

public class RecensioneServiceImpl implements RecensioneService{
    @Override
    public void inserisciRecensione(Recensione r) {
        RecensioneDAO recDAO = new RecensioneDAO();
        recDAO.addRecensione(r);
    }

    @Override
    public void eliminaRecensione(Recensione r) {
        RecensioneDAO recDAO = new RecensioneDAO();
        recDAO.deleteById(r.getIdRecensione());
    }

    @Override
    public void modificaRecensione(Recensione r) {
        RecensioneDAO recDAO= new RecensioneDAO();
        recDAO.doChanges(r);
    }

    @Override
    public ArrayList<Recensione> visualizzaRecensioni() {
        RecensioneDAO recDAO= new RecensioneDAO();
        return recDAO.doRetrieveAll();
    }

    @Override
    public ArrayList<Recensione> visualizzaRecensioniByIdStruttura(Struttura s) {
        RecensioneDAO recDAO= new RecensioneDAO();
        return recDAO.doRetrieveRecensioniWithIdStruttura(s);
    }
}
