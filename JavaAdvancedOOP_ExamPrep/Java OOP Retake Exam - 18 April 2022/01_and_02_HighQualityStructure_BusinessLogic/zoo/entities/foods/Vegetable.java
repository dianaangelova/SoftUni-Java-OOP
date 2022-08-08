package zoo.entities.foods;

public class Vegetable extends BaseFood {
    public static final int VEGETABLE_CALORIES = 50;
    public static final double VEGETABLE_PRICE = 5.00;

    public Vegetable() {
        super(VEGETABLE_CALORIES, VEGETABLE_PRICE);
    }
}
