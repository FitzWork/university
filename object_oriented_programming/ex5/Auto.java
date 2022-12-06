package Aufgabenblatt_5;

import java.util.Scanner;

public class Auto {
    protected String kz = "J-AA 01";
    protected Boolean at_Out = false;
    public Boolean pu = false;
    protected int kms = 0,sp = 5;
    public Auto() {
        Scanner s = new Scanner(System.in);
        System.out.print("(1)Standardeinstellung, " +
                "(2)Kennzeichen aendern, " +
                "(3)Sitzplatzanzahl aendern auf 2 oder " +
                "(4)beides aendern? ");
        switch (s.nextInt()) {
            case 2 -> {
                System.out.print("Neues Kennzeichen: ");
                kz = s.next();
            }
            case 3 -> {
                sp = 2;
                System.out.print("Sitzplatzanzahl auf 2 geändert.");
            }
            case 4 -> {
                System.out.print("Neues Kennzeichen: ");
                kz = s.next();
                sp = 2;
                System.out.print("Sitzplatzanzahl auf 2 geändert.");
            }
            default -> System.out.println("Falsche Eingabe!");
        }
    }
    public Auto(Boolean kz_aendern, String kz, Boolean zweisitzer) {
        if(kz_aendern) this.kz = kz; if(zweisitzer) sp = 2;
    }
    public Auto(int x){}
    public String getKennzeichen() { return kz; }

    public int getKilometerstand() { return kms; }

    public int getSitzplaetze() { return sp; }

    public void fahre(int kilometer) {
        if(kilometer >= 0) {
            System.out.println("Das Auto ist " + kilometer + " km gefahren.");
            kms += kilometer;
        }
    }

    public void fahreAntenneAus() {
        System.out.println("Die Antenne wird ausgefahren.");
        at_Out = true;
    }

    public void fahreAntenneEin() {
        System.out.println("Die Antenne wird eingefahren.");
        at_Out = false;
    }

    protected void bereiteWaschenVor() {if(at_Out) at_Out = false;}

    public void wasche() {
        System.out.println("Das Auto wird gewaschen.");
        bereiteWaschenVor();
    }

    public String toString() {
        return "Auto: " + kz
                + " | Sitzplaetze: " + sp
                + " | Kilometerstand: " + kms
                + " | Antenne draußen: " + at_Out;
    }
}
