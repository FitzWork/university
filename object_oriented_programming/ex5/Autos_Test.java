package Aufgabenblatt_5;

import java.util.Scanner;

class Autos_Test {
    private final Scanner s = new Scanner(System.in);
    private int count = 0,in = 0;
    private final int size;
    private final Auto[] a;
    private PickUP pu;

    public Autos_Test(int size) {a = new Auto[size]; this.size = size-1;}

    public void add() {
        if(a[size] != null) System.out.println("Leider ist die Garage schon voll. Es müssen erst welche entfernt werden.");
        else {
            count = 0;
            while(a[count] != null) count++;
            System.out.print("(1)Auto oder (2)PickUp? ");
            in = s.nextInt();
            if(in == 1)
                a[count] = new Auto();
            else if(in == 2){
                a[count] = new PickUP();
                a[count].pu = true;
            }
            else System.out.println("Falsche Eigabe.");
        }
    }

    public void remove() {
        if(a[0] == null) System.out.println("Leider stehen kein Autos in deiner Garage. Es müssen erst welche hinzugefügt werden.");
        else {
            print();
            System.out.print("Welche Nummer entfernt werden? ");
            in = s.nextInt() - 1;
            if (size - in >= 0) System.arraycopy(a, in + 1, a, in, size - in);
            if(a[size] != null) a[size] = null;
        }
    }

    public void use() {
        if(a[0] == null) System.out.println("Die Garage ist leer.");
        else {
            print();
            System.out.print("Welches nutzen? ");
            in = s.nextInt() - 1;
            if(a[in].pu) pu = (PickUP) a[in];
            count = 1;
            while(count != 0) {
                System.out.print("(0)Beenden, (1)Daten über Fahrzeug anzeigen oder es (2)nutzen? ");
                count = s.nextInt();
                if(count == 0) break;
                int x;
                if(count == 1) {
                    System.out.print("(1)Alle Daten, (2)Kennzeichen, (3)Kilometerstand, Anzahl an (4)Sitzplätzen");
                    if (a[in].pu) System.out.print(", (5)Ladung");
                    System.out.print("? ");
                    x = s.nextInt();
                    switch(x) {
                        case 1 -> {
                            if(a[in].pu) System.out.println(pu.toString());
                            else System.out.println(a[in].toString());
                        }
                        case 2 -> {
                            if(a[in].pu) System.out.println(pu.getKennzeichen());
                            else System.out.println(a[in].getKennzeichen());
                        }
                        case 3 -> {
                            if(a[in].pu) System.out.println(pu.getKilometerstand());
                            else System.out.println(a[in].getKilometerstand());
                        }
                        case 4 -> {
                            if(a[in].pu) System.out.println(pu.getSitzplaetze());
                            else System.out.println(a[in].getSitzplaetze());
                        }
                        case 5 -> {if(a[in].pu) System.out.println(pu.getLadung());}
                    }
                }
                else if(count == 2){
                    System.out.print("(1)fahren, Antenne (2)rein oder (3)raus, (4)waschen");
                    if (a[in].pu) System.out.print(", (5)beladen, (6)entladen, komplett (7)entladen");
                    System.out.print("? ");
                    x = s.nextInt();
                    switch(x) {
                        case 1 -> {
                            System.out.print("Gefahrene Kilometer: ");
                            if(a[in].pu) pu.fahre(s.nextInt());
                            else a[in].fahre(s.nextInt());
                        }
                        case 2 -> {
                            if(a[in].pu) pu.fahreAntenneEin();
                            else a[in].fahreAntenneEin();
                        }
                        case 3 -> {
                            if(a[in].pu) pu.fahreAntenneAus();
                            else a[in].fahreAntenneAus();
                        }
                        case 4 -> {
                            if (a[in].pu) pu.wasche();
                            else a[in].wasche();
                        }
                        case 5 -> {
                            if(a[in].pu) {
                                System.out.print("Wie viel? ");
                                System.out.println("Ladung hinzugefügt? " + pu.beladen(s.nextInt()));
                            }
                        }
                        case 6 -> {
                            if(a[in].pu) {
                                System.out.print("Wie viel? ");
                                pu.entladen(s.nextInt());
                            }
                        }
                        case 7 -> {
                            if(a[in].pu) pu.entladen();
                        }
                    }
                }
            }
        }
    }

    public void print() {
        if(a[0] == null) System.out.println("Die Garage ist leer.");
        else {
            for(int i = 0; i <= size && a[i] != null; i++) {
                if(a[i].pu) {
                    pu = (PickUP) a[i];
                    System.out.println((i+1) + ". " + pu.toString());
                }
                else System.out.println((i+1) + ". " + a[i].toString());
            }
        }
    }
}
