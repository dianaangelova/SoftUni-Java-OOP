package C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.data;

import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.interfaces.Repository;
import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.interfaces.Unit;

import java.util.Map;
import java.util.TreeMap;

public class UnitRepository implements Repository {

    private Map<String, Integer> amountOfUnits;

    public UnitRepository() {
        this.amountOfUnits = new TreeMap<>();
    }

    public void addUnit(Unit unit) {
        String unitType = unit.getClass().getSimpleName();
        if (!this.amountOfUnits.containsKey(unitType)) {
            this.amountOfUnits.put(unitType, 0);
        }

        int newAmount = this.amountOfUnits.get(unitType) + 1;
        this.amountOfUnits.put(unitType, newAmount);
    }

    public String getStatistics() {
        StringBuilder statBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : amountOfUnits.entrySet()) {
            String formatedEntry =
                    String.format("%s -> %d%n", entry.getKey(), entry.getValue());
            statBuilder.append(formatedEntry);
        }
        statBuilder.setLength(
                statBuilder.length() - System.lineSeparator().length());

        return statBuilder.toString();
    }

    public void removeUnit(String unitType)  {
        Integer countPeople = this.amountOfUnits.get(unitType);
        if (countPeople == null || countPeople <= 0) {
            this.amountOfUnits.remove(unitType);
            throw new RuntimeException("No such units in repository.");
        } else {
            this.amountOfUnits.put(unitType, countPeople - 1);
       }

    }
}
