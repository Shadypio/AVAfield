package moduloFIA;

import model.evento.Evento;
import java.util.ArrayList;

public class LinearSearch {
    public ArrayList<Evento> search(ArrayList<Evento> all,int k){
        ArrayList<Evento> result= new ArrayList<>();
        if (all.isEmpty())
            return result;
        Double intornoMin=k-0.5;
        Double intornoMax=k+0.5;
        int i=0;
        System.out.println("Ricerca Lineare:\nIntorno Max: "+intornoMax+" Intorno Min: "+intornoMin);
        while(i<all.size()){
            if(all.get(i).getMedia()>=intornoMin && all.get(i).getMedia()<=intornoMax)
                result.add(all.get(i));
            i++;
        }
        System.out.println("Numero di Eventi trovati:"+result.size());
        System.out.println("Numero di Iterazioni effettuate:"+i);
        return result;
    }
}