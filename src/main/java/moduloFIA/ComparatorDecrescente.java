package moduloFIA;
import model.evento.Evento;
import java.util.Comparator;

public class ComparatorDecrescente implements Comparator<Evento> {
    @Override
    public int compare(Evento e1, Evento e2) {
        if (Double.compare(e1.getMedia(), e2.getMedia())==0)
            return 0;
        if (Double.compare(e1.getMedia(), e2.getMedia())==-1)
            return 1;
        else
            return -1;
    }
}
