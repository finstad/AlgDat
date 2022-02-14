package oblig2;
import java.util.Random;
import java.util.Scanner;


public class springerProblemet {
    // Verdiene som kan lagres i labyrinten
    //
    // Hint til SPRINGERPROBLEMET: Trenger bare Ã¥ lagre om en rute er brukt
    // eller ledig

    int STENGT = 0, FRI = 1, BRUKT = 2, VEI = 3;

    // StÃ¸rrelse pÃ¥ kvadratisk labyrint og 2D-tabell som lagrer
    // labyrinten
    //
    // Den samme datastrukturen kan lagre sjakkbrettet i SPRINGERPROBLEMET
    int n;
    int L[][];


    // Konstruktør som oppretter en tilfeldig labyrint med en gitt
    // prosentandel blokkerte ruter.
    //
    // I SPRINGERPROBLEMET lages det her et n X n sjakkbrett der alle
    // rutene til å begynne med er FRI
    public springerProblemet(int n) {
        this.n = n;
        L = new int[n][n];
        Random R = new Random();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                    L[i][j] = FRI;
    }


    // Funksjonen finnVei leter rekursivt etter en vei gjennom
    // labyrinten fra rute (i,j) til rute (n-1,n-1).
    // Returnerer true hvis vi fant en slik vei, false ellers
    boolean finnVei(int i, int j)
    {
        // Bunn i rekursjonen: Ferdig hvis vi er i nedre høyre hjørne
        //
        // SPRINGERPROBLEMET mÃ¥ her ha et annet stoppkriterium
        if (i == n-1 && j == n-1)
        {
            // Markerer at siste rute i labyrinten ligger på veien som
            // er funnet, og returnerer true
            L[i][j] = VEI;
            return true;
        }

        // Markerer at rute (i,j) nÃ¥ er oppsÃ¸kt
        //
        // I SPRINGERPROBLEMET mÃ¥ vi her i tillegg lagre bÃ¥de antall
        // flytt som er gjort frem til nÃ¥, og hvike flytt som er gjort
        // for Ã¥ komme hit.
        L[i][j] = BRUKT;

        // PrÃ¸ver alle fire mulige lovlige veier videre fra rute (i,j)
        // i denne rekkefÃ¸lgen: 1. HÃ¸yre, 2. Ned , 3. Venstre, 4. Opp
        //
        // For Ã¥ lÃ¸se SPRINGERPROBLEMET, mÃ¥ koden her utvides til Ã¥
        // hÃ¥ndtere alle de Ã…TTE mulige lovlige stegene som en
        // springer kan ta fra rute (i,j)

        int dI[] = {  0,  1,  0, -1};
        int dJ[] = {  1,  0, -1,  0};

        for (int k = 0; k < 4; k++)
        {
            int nyI = i + dI[k];
            int nyJ = j + dJ[k];

            // Sjekker om det er lovlig Ã¥ gÃ¥ til ny posisjon
            if (nyI >=0 && nyI < n && nyJ >=0 && nyJ < n && L[nyI][nyJ] == FRI)
            {
                // PrÃ¸ver Ã¥ finne vei videre rekursivt
                if (finnVei(nyI, nyJ))
                {
                    // Her vet vi at det ble funnet en vei gjennom
                    // labyrinten fra rute (i,j). Merker av at (i,j)
                    // ligger pÃ¥ denne veien og stopper deretter videre
                    // leting etter vei ved Ã¥ returnere true
                    L[i][j] = VEI;
                    return true;
                }
            }
        }
        // Hvis vi kommer hit i koden, fantes det ingen vei gjennom
        // labyrinten fra rute (i,j), returnerer false
        //
        // I SPRINGERPROBLEMET mÃ¥ dette siste tilfellet, der vi ikke
        // finner noen lovlig lÃ¸sning med start i rute (i,j),
        // behandles litt anderledes. I labyrinten er det ingen vits i
        // Ã¥ gÃ¥ tilbake til en rute der vi har vÃ¦rt fÃ¸r. I
        // springerproblemet er det ikke slik, der mÃ¥ vi nÃ¥ markere at
        // ruten er blitt ledig igjen, slik at den kan brukes pÃ¥ nytt
        // i senere forsÃ¸k pÃ¥ Ã¥ bygge ut en lÃ¸sning. Ã˜vrig
        // datastruktur som brukes til Ã¥ lagre lÃ¸sningen mÃ¥ ogsÃ¥
        // oppdateres slik at dette steget som ikke ledet til lÃ¸sning
        // blir fjernet.

        return false;
    }


    // GjÃ¸r om labyrinten til en tekststreng for utskrift, markerer
    // evt. funnet vei med stjerner ('*')
    public String toString()
    {
        String result = "\n";
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                if (L[i][j] == VEI)
                    result += "* ";
                else
                    result += L[i][j] + " ";
            result += "\n";
        }
        return result;
    }


    // Testprogram
    public static void main(String argv[])
    {
        // Leser stÃ¸rrelsen pÃ¥ labyrinten og prosentandel blokkerte ruter
        Scanner scanner = new Scanner(System.in);
        System.out.print("n?: ");
        int n = scanner.nextInt();
        System.out.print("% blokkerte ruter?: ");
        int prosentBlokkert = scanner.nextInt();

        // Oppretter ny labyrint
        springerProblemet lab = new springerProblemet(n);

        // Leter etter vei fra Ã¸vre venstre hjÃ¸rne
        boolean funnetVei = lab.finnVei(0, 0);

        // Skriver ut labyrinten (og evt. funnet vei)
        System.out.println(lab);
        if (!funnetVei)
            System.out.println("Fant ingen vei gjennom labyrinten");
    }
}
