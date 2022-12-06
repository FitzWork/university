package Aufgabenblatt_5;

import java.util.Scanner;

public class PickUP extends Auto{
    private final int f = 25;
    private int lf = 0,sp = 2;
    public PickUP() {
        super(1);
        Scanner s = new Scanner(System.in);
        System.out.print("(1)Standardeinstellung oder " +
                "(2)Kennzeichen aendern? ");
        if(s.nextInt() == 2) {
            System.out.print("Neues Kennzeichen: ");
            kz = s.next();
        }
        System.out.println("Das Fassungsvermoegen der Ladeflaeche betraegt: " + f);
    }
    public PickUP(Boolean kz_aender,String kz){
        super(kz_aender, kz, true);
    }

    public int getLadung() {return lf;}
    public boolean beladen(int ladung) {
        if(lf + ladung <= f && ladung >= 0) {
            lf += ladung;
            System.out.println("Der PickUp wurde mit " + ladung + " beladen.");
        }
        return lf + ladung <= f && ladung >= 0;
    }
    public void entladen(int ladung) {
        if(lf - ladung >= 0 && ladung >= 0){
            lf -= ladung;
            System.out.println("Die Ladung wurde um " + ladung + " verringert.");
        }
    }

    public void entladen() {entladen(lf);}
    @Override
    protected void bereiteWaschenVor() {
        if(at_Out) at_Out = false;
        if(lf != 0) entladen();
    }

    @Override
    public String toString() {
        return "Auto: " + kz
                + " | Sitzplaetze: " + sp
                + " | Ladeflaeche: " + lf + " von " + f
                + " | Kilometerstand: " + kms
                + " | Antenne draußen: " + at_Out;
    }
}
