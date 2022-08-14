package football.entities.player;

public class Women extends BasePlayer {
    public static final double INITIAL_KG = 60;
    public static final int INCREASE_FOR_STRENGTH = 115;


    public Women(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(INITIAL_KG);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + INCREASE_FOR_STRENGTH);
    }
}
