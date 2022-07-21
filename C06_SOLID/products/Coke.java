package C06_SOLID.products;

public class Coke extends Drink implements Product {

    public static final double CALORIES_PER_100_GRAMS = 44.0;
    private static final double DENSITY = 0.6;

    public Coke(double milliliters) {
        super(milliliters, CALORIES_PER_100_GRAMS, DENSITY);
    }

    @Override
    public double getAmountInKilograms() {
        return (this.getMilliliters() /1000) * this.getDensity();
    }
}
