/**
 *              README
 *       Author: Felix Stahl
 *       Lab2 - Question 1
 *
 * This file implements the Insertion sort.
 * It asks the person running for a size of the initial array
 * and then wants the input to the array. One by one, press enter in between.
 *
 * Try with array size 6 and input [1,2,4,3,5,0].
 *
 */

import java.util.Scanner;

public class Question1 {

    public static Scanner scan = new Scanner(System.in);    // scanner for user to input integers

    public static boolean isSorted(int[] array){
        for(int i = 0; i < (array.length - 1); i++){   //in the end of main check if the array is sorted
            if(array[i] > array[i+1])
                return false;
        }
        return true;
    }

    public static void insertionSort(int array[]){      // perform insertion sort
        System.out.print("Before insertion sort: ");
        printArray(array);

        int n = array.length;
        for (int i = 1; i < n; i++){       // start at second place in array
            int key = array[i];
            int j = i - 1;
            while((j > -1) && (array[j] > key)){
                array [j+1] = array [j];
                j--;
                printArray(array);  // print array after each inner loop iteration
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
    public static void addNumbersToArray(int arraySize, int[] array){  // adds integer to the array
        for(int i = 0; i < arraySize; i++){
            System.out.print("Enter number for array index " + i + ": ");
            array[i] = scan.nextInt();
        }
    }

    public static void main(String[] args){
        System.out.print("Size of array: ");
        int sizeOfArray = 0;                    // what size of array do you want?
        while(scan.hasNext()) {
            if(scan.hasNextInt()) {
                sizeOfArray = scan.nextInt();
            break;
            }
        }

        int[] arrayToSort = new int[sizeOfArray];   // create an array of your selected size
        addNumbersToArray(sizeOfArray, arrayToSort);    // add numbers to the array

        insertionSort(arrayToSort);         // insertion sort the array

        System.out.println("Finished: ");   // done

        printArray(arrayToSort);    // print the array

                // is the array sorted?
        System.out.println("Check if array is sorted: " + isSorted(arrayToSort));
    }
}