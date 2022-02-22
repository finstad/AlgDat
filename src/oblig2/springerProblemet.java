package oblig2;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;


public class springerProblemet {

    int LEDIG = 0, BRUKT = 1;

    int n;
    int brett[][];
    int utskriftBrett[][];

    public springerProblemet(int n, int x, int y) {
        this.n = n;
        brett = new int[n][n];
        utskriftBrett = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                brett[i][j] = LEDIG;
                utskriftBrett[i][j] = LEDIG;
            }
        }

        brett[y][x] = BRUKT;

        if (!finnVei(y, x, 1)){
            System.out.println("Ingen løsning funnet!");
        } else {
            System.out.println("Løsning funnet!");
            skrivLosning();
        }
    }

    boolean finnVei(int i, int j, int trekkNr) {

        if (trekkNr == n * n ) {

            return true;
        }

        int dI[] = { -2, -1, 1, 2, 2, 1, -1, -2};
        int dJ[] = { -1, -2, -2, -1,  1, 2, 2, 1 };

        for (int k = 0; k < 8; k++) {
            int nyI = i + dI[k];
            int nyJ = j + dJ[k];
            // Sjekker om det er lovlig å gå til ny posisjon
            if (nyI >=0 && nyI < n && nyJ >=0 && nyJ < n && brett[nyI][nyJ] == LEDIG) {
                brett[nyI][nyJ] = BRUKT;
                // Prøver å finne vei videre rekursivt
                if (finnVei(nyI, nyJ, trekkNr + 1)) {
                    utskriftBrett[nyI][nyJ] = trekkNr;
                    return true;
                } else {
                    brett[nyI][nyJ] = LEDIG;
                }
            }
        }

        return false;
    }

    public void skrivLosning(){

        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(utskriftBrett[i][j] + " ");
            }
            System.out.println();
        }
    }


    // Testprogram
    public static void main(String argv[])
    {
        // Leser størrelsen på labyrinten og prosentandel blokkerte ruter
        Scanner scanner = new Scanner(System.in);
        System.out.print("n?: ");
        int n = scanner.nextInt();
        System.out.println("Start koordinat x: ");
        int x = scanner.nextInt();
        System.out.println("Start koordinat y: ");
        int y = scanner.nextInt();

        // Oppretter ny labyrint
        springerProblemet sjakk = new springerProblemet(n, x, y);

    }
}
