package C05_Polymorphism.Exercise02_VehiclesExtension;

public class Car extends Vehicle {
    private  final  static  double AC_CONSUMPTION =0.9;
    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption+AC_CONSUMPTION, tankCapacity);
    }
}
