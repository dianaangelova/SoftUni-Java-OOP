package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class HelperRepository implements Repository<Helper> {
    private Map<String, Helper> helpers;

    public HelperRepository() {
        helpers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Helper> getModels() {
        return helpers.values();
    }

    @Override
    public void add(Helper helper) {
        helpers.putIfAbsent(helper.getName(), helper);
    }

    @Override
    public boolean remove(Helper helper) {
        return helpers.remove(helper.getName())!=null;
    }

    @Override
    public Helper findByName(String name) {
        return helpers.get(name);
    }
}
