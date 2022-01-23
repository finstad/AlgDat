package ovingStack;

/* Enkel implementasjon av stack med heltallsarray, uten feilsjekking... */

public class IntStack
{
    private int top, max;
    private int stack[];

    public IntStack(int length)
    {
        top = 0;
        max = length;
        stack = new int[max];
    }

    public void push(int i)
    {
        stack[top] = i;
        top++;
    }

    public int pop()
    {
        top--;
        return(stack[top]);
    }

    public int peek()
    {
        return(stack[top-1]);
    }

    public boolean isEmpty()
    {
        return (top == 0);
    }

    public int size()
    {
        return top;
    }
}