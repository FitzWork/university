package Aufgabenblatt_6.A2;

public class Roman {
    protected int a;
    protected String r;
    public Roman(int a) {this.a = a; r = toString();}
    public Roman(String r) {this.r = r; a = hashCode();}

    public Roman addition(String input) {
        Roman in = new Roman(input);
        in.r = input;
        in.a = in.hashCode();
        in.a = a + in.a;
        in.r = in.toString();
        return in;
    }
    public Roman subtraktion(String input) {
        Roman in = new Roman(input);
        in.r = input;
        in.a = in.hashCode();
        in.a = a - in.a;
        in.r = in.toString();
        return in;
    }
    public Roman multiplikation(String input) {
        Roman in = new Roman(input);
        in.r = input;
        in.a = in.hashCode();
        in.a = a * in.a;
        in.r = in.toString();
        return in;
    }
    public Roman division(String input) {
        Roman in = new Roman(input);
        in.r = input;
        in.a = in.hashCode();
        in.a = a / in.a;
        in.r = in.toString();
        return in;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Roman h = this;
        int help = h.a;
        int M = 0, D = 0, C = 0, L = 0, X = 0, V = 0, I = 0;
        if(help - 1000 >= 0) M = 1;
        if(help - 2000 >= 0) M = 2;
        if(help - 3000 >= 0) M = 3;
        help -= 1000 * M;
        output.append("M".repeat(M));
        if(help - 900 >= 0) {output.append("CM"); help -= 900;}
        if(help - 500 >= 0) {output.append("D"); help -= 500;}
        if(help - 100 >= 0) C = 1;
        if(help - 200 >= 0) C = 2;
        if(help - 300 >= 0) C = 3;
        help -= 100 * C;
        output.append("C".repeat(C));
        if(help - 90 >= 0) {output.append("XC"); help -= 90;}
        if(help - 50 >= 0) {output.append("L"); help -= 50;}
        if(help - 10 >= 0) X = 1;
        if(help - 20 >= 0) X = 2;
        if(help - 30 >= 0) X = 3;
        help -= 10 * X;
        output.append("X".repeat(X));
        if(help - 9 >= 0) {output.append("IX"); help -= 9;}
        if(help - 5 >= 0) {output.append("V"); help -= 5;}
        if(help - 4 >= 0) {output.append("IV"); help -= 4;}
        if(help - 1 >= 0) I = 1;
        if(help - 2 >= 0) I = 2;
        if(help - 3 >= 0) I = 3;
        help -= 10 * I;
        output.append("I".repeat(I));
        return output.toString();
    }
    @Override
    public int hashCode() {
        int x = 0;
        char befor = r.charAt(0);
        for(int i = 0; i < r.length(); i++) {
            switch (r.charAt(i)) {
                case 'I' -> x++;
                case 'V' -> {
                    if(befor == 'I')
                        x += 3;
                    else x += 5;
                }
                case 'X' -> {
                    if(befor == 'I')
                        x += 8;
                    else x += 10;
                }
                case 'L' -> x += 50;
                case 'C' -> {
                    if(befor == 'X')
                        x += 80;
                    else x += 100;
                }
                case 'D' -> {
                    if(befor == 'C')
                        x += 300;
                    else x += 500;
                }
                case 'M' -> {
                    if(befor == 'C')
                        x += 800;
                    else x += 1000;
                }
            }
            befor = r.charAt(i);
        }
        return x;
    }
    @Override
    public boolean equals(Object obj){
        return a == ((Roman) obj).a && r.equals(((Roman) obj).r);
    }
}
