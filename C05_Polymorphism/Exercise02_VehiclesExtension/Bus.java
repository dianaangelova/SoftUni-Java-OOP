package C05_Polymorphism.Exercise02_VehiclesExtension;

public class Bus extends Vehicle {
    public final static double ADDITIONAL_FUEL_CONSUMPTION = 1.4;
    private boolean isEmpty;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        this.isEmpty = true;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public String drive(double distance, boolean empty) {
        this.setEmpty(empty);
        return super.drive(distance);
    }

    public void setEmpty(boolean empty) {
        if (!isEmpty && empty) {
            this.fuelConsumption -= ADDITIONAL_FUEL_CONSUMPTION;

        }

        if (isEmpty && !empty) {
            this.fuelConsumption += ADDITIONAL_FUEL_CONSUMPTION;
        }
    }

    @Override
    public String drive(double distance) {
        return super.drive(distance);
    }
}
