package oblig3;

import java.io.*;
import java.util.*;

public class sortTest
{
    // Test av utplukksortering

    public static void main(String args[])
    {
        Random r = new Random();

        System.out.println("-------------------------------------------------");
        System.out.println("  n      t(ms)   t/n         t/n^2       t/n^3");
        System.out.println("-------------------------------------------------");

        for (int n = 1000; n <= 10000; n += 1000)
        {
            int a[] = new int[n];
            long time = 0;

            for (int i = 0; i < n; i++)
                a[i] = r.nextInt(n);

            time = System.currentTimeMillis();
            selectionSort(a);
            time = System.currentTimeMillis() - time;

            System.out.printf("%6d %6d  %9.4e  %9.4e  %9.4e\n", n, time,
                    (float)time/((float)n),
                    (float)time/((float)n*n),
                    (float)time/((float)n*n*n));
        }

        for (int n = 20000; n <= 150000; n += 10000)
        {
            int a[] = new int[n];
            long time = 0;

            for (int i = 0; i < n; i++)
                a[i] = r.nextInt(n);

            time = System.currentTimeMillis();
            selectionSort(a);
            time = System.currentTimeMillis() - time;

            System.out.printf("%6d %6d  %9.4e  %9.4e  %9.4e\n", n, time,
                    (float)time/((float)n),
                    (float)time/((float)n*n),
                    (float)time/((float)n*n*n));
        }
        System.out.println("-------------------------------------------------");
    }

    public static void selectionSort(int a[])
    {
        // Utplukksortering av en array med heltall

        int n = a.length, tmp = 0;

        for (int i = 0; i < n - 1; i++)
        {
            int min = i;
            for (int j = i + 1; j < n; j++)
            {
                if (a[j] < a[min])
                    min = j;
            }

            tmp = a[min];
            a[min] = a[i];
            a[i] = tmp;
        }
    }
}