package oblig1;

import java.util.*;



public class Flyplass {

    Integer tidsenhet;
    Double ankomst;
    Double avgang;
    final Integer maks_i_ko = 10;

    public Flyplass(String navn){
        Scanner sc = new Scanner(System.in);
        System.out.println("Velkommen til " + navn + " flyplass");
        System.out.println("Hvor mange tidsenheter skal simuleringen gå?");
        tidsenhet = sc.nextInt();
        System.out.println("Forventet antall ankomster pr. tidsenhet ?");
        ankomst = sc.nextDouble();
        System.out.println("Forventet antall avganger pr. tidsenhet ?");
        avgang = sc.nextDouble();
    }

    private class Fly {
        Integer id;
        Integer tidsenhet;
        public Fly(Integer id,Integer tidsenhet){
            this.id = id;
            this.tidsenhet = tidsenhet;
        }
        public Integer getId() {
            return id;
        }
        public Integer getTidsenhet() { return tidsenhet;}
    }


    public void simuler(){

        Queue<Fly> landingKo = new LinkedList<>();
        Queue<Fly> letteKo = new LinkedList<>();
        Integer id = 0;
        int totAnkommnefly = 0;
        int avvisteFly = 0;
        int fly_landet = 0;
        int fly_lettet = 0;
        int tomFlyplass = 0;
        int lengde_letteKo = 0;
        int lengde_landeKo = 0;
        int venteTidLanding = 0;
        int venteTidlette = 0;



        //tidsregning
        for (int i = 0; i < tidsenhet; i++) {
            System.out.println("Tidsenhet: " + (i+1));

            for (int j = 0; j < getPoissonRandom(ankomst); j++) {
                Fly fly = new Fly(id, i+1);
                System.out.println("Fly " + fly.getId() + " ønsker å lande");
                if (landingKo.size() > maks_i_ko){
                    System.out.println("Køen er full, fly " + fly.getId() + " henvist til nærmeste flyplass");
                    avvisteFly++;
                } else {
                    landingKo.add(fly);
                }
                id++;
                totAnkommnefly++;
            }

            for (int j = 0; j < getPoissonRandom(avgang); j++) {
                Fly fly = new Fly(id, i+1);
                System.out.println("Fly " + fly.getId() + " ønsker å ta av");
                if (letteKo.size() >= maks_i_ko){
                    System.out.println("Avgangen er kanselert for fly " + fly.getId() + ", køen er full");
                    avvisteFly++;
                } else {
                    letteKo.add(fly);
                }
                id++;
                totAnkommnefly++;
            }


            if(!landingKo.isEmpty()){
                Fly landetFly = landingKo.remove();
                System.out.println("Fly " + landetFly.getId() + " landet");
                fly_landet++;
                lengde_landeKo += landingKo.size();
                venteTidLanding += (i+1 - landetFly.getTidsenhet());
            } else if (!letteKo.isEmpty()){
                Fly lettetFly = letteKo.remove();
                System.out.println("Fly " + lettetFly.getId() + " lettet");
                fly_lettet++;
                lengde_letteKo += letteKo.size();
                venteTidlette += (i+1 - lettetFly.getTidsenhet());
            } else{
                System.out.println("Flyplassen er tom");
                tomFlyplass++;
            }

            System.out.println("");
        }

        System.out.println("Totalt ankomne fly: " + totAnkommnefly);
        System.out.println("Totalt avviste fly: " + avvisteFly);
        System.out.println("Totale fly landet: " + fly_landet);
        System.out.println("Totale fly lettet: " + fly_lettet);
        System.out.println("Prosenandel flyplassen var tom " + ((float) tomFlyplass/ (float) tidsenhet)*100);
        System.out.println("Gjennomsnittlig lengde landingskø " + ((float) lengde_landeKo/ (float) tidsenhet));
        System.out.println("Gjennomsnittlig lengde lettekø " + ((float) lengde_letteKo/ (float) tidsenhet));
        System.out.println("Gjennomsnittlig ventetid landing: " + ((float) venteTidLanding/ (float) fly_landet));
        System.out.println("Gjennomsnittlig ventetid avgang: " + ((float) venteTidlette/ (float) fly_lettet));


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
        Flyplass rygge = new Flyplass("Rygge");
        rygge.simuler();


    }

}
