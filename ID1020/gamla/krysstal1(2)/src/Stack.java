import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

/* *
*
*                      README -- Felix Ståhl
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
    private Node first;     // most recently added node
    private int N;          // number of items
    private class Node{    // nested class to define nodes, simplifies the creation in the future of the program
        Item item;
        Node next;
    }
    public boolean isEmpty() { return first == null; }  // checks if empty
    public int size() { return N; } // number of items
    public void push(Item item) { // Add item to top of stack.
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop() { // Remove item from stack
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public static void iterate(Stack characters){       // this function was created when i had very little time until
        Character c = StdIn.readChar();                 // deadline. i dont know if i was supposed to use an already
        while(c != '\n'){                               // created iterator(i did copy one from the book later on in..
            characters.push(c);                         // ..the assignments) any how, this iterates through the input
            c = StdIn.readChar();                       // and pushes to the stack
        }
    }

    public static void recursive(Stack characters, char input){     // this is the recursive function which
        if(input == '\n') return;                                   // keeps calling itself and unless the input is
        else{                                                       // new line(enter) it will push to the stack
            characters.push(input);
        }
        recursive(characters, StdIn.readChar());
    }

    public static void print_iterate(Stack characters){             // this is the iterative print function
        for(int i = characters.N; i > 0; i--){                      // it iterates through the object.
            if(i > 1)                                               // it does not create a string (i saw that aswell
                System.out.print("[" + characters.pop() + "], ");   // when i had very little time until deadline
            else{                                                   // but thats not so hard and i guess its not the
                System.out.print("[" + characters.pop() + "]");     // major focus of the assignment) though it does
            }                                                       // print every character in brackets seperated by
        }                                                           // a comma.
    }

    public static void print_recursive(Stack characters){           // this is the recursive print function
            if(characters.N > 1) {                                  // same outcome as the iterative one but
                System.out.print("[" + characters.pop() + "], ");   // but it calls itself everytime between the
                print_recursive(characters);                        // printouts.
            }
            else if(characters.N == 1)
                System.out.print("[" + characters.pop() + "]");
    }

    public static void main(String[] args){                         // first of all main creates a stack
        Stack<Character> characters = new Stack<Character>();
        System.out.println("Enter Characters:");
        iterate(characters);                                        // the iterate function is called with the stack
        print_iterate(characters);                                  // as an argument

        System.out.println("\nEnter Again: ");                      // the recursive one is called with the stack and
        recursive(characters, StdIn.readChar());                    // an input as arguments
        print_recursive(characters);                                // this one prints.
    }

    @Override
    public Iterator<Item> iterator(){
        return null;
    }
}