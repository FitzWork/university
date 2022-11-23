package Aufgabenblatt_3.A1;

public class SeminarRaum<Uebung> extends Hoersaal{
        protected Uebung[]uebungen;

        public SeminarRaum(int plz,Adresse adr,Uebung[]uebungen){
            super(plz,adr);
            this.uebungen=uebungen;
        }
        public Uebung[]getUebungen(){return uebungen;}
        public int getPlaetze(){return super.getPlaetze();}
        public Adresse getAdresse(){return adr;}
}