package C05_Polymorphism.Exercise03_WildFarm;

public class Tiger extends Feline {
    //private String livingRegion;


    public Tiger(String animalName, Double animalWeight, String livingRegion) {
        super(animalName, "Tiger", animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {

        if (food instanceof Meat) {
            super.setFoodEaten(getFoodEaten() + food.getQuantity());
        } else {
            System.out.println("Tigers are not eating that type of food!");
        }

    }
}
