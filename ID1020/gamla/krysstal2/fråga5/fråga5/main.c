#include <stdio.h>
#include <stdlib.h>
/*
*               README - Felix Ståhl, assignment5 and 6
* This program implements a function in C which takes an array of integers (both positive and negative)
* and orders the elements in the array so that all negative elements come before the positive.
*(not allowed to sort the array - only collect all negative values first.)
* The algorithm should only use O(1) extra memory.
*/
void negative_first(int arr[]){                 // Switches places so that negative integers
    int swap_counter = 0;                       // place in the beginning.
    int temp;
    int i;
    for(i = 0; i < 6; i++){
        if(arr[i] < 0){                 // assignment 6(swap_counter <= i) is a statement which is always true
            temp = arr[i];              // before, after and during the iteration.
            arr[i] = arr[swap_counter];
            arr[swap_counter] = temp;
            swap_counter++;
        }
    }
}
void print(int arr[]){                          // prints the array
    int i;
    for(i = 0; i < 6; i++){
        printf("%d\n", arr[i]);
    }
}
int main(){
    int array[] = {-3, -4, -3, 6, 5, -1};
    negative_first(array);
    print(array);

    return 0;
}
