package moduloFIA;
import model.evento.Evento;
import java.util.Comparator;

public class ComparatorDecrescente implements Comparator<Evento> {
    @Override
    public int compare(Evento e1, Evento e2) {
        if (e1.getMedia()>=e2.getMedia())
            return -1;
        else
            return 1;
    }
}
