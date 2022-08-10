package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;
import static christmasRaces.common.ExceptionMessages.INVALID_MODEL;

public abstract class BaseCar implements Car {

    private String model;
    private int horsePower;
    private double cubicCentimeters;


    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        setCubicCentimeters(cubicCentimeters);
    }

    private void setModel(String model) {
        if (model == null || model.trim().length() < 4) {
            throw new IllegalArgumentException(String.format(INVALID_MODEL, model, 4));
        }
        this.model = model;
    }

    protected abstract void checkHorsePower(int horsePower);

    private void setHorsePower(int horsePower) {
        this.checkHorsePower(horsePower);
        this.horsePower=horsePower;
    }

    private void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return cubicCentimeters / horsePower * laps;
    }


}
