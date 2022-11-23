#include <stdio.h>

int main(){
    int Ergebnis;
    printf("Alle froehlichen Zahlen zwischen 1 und 500 sind:\n");
    for(int Zahl=1;Zahl<=500;Zahl++){                   //Zählschleife um alle Zahlen des Intervalls abzufangen
        Ergebnis = Zahl;                                //Ergebnis wird mit Wert von Zahl überschrieben, damit Zahl später ausgegeben und immer um 1 erhöht werden kann
        do{                                             //do-while-Schleife, um mindestens einen Durchlauf zu gewehrleisten
            Ergebnis = ((Ergebnis/100)%10)*((Ergebnis/100)%10) + ((Ergebnis/10)%10)*((Ergebnis/10)%10) + (Ergebnis%10)*(Ergebnis%10);       //Berechnung des Zwischenergebnisses
        }while(Ergebnis!=20 && Ergebnis!=4 && Ergebnis!=16 && Ergebnis!=37 && Ergebnis!=58 && Ergebnis!=89 && Ergebnis!=145 && Ergebnis!=42 && Ergebnis!=1);        //wenn Ergebnis einem dieser Werte entspricht, dann Abbruch
        if(Ergebnis==1){
            printf("%d\n",Zahl);                        //Zahl wird ausgegeben, wenn Ergebnis dem Wert 1 entspricht
        }
    }
    return 0;
}
