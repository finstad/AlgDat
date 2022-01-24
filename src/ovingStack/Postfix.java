package ovingStack;

import java.util.Scanner;
import java.util.StringTokenizer;
import ovingStack.IntStack;

/* Enkel beregning av postfix regneuttrykk med positive heltall */
/* Regneuttrykk mÃ¥ skrives med space mellom tall og binÃ¦re operatorer */

public class Postfix
{
    public static int beregn(String uttrykk)
    {
        IntStack S = new IntStack(1000);

        char operasjon;
        int operand_1, operand_2, resultat = 0;

        String token;
        StringTokenizer tokenizer = new StringTokenizer(uttrykk);

        while (tokenizer.hasMoreTokens())
        {
            token = tokenizer.nextToken();
            System.out.println(token);
            operasjon = token.charAt(0);

            if (operasjon == '+' || operasjon == '-' ||
                    operasjon == '*' || operasjon == '/')
            {
                operand_2 = S.pop();
                operand_1 = S.pop();

                switch (operasjon)
                {
                    case '+':
                        resultat = operand_1 + operand_2;
                        break;
                    case '-':
                        resultat = operand_1 - operand_2;
                        break;
                    case '*':
                        resultat = operand_1 * operand_2;
                        break;
                    case '/':
                        resultat = operand_1 / operand_2;
                }
                S.push(resultat);
            }
            else
                S.push(Integer.parseInt(token));
        }
        return S.pop();
    }

    public static void main (String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Postfix ? ");
        System.out.println("Resultat: " + beregn(in.nextLine()));
    }
}