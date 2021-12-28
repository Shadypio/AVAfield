package model.struttura;

import java.util.ArrayList;

public class StrutturaServiceImpl implements StrutturaService{
    @Override
    public void inserisciStruttura(Struttura s) {
        StrutturaDAO strDAO=new StrutturaDAO();
        strDAO.addStruttura(s);
    }

    @Override
    public void eliminaStruttura(Struttura s) {
        StrutturaDAO strDAO=new StrutturaDAO();
        strDAO.deleteById(s.getIdStruttura());
    }

    @Override
    public void modificaStruttura(Struttura s) {
        StrutturaDAO strDAO=new StrutturaDAO();
        strDAO.doChanges(s);
    }

    @Override
    public ArrayList<Struttura> visualizzaStruttura() {
        StrutturaDAO strDAO=new StrutturaDAO();
        return strDAO.doRetrieveAll();
    }
}
