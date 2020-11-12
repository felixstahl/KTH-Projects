import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *              README -- Felix Ståhl, assignment 4
 * This program implements the Section sort algorithms in chapter 2.1.
 * The program prints the content of the array that is sorted.
 * The user enters the size of the array followed with the integer for each index in the array.
 * It will sort the array in descending order.
 * It prints the number of swaps performed, all of the inversions from the input,
 * also how many inversions there are.
 * Use the input [1, 2, 4, 3, 5, 0]
 *
 */
public class Selection{
    public static void sort(Comparable[] a){     // Sort a[] into increasing order.
        int swaps = 0;                           // number of swaps performed during sorting
        int N = a.length;                        // array length
        for (int i = 0; i < N; i++){             // Exchange a[i] with smallest entry in a[i+1...N).
            int min = i;                         // index of minimal entry.
            for (int j = i+1; j < N; j++)
                if (less(a[j], a[min])) {min = j;}
            exch(a, i, min);
            show(a);
            if(i != min) {
                swaps++;
                System.out.println("Swaps so far: " + swaps);
            }
        }
        System.out.println("Total number of swaps: " + swaps);
    }
    private static boolean less(Comparable v, Comparable w){return v.compareTo(w) < 0;}
    private static void exch(Comparable[] a, int i, int j){Comparable t = a[i]; a[i] = a[j]; a[j] = t;}
    private static void show(Comparable[] a) {              // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a) {        // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
    public static void addNumbers(Comparable[] array){      // adds integers to the array
        for(int i = 0; i < array.length; i++){
            System.out.print("Enter number for array index " + i + ": ");
            array[i] = StdIn.readInt();
        }
    }
    public static void sortDescending(Comparable[] array){  // sorts in descending order.
        for(int i = 0; i < array.length; i++){              // By multiplying all elements with -1,
            array[i] = (int)array[i] * (-1);                // sort so that the smallest has the first index
        }                                                   // and then multiplying by -1. This method will result
        sort(array);                                        // in the biggest element on index 0
        for(int i = 0; i < array.length; i++){
            array[i] = (int)array[i] * (-1);
        }
    }
    public static void inversion_counter(Comparable[] a){   // counts inversions and prints the pairs.
        int N = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++){
            for(int j = i + 1; j < a.length; j++){
                if((int)a[i] > (int)a[j] && i < j){
                    N++;
                    sb.append("[" + i + "," + a[i] + "]," + "[" + j + "," + a[j] + "]\n ");
                }
            }
        }
        System.out.println("Total number of inversions: " + N + ".\nThe pairs are:\n " + sb);
    }

    public static void main(String[] args){
        System.out.print("Size of array: ");
        Comparable[] array = new Integer[StdIn.readInt()];
        addNumbers(array);
        show(array);
        inversion_counter(array);
        sortDescending(array);
        show(array);

        System.out.print("Done");
    }
}