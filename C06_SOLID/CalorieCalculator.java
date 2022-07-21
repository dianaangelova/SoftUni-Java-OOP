package C06_SOLID;

import C06_SOLID.products.Product;

import java.util.ArrayList;

public class CalorieCalculator {

    public static final String SUM_FORMAT = "Sum: %f";
    public static final String AVERAGE_FORMAT = "Average: %f";


    public double sum(ArrayList<Product> products) {

        final double sum = products.stream()
                            .mapToDouble(Product::getAmountOfCalories)
                            .sum();

        return sum;
    }

    public double average(ArrayList<Product> products) {
        return sum(products) / products.size();
    }
}
