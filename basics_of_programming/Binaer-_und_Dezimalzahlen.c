#include <stdio.h>

int binaryToDecimal(int binZahl){                   //erzeugen einer Funktion mit Wertübergabe
    int rest=0,dezZahl=0,zaehl=0;                   //erzeugen der Variblen mit Wertzuweisung
    while(binZahl>=1){                              //Zählschleife bis binZahl/10=0 ergibt
        rest=binZahl%10;                            //%10 um die letzte ziffer zu ermitteln
        if(rest==1){                                //falls diese letzte ziffer 1 ist
            for(int i=zaehl;i!=0;i--) rest=rest*2;  //so wird sie so oft mit 2 wie es bisherige Wiederholungen gab
            dezZahl=dezZahl+rest;                   //anschließend wir der errechnete Wert auf die bestehende dezZahl auf addiert
        }else if(rest>1){                           //falls der Benutzer an irgendeiner Stelle einen Wert >1 eingegeben hat wird abgebrochen
            dezZahl=0;                              //die entstehenede dezimal Zahl ist somit 0
            binZahl=0;
            }
        binZahl=binZahl/10;                         //binzahl wird um eine Stelle gekürzt
        zaehl++;                                    //Anzahl der Wiederholungen wird um eins erhöht
    }
    return dezZahl;                                 //berechnete Dezimalzahl wird zurückgegeben
}

int decimalToBinary(int dezZahl){                   //erzeugen einer Funktion mit Wertübergabe
    int rest=0, binZahl=0, zaehl=0;                 //Variablen werden erzeugt
    while(dezZahl>=1){                              //Zählschleife bis dezZahl=0 ist
        rest=dezZahl%2;                             //modulo 2 um die nächste stelle der binärzahl zu ermitteln
        if(rest==1){                                //sollte die dezzahl ungerade sein würde ein rest von 1 herrauskommen
            for(int i=zaehl;i!=0;i--) rest=rest*10; //diese 1 so oft mal mit 10 multipliziert wie es bisherige Wiederholungen gab
            binZahl=binZahl+rest;                   //anschließend wird der errechnete Wert auf die bestehenede binZahl auf addiert
        }
        dezZahl=dezZahl/2;                          //dezZahl wird halbiert (und dadruch, dass das komma abgeschnitten wird, auch abgerundet falls dezZahl ungerade ist)
        zaehl++;                                    //anzahl der Wiederholungen wird um eins erhöht
    }
    return binZahl;                                 //Berechnete Binärzahl wird zurück gegeben
}

int main(){
    int dezein, dezaus, binein, binaus;             //anlegen der Variablen
    printf("Es sind fuer beide Umrechnungen leider nur Werte bis 1023 moeglich, da nur Interger gefordert sind!\nDezimalzahl >-1: ");
    scanf("%d",&dezein);                            //abfragen und abfangen der vom Benutzer eingegebenen Dezimalzahl
    binaus = decimalToBinary(dezein);               //binaus bekommt den Wert übergeben den die Funktion decimalToBinary berechnet hat
    printf("%d als Binaerzahl: %d\n",dezein,binaus);
    printf("Binaerzahl >-1: ");                     //Abfrage und abfangen der vom Benutzer eingegebenen binär Zahl
    scanf("%d",&binein);
    dezaus = binaryToDecimal(binein);               //dezaus bekommt den Wert übergeben den die Funktion binaryToDecimal berechnet hat
    printf("%d als Dezimalzahl: %d\n",binein,dezaus);
    return 0;
}

