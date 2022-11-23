#include <stdio.h>

int main(){
    int tempF, tempC;

    printf("Add a temperature in degrees Fahrenheit and this is then converted to degrees Celsius: ");
    scanf("%d", &tempF);        //Abfrage der Temperatur in Fahrenheit

    if (tempF>=0){      //Prüfen ob Eingabe größer als 0 ist
        tempC = (tempF-32)*5/9;     //Umrechnung in Celsius
        printf("%d degrees Fahrenheit is round about %d degrees Celsius.\n", tempF, tempC);}
    else printf("This was the wrong answer."); //Fehlerausgabe falls Eingabe kleiner als 0 ist

    return 0;
}
