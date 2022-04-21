package oblig6;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class topSort extends enkelGraf{

    public topSort(String filNavn) {
        super(filNavn);
    }

    public void findAndPrint(){

        int inngrad[] = new int[n];

        for (int i = 0; i < n; i++) {
            inngrad[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && nabo[i][j]){
                    inngrad[j]++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (inngrad[i] == 0){
                queue.add(i);
            }
        }


        while (!queue.isEmpty()){

            int a = queue.remove();
            System.out.print(data[a] + ", ");

            for (int i = 0; i < n; i++) {
                if (a != i && nabo[a][i] && inngrad[i] != 0){
                    inngrad[i]--;
                    if (inngrad[i] == 0){
                        queue.add(i);
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        String filNavn = " ";
        try
        {
            if (args.length != 1)
                throw new IOException("Mangler filnavn");
            filNavn = args[0];
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }

        // Oppretter ny graf
        topSort G = new topSort(filNavn);
        
        G.findAndPrint();
    }
}
