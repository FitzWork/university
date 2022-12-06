#include <stdio.h>

void MQ(int n,int a[n][n]){                                         //Methode zum eintragen der Werte
    int x=(n-2)/2, y=n-2, max=n*n;                                  //x und y Koordinaten sowie der größte Wert wird erzeugt und berechnet
    for(int counter=1;counter<=max;counter++){                      //wiederholt bis alle Werte eingetragen sind und der counter max erreicht hat
        x=x+1;                                                      //x und y werden um eins erhöht und
        y=y+1;
        if(x>n-1)x=0;                                               //ggf werden die Werte angepasst falls sie den Index über- oder unterschreiden
        if(y>n-1)y=0;
        if(a[x][y]!=0){                                             //sollte an der Stelle ein anderer Wert außer 0 stehen
            x=x-1;                                                  //wird das wiederholt, nur dass x um 1 verringert und nicht erhöht wird
            y=y+1;
            if(x<0)x=n-1;
            if(y>n-1)y=0;
        }
        a[x][y]=counter;                                            //am Ende wird der Wert an die richtige Stelle eingetragen
    }
}

int main(void){
    int n=0;
    while(1){                                                       //so lange wie Eingabe nicht den Anforderungen entspricht wird wiederholt
        printf("Zahl eingeben die groeßer 0 und ungerade ist: ");
        scanf("%d",&n);
        if((n>0)&&(n%2==1))break;
        printf("Diese Zahl ist leider nicht erlaubt.\n");
    }
    int a[n][n];                                                    //Array mit n Länge und Breite erzeugt
    for(int y=0;y<n;y++){                                           //an jede Stelle des Arrays wird 0 eingetragen
        for(int x=0;x<n;x++){
            a[x][y]=0;
        }
    }
    MQ(n,a);                                                        //Methodenaufruf
    printf("\n");
    for(int y=0;y<n;y++){                                           //Array wird mit den eingetragenen Werten ausgegeben
        for(int x=0;x<n;x++){
            printf("%d\t",a[x][y]);
        }
        printf("\n");
    }
    return 0;
}
