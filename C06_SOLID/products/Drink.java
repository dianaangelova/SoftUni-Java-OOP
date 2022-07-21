package C06_SOLID.products;

public abstract class Drink implements Product {

    private double caloriesPer100Grams;
    private double milliliters;
    private double density;

    public Drink(double milliliters, double caloriesPer100Grams, double density) {
        this.milliliters = milliliters;
        this.caloriesPer100Grams = caloriesPer100Grams;
        this.density = density;
    }

    public double getMilliliters() {
        return milliliters;
    }

    public double getDensity() {
        return this.density;
    }

    public double getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    public double getAmountOfCalories() {
        return (this.getCaloriesPer100Grams() / 100) * this.getMilliliters() * this.getDensity();
    }

}
