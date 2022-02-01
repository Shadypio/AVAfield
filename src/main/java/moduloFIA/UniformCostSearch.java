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
        System.out.println("Ricerca a Costo Uniforme:\nIntorno Max: "+intornoMax+" Intorno Min: "+intornoMin);
        boolean outRange=false;
        while(i<all.size() && !outRange){
            if (all.get(i).getMedia()>=intornoMin && all.get(i).getMedia()<=intornoMax)
                result.add(all.get(i));
            if (all.get(i).getMedia()<intornoMin && k>2.5)
                outRange=true;
            if (all.get(i).getMedia()>intornoMax && k<2.5)
                outRange=true;
            i++;
        }
        System.out.println("Numero di Eventi trovati:"+result.size());
        System.out.println("Numero di Iterazioni effettuate:"+i);
        return result;
    }
}
