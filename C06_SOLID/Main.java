package C06_SOLID;

import C06_SOLID.products.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        CalorieCalculator calorieCalculator = new CalorieCalculator();

        Printer printer = new Printer();

        ArrayList<Product> products = new ArrayList<>();

        products.add(new Chips(100.0));
        products.add(new Chocolate(100.0));
        products.add(new Coke(100.0));
        products.add(new Lemonade(100.0));

        double sum = calorieCalculator.sum(products);
        double average = calorieCalculator.average(products);

        printer.print(calorieCalculator.SUM_FORMAT, sum);
        System.out.println();
        printer.print(calorieCalculator.AVERAGE_FORMAT, average);

    }
}
