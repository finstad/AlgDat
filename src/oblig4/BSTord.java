package oblig4;
import java.io.*;
import java.util.ArrayList;

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


    public static void main (String argv[]) {
        ArrayList<String> test = new ArrayList<>();
        BSTord tre = new BSTord();

        try
        {
        BufferedReader r = new BufferedReader(new FileReader("D:/Skole/AlgDat/src/oblig4/tekst.txt")); //Husk å bytte til egen fil
        StreamTokenizer input = new StreamTokenizer(r);


            int x = input.nextToken();
            while (x != input.TT_EOF) {
                if (input.ttype == input.TT_WORD)
                    //System.out.println(input.sval.toUpperCase());
                    tre.settInn(input.sval.toUpperCase());
                    //test.add(input.sval.toUpperCase());
                x = input.nextToken();
            }

            tre.skrivUtTre();
            //System.out.println(test);
            //System.out.println(test.size());
            //System.out.println(test.get(2).compareTo(test.get(3)));
            //System.out.println(tre);

            /*for (int i = 0; i < test.size() - 1; i++) {
                tre.settInn(test.get(i));
            }*/
        }
        catch (IOException e) {};

    }
}
