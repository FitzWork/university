#include <stdio.h>

int linearSearch(int n, int a[], int length){           //lineare Suche wie in Foliensatz 10 auf der Folie 8
    int pos=length-1;
    while(pos>=0 && a[pos]!=n)pos--;
    return pos;
}

int binarySearch(int n,int a[],int length){             //binäre Suche mit Rückgabewert
    int og=length, ug=0,mw=0,lastmw=1;                  //obere und untere Grenze sowie Mittelwert wird erzeugt und bekommt Werte zugewiesen
    while(mw!=lastmw){                                  //solange wie der Mittelwert nicht dem letzten entspricht
        lastmw=mw;
        mw=ug+((og-ug)/2);                              //Wert zwischen oberer und unterer Grenzen wird neuer Mittelwert
        if(a[mw]==n)return mw;                          //falls gefunden den Wert zurück
        if(a[mw]>n)og=mw;                               //falls nicht, dann prüfen ob größer oder kleiner
        if(a[mw]<n)ug=mw;                               //und Grenzen ggf anpassen
    }
    return -1;                                          //falls gesuchtes Element nicht vorhanden, dann -1 als Rückgabewert
}

int binarySearchPointer(int n,int *a,int length){       //binäre Suche grundlegen wie die obere nur, dass a einen Pointer ist
    int og=length, ug=0,mw,lastmw,k;
    while(mw!=lastmw){
        k+=1;
        lastmw=mw;
        mw=ug+((og-ug)/2);
        if(*(a+mw)==n)return mw;                        //somit statt a[mw]   *(a+mw)
        if(*(a+mw)>n)og=mw;
        if(*(a+mw)<n)ug=mw;
    }
    return -1;
}

int main(){
    int a[]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97}, length=25, n,pos=length-1;
    printf("Gesuchte Zahl eingeben: ");
    scanf("%d\n",&n);

    int poslS=linearSearch(n, a, length);               //Aufruf der Methoden mit Rückgabe des Indey
    int posbS=binarySearch(n, a, length);
    int posbSP=binarySearchPointer(n, a, length);

    if(poslS!=-1)printf("Der lineargesuchte Wert ist enthalten und besitzt den Index %d\n",poslS);
    if(posbS!=-1)printf("Der binaergesuchte Wert ist enthalten und besitzt den Index %d\n",posbS);
    if(posbSP!=-1)printf("Der mit Pointer binaergesuchte Wert ist enthalten und besitzt den Index %d\n", posbSP);
    else printf("Der gesuchte Wert ist nicht enthalten.\n");    //sollte der Index -1 sein, dann wird zurückgegeben, dass es nicht enthalten ist

    return 0;
}
