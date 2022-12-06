#include <stdio.h>

int main(){
    double Gewinn, Kapital, Zinssatz, Startkapital;           //Anlegen der Variablen
    int Jahre;
    printf("Startkapital: ");                   //Zuweisung von Werten an Variablen durch Benutzer
    scanf("%lf", &Startkapital);
    printf("Aktueller Zinssatz: ");
    scanf("%lf", &Zinssatz);
    printf("Lauzeit in Jahren: ");
    scanf("%d", &Jahre);
    Kapital=Startkapital;                       //Startkapital wird an Kapital übergeben, damit Starkapital für spätere Berechung des Gewinns gesichert ist

    for(int n=1;n<=Jahre;n++){                  //Zähl-Schleife für Wiederholung der gewünschten Laufzeit
        Kapital=Kapital*((Zinssatz/100)+1);     //Berechnung des Gesamtenkapitels nach Jahr n
        Gewinn=Kapital-Startkapital;            //Berechnung des insgesamten Gewinns nach n Jahren
        printf("Nach dem %d. Jahr eine Summe von %0.2f und damit insgesamt %0.2f Gewinn gemacht.\n",n,Kapital,Gewinn);
    };
    return 0;
}
