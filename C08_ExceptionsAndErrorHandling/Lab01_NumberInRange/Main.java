package C08_ExceptionsAndErrorHandling.Lab01_NumberInRange;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputRange = scanner.nextLine().split("\\s+");
        int startNumber = Integer.parseInt(inputRange[0]);
        int endNumber = Integer.parseInt(inputRange[1]);

        System.out.printf("Range: [%d...%d]%n", startNumber, endNumber);

        String checkNumber = scanner.nextLine();

        checkNumberInRage(scanner, startNumber, endNumber, checkNumber);
    }

    private static void checkNumberInRage(Scanner scanner, int startNumber, int endNumber, String checkNumber) {
        while (true) {
            try {
                int checkNumberParse = Integer.parseInt(checkNumber);
                if (checkNumberParse >= startNumber && checkNumberParse <= endNumber) {
                    System.out.println("Valid number: " + checkNumber);
                    return;
                }
            } catch (NumberFormatException exception) {
            }
            System.out.println("Invalid number: " + checkNumber);

            checkNumber = scanner.nextLine();
        }
    }
}
