package C05_Polymorphism.Exercise01_Vehicles;

import java.text.DecimalFormat;

public  class Vehicle {

    private double fuelQuantity;
    private double fuelConsumption;

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    public String drive(double distance) {

        double fuelNeeded = distance * fuelConsumption;
        if (fuelNeeded > fuelQuantity) {
            if (this instanceof Car) {
                return "Car needs refueling";
            } else {
                return "Truck needs refueling";
            }
        }

        this.fuelQuantity = this.fuelQuantity - fuelNeeded;
        DecimalFormat decimalFormat= new DecimalFormat("##.##");

            return String.format("%s travelled %s km", this.getClass().getSimpleName(), decimalFormat.format(distance));

    }

    public void refuel(double liters) {
this.fuelQuantity=fuelQuantity + liters;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
