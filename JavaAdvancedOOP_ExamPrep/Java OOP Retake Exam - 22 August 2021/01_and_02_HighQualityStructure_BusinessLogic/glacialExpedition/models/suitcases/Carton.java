package glacialExpedition.models.suitcases;

import java.util.*;

public class Carton implements Suitcase {
    private Collection<String> exhibits;

    public Carton() {
        exhibits = new ArrayList<>();
    }

    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }
}
