package C05_Polymorphism.Exercise03_WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();
        List<Food> foods = new ArrayList<>();

        String[] inputAnimal = scanner.nextLine().split("\\s+");

        while (!"End".equals(inputAnimal[0])) {

            String animalType = inputAnimal[0];
            String animalName = inputAnimal[1];
            double animalWeight = Double.parseDouble(inputAnimal[2]);
            String animalLivingRegion = inputAnimal[3];

            if ("Cat".equals(animalType)) {
                String catBreed = inputAnimal[4];

                Animal cat = new Cat(animalName, animalWeight, animalLivingRegion, catBreed);
                animals.add(cat);

            } else if ("Mouse".equals(animalType)) {

                Animal mouse = new Mouse(animalName, animalWeight, animalLivingRegion);
                animals.add(mouse);

            } else if ("Zebra".equals(animalType)) {

                Animal zebra = new Zebra(animalName, animalWeight, animalLivingRegion);
                animals.add(zebra);

            } else if ("Tiger".equals(animalType)) {

                Animal tiger = new Tiger(animalName, animalWeight, animalLivingRegion);
                animals.add(tiger);
            }

            String[] inputFood = scanner.nextLine().split("\\s+");

            String foodType = inputFood[0];
            int foodQuantity = Integer.parseInt(inputFood[1]);

            if ("Vegetable".equals(foodType)) {

                Food vegetable = new Vegetable(foodQuantity);
                foods.add(vegetable);

            } else if ("Meat".equals(foodType)) {

                Food meat = new Meat(foodQuantity);
                foods.add(meat);

            }

            inputAnimal = scanner.nextLine().split("\\s+");
        }

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            Food food = foods.get(i);

            animal.makeSound();
            animal.eat(food);

        }

        for (Animal animal : animals) {
            
            System.out.println(animal);

        }
    }
}
