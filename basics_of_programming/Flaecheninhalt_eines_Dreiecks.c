#include <stdio.h>
#include <math.h>

int main(){
    double x1,x2,x3,y1,y2,y3,a,b,c,s,A;         //Anlegen der Variablen
    printf("erst x, dann y\nWerte P1:\n");      //Zuweisung von Werten durch Benutzer
    scanf("%lf%lf",&x1,&y1);
    printf("Werte P2:\n");
    scanf("%lf%lf",&x2,&y2);
    printf("Werte P3:\n");
    scanf("%lf%lf",&x3,&y3);

    a=sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));    //Berechnug der Seitenlängen
    b=sqrt((x2-x3)*(x2-x3)+(y2-y3)*(y2-y3));
    c=sqrt((x3-x1)*(x3-x1)+(y3-y1)*(y3-y1));
    s=(a+b+c)/2;                                //Berechnung der Hilfsvariablen nach Heron
    A=sqrt(s*(s-a)*(s-b)*(s-c));                //Berechnung des Flächeninhalts mit Satz von Heron
    printf("Flaecheninhalt: %0.2f FE\n",A);

    return 0;
}

