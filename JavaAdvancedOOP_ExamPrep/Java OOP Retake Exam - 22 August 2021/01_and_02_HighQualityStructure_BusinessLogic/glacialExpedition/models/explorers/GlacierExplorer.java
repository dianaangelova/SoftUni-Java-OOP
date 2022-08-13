package glacialExpedition.models.explorers;

public class GlacierExplorer extends BaseExplorer{
    public static final int INITIAL_ENERGY = 40;
    public GlacierExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }
}
