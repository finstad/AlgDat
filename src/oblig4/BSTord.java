package oblig4;
import java.io.*;
import java.util.*;
import java.lang.*;

public class BSTord {

    private class treNode {

        String ord;
        int antall = 1;
        treNode venstre, hoyre;

        public treNode(String ord) {
            this.ord = ord;
            venstre = hoyre = null;
        }
        void skrivUt(){
            System.out.print(ord + " Count: " + antall + ", ");
        }
    }

    private treNode root;


    public void BSTord() {
        root = null;
    }

    public boolean isEmpty(){
        return (root == null);
    }


    public void settInn(String ord){
        treNode node = new treNode(ord);

        if (isEmpty()){
            root = node;
            return;
        }

        treNode current = root;
        boolean ferdig = false;

        while(!ferdig){
            if (ord.compareTo(current.ord) < 0){
                if (current.venstre == null) {
                    current.venstre = node;
                    ferdig = true;
                } else {
                    current = current.venstre;
                }
            } else if (ord.compareTo(current.ord) > 0){
                if (current.hoyre == null){
                    current.hoyre = node;
                    ferdig = true;
                } else {
                    current = current.hoyre;
                }
            } else {
                current.antall++;
                ferdig = true;
            }
        }

    }

    public void skrivUtTre(){
        System.out.print("Utskrift tre: " );
        skrivUtTre(root);
        System.out.println("\n");

    }

    private void skrivUtTre(treNode t){
        if (t != null){
            skrivUtTre(t.venstre);
            t.skrivUt();
            skrivUtTre(t.hoyre);
        }
    }


    public static void main (String[] argv) {
        BSTord tre = new BSTord();

        try
        {
        BufferedReader r = new BufferedReader(new FileReader("D:/Skole/AlgDat/src/oblig4/tekst.txt")); //Husk å bytte til egen fil
        StreamTokenizer input = new StreamTokenizer(r);


            int x = input.nextToken();
            while (x != input.TT_EOF) {
                if (input.ttype == input.TT_WORD)
                    tre.settInn(input.sval.toUpperCase().replace(".", ""));
                x = input.nextToken();
            }

            tre.skrivUtTre();
        }
        catch (IOException e) {};

    }
}
