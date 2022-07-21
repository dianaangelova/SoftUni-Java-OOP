package C06_SOLID;

import C06_SOLID.products.Drink;
import C06_SOLID.products.Food;
import C06_SOLID.products.Product;

public class QuantityCalculator implements Quantity, Product {

    @Override
    public double getAmountInKilograms() {
        return 0;
    }

    @Override
    public double getAmountOfCalories() {
        return 0;
    }
}
