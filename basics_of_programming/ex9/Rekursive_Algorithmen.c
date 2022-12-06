#include <stdio.h>

int a(int n) {
	if (n == 1 || n == 0) return 1;                             // Rekurssionsende bei n=1 oder n=0 -> Rückgabe 1
	return a(n - 1)+a(n-2);                                     // sonst -> Rückgabe Selbstaufruf mit veringertem Wert
}

int b(int n, int ziffer) {
	if (n == 0) return 0;                                       // Rekurssionsende bei n=0
	if ((n - ziffer) % 10 == 0) return 1 + b(n / 10, ziffer);   // letzte Ziffer wird mit der gesuchten Ziffer verglichen, wenn gleich dann 1 +  neuen Rekurssionsaufruf
	return 0 + b(n / 10, ziffer);                               // sonst 0 + neuen Rekurssionsaufruf
}

int c(int n) {
	if (n/10 == 0) return 1;                                    // Rekurssionsende falls alle Zahlen verglichen wurden
	if ((n / 10) % 10 >= n % 10) return c(n / 10);              // Rekursiver Aufruf, wenn die letzte Ziffer kleiner gleich der vorletzen ist
	return 0;                                                   // falls nicht, dann Ende
}

int main() {                                                    // Beispiel Testausgabe ...
	int a1 = a(4);
	int a2 = a(5);
	int a3 = a(6);
	printf("a)\tf(4)=%d\n\tf(5)=%d\n\tf(6)=%d\n\n",a1,a2,a3);

	int b1 = b(2345678, 1);
	int b2 = b(5554555, 5);
	int b3 = b(1110111, 0);
	printf("b)\tAnzahl der Ziffer 1 in 2345678: %d\n\tAnzahl der Ziffer 5 in 5554555: %d\n\tAnzahl der Ziffer 0 in 1110111: %d\n\n",b1,b2,b3);

	int c1 = c(987654321);
	int c2 = c(123456789);
	int c3 = c(988852221);
    printf("c)\t0 bedeutet ist keine Zahlenfolge, 1 bedeutet ist eine \n\t987654321 ist %d\n\t123456789 ist %d\n\t988852221 ist %d\n",c1, c2, c3);

	return 0;
}
