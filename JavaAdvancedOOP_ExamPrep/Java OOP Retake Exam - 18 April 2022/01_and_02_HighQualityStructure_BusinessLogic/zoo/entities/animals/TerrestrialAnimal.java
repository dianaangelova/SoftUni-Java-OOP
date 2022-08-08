package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal {
    public static final double TERRESTRIALANIMAL_KG = 5.50;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, TERRESTRIALANIMAL_KG, price);
    }

    @Override
    public void eat() {
        setKg(getKg() + 5.70);
    }
}
