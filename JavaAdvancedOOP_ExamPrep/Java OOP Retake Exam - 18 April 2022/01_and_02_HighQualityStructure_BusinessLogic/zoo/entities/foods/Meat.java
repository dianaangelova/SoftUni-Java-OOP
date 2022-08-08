package zoo.entities.foods;

public class Meat extends BaseFood {
    public static final int MEAT_CALORIES = 70;
    public static final double MEAT_PRICE = 10.00;

    public Meat() {
        super(MEAT_CALORIES, MEAT_PRICE);
    }
}
