package Aufgabenblatt_3.A2;

public class Airport {
    private final Flight[] mf;
    private int i;

    public Airport (int maxFlight) {
        if (maxFlight >= 0){
            mf = new Flight[maxFlight];
        }
        else {
            mf = new Flight[0];
        }
    }

    public void addNewFlight (Flight flight) {
        i = -1;
        if (mf[mf.length-1] != null) System.out.println("Es ist leider alles voll.");
        else {
            while (++i < mf.length) {
                if (mf[i] == null) {
                    mf[i] = flight;
                    break;
                }
                if (flight.flightNumber == mf[i].flightNumber) {
                    System.out.println("Ein Flug mit dieser Nummer ist schon enthalten.");
                    break;
                }
            }
        }
    }

    public void removeFlight (int flightNumber) {
        i = -1;
        if (mf[i+1] != null) {
            while (++i < mf.length) {
                if (mf[i] != null) {
                    if (mf[i].flightNumber == flightNumber) {
                        if (mf.length - 1 - i >= 0){
                            System.arraycopy(mf, i + 1, mf, i, mf.length - 1 - i);
                            if (i == mf.length - 1)
                                mf[i] = null;
                        }
                        break;
                    }
                }
            }
            if (i == mf.length - 1) {
                System.out.println("Ein Flug mit dieser Nummer ist nicht im System.");
            }
        }
        else System.out.println("Es ist gibt leider keinen Flug zum entfernen.");
    }

    public void listDeparturesOnScreen (){
        for (Flight flight : mf)
            if (flight != null)
                if (!flight.inOut)
                    System.out.println(flight.flightNumber
                            + " | " + flight.time
                            + " | " + flight.gate
                            + " | " + flight.location);
    }

    public void listArrivalsOnScreen() {
        for (Flight flight : mf)
            if (flight != null)
                if (flight.inOut)
                    System.out.println(flight.flightNumber
                            + " | " + flight.time
                            + " | " + flight.gate
                            + " | " + flight.location);
    }
}
