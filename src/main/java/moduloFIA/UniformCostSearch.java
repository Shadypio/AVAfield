package moduloFIA;

import model.evento.Evento;
import java.util.ArrayList;
import java.util.Collections;


public class UniformCostSearch {
    public ArrayList<Evento> search(ArrayList<Evento> all,int k){
        ArrayList<Evento> result= new ArrayList<>();
        if (all.isEmpty())
            return result;
        if (k>2.5)
            Collections.sort(all,new ComparatorDecrescente());
        else
            Collections.sort(all,new ComparatorCrescente());
        Double intornoMin=k-0.5;
        Double intornoMax=k+0.5;
        int i=0;
        while(all.get(i).getMedia()>=intornoMin && all.get(i).getMedia()<=intornoMax){
            result.add(all.get(i));
            i++;
        }
        return result;
    }
}
