#include <stdio.h>
#include <stdlib.h>

int test(int a){
    int result = 0;
    for(int i=0; i<100; i++){
        result = result + 50 - i;
    }
    return result;
}
int main()
{
    int result = test(23);
    printf("%d", result);
    return 0;
}
