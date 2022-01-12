/**
 * Questa classe funge da intermediario tra le servlet e gli oggetti DAO
 */

package model.recensione;

import model.struttura.Struttura;

import java.util.ArrayList;

public class RecensioneServiceImpl implements RecensioneService{
    private RecensioneDAO recDAO;

    public RecensioneServiceImpl(){
        this.recDAO=new RecensioneDAO();
    }

    public RecensioneServiceImpl(RecensioneDAO recDAO){
        this.recDAO=recDAO;
    }

    @Override
    public void inserisciRecensione(Recensione r) {
        recDAO.addRecensione(r);
    }

    @Override
    public void eliminaRecensione(Recensione r) {
        recDAO.deleteById(r.getIdRecensione());
    }

    @Override
    public void modificaRecensione(Recensione r) {
        recDAO.doChanges(r);
    }

    @Override
    public ArrayList<Recensione> visualizzaRecensioni() {
        return recDAO.doRetrieveAll();
    }

    @Override
    public ArrayList<Recensione> visualizzaRecensioniByIdStruttura(Struttura s) {
        return recDAO.doRetrieveRecensioniWithIdStruttura(s);
    }
}
