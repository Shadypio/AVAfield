package model.struttura;

import java.util.ArrayList;

public interface StrutturaService {
    void inserisciStruttura(Struttura s);
    void eliminaStruttura(Struttura s);
    void modificaStruttura(Struttura s);
    ArrayList<Struttura> visualizzaStruttura();
}
