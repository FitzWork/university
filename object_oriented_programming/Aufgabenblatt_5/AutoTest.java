package Aufgabenblatt_5;

import java.util.Random;

public class AutoTest {
    private final Random r = new Random();
    private final Auto[] a = {
            new Auto(false,"",false),
            new PickUP(false, ""),
            new Auto(true, "J-AB 1",true),
            new PickUP(true, "J-BA 2")
    };
    private PickUP pu;

    public void print() {
        System.out.println("-------------------------");
        for(int i = 0; i < a.length && a[i] != null; i++)
            if (a[i].pu) {
                pu = (PickUP) a[i];
                System.out.println((i + 1) + ". " + pu.toString());
            } else System.out.println((i + 1) + ". " + a[i].toString());
        System.out.println("-------------------------");
    }

    public void fahren() {
        a[0].fahre(r.nextInt(101));
        a[1].fahre(r.nextInt(101));
        a[2].fahre(r.nextInt(101));
        a[3].fahre(r.nextInt(101));
        System.out.println("-------------------------");
    }

    public void waschen() {
        a[0].wasche();
        pu = (PickUP) a[1];
        pu.wasche();
        a[1] = pu;
        a[2].wasche();
        pu = (PickUP) a[1];
        pu.wasche();
        a[1] = pu;
        System.out.println("-------------------------");
    }

    public void beladen() {
        pu = (PickUP) a[1];
        pu.beladen(r.nextInt(10));
        a[1] = pu;
        pu = (PickUP) a[3];
        pu.beladen(r.nextInt(10));
        a[3] = pu;
        System.out.println("-------------------------");
    }

    public void entladen() {
        pu = (PickUP) a[1];
        pu.entladen(r.nextInt(10));
        a[1] = pu;
        pu = (PickUP) a[3];
        pu.entladen(r.nextInt(10));
        a[3] = pu;
        print();
    }

    public void komplett_entladen() { // komplett entladen werden können nur PickUps (über Hilfsvariable pu)
        pu = (PickUP) a[1];
        pu.entladen();
        a[1] = pu;
        pu = (PickUP) a[3];
        pu.entladen();
        a[3] = pu;
        print();
    }
}
