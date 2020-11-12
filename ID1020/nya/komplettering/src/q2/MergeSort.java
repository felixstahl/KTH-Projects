package q2;

/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a MergeSort
 *
 * O(n logn) Best, worst & avarage
 *
 */
public class MergeSort {
    private static int CUTOFF = 0;

    public MergeSort(int cutoff) {
        this.CUTOFF = cutoff;
    }

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {

        // precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        //assert isSorted(src, lo, mid);
        //assert isSorted(src, mid+1, hi);

        int i = lo, j = mid+1;  //
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++]; // is src[j] smaller than src[i], move to destination array
            else                           dst[k] = src[i++];
        }

       // assert isSorted(dst, lo, hi);
    }

    // src = source, dts = destination
    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;   // break into smaller arrays
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        // according to sedgewick, using System.arraycopy() is a bit faster than the above loop
        /*if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }*/

        merge(src, dst, lo, mid, hi);   // merge the arrays together
    }

    // sort the array in ascending order
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();       // first
        sort(aux, a, 0, a.length-1);
       // assert isSorted(a);
    }

    // sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // is a[i] < a[j]
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

   /* private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }*/

    /*private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }*/
}