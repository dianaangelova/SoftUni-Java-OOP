package fairyShop.repositories;

import fairyShop.models.Helper;
import fairyShop.models.Present;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class PresentRepository implements Repository<Present> {
    private Map<String, Present> presents;

    public PresentRepository() {
        presents = new LinkedHashMap<>();
    }

    @Override
    public Collection<Present> getModels() {
        return presents.values();
    }

    @Override
    public void add(Present present) {
        presents.putIfAbsent(present.getName(), present);
    }

    @Override
    public boolean remove(Present present) {
        return presents.remove(present.getName(), present);
    }

    @Override
    public Present findByName(String name) {
        return presents.get(name);
    }
}
