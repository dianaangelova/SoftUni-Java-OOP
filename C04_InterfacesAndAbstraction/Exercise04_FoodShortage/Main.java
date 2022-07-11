package C04_InterfacesAndAbstraction.Exercise04_FoodShortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());

        Map<String, Buyer> someoneBuying = new HashMap<>();

        while (number-- > 0) {

            String[] input = scanner.nextLine().split("\\s+");

            String name = input[0];
            if (input.length == 4) {
                Citizen citizen = new Citizen(input[0], Integer.parseInt(input[1]), input[2], input[3]);
                someoneBuying.put(name, citizen);
            } else if (input.length == 3) {
                Rebel rebel = new Rebel(input[0], Integer.parseInt(input[1]), input[2]);
                someoneBuying.put(name, rebel);
            }
        }

        String inputBuyer = scanner.nextLine();



        while (!"End".equals(inputBuyer)) {
            Buyer buyer = someoneBuying.get(inputBuyer);
            if (buyer != null) {
                buyer.buyFood();
            }
            inputBuyer = scanner.nextLine();
        }

        int sumFood = someoneBuying.values().stream().mapToInt(Buyer::getFood).sum();

        System.out.println(sumFood);
    }
}
