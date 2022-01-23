package ovingStack;

import ovingStack.ArrayStack;

public class oppg2_1 {

    public static Object element_3(ArrayStack S){

        Object x1, x2, x3 = null;

        if (!S.isEmpty()){
            x1 = S.pop();
            if (!S.isEmpty()){
                x2 = S.pop();
                if (!S.isEmpty()){
                    x3 = S.peek();
                    S.push(x2);
                    S.push(x1);
                }
            }
        }

        return x3;
    }

    public static Object bunn(ArrayStack S){

        Object last;
        ArrayStack<Object> L = new ArrayStack<Object>();

        if (S.isEmpty()){
            return null;
        }

        while (!S.isEmpty()){
            L.push(S.pop());
        }

        last = L.peek();

        while (!L.isEmpty()){
            S.push(L.pop());
        }

        return last;
    }

    public static void fjernX(ArrayStack S, Integer x){

        ArrayStack<Object> L = new ArrayStack<Object>();

        while (!S.isEmpty()){
            if (S.peek() == x){
                S.pop();
            } else {
                L.push(S.pop());

            }
        }

        while (!L.isEmpty()){
            S.push(L.pop());
            System.out.println(S.peek());
        }
    }

    public static void main(String[] args){

        ArrayStack<Integer> S = new ArrayStack<Integer>();
        //Integer X = new Integer(0);

        for (int i = 0; i < 10; i++) {
            S.push(i);
        }


    }
}
