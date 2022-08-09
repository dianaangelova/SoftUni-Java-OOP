package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HouseTests {
    private Cat cat;
    private Cat cat1;
    private Cat cat2;
    private List<Cat> catsList;

    @Before
    public void setup() {
        cat = new Cat("LongHair");
        cat1 = new Cat("ShortHair");
        cat2 = new Cat("WhiteHair");
        catsList = new ArrayList<>();
        catsList.add(cat);
        catsList.add(cat1);
        catsList.add(cat2);
    }

    @Test
    public void test_ConstructorSuccess() {
        House house = new House("LongHouse", 15);
        Assert.assertEquals("LongHouse", house.getName());
    }

    @Test(expected = NullPointerException.class)
    public void test_SetNameThrowWhitespaces() {
        House house = new House("   ", 15);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetNameThrowNullName() {
        House house = new House(null, 15);
    }

    @Test
    public void test_GetNameSuccess() {
        House house = new House("LongHouse", 15);
        house.getName();
        Assert.assertEquals("LongHouse", house.getName());
    }

    @Test
    public void test_GetCapacitySuccess() {
        House house = new House("LongHouse", 15);
        Assert.assertEquals(15, house.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetCapacityThrow() {
        House house = new House("Test", -15);
    }

    @Test
    public void test_GetCountAndAddSuccess() {
        House house = new House("LongHouse", 15);
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);
        Assert.assertEquals(catsList.size(), house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddCatThrowNoCapacity() {
        House house = new House("LongHouse", 2);
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveCatThrow() {
        House house = new House("LongHouse", 2);
        house.addCat(cat);
        house.removeCat("Dog");
    }

    @Test
    public void test_RemoveCatSuccess() {
        House house = new House("LongHouse", 2);
        house.addCat(cat);
        house.addCat(cat1);
        house.removeCat("LongHair");
        Assert.assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CatForSaleThrow() {
        House house = new House("LongHouse", 3);
        house.addCat(cat);
        house.addCat(cat1);
        house.catForSale("Dog");
    }

    @Test
    public void test_CatForSaleSuccess() {

        House house = new House("LongHouse", 3);
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);

        Cat actualCat = house.catForSale("LongHair");


        Assert.assertEquals(cat, actualCat);

        Assert.assertFalse(actualCat.isHungry());
    }


    @Test
    public void test_Statistics() {
    String expectedOutput = "The cat LongHair, ShortHair, WhiteHair is in the house LongHouse!";
        House house = new House("LongHouse", 15);
        house.addCat(cat);
        house.addCat(cat1);
        house.addCat(cat2);

        String actualStatistics = house.statistics();

        Assert.assertEquals(expectedOutput, actualStatistics);
    }
}
