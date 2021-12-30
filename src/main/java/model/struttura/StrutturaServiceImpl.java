package model.struttura;

import java.util.ArrayList;

public class StrutturaServiceImpl implements StrutturaService{
    private StrutturaDAO strDAO=new StrutturaDAO();
    @Override
    public void inserisciStruttura(Struttura s) {
        strDAO.addStruttura(s);
    }

    @Override
    public void eliminaStruttura(Struttura s) {
        strDAO.deleteById(s.getIdStruttura());
    }

    @Override
    public void modificaStruttura(Struttura s) {
        strDAO.doChanges(s);
    }

    @Override
    public ArrayList<Struttura> visualizzaStrutture() {
        return strDAO.doRetrieveAll();
    }

    @Override
    public Struttura trovaStruttura(int id){
        return strDAO.doRetrieveById(id);
    }
}
