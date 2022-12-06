package Aufgabenblatt_7.A2;

public class StringMengeImpl extends AbstrakteStringMenge{
    String[] help;

    public void add(String s) {
        if(isEmpty()){
            this.s = new String[1];
            this.s[0] = s;
        }
        else {
            if(!contains(s)){
                help = this.s;
                this.s = new String[this.s.length+1];
                System.arraycopy(help, 0, this.s, 0, help.length);
                this.s[this.s.length-1] = s;
            }
        }
    }

    public void remove(String s) {
        if(contains(s)){
            if(this.s.length == 1)
                this.s = null;
            else{
                help = this.s;
                count = 0;
                for(int i = 0; i < this.s.length - 1; i++) {
                    if(this.s[i].equals(s)) {
                        System.arraycopy(this.s, count + 1, this.s, count, this.s.length - count - 1);
                        this.s = new String[this.s.length - 1];
                        this.s = help;
                        break;
                    }
                }
            }
        }
    }

    public boolean contains(String s) {
        if(!isEmpty())
            for (String value : this.s) if (value.equals(s)) return true;
        return false;
    }

    public int getSize() {
        if(s == null)
            return 0;
        return s.length;
    }

    public String[] getElements() {return s;}
}
