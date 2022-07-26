package C08_ExceptionsAndErrorHandling.Lab02_SquareRoot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            int numberInt = Integer.parseInt(input);
            Double result = Math.sqrt(numberInt);
            if (result.isNaN()) {
                throw new ArithmeticException();
            }
            System.out.printf("%.2f%n", result);
        } catch (NumberFormatException | ArithmeticException exception) {
            System.out.println("Invalid");
        } finally {
            System.out.println("Goodbye");
        }
    }
}
