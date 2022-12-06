package Aufgabenblatt_1;

public class Arrays_und_Schleifen {
    public static void main(String[] args){
        int [] a = {1,2,3,4,-1,-2,-3,-4};                           // Array der Aufgabe übernommen
        System.out.println(java.util.Arrays.toString(a));

        System.out.println("\nc)");                               // TEILAUFGABE 1.c)
        int countodd = 0, counteven = 0, i;                         // notwendige Variablen werden erzeugt
        if (a.length % 2 == 0){                                     // prüfen ob das Array eine gerade oder ungerade Anzahl an Elementen besitzt
            countodd = a.length/2;
            counteven = a.length/2;
        }
        else{
            countodd = a.length/2;
            counteven = 1 + a.length/2;                             // falls ungerade, dann bekommt das Teilarray mit geraden Index-Elementen ein Element mehr Platz
        }
        int [] a1 = new int[counteven], a2 = new int[countodd];     // Teilarrays werden mit entsprechender Länge angelegt
        // optional auch:   int [] a1= new int[a.length%2==0 ? a.length/2 : a.length/2+1];
        counteven = 0; countodd = 0;
        for (i = 0; i < a.length; i++){                             // gesamte Array a wird betrachtet
            if (i % 2 == 0)                                         // ist der Index gerade, so wird das Element in a1 geschrieben
                a1[counteven++] = a[i];
            else                                                    // sonst muss es ungerade sein und landet in a2
                a2[countodd++] = a[i];
        }
        // Ausgabe beider finaler Teilfelder
        System.out.println("gerader Index: " + java.util.Arrays.toString(a1));
        System.out.println("ungerader Index: " + java.util.Arrays.toString(a2));

        System.out.println("\nd)");                                 // TEILAUFGABE 1.d)
        countodd = 0; counteven = 0;                                // Hilfsvariablen werden wieder auf 0 gesetzt
        for (i = 0; i < a.length; i++){                             // Jedes Element des Feldes a wird betrachtet
            if(a[i] % 2 == 0)                                       // sollte der entsprechende Wert gerade sein
                counteven++;                                        // wird counter für gerade Zahlen um 1 erhäht
            else
                countodd++;                                         // sonst counter der ungeraden Zahlen um 1
        }
        int [] b1 = new int[counteven], b2 = new int[countodd];     // Teilfelder werden mit Hilfe der entsprechenden Variablen erzeugt
        counteven = 0; countodd = 0;
        for (i = 0; i < a.length; i++){                             // Alle Werte des Arrays a werden betrachtet
            if(a[i] % 2 == 0){                                      // sollten der Wert gerade sein, dann wird er in
                b1[counteven] = a[i];                               // der Teilfeld der geraden Werte eingetragen
                counteven++;
            }
            else{
                b2[countodd] = a[i];                                // sonst in das Teilfeld der ungeraden Werte
                countodd++;
            }
        }
        // Ausgabe beider finaler Felder
        System.out.println("gerade Werte: " + java.util.Arrays.toString(b1));
        System.out.println("ungerade Werte: " + java.util.Arrays.toString(b2));
    }
}