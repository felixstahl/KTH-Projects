import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

/* *
 *
 *                      README
 * This program implements the Stack ADT from ch1.3
 * It also contains two recursive and  two iterative functions.
 * Two print functions, one iterative and one recursive.
 * Two functions that add characters to a stack. One iterative and one recursive.
 *
 * The main function creates the stack and calls 'iterate' one time which will run a loop
 * and push characters to the stack as long as the input character isn't '\n'(new line).
 * When '\n' is entered the print function will print the symbol and pop from the stack until empty.
 * The same happens with the recursive functions but those will keep calling themselves.
 *
 * */

public class Stack<Item> implements Iterable<Item> {
    private Node first;     // top of stack (most recently added node)
    private int N;          // number of items
    private class Node{    // nested class to define nodes
        Item item;
        Node next;
    }
    public boolean isEmpty() { return first == null; } // Or: N == 0.
    public int size() { return N; }
    public void push(Item item) { // Add item to top of stack.
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop() { // Remove item from top of stack.
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public boolean fraga_sex(Stack characters){
        if(StdIn.readChar() != EOF){
            while(){

            }
        }
        else{
            return false;
        }
    }

 /*   public static void iterate(Stack characters){
        Character c = StdIn.readChar();
        while(c != '\n'){
            characters.push(c);
            c = StdIn.readChar();
        }
    }

    public static void recursive(Stack characters, char input){
        if(input == '\n') return;
        else{
            characters.push(input);
        }
        recursive(characters, StdIn.readChar());
    }

    public static void print_iterate(Stack characters){
        for(int i = characters.N; i > 0; i--){
            if(i > 1)
                System.out.print("[" + characters.pop() + "], ");
            else{
                System.out.print("[" + characters.pop() + "]");
            }
        }
    }

    public static void print_recursive(Stack characters){
        if(characters.N > 1) {
            System.out.print("[" + characters.pop() + "], ");
            print_recursive(characters);
        }
        else if(characters.N == 1)
            System.out.print("[" + characters.pop() + "]");
    }

    */

    public static void main(String[] args){
        Stack<Character> characters = new Stack<Character>();
        fraga_sex(characters);
    }

    @Override
    public Iterator<Item> iterator(){
        return null;
    }
}