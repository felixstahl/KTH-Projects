/**
 *              README
 *       Author: Felix Stahl
 *       Lab2 - Question 5 (Merge sort)
 *
 * This file implements Merge sort
 * It also measures and prints the elapsed time for a partly sorted and totally random array.
 *
 * There is an analysis of the time measurement in a separate text file.
 *
 */

public class Question5Merge {

    public static boolean isSorted(int[] array){
        for(int i = 0; i < (array.length - 1); i++)    //in the end of main check if the array is sorted
            if(array[i] > array[i+1])
                return false;
        return true;
    }

    // Merge two sub-arrays. First sub-array is array[left..middle], second sub-array is array[middle+1..right]
    public static void merge(int array[], int left, int middle, int right) {
        int n1 = middle - left + 1;  // Find sizes of two sub-arrays to be merged
        int n2 = right - middle;

        int Left[] = new int[n1];      // Create temporary arrays
        int Right[] = new int[n2];

        for (int i = 0; i < n1; ++i)    // Copy data to temporary arrays
            Left[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            Right[j] = array[middle + 1 + j];

        int i = 0;                  // First indexes of first and second sub-arrays
        int j = 0;

        int k = left;                  // First index of merged sub-array array
        while (i < n1 && j < n2) {
            if (Left[i] <= Right[j]) {
                array[k] = Left[i];
                i++;
            }
            else {
                array[k] = Right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {    // Copy remaining elements of Left[], if there is any
            array[k] = Left[i];
            i++;
            k++;
        }

        while (j < n2) {    // Copy remaining elements of Right[], if there is any
            array[k] = Right[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] array, int left, int right) {    // sorts array[left..right] with merge()
        if (left < right) {
            int middle = (left + right) / 2;      // Find the middle

            mergeSort(array, left, middle);       // Sort first and second halves
            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);    // Merge the sorted halves
        }
    }

    public static void printArray(int[] array){   // print the array
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static void largeArrayPartlySorted(int[] array, int ranVal){
        int incrementingInteger = 0;
        for(int i = 0; i < 250; i++){
            array[i] = incrementingInteger;     // first 250 elements are sorted in ascending order from 0
            incrementingInteger++;
        }
        for(int i = 251; i < 500; i++){
            array[i] = (int)(Math.random() * ranVal + 1) + ranVal; // add random values to index 251 up to 499
        }
    }

    public static void largeArrayRandom(int[] array, int ranVal){
        for(int i = 0; i < 500; i++){
            array[i] = (int)(Math.random() * ranVal + 1) + ranVal; // add random values
        }
    }

    public static void main(String args[]) {
        int[] array = new int[500];              // create array
        largeArrayRandom(array, 353);      // generate 500 random integer array

        System.out.println("\nStart merge sort timer of large random array");
        long start = System.nanoTime();
        mergeSort(array, 0, array.length - 1);      // sort it and print elapsed time
        long stop = System.nanoTime();
        long elapsed = stop - start;
        System.out.println("Sorted array time: " + elapsed);
                                                             // is the array sorted?
        System.out.println("Check if array is sorted: " + isSorted(array));

        largeArrayPartlySorted(array, 353);            // generate a partly sorted array
        System.out.println("\nStart merge sort timer of large partly sorted array");
        start = System.nanoTime();
        mergeSort(array, 0, array.length - 1);      // sort it and print elapsed time
        stop = System.nanoTime();
        elapsed = stop - start;
        System.out.println("Sorted array time: " + elapsed);

                                                             // is the array sorted?
        System.out.println("Check if array is sorted: " + isSorted(array));
    }
}