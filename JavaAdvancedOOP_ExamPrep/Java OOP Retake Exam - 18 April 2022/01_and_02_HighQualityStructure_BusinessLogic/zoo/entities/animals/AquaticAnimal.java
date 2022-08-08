package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal {
    public static final double AQUATICANIMAL_KG = 2.50;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, AQUATICANIMAL_KG, price);
    }


    @Override
    public void eat() {
        setKg(7.5 + getKg());
    }
}
