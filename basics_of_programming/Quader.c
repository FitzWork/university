#include <stdio.h>

int main()
{
    int x1,y1,z1,x2,y2,z2;
    printf("x1,y1,z1:\n");      //Abfrage der Werte
    scanf("%d %d %d",&x1,&y1,&z1);
    printf("x2,y2,z2:\n");
    scanf("%d %d %d",&x2,&y2,&z2);

    int a= (y1-y2); //L�nge
    if(a<0)a*=-1;   //Betrag von a falls n�tig
    int b= (x1-x2); //Breite
    if(b<0)b*=-1;   //Betrag von b falls n�tig
    int c= (z1-z2); //Tiefe
    if(c<0)c*=-1;   //Betrag von c falls n�tig

    int V = a*b*c;              //Volumen
    int A = 2*(a*b+a*c+b*c);    //Oberfl�che
    int Kl = 4*(a+b+c);         //Kantenl�nge

    printf("L=%d ; B=%d ; H=%d\nV=%d ; A=%d ; Kl=%d\n",a,b,c,V,A,Kl);

    return 0;
}
