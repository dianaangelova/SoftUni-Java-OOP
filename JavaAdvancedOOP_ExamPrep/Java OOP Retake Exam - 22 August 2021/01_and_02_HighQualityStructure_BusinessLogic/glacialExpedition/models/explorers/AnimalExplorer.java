package glacialExpedition.models.explorers;

public class AnimalExplorer extends BaseExplorer{
    public static final int INITIAL_ENERGY = 100;

    public AnimalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }
}
