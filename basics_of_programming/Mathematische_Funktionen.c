#include <stdio.h>
#include <math.h>

double f1(double x,double y){               //Funktion f1 an welche die Werte x und y übergeben bekommt wird erzeugt
    double zaehler=x+y, nenner=x-y, bruch;  //Erzeugen der Variablen und berechnen von zaehler und nenner
    if(nenner==0) bruch=0;                  //fall der nenner gleich null sein sollte ist soll das Ergebnis der Bruchs 0 sein
    else bruch=zaehler/nenner;              //sonst wird der Bruch bestimmt
    return bruch;                           //berechnete Bruch wird zurückgegeben
}
double f2(double x, double y){              //Funktion f2 an welche die Werte x und y übergeben bekommt wird erzeugt
    double wurzel=sqrt((x*x)+(y*y));        //Wurzel wird berechnet
    return wurzel;                          //berechnete Wurzel wird zurückgegeben
}

int main(){
    double x1,x2,y1,y2,ft1,ft2;             //erzeugen der Variablen
    printf("x und y f1: \n");               //erfassen der x und y Werte für f1
    scanf("%lf%lf",&x1,&y1);
    printf("x und y f2: \n");               //erfassen der x und y Werte für f2
    scanf("%lf%lf",&x2,&y2);
    ft1=f1(x1,y1);                          //ft1 wird der in f1 berechnete Wert zugewiesen
    ft2=f2(x2,y2);                          //ft2 wird der in f2 berechnete Wert zugewiesen
    printf("\nErgebnis f1: %f\nErgebnis f2: %f\n",ft1,ft2); //Ausgabe der berechneten Ergebnisse
    return 0;
}

