package football.entities.supplement;

public class Liquid extends BaseSupplement{
    public static final int INITIAL_ENERGY = 90;
    public static final double INITIAL_PRICE = 25;

    public Liquid() {
        super(INITIAL_ENERGY, INITIAL_PRICE);
    }
}
