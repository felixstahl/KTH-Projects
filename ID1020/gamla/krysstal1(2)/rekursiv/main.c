#include <stdio.h>
#include <stdlib.h>

/* *
*
*                README -- Felix Ståhl
* This program implements a recursive function.
* First a struct for the node is created,
* to easier keep track of each nodes character and pointer to previous node
* The main function creates the first node and as 'recursive' is called it makes sure
* to save the last node that 'recursive' will end at.
* After the recursive function is done the print function is called.
* It prints each character that each node holds and calls itself over and over until NULL.
*
* */

typedef struct node{                            // creating a structure to simplify the creation of nodes in the future
    char character;                             // of the program.
    struct node *previous;
} node;

node *recursive(char input, node *current){     // a character from input and a pointer to current position is used as argument
    if(input == '\n') return current;           // everytime a character is entered. a place in the memory for it is saved.
    node *newNode = malloc(sizeof(node));       // The function calls itself until new line(enter) is the input.
    newNode->character = input;
    newNode->previous = current;
    recursive(getchar(), newNode);
}
void print(node *last){                         // a pointer to the last symbol is an argument
    if(last != NULL){                           // as long as the pointer doesn't point to NULL it will print
        printf("%c\n", last->character);        // characters.
        last = last->previous;
        print(last);
    }
}

int main(){
    printf("Enter Characters: \n");
    node *current = NULL;                       // creates first node
    current = recursive(getchar(), current);    // recursive is called one time from main, returns most recent node
    print(current);                             // print does as the name says, it prints each nodes char.
    return 0;
}
