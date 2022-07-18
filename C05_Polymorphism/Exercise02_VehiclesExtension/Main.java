package C05_Polymorphism.Exercise02_VehiclesExtension;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");

        double carFuelQuantity = Double.parseDouble(tokens[1]);
        double carFuelConsumption = Double.parseDouble(tokens[2]);
        double carTankCapacity = Double.parseDouble(tokens[3]);

        Car car = new Car(carFuelQuantity, carFuelConsumption, carTankCapacity);

        tokens = scanner.nextLine().split("\\s+");

        double truckFuelQuantity = Double.parseDouble(tokens[1]);
        double truckFuelConsumption = Double.parseDouble(tokens[2]);
        double truckTankCapacity = Double.parseDouble(tokens[3]);

        Truck truck = new Truck(truckFuelQuantity, truckFuelConsumption, truckTankCapacity);

        tokens = scanner.nextLine().split("\\s+");

        double busFuelQuantity = Double.parseDouble(tokens[1]);
        double busFuelConsumption = Double.parseDouble(tokens[2]);
        double busTankCapacity = Double.parseDouble(tokens[3]);

        Bus bus = new Bus(busFuelQuantity, busFuelConsumption, busTankCapacity);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            tokens = scanner.nextLine().split("\\s+");

            String commandName = tokens[0];
            String vehicleType = tokens[1];

            switch (commandName) {
                case "Drive":
                    double distance = Double.parseDouble(tokens[2]);
                    if (vehicleType.equals("Car")) {
                        System.out.println(car.drive(distance));
                    } else if (vehicleType.equals("Truck")){
                        System.out.println(truck.drive(distance));
                    } else if (vehicleType.equals("Bus")){
                        System.out.println(bus.drive(distance, false));
                    }
                    break;
                case "Refuel":
                    double fuelAmount = Double.parseDouble(tokens[2]);
                    if (vehicleType.equals("Car")) {
                        car.refuel(fuelAmount);
                    } else if (vehicleType.equals("Truck")){
                        truck.refuel(fuelAmount);
                    } else if (vehicleType.equals("Bus")){
                        bus.refuel(fuelAmount);
                    }
                    break;
                case "DriveEmpty":
                    double driveEmptyDistance = Double.parseDouble(tokens[2]);
                    String driveEmptyMessage = bus.drive(driveEmptyDistance, true);
                    System.out.println(driveEmptyMessage);
                    break;
            }


        }

        System.out.println(car.toString());;
        System.out.println(truck.toString());
        System.out.println(bus.toString());
    }
}
