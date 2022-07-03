package C01_WorkingWithAbstraction.Lab;

import java.util.Scanner;

public class L01_RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        printTop(n);
        printMiddle(n);
        printBottom(n);

    }

    public static void printMiddle(int n) {
        printString(n, "* ");
        System.out.println();
    }

    public static void printBottom(int n) {
        for (int r = 1; r < n; r++) {
            printString(r, " ");
            printString(n - r, "* ");
            System.out.println();
        }
    }

    public static void printTop(int n) {
        for (int r = 1; r < n; r++) {
            printString(n - r, " ");
            printString(r, "* ");
            System.out.println();
        }
    }

    public static void printString(int count, String s) {
        for (int i = 0; i < count; i++) {
            System.out.print(s);
        }
    }


}
