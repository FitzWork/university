package Aufgabenblatt_1;

public class Arrays_und_Schleifen_Schäfer {
    public static void main(String[] args) {
        int input = Integer.parseInt(System.console().readLine());
        while (input>=0){
            if(input >= 0 && input < 2){
                System.out.println("Result: " + input);
                input = Integer.parseInt(System.console().readLine());
                continue;
            }
            input--;
            int first = 0;
            int second = 1;
            while(input-- > 0){
                int placeholder = second;
                second += first;
                first = placeholder;
            }
            System.out.println("Result: " + second);
            input = Integer.parseInt(System.console().readLine());
        }
    }
}
