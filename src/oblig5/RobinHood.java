package oblig5;

import java.io.*;
import java.util.Scanner;


//HAR ENDRET INPUT TIL Å VÆRE EN FIL, MÅ FORTSATT GI HASHLENGDE SOM INPUT!!

public class RobinHood {
    // Hashlengde
    private int hashLengde;

    // Hashtabell
    private String hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall probes ved innsetting
    private int antProbes;

    // KonstruktÃ¸r
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public RobinHood(int lengde) {
        hashLengde = lengde;
        hashTabell = new String[lengde];
        n = 0;
        antProbes = 0;
    }

    // Returnerer load factor
    public float loadFactor() {
        return ((float) n)/hashLengde;
    }

    // Returnerer antall data i tabellen
    public int antData() {
        return n;
    }

    // Returnerer antall probes ved innsetting
    public int antProbes() {
        return antProbes;
    }

    // Hashfunksjon
    int hash(String S) {
        int h = Math.abs(S.hashCode());
        return h % hashLengde;
    }


    void insert(String S) {
        // Beregner hashverdien
        int h = hash(S);
        String s2 = null;
        int nyHash;
        int currentHashOffset;
        int hashOffset = 0;
        int neste = h;

        System.out.println(h);


        // ENDRET PÅ STARTEN AV WHILE LOOPEN, ENDRE MÅTEN ELEMNTER BLIR SJEKKET OG BYTTET
        while (hashTabell[neste] != null) {

            //la her til en swap som flytter en ett hakk til høyre
            nyHash = hash(hashTabell[neste]);
            currentHashOffset = neste - nyHash;

            if(currentHashOffset < 0){
                currentHashOffset = neste + (hashLengde - nyHash);
            }

            if(currentHashOffset < hashOffset){
                s2 = hashTabell[neste];
                hashTabell[neste] = S;
                hashOffset = currentHashOffset;
            }

            // Ny probe
            antProbes++;

            // Denne indeksen er opptatt, prÃ¸ver neste
            neste++;

            // Wrap-around
            if (neste >= hashLengde)
                neste = 0;


            if (neste == h) {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }
        }



        hashTabell[neste] = s2;
        // Ã˜ker antall elementer som er lagret
        n++;
    }

    // SÃ¸king etter tekststreng med lineÃ¦r probing
    // Returnerer true hvis strengen er lagret, false ellers
    //
    boolean search(String S) {
        // Beregner hashverdien
        int h = hash(S);

        // LineÃ¦r probing
        int neste = h;

        while (hashTabell[neste] != null) {
            // Har vi funnet tekststrengen?
            if (hashTabell[neste].compareTo(S) == 0)
                return true;

            // PrÃ¸ver neste mulige
            neste++;

            // Wrap-around
            if (neste >= hashLengde)
                neste = 0;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi,
            // finnes ikke strengen i tabellen
            if (neste == h)
                return false;
        }

        // Finner ikke strengen, har kommet til en probe som er null
        return false;
    }

    // Enkelt testprogram:
    //
    // * Hashlengde gis som input pÃ¥ kommandolinjen
    //
    // * Leser tekststrenger linje for linje fra standard input
    //   og lagrer dem i hashtabellen
    //
    // * Skriver ut litt statistikk etter innsetting
    //
    // * Tester om sÃ¸k fungerer for et par konstante verdier
    //
    public static void main(String argv[]) throws FileNotFoundException {
        // Hashlengde leses fra kommandolinjen
        int hashLengde = 0;
        try {
            if (argv.length != 1)
                throw new IOException("Feil: Hashlengde mÃ¥ angis");
            hashLengde = Integer.parseInt(argv[0]);
            if (hashLengde < 1 )
                throw new IOException("Feil: Hashlengde mÃ¥ vÃ¦re stÃ¸rre enn 0");
        }
        catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

        File fil = new File("src/oblig5/cars.txt");


        Scanner input = new Scanner(fil);

        // Lager ny hashTabell
        HashLinear hL = new HashLinear(hashLengde);

        // Leser input og hasher alle linjer
        while (input.hasNext()) {
            hL.insert(input.nextLine());
        }

        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + hL.antData());
        System.out.printf( "Load factor : %5.3f\n",  hL.loadFactor());
        System.out.println("Probes      : " + hL.antProbes());

        // Et par enkle sÃ¸k
        String S = "Volkswagen GTI";

        if (hL.search(S)) {
            System.out.println("\"" + S + "\"" + " finnes i hashtabellen");
        }
        if (!hL.search(S)) {
            System.out.println("\"" + S + "\"" + " finnes ikke i hashtabellen");
        }
    }
}
