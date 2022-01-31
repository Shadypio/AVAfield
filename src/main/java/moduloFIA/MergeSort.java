package moduloFIA;

import model.evento.Evento;
import java.util.ArrayList;

public class MergeSort {
    public static void mergeSort(ArrayList<Evento> all,int left,int right,int k){
        if (all.size()>1){
            int m=left+(right-1)/2;
            mergeSort(all,left,m,k);
            mergeSort(all,m,right,k);
            merge(all,left,m,right,k);
        }
    }

    public static void merge(ArrayList<Evento> all, int left, int m, int right, int k) {
        ArrayList<Evento> mergedSortedArray = new ArrayList<Evento>();
        int leftIndex = left;
        int rightIndex =  m+1;

        while(leftIndex<=m && rightIndex<=right){
            if(all.get(leftIndex).getNumeroPartecipanti()<=all.get(rightIndex).getNumeroPartecipanti()){
                mergedSortedArray.add(all.get(leftIndex));
                leftIndex++;
            }else{
                mergedSortedArray.add(all.get(rightIndex));
                rightIndex++;
            }
        }

        while(leftIndex<=m){
            mergedSortedArray.add(all.get(leftIndex));
            leftIndex++;
        }

        while(rightIndex<=right){
            mergedSortedArray.add(all.get(rightIndex));
            rightIndex++;
        }

    }

}

