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

    /**
     * Invoca il metodo DAO che inserisce la recensione nella base di dati
     * @param r la recensione da inserire nella base di dati
     */
    @Override
    public void inserisciRecensione(Recensione r) {
        recDAO.addRecensione(r);
    }

    /**
     * Invoca il metodo DAO che elimina la recensione dalla base di dati
     * @param r la recensione da eliminare
     */
    @Override
    public void eliminaRecensione(Recensione r) {
        recDAO.deleteById(r.getIdRecensione());
    }

    /**
     * Invoca il metodo DAO che modifica la recensione nella base di dati
     * @param r la recensione da modificare
     */
    @Override
    public void modificaRecensione(Recensione r) {
        recDAO.doChanges(r);
    }

    /**
     * Invoca il metodo DAO che si occupa del recupero di tutte le recensioni
     * @return la lista delle recensioni
     */
    @Override
    public ArrayList<Recensione> visualizzaRecensioni() {
        return recDAO.doRetrieveAll();
    }

    /**
     * Trova tutte le recensioni a partire da una struttura
     * @param s la struttura di cui si desidera conoscere le recensioni
     * @return la lista di recensioni della struttura
     */
    @Override
    public ArrayList<Recensione> visualizzaRecensioniByIdStruttura(Struttura s) {
        return recDAO.doRetrieveRecensioniWithIdStruttura(s);
    }
}
