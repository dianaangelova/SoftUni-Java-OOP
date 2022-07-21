package C06_SOLID.products;

public abstract class Food implements Product {
    private double grams;
    private double caloriesPer100Grams;

    public Food(double grams, double caloriesPer100Grams) {
        this.grams = grams;
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    public double getGrams() {
        return grams;
    }

    public double getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    public double getAmountOfCalories() {
        return (this.getCaloriesPer100Grams() / 100) * this.getGrams();
    }

}
