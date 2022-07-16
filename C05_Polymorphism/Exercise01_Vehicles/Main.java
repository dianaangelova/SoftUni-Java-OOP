package C05_Polymorphism.Exercise01_Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");

        double carFuelQuantity = Double.parseDouble(tokens[1]);
        double carFuelConsumption = Double.parseDouble(tokens[2]);

        Car car = new Car(carFuelQuantity, carFuelConsumption);

        tokens = scanner.nextLine().split("\\s+");

        double truckFuelQuantity = Double.parseDouble(tokens[1]);
        double truckFuelConsumption = Double.parseDouble(tokens[2]);

        Truck truck = new Truck(truckFuelQuantity, truckFuelConsumption);

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
                    } else {
                        System.out.println(truck.drive(distance));
                    }
                    break;
                case "Refuel":
                    double fuelAmount = Double.parseDouble(tokens[2]);
                    if (vehicleType.equals("Car")) {
                        car.refuel(fuelAmount);
                    } else {
                        truck.refuel(fuelAmount);
                    }
                    break;
            }


        }

        System.out.println(car.toString());;
        System.out.println(truck.toString());
    }
}
