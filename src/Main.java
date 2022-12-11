import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Matrix. Chumak Sofiia, KM-12.");
        Scanner myScanner = new Scanner(System.in);
        PolishCalc calculator = new PolishCalc();
        while (true) {
            System.out.println("enter expression: ");
            String expression = myScanner.nextLine();
            if (calculator.calculate(expression)) {System.out.println("success");}
            else {System.out.println("fail");}

        }
    }
}