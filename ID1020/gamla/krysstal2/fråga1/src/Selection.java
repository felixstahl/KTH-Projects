import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *              README -- Felix Ståhl, assignment 1
 * This program implements the Section sort algorithms in chapter 2.1.
 * The program prints the content of the array that is sorted.
 * The user enters the size of the array followed with the integer
 * for each index in the array.
 * Use the input [1, 2, 4, 3, 5, 0]
 */
public class Selection{
    public static void sort(Comparable[] a){        // Sort a[] into increasing order.
        int N = a.length;                           // array length
        for (int i = 0; i < N; i++){                // Exchange a[i] with smallest entry in a[i+1...N).
            int min = i;                            // index of minimal entry.
            for (int j = i+1; j < N; j++) {         // Prints out all 15 of the inner loops iterations
                if (less(a[j], a[min])) min = j;    // (if amount of input integers is 6)
                exch(a, i, min);
                show(a);
            }
        }
    }
    private static boolean less(Comparable v, Comparable w){return v.compareTo(w) < 0;}
    private static void exch(Comparable[] a, int i, int j){Comparable t = a[i]; a[i] = a[j]; a[j] = t;}
    private static void show(Comparable[] a) { // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a) {    // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
    public static void addNumbers(Comparable[] array){  // adds integer to the array
        for(int i = 0; i < array.length; i++){
            System.out.print("Enter number for array index " + i + ": ");
            array[i] = StdIn.readInt();
        }
    }

    public static void main(String[] args){
        System.out.print("Size of array: ");
        Comparable[] array = new Integer[StdIn.readInt()];
        addNumbers(array);
        sort(array);
        show(array);

        System.out.print("Done");
    }
}