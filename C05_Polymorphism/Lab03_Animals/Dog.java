package C05_Polymorphism.Lab03_Animals;

public class Dog extends Animal{

    public Dog(String name, String favoriteFood) {
        super(name, favoriteFood);
    }

    @Override
    public String explainSelf() {
        return super.explainSelf() + System.lineSeparator() +"DJAAF";
    }
}
