package Aufgabenblatt_1;
import java.util.Scanner;

public class Anstossen {
    static int prost(int n){
        if(n < 2)                                               // sollte die Personenanzahl kleiner als 2 sein,
            return 0;                                           // so kann nicht angestoßen werden
        if(n == 2)                                              // sollten genug Personen für einen Anstoß da sein,
            return 1;                                           // so ist 2 das Abbruchkriterium für die Rekursion
        else                                                    // sollte die Anzahl größer als 2 sein,
            return (n-1)+prost(n-1);                        // so werden die Anstöße rekursiv ermittelt
    }

    int pröstercher(int n){
        if(n < 2)
            return 0;
        return pröstercher(n - 1) + n - 1;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Personenanzahl: ");
        int people = s.nextInt();                               // Anzahl der Personen wird abgefangen
        System.out.println("Anstöße: " + prost(people));        // Methoden-Aufruf direkt in Ausgabe
    }
}
