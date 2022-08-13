package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    public static final int INITIAL_ENERGY = 60;
    public static final int ENERGY_DECREASE = 7;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {
        setEnergy(Math.max(0, getEnergy() - ENERGY_DECREASE));
    }
}
