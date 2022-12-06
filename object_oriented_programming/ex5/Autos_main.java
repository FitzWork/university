package Aufgabenblatt_5;

import java.util.Scanner;

public class Autos_main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // Durchführung der vorgefertigte Testausgabe
        AutoTest a = new AutoTest();
        a.print();
        a.fahren();
        a.waschen();
        a.beladen();
        a.entladen();
        a.komplett_entladen();

        System.out.println("\n\n");
        // Selbstständige Testausgabe bei Bedarf
        System.out.print("Wie viele Fahrzeuge passen in die Garage? ");
        Autos_Test test = new Autos_Test(s.nextInt());
        int choice = 1;
        while(choice != 0) {
            System.out.print("\n(0) Beenden , (1) Hinzufuegen, (2) Entfernen, (3) Nutzen, (4) Anzeige aller vorhandenen? ");
            choice = s.nextInt();
            if(choice == 0){
                break;
            }
            else {
                switch (choice){
                    case 1 -> test.add();
                    case 2 -> test.remove();
                    case 3 -> test.use();
                    case 4 -> test.print();
                }
            }
        }
    }
}
