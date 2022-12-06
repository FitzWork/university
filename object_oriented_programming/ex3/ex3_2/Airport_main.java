package Aufgabenblatt_3.A2;

import java.util.Scanner;

public class Airport_main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Airportsize: ");
        Airport a = new Airport(s.nextInt());
        int choice;
        while(true) {
            System.out.print("(1)add, (2)remove, (3)listDepartures, (4)listArrival, (0)Exit: ");
            choice = s.nextInt();
            if (choice == 0) break;

            System.out.println("-------------------------");
            switch (choice) {
                case 1 -> {
                    System.out.println("1.Number, 2.Location, 3.Gate, 4.Time, 5.In/Out");
                    a.addNewFlight(new Flight(s.nextInt(),s.next(),s.next(),s.next(),s.nextBoolean()));
                }
                case 2 -> {
                    System.out.print("number:  ");
                    a.removeFlight(s.nextInt());
                }
                case 3 -> a.listDeparturesOnScreen();
                case 4 -> a.listArrivalsOnScreen();
                default -> System.out.println("Das war leider eine falsche Eingabe. Versuch es erneut.");
            }
            System.out.println("-------------------------\n");
        }
    }
}
