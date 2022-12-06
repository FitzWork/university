package Aufgabenblatt_1;
import java.util.Scanner;

public class Fibonacci_Folge {
    static void fib(int in){                                // Fib-Zahlen-Methode wird für wiederholbaren Aufruf erzeugt
        int i, rem1 = 1, rem2 = 0, sum = 0;                 // diverse Hilfsvariablen werden deklariert und definiert
        if (in != 0){                                       // sollte die Eingabe 0 sein, so ist die dazu gehärige Fib-Zahl 0
            for (i = 1;(in & i) == 1 || i < in; i++){       // sollte sie != 0 sein
                sum = rem1 + rem2;                          // so wird die zugehörige Fib-Zahl iterativ ermittelt
                rem2 = rem1;
                rem1 = sum;
            }
        }
        System.out.println("Die " + in + ". Fibonacci Zahl hat den Wert: " + sum);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Eingabewert (0 oder größer): ");
        int in = s.nextInt();                               // Eingabe wird abgefangen
        if(in >= 0)                                         // sollte die Eingabe negativ sein,
            fib(in);                                        // wird die Methode nicht ausgeführt
    }
}