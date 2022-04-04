package oblig5;

import java.io.*;
import java.util.Scanner;

// Hashing av tekststrenger med kjeding i lenket liste
// Bruker Javas innebygde hashfunksjon for strenger
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell/lange lister
// - Tilbyr bare innsetting og sÃ¸king
//
public class HashChained
{
    // Indre klasse:
    // Node med data, kjedes sammen i lenkede lister
    //
    private class hashNode
    {
        // Data, en tekststreng
        String data;
        // Neste node i listen
        hashNode neste;

        // KonstruktÃ¸r for listenoder
        public hashNode(String S, hashNode hN)
        {
            data = S;
            neste = hN;
        }
    }

    // Hashlengde
    private int hashLengde;

    // Hashtabell, pekere til lister
    private hashNode hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // Antall kollisjoner ved innsetting
    private int antKollisjoner;

    // KonstruktÃ¸r
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public HashChained(int lengde)
    {
        hashLengde = lengde;
        hashTabell = new hashNode[lengde];
        n = 0;
        antKollisjoner = 0;
    }

    // Returnerer load factor
    public float loadFactor()
    {
        return ((float) n)/hashLengde;
    }

    // Returnerer antall data i tabellen
    public int antData()
    {
        return n;
    }

    // Returnerer antall kollisjoner ved innsetting
    public int antKollisjoner()
    {
        return antKollisjoner;
    }

    // Hashfunksjon
    int hash(String S)
    {
        int h = Math.abs(S.hashCode());
        return h % hashLengde;
    }

    // Innsetting av tekststreng med kjeding
    //
    void insert(String S) {
        // Beregner hashverdien
        int h = hash(S);

        // Ã˜ker antall elementer som er lagret
        n++;

        // Sjekker om kollisjon
        if (hashTabell[h] != null)
            antKollisjoner++;

        // Setter inn ny node fÃ¸rst i listen
        hashTabell[h] = new hashNode(S, hashTabell[h]);
    }

    // SÃ¸king etter tekststreng i hashtabell med kjeding
    // Returnerer true hvis strengen er lagret, false ellers
    //
    boolean search(String S) {
        // Finner listen som S skal ligge i
        hashNode hN = hashTabell[hash(S)];

        // Leter gjennom listen
        while (hN != null)
        {
            // Har vi funnet tekststrengen?
            if (hN.data.compareTo(S) == 0)
                return true;
            // PrÃ¸ver neste
            hN = hN.neste;
        }
        // Finner ikke strengen, har kommet til slutten av listen
        return false;
    }

    // LAGET REMOVE METODE!!!!
    String remove(String S){
        if (search(S) == false) {return "Item not found";}

        hashNode current = hashTabell[hash(S)];

        if (current.data.compareTo(S) == 0){
            hashTabell[hash(S)] = current.neste;
            return "The element was removed";
        }

        hashNode prev = hashTabell[hash(S)];

        while (current.neste != null){

            if (current.neste.data.compareTo(S) == 0){
                prev.neste = current.neste;
                return "The element was removed";
            }

            prev = current;
            current = current.neste;

        }

        return "The element was removed";
    }


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
        HashChained hL = new HashChained(hashLengde);

        // Leser input og hasher alle linjer
        while (input.hasNext()) {
            hL.insert(input.nextLine());
        }

        // Skriver ut hashlengde, antall data lest, antall kollisjoner
        // og load factor
        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + hL.antData());
        System.out.printf( "Load factor : %5.3f\n",  hL.loadFactor());
        System.out.println("Probes      : " + hL.antKollisjoner());

        // Et par enkle sÃ¸k
        String S = "Volkswagen GTI";

        if (hL.search(S)) {
            System.out.println("\"" + S + "\"" + " finnes i hashtabellen");
        }
        if (!hL.search(S)) {
            System.out.println("\"" + S + "\"" + " finnes ikke i hashtabellen");
        }

        System.out.println(hL.remove("Mercedes Benz SLK280"));
    }
}

