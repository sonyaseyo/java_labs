import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("String Calculator. Chumak Sofiia KM-12.");
        System.out.println("Enter your string: ");
        String input = scanner.nextLine();
        try {
        Integer output = new StringCalculator().add(input.translateEscapes());
        System.out.println("Summary is " + output);
        }
        catch (ArithmeticException_ext e) {
            System.err.println(e.getMessage() + ' ' + e.negatives);
        }

    }
}