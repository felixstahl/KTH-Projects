#include <stdio.h>
#include <stdlib.h>

/* *
*                   README -- Felix Ståhl
* This program implements an iterative function.
* First a structure for the node is created,
* to easier keep track of each nodes character and pointer to previous nodes.
* The main function creates the first node and as 'iterate' is called it makes sure
* to save the last node that 'iterate' will end at.
* After the iterative function is done the print function is called.
* It prints each character that each node holds and calls itself over and over until NULL.
*
* */
typedef struct node{                            // creating a structure to simplify the creation of nodes in the future
    char character;                             // of the program.
    struct node *next;
} node;

node *previous = NULL;

void iterate(){                                 // iterate collects a character from the user and as long as that character isn't
    char c = getchar();                         // end of line(enter) the while loop will keep looping and adding more nodes
    while(c != '\n'){                           // to the chain.
        add(c);
        c = getchar();
    }
}

void add(char c){                               // add creates a memoryspace for a new node and
    node *newNode = malloc(sizeof(node));       // puts the data(in this case characters) in the nodes variable
    newNode->character = c;                     //
    newNode->next = previous;
    previous = newNode;
}

void print(){                                   // this function creates a pointer which points to the previous node
    node *current = previous;                   // as long as it doesn't point to NULL, it will print the character
    while(current != NULL){                     // and move on to the next character until NULL.
        printf("%c\n", current->character);
        current = current->next;
    }
}

int main(){
    printf("Enter characters: \n");
    iterate();                                  // calls iterate one time
    print();                                    // print does as the name says, it prints the characters

    return 0;
}
