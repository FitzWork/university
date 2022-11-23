#include <stdio.h>

int main()
{
    char temp;      //Zwischepuffer für Char-Abfrage, sonst Probleme mit den im Tastaturpuffer gespeicherten ENTER-Eingaben

    double pi;
    printf("Add Pi to the sixth digit: ");      //Abfrage: Pi
    scanf("%lf%c", &pi,&temp);

    int feeling;
    printf("How do you feel on a scale, 1 is very bad and 10 is perfect? ");        //Abfrage: Gefühl
    scanf("%d%c", &feeling,&temp);

    int age;
    printf("Enter your age: ");     //Abfrage: Alter
    scanf("%d%c", &age,&temp);

    char familyname;
    printf("Enter the first letter of your family name: ");     //Abfrage: 1. Buchstabe von Nachname
    scanf("%c%c", &familyname, &temp);

    char firstname[10];
    printf("Enter your first name: ");      //Abfrage: gesamter Vorname
    scanf("%s", firstname);

    printf("Hello %s %c. you are %d years old and on a scale you feel like a %d. You also think that pi to the sixth digit is %lf.", firstname, familyname, age, feeling, pi);

    return 0;
}
