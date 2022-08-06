package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PetStoreTests {
    private Animal animal;
    private Animal animal1;
    private Animal animal2;
    private Animal animal3;
    private List<Animal> animalList;
    private PetStore petStore;


    @Before
    public void setup() {
        animal = new Animal("Dog", 20, 150.00);
        animal1 = new Animal("Cat", 7, 50.00);
        animal2 = new Animal("Goat", 12, 80.00);
        animal3 = new Animal("Dog", 12, 90.00);

        animalList = new ArrayList<>();

        animalList.add(animal);
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);

        petStore = new PetStore();
    }

    @Test
    public void test_GetAnimalsSuccess() {
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);

        List<Animal> expected = petStore.getAnimals();
        Assert.assertEquals(animalList, expected);
    }

    @Test
    public void test_FindAllAnimalsWithMaxKilogramsSuccess() {
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);

        List<Animal> expectedList = animalList.stream()
                .filter(c -> c.getMaxKilograms() > 50)
                .collect(Collectors.toList());

        List<Animal> actual = petStore.findAllAnimalsWithMaxKilograms(50);

        Assert.assertEquals(expectedList, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAnimalThrow() {
        petStore.addAnimal(null);
    }

    @Test
    public void test_GetTheMostExpensiveAnimal() {
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);

        Animal actualAnimal = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(animal, actualAnimal);
    }

    @Test
    public void test_findAllAnimalBySpecie() {
        petStore.addAnimal(animal);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);

        List<Animal> actualList = animalList.stream()
                .filter(c -> c.getSpecie().equals("Dog"))
                .collect(Collectors.toList());

        List<Animal> expectedList = petStore.findAllAnimalBySpecie("Dog");
        Assert.assertEquals(actualList, expectedList);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetAnimalsShouldReturnUnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(1);
    }

}

