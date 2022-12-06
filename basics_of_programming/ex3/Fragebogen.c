/*
Task: 
    - Fünf selbstgewählte Fragen stellen
    - Antwortmöglichkeit als:
        - Char
        - Int
        - Double
    - Antworten in umgekehrter Reihenfolge (zur Eingabe) in einer Zeile ausgeben
*/

#include <stdio.h>

int main()
{
    char temp;      //Zwischepuffer fuer ENTER-Eingaben

    double pi;
    printf("sixth digit of pi: ");
    scanf("%lf%c", &pi,&temp);

    int feeling;
    printf("Feeling from 0(baddest) to 9(best): ");
    scanf("%d%c", &feeling,&temp);

    int age;
    printf("age: ");
    scanf("%d%c", &age,&temp);

    char familyname;
    printf("first letter of family name: ");
    scanf("%c%c", &familyname, &temp);

    // Zusatzfrage
    char firstname[10];
    printf("first name: ");
    scanf("%s", firstname);

    printf("%s %c is %d years old, feel like a %d and think that the sixth digit of pi is %lf.", firstname, familyname, age, feeling, pi);

    return 0;
}
