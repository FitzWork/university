/*1.Aufgabe
    a)
        1.Ausdruck ist false
        2.Ausdruck ist true
        3.Ausdruck ist true
        4.Ausdruck ist true
    b)
        1.Ausdruck: !(m>=10)
        2.Ausdruck: (x>=0)&&(y>=0)&&(z>=0))&&(((x%5)==0)&&((y%5)==0)&&((z%5)==0))   //Kommazahlen werden ausgeschlossen, da nur natürliche Zahlen abgefragt werden (0 gehört in diesem Beispiel dazu)
        3.Ausdruck: (1<a<10)||(a<-7)
        4.Ausdruck: (1)     //wenn <stdbool.h> eingebunden wurde, dann ginge auch true statt 1

2. Aufgabe*/
#include <stdio.h>
int main(){
    int start, ziel, preis=3;       //Variablen erzeugen und den Preis auf 3 (Pauschale) setzen
    printf("Starthaltestelle: "); scanf("%d", &start);     //Starthaltestelle
    printf("Zielhaltestelle: "); scanf("%d", &ziel);       //Zielhaltestelle
    if((start%10)<3){       //Start ist in inneren Zone
        if((ziel%10)>2) preis+=1;       //Ziel ist in äußerer Zone
        if((ziel%10)==5) preis+=1;      //Ziel = Endstation
        if((((ziel%10)==2)&&((start%10)==2))&&((ziel==(start-10))||(ziel==(start+10)))) preis-=1;       //Start und Ziel sind nebeneinander und haben Zahl 2 an zweiter Stelle
        if(((start==12)&&(ziel==52)) || ((start==52)&&(ziel==12))) preis-=1;        //Start oder Ziel sind 52 oder 12
        if((ziel==start-1)||(ziel==start+1)) preis-=1;      //Start und Ziel sind benachbart
        if(((start==00)||(ziel==00))&&(((start%10)==1)||((ziel%10)==1))) preis-=1;     //Start oder Ziel ist 00 und Start oder Ziel ist benachbart
    }
    if((start%10)>2){     //Start ist in äußerer Zone
        if((ziel%10)<3) preis+=1;       //Ziel ist inneren Zone
        if((ziel%10)==5) preis+=1;      //Ziel = Endstation
        if((start%10)==5) preis+=1;     //Start = Endstation
        if((((ziel/10)%10)!=((start/10)%10))&&((ziel%10)>2)) preis+=2; //Start & Ziel sind in äußerer Zone aber nicht gleiche Linie
        if((ziel==start-1)||(ziel==start+1)) preis-=1;        //Start und Ziel sind benachbart
    }
    if(((ziel==42)&&(start==52))||((ziel==52)&&(start==42))||((ziel==21)&&(start==00))||((ziel==00)&&(start==21))) preis+=1;    //durch Baustelle Umweg -> (+1)
    printf("Preis: %d Euro\n", preis);
    return 0;
}
