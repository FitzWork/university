#include <stdio.h>

int main(){
    double weiteste1=0, weiteste2=0, kuerzesete, anzahl=0, aktuell, summe, aM, diff;      //Erzeugen der Variablen und ggf Zuweisung von Werten
    printf("1. Sprung: ");                      //Abfrage des ersten Sprungwertes
    scanf("%lf", &aktuell);
    kuerzesete=aktuell;                         //Setze den ersten Wert als kleinsten Wert

    while(aktuell>0){                           //while-Schleife bis Benutzer eine Eingabe von null oder kleiner macht
        anzahl+=1;                              //Anzahl an Spüngen wird um 1 erhöht
        if(aktuell>weiteste2){                  //Prüfen ob aktuell besser als der zweit Beste ist
            if(aktuell>weiteste1){               //Prüfen ob aktuell besser als der Beste ist
                    weiteste2=weiteste1;        //der bis jetzt Beste wird auf zweitbester überschrieben
                    weiteste1=aktuell;          //aktuell wird zu dem neuen Besten
            }
            else weiteste2=aktuell;             //aktuell wird sonst zu dem zweit Besten
        }
        summe+=aktuell;                         //summe aller Werte wird um aktuell erhöht
        aM=summe/anzahl;                        //Berechnung des arithmetischen Mittels
        if(aktuell<kuerzesete) kuerzesete=aktuell;  //wenn aktuell kleiner als
        diff=weiteste1-kuerzesete;              //Berechnung der Differenz
        printf("%0.0f. Sprung: ", anzahl+1);    //Abfrage des nächsten Sprungwertes
        scanf("%lf", &aktuell);                 //aktuell wird mit neuem Wert des Benutzers überschrieben
    }
    printf("Anzahl der Spuenge: %0.0f\nDie zwei weitesten Spuenge waren: %0.2f LE und %0.2f LE\nDer Mittelwert ist: %0.2f LE\nUnd die Differenz zwischen dem weitesten und dem kuerzesten betraegt: %0.2f LE\n", anzahl, weiteste1, weiteste2, aM, diff);
    return 0;
}


