package moduloFIA;

import model.evento.Evento;
import java.util.ArrayList;

public class UniformCostSearch {
    public ArrayList<Evento> search(ArrayList<Evento> all,int k){
        ArrayList<Evento> result= new ArrayList<>();
        if (all.isEmpty())
            return result;
        MergeSort.mergeSort(all,1,all.size(),k);
        Double intornoMin=k-0.5;
        Double intornoMax=k+0.5;
        int i=0;
        /*while(all.get(i).getMedia()>=intornoMin){
            if(all.get(i).getMedia() <= intornoMax || all.get(i).getMedia())
            result.add(all.get(i));
            i++;
        }*/
        return result;

    }
}
