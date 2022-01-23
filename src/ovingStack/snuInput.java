package ovingStack;

import java.io.*;
import java.util.Scanner;

// Bruker lÃ¦rebokas stack-modul. Last ned koden til egen maskin,
// legg den i en underkatalog "jsjf" og kompiler. Husk Ã¥ legge bÃ¥de
// stÃ¥ende katalog og forelderkatalog pÃ¥ CLASSPATH slik at Java-
// kompilatoren finner modulene som inkluderes

import ovingStack.ArrayStack;

public class snuInput
{


    public static boolean palindrom(String line){

        ArrayStack<Character> stack = new ArrayStack<Character>();

        int l;
        l = line.length();

        for (int i = 0; i < l; i++){
            stack.push(new Character(line.charAt(i)));}

        int i = 0;

        while (!stack.isEmpty()){
            if (line.charAt(i++) != stack.pop()){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args)
    {


        Character c;

        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();

        System.out.println(palindrom(line));

    }
}
