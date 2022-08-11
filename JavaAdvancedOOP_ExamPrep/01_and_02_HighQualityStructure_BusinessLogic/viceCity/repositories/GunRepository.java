package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.*;

public class GunRepository implements Repository<Gun> {
    private Map<String, Gun> guns;

    public GunRepository() {
        guns = new LinkedHashMap<>();
    }

    @Override
    public Collection getGuns() {
        return Collections.unmodifiableCollection(guns.values());
    }

    @Override
    public void add(Gun gun) {
        guns.putIfAbsent(gun.getName(), gun);
    }

    @Override
    public boolean remove(Gun gun) {
        return guns.remove(gun.getName()) != null;
    }

    @Override
    public Gun find(String name) {
        return guns.get(name);
    }
}
