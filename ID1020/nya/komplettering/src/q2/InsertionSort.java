package q2;

/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a Insertion Sort, Stable
 *
 */
public class InsertionSort {
    private InsertionSort() { }

    // sorts the subarray a[lo..hi) in ascending order
    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {   // is v < w
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {    // exchange a[i] and a[j]
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}