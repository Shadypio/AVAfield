/**
 * Questa classe funge da intermediario tra le servlet e gli oggetti DAO
 */

package model.struttura;

import java.util.ArrayList;

public class StrutturaServiceImpl implements StrutturaService{
    private StrutturaDAO strDAO;

    public StrutturaServiceImpl() {
        this.strDAO = new StrutturaDAO();
    }

    public StrutturaServiceImpl(StrutturaDAO strDAO){
        this.strDAO=strDAO;
    }

    /**
     * Invoca il metodo DAO che inserisce la struttura nella base di dati
     * @param s la struttura da inserire nella base di dati
     */
    @Override
    public void inserisciStruttura(Struttura s) {
        strDAO.addStruttura(s);
    }

    /**
     * Invoca il metodo DAO che elimina la struttura dalla base di dati
     * @param s la struttura da eliminare
     */
    @Override
    public void eliminaStruttura(Struttura s) {
        strDAO.deleteById(s.getIdStruttura());
    }

    /**
     * Invoca il metodo DAO che modifica la struttura nella base di dati
     * @param s la struttura da modificare
     */
    @Override
    public void modificaStruttura(Struttura s) {
        strDAO.doChanges(s);
    }

    /**
     * Invoca il metodo DAO che si occupa del recupero di tutte le strutture
     * @return la lista delle strutture
     */
    @Override
    public ArrayList<Struttura> visualizzaStrutture() {
        return strDAO.doRetrieveAll();
    }

    /**
     * Invoca il metodo DAO che si occupa del recupero di una struttura a partire da un identificativo
     * @param id l'identificativo della struttura
     * @return la struttura recuperata
     */
    @Override
    public Struttura trovaStruttura(int id){
        return strDAO.doRetrieveById(id);
    }
}
