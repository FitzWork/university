#include <stdio.h>
#include <math.h>

int main(){
    double pi=0, zaehler=4, nenner= -1, bruch, x= -1, eingabe;  //Variablen erzeugen und ggf. Werte zuweisen
    printf("Gewuenschte Genauigkeit eingeben: ");
    scanf("%lf",&eingabe);                                      //scan der Eingabe als double-Wert
    do{                                                         //do-While-Schleife um mindestens einmal auzuführen
        x=x*(-1);                                               //Vorzeichen festlegen
        nenner=nenner+2;                                        //Nenner pro Durchlauf um 2 erhöhen
        bruch=zaehler/nenner;                                   //Bruch berechnen
        pi=pi+(bruch*x);                                        //neuen Bruch auf vorheriges Pi addieren
    }while(bruch>=eingabe);                                     //nach erstem Durchlauf prüfen ob die gewünschte Genauigkeit gegeben ist
    double dif=pi-M_PI;                                         //Berechnung der Differenz zum tatsächlichen PI
    printf("Das berechnete Pi weicht um %0.16f vom tatsaechlichem Pi ab.\n%0.16f (annaehernd)\n%0.16f (exakt)\n",dif,pi,M_PI);      //Differenz mit 10 Nachkommastellen ausgeben
    return 0;
}
