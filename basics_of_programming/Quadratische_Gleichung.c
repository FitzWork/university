#include <stdio.h>
#include <math.h>

int solve(double a,double b,double c,double *x1,double *x2){    //erzeugen der Funktion mit Wertübergabe und Zeiger auf x1 und x2
    int Anzahl;                                                 //deklarieren der zurückzugebenden Variable
    double D=(b*b)-(4*a*c);                                     //Berechnung der Diskriminanten ermöglicht es im nächsten Schritt zu prüfen
    if(D<0||a==0)Anzahl=0;                                      //ob die Determinante kleiner als 0 ist und somit kein x existiert genauso wie wenn a=0 ist, falls ja wird anzahl gleich null gesetzt, da kein x zur lösung der quadratischen Gleichung existiert
    else if(D==0){                                              //falls die Determinante gleich null ist gibt es eine Lösung
        *x1=-(b/(2*a))+sqrt(((b*b)-4*a*c)/(4*(a*a)));           //x1 in der main ergibt sich aus der allgemeinen Lösungsformel von Quadratischen Gleichungen
        Anzahl=1;                                               //da ein x existiert wird Anzahl der Wert 1 zugewiesen
    }
    else if(D>0){                                               //falls die Determinante jedoch größer als 0 ist, dann gibt es zwei mögliche lösungen für x
        *x1=-(b/(2*a))+sqrt(((b*b)-4*a*c)/(4*(a*a)));           //x1 in der main ergibt sich aus der allgemeinen Lösungsformel von Quadratischen Gleichungen
        *x2=-(b/(2*a))-sqrt(((b*b)-4*a*c)/(4*(a*a)));           //x2 in der main ergibt sich aus der allgemeinen Lösungsformel von Quadratischen Gleichungen
        Anzahl=2;                                               //da es zwei mögliche x-Werte gibt wird Anzahl der Wert 2 zugewiesen
    }
    return Anzahl;                                              //Anzahl wird an Funktionsaufruf übergeben
}

int main(){                                                     //Vorgegebene Main-Methode
    double a,b,c;
    scanf("%lf %lf %lf",&a,&b,&c);
    double x1,x2;
    int anzahl=solve(a,b,c,&x1,&x2);
    if(!anzahl)printf("keine Loesung!");
    else if(anzahl==1)printf("eine Loesung x: %f",x1);
    else if(anzahl==2)printf("zwei Loesungen x1: %lf x2: %lf",x1,x2);
    return 0;
}
