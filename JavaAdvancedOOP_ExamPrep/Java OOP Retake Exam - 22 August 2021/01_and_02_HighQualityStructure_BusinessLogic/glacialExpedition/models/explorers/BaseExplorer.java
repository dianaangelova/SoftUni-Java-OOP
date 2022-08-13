package glacialExpedition.models.explorers;

import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO;
import static glacialExpedition.common.ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY;

public abstract class BaseExplorer implements Explorer {
    public static final int ENERGY_DECREASE = 15;
    private String name;
    private double energy;
    private Suitcase suitcase;

    public BaseExplorer(String name, double energy) {
        setName(name);
        setEnergy(energy);
        suitcase = new Carton();
    }

    protected void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(String.format(EXPLORER_ENERGY_LESS_THAN_ZERO));
        }
        this.energy = energy;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(String.format(EXPLORER_NAME_NULL_OR_EMPTY));
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public boolean canSearch() {
        return energy > 0;
    }

    @Override
    public Suitcase getSuitcase() {
        return suitcase;
    }

    @Override
    public void search() {
        energy = Math.max(0, energy - ENERGY_DECREASE);
    }

    @Override
    public String toString() {
        String exhibitsOutput = suitcase.getExhibits().isEmpty()
                ? "None"
                : suitcase.getExhibits().stream()
                .collect(Collectors.joining(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER));

        return String.format(FINAL_EXPLORER_NAME + System.lineSeparator() +
                FINAL_EXPLORER_ENERGY + System.lineSeparator() +
                FINAL_EXPLORER_SUITCASE_EXHIBITS, name, energy, exhibitsOutput);
    }
}
