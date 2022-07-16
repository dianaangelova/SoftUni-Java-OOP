package C05_Polymorphism.Exercise01_Vehicles;

public class Truck extends Vehicle {
    private final static double AC_CONSUMPTION = 1.6;
    private final static double REFUEL_PERCENTAGE = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + AC_CONSUMPTION);
    }

    public void refuel(double liters) {
        liters = liters * REFUEL_PERCENTAGE;
        super.refuel(liters);

    }
}
