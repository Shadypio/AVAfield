package moduloFIA;
import model.evento.Evento;
import java.util.Comparator;

public class ComparatorCrescente implements Comparator<Evento> {
    @Override
    public int compare(Evento e1, Evento e2) {
        return Double.compare(e1.getMedia(),e2.getMedia());
    }
}