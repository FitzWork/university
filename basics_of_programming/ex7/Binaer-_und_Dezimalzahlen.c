#include <stdio.h>

int binaryToDecimal(int binZahl){                   //erzeugen einer Funktion mit Wert�bergabe
    int rest=0,dezZahl=0,zaehl=0;                   //erzeugen der Variblen mit Wertzuweisung
    while(binZahl>=1){                              //Z�hlschleife bis binZahl/10=0 ergibt
        rest=binZahl%10;                            //%10 um die letzte ziffer zu ermitteln
        if(rest==1){                                //falls diese letzte ziffer 1 ist
            for(int i=zaehl;i!=0;i--) rest=rest*2;  //so wird sie so oft mit 2 wie es bisherige Wiederholungen gab
            dezZahl=dezZahl+rest;                   //anschlie�end wir der errechnete Wert auf die bestehende dezZahl auf addiert
        }else if(rest>1){                           //falls der Benutzer an irgendeiner Stelle einen Wert >1 eingegeben hat wird abgebrochen
            dezZahl=0;                              //die entstehenede dezimal Zahl ist somit 0
            binZahl=0;
            }
        binZahl=binZahl/10;                         //binzahl wird um eine Stelle gek�rzt
        zaehl++;                                    //Anzahl der Wiederholungen wird um eins erh�ht
    }
    return dezZahl;                                 //berechnete Dezimalzahl wird zur�ckgegeben
}

int decimalToBinary(int dezZahl){                   //erzeugen einer Funktion mit Wert�bergabe
    int rest=0, binZahl=0, zaehl=0;                 //Variablen werden erzeugt
    while(dezZahl>=1){                              //Z�hlschleife bis dezZahl=0 ist
        rest=dezZahl%2;                             //modulo 2 um die n�chste stelle der bin�rzahl zu ermitteln
        if(rest==1){                                //sollte die dezzahl ungerade sein w�rde ein rest von 1 herrauskommen
            for(int i=zaehl;i!=0;i--) rest=rest*10; //diese 1 so oft mal mit 10 multipliziert wie es bisherige Wiederholungen gab
            binZahl=binZahl+rest;                   //anschlie�end wird der errechnete Wert auf die bestehenede binZahl auf addiert
        }
        dezZahl=dezZahl/2;                          //dezZahl wird halbiert (und dadruch, dass das komma abgeschnitten wird, auch abgerundet falls dezZahl ungerade ist)
        zaehl++;                                    //anzahl der Wiederholungen wird um eins erh�ht
    }
    return binZahl;                                 //Berechnete Bin�rzahl wird zur�ck gegeben
}

int main(){
    int dezein, dezaus, binein, binaus;             //anlegen der Variablen
    printf("Es sind fuer beide Umrechnungen leider nur Werte bis 1023 moeglich, da nur Interger gefordert sind!\nDezimalzahl >-1: ");
    scanf("%d",&dezein);                            //abfragen und abfangen der vom Benutzer eingegebenen Dezimalzahl
    binaus = decimalToBinary(dezein);               //binaus bekommt den Wert �bergeben den die Funktion decimalToBinary berechnet hat
    printf("%d als Binaerzahl: %d\n",dezein,binaus);
    printf("Binaerzahl >-1: ");                     //Abfrage und abfangen der vom Benutzer eingegebenen bin�r Zahl
    scanf("%d",&binein);
    dezaus = binaryToDecimal(binein);               //dezaus bekommt den Wert �bergeben den die Funktion binaryToDecimal berechnet hat
    printf("%d als Dezimalzahl: %d\n",binein,dezaus);
    return 0;
}

