package moduloFIA;

import model.evento.Evento;

import java.util.ArrayList;
import java.util.HashMap;

public class LinearSearch {
    public ArrayList<Evento> search(ArrayList<Evento> all,int k){
        ArrayList<Evento> result= new ArrayList<>();
        if (all.isEmpty())
            return result;
        Double intornoMin=k-0.5;
        Double intornoMax=k+0.5;
        int i=0;
        while(i<all.size()){
            //if(all.get(i).getMedia() <= intornoMax || all.get(i).getMedia())
                result.add(all.get(i));
            i++;
        }
        return result;
    }


}
