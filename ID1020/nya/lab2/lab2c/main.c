#include <stdio.h>

/*
*              README
*       Author: Felix Stahl
*       Lab2 - Question 4
*
* This program implements a function in C which takes an array of integers (both positive and negative)
* and orders the elements in the array so that all negative elements come before the positive.
* (not allowed to sort the array - only collect all negative values first.)
* The algorithm only use O(1) extra memory.
*
*/
void negative_first(int arr[]){        // Switches places of integers so that negative ...
    int swap_counter = 0;              // ... are in the beginning.
    int temp;
    int i;
    for(i = 0; i < 6; i++){
        if(arr[i] < 0){                     // Only enter here if negative integer.
            temp = arr[i];                  // Because i is increasing each turn and swap_counter only increase...
            arr[i] = arr[swap_counter];     // when a negative is found, the algorithm will keep pushing...
            arr[swap_counter] = temp;       // positives to the right until another positive is found, ...
            swap_counter++;                 // then nothing happens until it finds another negative, ...
        }                                   // it will then switch the leftmost positive with the...
    }                                       // just found negative.
}
void print(int arr[]){            // print the array
    int i;
    for(i = 0; i < 6; i++){
        printf("%d ", arr[i]);
    }
}
int main(){
    int array[] = {3, -4, -3, 6, 5, -1};
    printf("Before re-order: ");
    print(array);
    negative_first(array);
    printf("\nAfter re-order: ");
    print(array);

    return 0;
}
