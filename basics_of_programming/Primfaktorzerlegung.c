#include <stdio.h>
#include <math.h>
#include <stdbool.h>

int main(){
    double squareroot;
    int x,input, prim=true, cancel=false;   //Variablen anlegen
    printf("Zahl > 1 eingeben: ");
    scanf("%d",&input);                     //Eingabe abfragen
    while(cancel==false){                   //solange kein Abbruch gegeben ist soll Schleife ausgeführt werden
        x=false;
        squareroot=sqrt(input);             //Quadratwurzel von Eingabe bestimmen um auch größere Eingaben effektiv berechnen zu können
        for(int n=2;n<=squareroot;n++){     //Zählschleife wird so lange ausgeführt wie Wert von n nicht größer als die Wurzel ist
            if(input%n==0){                 //wenn die Eingabe modulo n null ergibt soll
                printf("%d\n",n);           //n ausgegeben werden und
                prim=false;                 //für unterste if-Bedingung festgelegt werden, dass die Eingabe keine Primzahl ist
                input/=n;
                if(n==input){               //wenn n gleich der Eingabe ist kann die while-Schleife abgebrochen werden
                    printf("%d\n",n);
                    cancel=true;
                }
                n=squareroot;               //festlegen des Wertes der Variablen für neubegin der for-Schleife
                x=true;
            }
        }
        if(x==false){                       //wenn x nicht auf true gesetzt wurde wird direkt abgebrochen
            printf("%d\n",input);
            cancel=true;
        }
    }
    if(prim==true) printf("Primzahl, da es sich und 1 als einziege Teiler hat.\n");     //Erfassen ob Eingabe eine Primzahl ist
    return false;
}
