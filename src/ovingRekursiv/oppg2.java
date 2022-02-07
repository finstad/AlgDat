package ovingRekursiv;

public class oppg2 {

    public static int loop(int n){
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static int rekursiv(int n){
        if(n == 0){
            return 0;
        } else {
           return n + rekursiv(n - 1);
        }

    }

    public static int formel3(int n){
        return n * ((n+1)/2);
    }

    public static void main(String[] args) {

        //System.out.println(loop(5));
        //System.out.println(rekursiv(2));

        System.out.println(formel3(5));
    }
}
