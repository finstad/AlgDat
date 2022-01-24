package oblig1;

import javax.sound.midi.Soundbank;
import java.util.*;



public class Main {



    public static void behandleKo(Integer tidsenhet, Double ankomst, Double avgang){

        Queue<Fly> landingKo = new LinkedList<>();
        Queue<Fly> letteKo = new LinkedList<>();
        Integer id = 0;
        final Integer maks_i_ko = 10;

        //tidsregning
        for (int i = 0; i < tidsenhet; i++) {

            for (int j = 0; j < getPoissonRandom(ankomst); j++) {
                Fly fly = new Fly(id);
                id++;



                landingKo.add(fly);

            }




        }
    }



    private static int getPoissonRandom(double mean)
    {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do
        {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Hvor mange tidsenheter skal simuleringen gå?");
        Integer tidsenhet = sc.nextInt();
        System.out.println("Forventet antall ankomster pr. tidsenhet ?");
        Double ankomst = sc.nextDouble();
        System.out.println("Forventet antall avganger pr. tidsenhet ?");
        Double avgang = sc.nextDouble();


        behandleKo(tidsenhet, ankomst, avgang);


    }

}
