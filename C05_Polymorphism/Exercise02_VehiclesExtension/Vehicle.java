package C05_Polymorphism.Exercise02_VehiclesExtension;

import java.text.DecimalFormat;

public class Vehicle {

    private double fuelQuantity;
    protected double fuelConsumption;
    protected double tankCapacity;

    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public String drive(double distance) {

        double fuelNeeded = distance * fuelConsumption;
        if (fuelNeeded > fuelQuantity) {
            if (this instanceof Car) {
                return "Car needs refueling";
            } else if (this instanceof Truck) {
                return "Truck needs refueling";
            } else if (this instanceof Bus) {
                return "Bus needs refueling";
            }
        }

        this.fuelQuantity = this.fuelQuantity - fuelNeeded;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");

        return String.format("%s travelled %s km", this.getClass().getSimpleName(), decimalFormat.format(distance));

    }

    public void refuel(double liters) {

        if (liters <= 0) {
            System.out.println("Fuel must be a positive number");
            return;
        }

        if (liters > this.tankCapacity - this.fuelQuantity) {
            System.out.println("Cannot fit fuel in tank");
            return;
        }

        this.fuelQuantity = fuelQuantity + liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
