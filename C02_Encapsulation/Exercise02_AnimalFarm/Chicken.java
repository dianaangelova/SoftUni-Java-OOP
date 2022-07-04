package C02_Encapsulation.Exercise02_AnimalFarm;

public class Chicken {

    private String name;
    private int age;

    public Chicken(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private void setName(String name) {
        if (name.equals(null) || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    private void setAge(int age) {
        if (age < 0 || age > 15) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    private double calculateProductPerDay() {
        double numberEggs;
        if (age <= 5) {
            numberEggs = 2.00;
        } else if (age >= 6 && age <= 11) {
            numberEggs = 1.00;
        } else numberEggs = 0.75;

        return numberEggs;
    }

    public double productPerDay() {
        return calculateProductPerDay();
    }

    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day.", name, age, productPerDay());
    }
}
