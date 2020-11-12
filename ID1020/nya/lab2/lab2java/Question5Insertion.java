/**
 *              README
 *       Author: Felix Stahl
 *       Lab2 - Question 5 (Insertion sort)
 *
 * This file implements the Insertion sort with a 500 integer large array.
 * It also measures and prints the elapsed time for a partly sorted and totally random array.
 *
 * There is an analysis of the time measurement in a separate text file.
 *
 */

public class Question5Insertion {

    public static boolean isSorted(int[] array){
        for(int i = 0; i < (array.length - 1); i++){    //in the end of main check if the array is sorted
            if(array[i] > array[i+1])
                return false;
        }
        return true;
    }

    public static void insertionSort(int array[]){      // perform insertion sort
        int n = array.length;
        for (int i = 1; i < n; i++){     // start at second place in array
            int key = array[i];
            int j = i - 1;
            while((j > -1) && (array[j] > key)){
                array [j+1] = array [j];
                j--;
            }
            array[j+1] = key;
        }
    }

    public static void printArray(int[] array){     // print the array
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

    public static void largeArrayRandom(int[] array, int ranVal) {
        for (int i = 0; i < 500; i++) {
            array[i] = (int) (Math.random() * ranVal + 1) + ranVal; // add random values
        }
    }

    public static void main(String args[]) {
        int[] array = new int[500];             // create array
        largeArrayRandom(array, 353);     // generate 500 random integer array

        System.out.println("\nStart insertion sort timer of large random array");
        long start = System.nanoTime();
        insertionSort(array);                   // sort it and print elapsed time
        long stop = System.nanoTime();
        long elapsed = stop - start;
        System.out.println("Sorted array time: " + elapsed);
                    // is the array sorted?
        System.out.println("Check if array is sorted: " + isSorted(array));

        largeArrayPartlySorted(array, 353);     // generate a partly sorted array
        System.out.println("\nStart insertion sort timer of large partly sorted array");
        start = System.nanoTime();
        insertionSort(array);                         // sort it and print elapsed time
        stop = System.nanoTime();
        elapsed = stop - start;
        System.out.println("Sorted array time: " + elapsed);

                // is the array sorted?
        System.out.println("Check if array is sorted: " + isSorted(array));
    }
}