/*
Task:
    - Input: Koordinaten zweier Eckpunkte
    - Berechnen und Ausgeben: Volumen, Oberfläche und Gesamtlänge aller Kanten
*/

#include <stdio.h>

int main()
{
    int x1,y1,z1,x2,y2,z2;
    printf("x1,y1,z1:\n");
    scanf("%d %d %d",&x1,&y1,&z1);
    printf("x2,y2,z2:\n");
    scanf("%d %d %d",&x2,&y2,&z2);

    int x= (x1-x2);
    if(x<0)x*=-1;
    int y= (y1-y2);
    if(y<0)y*=-1;
    int z= (z1-z2);
    if(z<0)z*=-1;

    int V = x*y*z;
    int A = 2*(x*y+x*z+y*z);
    int Kl = 4*(x+y+z);

    printf("B=%d ; L=%d ; H=%d\nV=%d ; A=%d ; Kl=%d\n",x,y,z,V,A,Kl);

    return 0;
}
