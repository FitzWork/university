/*
Task:
    - Input Int >= 0
    - Input umrechen von Fahrenheit in Celsius (Nachkomma vernachlässigen)
    - Umrechung ausgeben
*/

#include <stdio.h>

int main(){
    int tempF, tempC;

    printf("temperature in degrees Fahrenheit: ");
    scanf("%d", &tempF);

    if (tempF>=0){
        tempC = (tempF-32)*5/9;
        printf("%d degrees Fahrenheit = %d degrees Celsius.\n", tempF, tempC);}
    else printf("input < 0");

    return 0;
}
