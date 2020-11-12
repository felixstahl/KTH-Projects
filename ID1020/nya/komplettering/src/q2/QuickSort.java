package q2;


/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of QuickSort
 *
 * O(n logn) best & avarage
 * O(n^2) worst case
 *
 */
public class QuickSort {
    private static int CUTOFF = 0;  // cutoff to insertion sort, must be >= 1

    public QuickSort(int cutoff) {
        CUTOFF = cutoff;
    }

    //Rearranges the array in ascending order, using the natural order.
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
        //assert isSorted(a);  check each time that it is sorted
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        // cutoff to insertion sort (Insertion.sort() uses half-open intervals)
        int n = hi - lo + 1;
        if (n <= CUTOFF) {
            InsertionSort.sort(a, lo, hi + 1);
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;
        int m = median3(a, lo, lo + n/2, hi);
        exch(a, m, lo);

        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        // a[lo] is unique largest element
        while (less(a[++i], v)) {
            if (i == hi) { exch(a, lo, hi); return hi; }
        }

        // a[lo] is unique smallest element
        while (less(v, a[--j])) {
            if (j == lo + 1) return lo;
        }

        // the main loop
        while (i < j) {
            exch(a, i, j);
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // a[lo .. j-1] less / equal a[j] less / equal a[j+1 .. hi]
        // basically returns the element that is in its correct place
        return j;
    }

    // return the index of the median element among a[i], a[j], and a[k]
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
                (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    private static boolean less(Comparable v, Comparable w) {   // is v < w ?
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) { // exchange a[i] and a[j]
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /*private static boolean isSorted(Comparable[] a) {   //is it sorted?
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }*/
}