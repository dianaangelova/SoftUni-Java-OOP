package C01_WorkingWithAbstraction.Lecture02_PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputCoordinates = readArray(scanner);

        Point pointA = new Point(inputCoordinates[0], inputCoordinates[1]);
        Point pointC = new Point(inputCoordinates[2], inputCoordinates[3]);

        Rectangle rectangle = new Rectangle(pointA, pointC);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            int[] tokens = readArray(scanner);

            Point pointX = new Point(tokens[0], tokens[1]);

            boolean isInside = rectangle.isInside(pointX);

            System.out.println(isInside);

        }
    }

    public static int[] readArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
