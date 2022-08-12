package fairyShop.models;

import java.util.ArrayList;
import java.util.Collection;

import static fairyShop.common.ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY;

public abstract class BaseHelper implements Helper {
    public static final int HELPER_WORK_DROP = 10;

    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    public BaseHelper(String name, int energy) {
        setName(name);
        setEnergy(energy);
        instruments = new ArrayList<>();
    }

    protected void setEnergy(int energy) {
        this.energy = energy;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(String.format(HELPER_NAME_NULL_OR_EMPTY));
        }
        this.name = name;
    }

    @Override
    public void work() {
        energy = (Math.max(0, energy - HELPER_WORK_DROP));
    }

    @Override
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return energy > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return instruments;
    }

    public String toString() {
        long instrumentsNotBroken = getInstruments().stream().filter(i -> !i.isBroken()).count();
        return String.format("Name: %s" +
                System.lineSeparator() +
                "Energy: %d" +
                System.lineSeparator() +
                "Instruments: %d not broken left", name, energy, instrumentsNotBroken);
    }
}
