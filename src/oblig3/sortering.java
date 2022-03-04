package oblig3;

import java.util.*;
import java.io.*;

public class sortering {

    public sortering(int metode, int n, int z) {
        long tid = 0;

        if(z == 1) {
            if (metode == 1) {
                tid = System.currentTimeMillis();
                instikkSortering(randomTall(n));
                tid = System.currentTimeMillis() - tid;
                System.out.printf("Instikksortering tid: %2.3f", tid / 1000.0);
            }
            if (metode == 2) {
                tid = System.currentTimeMillis();
                quickSort(randomTall(n), 0, n - 1);
                tid = System.currentTimeMillis() - tid;
                System.out.printf("Quicksort tid: %2.3f", tid / 1000.0);
            }
            if (metode == 3) {
                tid = System.currentTimeMillis();
                fletteSortering(randomTall(n), 0, n - 1);
                tid = System.currentTimeMillis() - tid;
                System.out.printf("Flettesortering tid: %2.3f", tid / 1000.0);
            }
            if (metode == 4) {
                tid = System.currentTimeMillis();
                radixSortering(randomTall(n));
                tid = System.currentTimeMillis() - tid;
                System.out.printf("Radixsortering tid: %2.3f", tid / 1000.0);
            }
        }

        if(z == 2){
            if (metode == 1) {
                tid = System.currentTimeMillis();
                instikkSortering(randomTall(n));
                tid = System.currentTimeMillis() - tid;

                System.out.println(" n       Tid(ms) Best       Worst");
                System.out.printf("%6d %6d %6.4e %6.4e", n, tid, (float)tid/((float)n), (float)tid/((float)n*n));
            }
            if (metode == 2) {
                tid = System.currentTimeMillis();
                quickSort(randomTall(n), 0, n - 1);
                tid = System.currentTimeMillis() - tid;

                System.out.println(" n       Tid(ms) Best       Worst");
                System.out.printf("%6d %6d %6.4e %6.4e", n, tid, (float)tid/((float)n*Math.log(n)), (float)tid/((float)n*n));
            }
            if (metode == 3) {
                tid = System.currentTimeMillis();
                fletteSortering(randomTall(n), 0, n - 1);
                tid = System.currentTimeMillis() - tid;

                System.out.println(" n       Tid(ms) Best       Worst");
                System.out.printf("%6d %6d %6.4e %6.4e", n, tid, (float)tid/((float)n*Math.log(n)), (float)tid/((float)n*Math.log(n)));
            }
            if (metode == 4) {
                tid = System.currentTimeMillis();
                radixSortering(randomTall(n));
                tid = System.currentTimeMillis() - tid;

                System.out.println(" n       Tid(ms) Best       Worst");
                System.out.printf("%6d %6d %6.4e %6.4e", n, tid, (float)tid/((float)n), (float)tid/((float)n*n));
            }
        }
    }

    public static void instikkSortering(int tall[]){
        //System.out.println(Arrays.toString(tall));
        int key;

        for (int i = 1; i < tall.length; i++) {
            key = tall[i];
            int j = i;

            while (j > 0 && tall[j-1] > key){
                tall[j] = tall[j-1];
                j--;
            }
            tall[j] = key;
        }
        //System.out.println(Arrays.toString(tall));
    }

    public static void quickSort(int tall[], int min, int max){
        //System.out.println(Arrays.toString(tall));
        int index;

        if (max - min > 0) {
            index = finnInndeling(tall, min, max);
            quickSort(tall, min, index - 1);
            quickSort(tall, index + 1, max);
        }
        //System.out.println(Arrays.toString(tall));
    }

    public static void fletteSortering(int tall[], int min, int max){
        //System.out.println(Arrays.toString(tall));
        if (min == max){
            return;
        }
        int temp[];
        int index1, venstre, hoyre;
        int storrelse = max - min + 1;
        int mid = (min + max)/2;
        temp = new int[storrelse];

        fletteSortering(tall, min, mid);
        fletteSortering(tall, mid + 1, max);

        for (index1 = 0; index1 < storrelse; index1++) {
            temp[index1] = tall[min + index1];
        }

        venstre = 0;
        hoyre = mid - min + 1;
        for (index1 = 0; index1 < storrelse; index1++) {
            if (hoyre <= max - min){
                if (venstre <= mid - min){
                    if (temp[venstre] > temp[hoyre]){
                        tall[index1 + min] = temp[hoyre++];
                    } else {
                        tall[index1 + min] = temp[venstre++];
                    }
                } else {
                    tall[index1 + min] = temp[hoyre++];
                }
            } else {
                tall[index1 + min] = temp[venstre++];
            }
        }
        //System.out.println(Arrays.toString(tall));
    }

    public static void radixSortering(int tall[]){
        //System.out.println(Arrays.toString(tall));
        int ti_i_m = 1;
        int n = tall.length;
        int maksAntSiffer = (int) Math.log10(n);

        Queue<Integer>[] Q = (Queue<Integer>[])(new Queue[10]);

        for (int i = 0; i < 10; i++) {
            Q[i] = new LinkedList<>();
        }

        for (int m = 0; m < maksAntSiffer; m++) {
            for (int i = 0; i < n; i++) {

                int siffer = (tall[i]/ti_i_m) % 10;
                Q[siffer].add(new Integer(tall[i]));
            }

            int j = 0;
            for (int i = 0; i < 10; i++) {
                while (!Q[i].isEmpty()){
                    tall[j++] = (int) Q[i].remove();
                }
            }
            ti_i_m *= 10;
        }
        //System.out.println(Arrays.toString(tall));
    }

    public static int finnInndeling(int tall[], int min, int max){
        int venstre, hoyre;
        int temp, inndelingElement;

        inndelingElement = tall[min];

        venstre = min;
        hoyre = max;

        while (venstre < hoyre){
            while (tall[venstre] <= inndelingElement && venstre < hoyre){ venstre++;}
            while (tall[hoyre] > inndelingElement){ hoyre--;}

            if (venstre < hoyre){
                temp = tall[venstre];
                tall[venstre] = tall[hoyre];
                tall[hoyre] = temp;
            }
        }

        temp = tall[min];
        tall[min] = tall[hoyre];
        tall[hoyre] = temp;

        return hoyre;
    }


    public static int[] randomTall(int n){

        int tall[];
        tall = new int[n];
        int n_2 = 2*n;
        Random r = new Random();
        for (int i = 0; i < tall.length; i++) {
            tall[i] = r.nextInt(n_2);
        }
        return tall;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Velg antall tall som skal sorteres: ");
        int n = scanner.nextInt();
        System.out.println("Velg sorteringsmetode");
        System.out.println("1 = Instikk sortering, 2 = Quicksort, 3 = Flettesortering, 4 = Radixsort");
        int metode = scanner.nextInt();
        System.out.println("Hva skal programmet gjøre?");
        System.out.println("1 = Beregne hvor lang tid sorteringen bruker, 2 = Estimere konstanten C");
        int z = scanner.nextInt();

        sortering sorter = new sortering(metode, n, z);
    }
}

