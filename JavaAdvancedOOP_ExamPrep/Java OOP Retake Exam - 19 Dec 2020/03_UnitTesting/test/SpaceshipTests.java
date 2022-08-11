package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SpaceshipTests {
    private Astronaut astronaut1;
    private Astronaut astronaut2;
    private Astronaut astronaut3;
    private List<Astronaut> astronautList;

    @Before
    public void setup() {
        astronaut1 = new Astronaut("First", 59.10);
        astronaut2 = new Astronaut("Second", 60.10);
        astronaut3 = new Astronaut("Third", 61.10);
    }

    @Test
    public void test_ConstructorSuccess() {
        Spaceship spaceship = new Spaceship("Apollo", 100);
    }

    @Test
    public void test_GetCountSuccess() {
        Spaceship spaceship = new Spaceship("Apollo", 100);
        int actual = spaceship.getCount();
        Assert.assertEquals(0, actual);

        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        Assert.assertEquals(2, spaceship.getCount());
    }

    @Test
    public void test_GetCapacitySuccess() {
        Spaceship spaceship = new Spaceship("Apollo", 5);
        Assert.assertEquals(5, spaceship.getCapacity());
    }

    @Test
    public void test_GetNameSuccess() {
        Spaceship spaceship = new Spaceship("Apollo", 5);
        Assert.assertEquals("Apollo", spaceship.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAstronautsThrowNoCapacity() {
        Spaceship spaceship = new Spaceship("Apollo", 2);
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        spaceship.add(astronaut3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAstronautsThrowAstronautExists() {
        Spaceship spaceship = new Spaceship("Apollo", 5);
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        spaceship.add(astronaut2);
    }

    @Test
    public void test_RemoveFalseNullFound() {
        Spaceship spaceship = new Spaceship("Apollo", 5);
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        spaceship.add(astronaut3);
        boolean isRemoveActual = spaceship.remove("Petra");
        Assert.assertFalse(isRemoveActual);
    }

    @Test
    public void test_RemoveTrueSuccess() {
        Spaceship spaceship = new Spaceship("Apollo", 5);
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        spaceship.add(astronaut3);
        boolean isRemoveActual = spaceship.remove("First");
        Assert.assertTrue(isRemoveActual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_setCapacityNegativeThrow() {
        Spaceship spaceship = new Spaceship("Apollo", -5);
    }

    @Test(expected = NullPointerException.class)
    public void test_setNameEmptyThrow() {
        Spaceship spaceship = new Spaceship("", 5);
    }

}
