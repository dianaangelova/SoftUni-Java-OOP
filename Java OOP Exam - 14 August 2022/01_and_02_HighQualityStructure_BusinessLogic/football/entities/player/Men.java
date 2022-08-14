package football.entities.player;

public class Men extends BasePlayer{
    public static final double INITIAL_KG = 85.50;
    public static final int INCREASE_FOR_STRENGTH = 145;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(INITIAL_KG);
    }


//    public Men(String name, String nationality, int strength) {
//        super(name, nationality, strength);
//        setKg(INITIAL_KG);
//    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + INCREASE_FOR_STRENGTH);
    }
}
