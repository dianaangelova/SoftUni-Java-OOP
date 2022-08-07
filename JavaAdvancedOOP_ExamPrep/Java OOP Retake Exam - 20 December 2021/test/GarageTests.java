package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GarageTests {

    private Car car;
    private Car car1;
    private Car car2;
    private Garage garage;
    private List<Car> carList;

    @Before
    public void setup() {
        car = new Car("Honda", 1500, 2500.00);
        car1 = new Car("Mazda", 1200, 1500.00);
        car2 = new Car("Skoda", 1900, 3500.00);
        carList = new ArrayList<>();
        garage = new Garage();
        carList.add(car);
        carList.add(car1);
        carList.add(car2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_getCarsThrow() {
        List<Car> cars = garage.getCars();
        cars.remove(1);
    }

    @Test
    public void test_getCountSuccess() {
        garage.addCar(car);
        garage.addCar(car1);
        garage.addCar(car2);

        Assert.assertEquals(3, garage.getCount());
    }

    @Test
    public void test_findAllCarsWithMaxSpeedAboveSuccess() {
        List<Car> expectedCarsList = carList.stream()
                .filter(c -> c.getMaxSpeed() > 1500)
                .collect(Collectors.toList());

        garage.addCar(car);
        garage.addCar(car1);
        garage.addCar(car2);

        List<Car> actualCarsList = garage.findAllCarsWithMaxSpeedAbove(1500);

        Assert.assertEquals(expectedCarsList, actualCarsList);
    }

    @Test
    public void test_addCarSuccess() {
        garage.addCar(car);
        Assert.assertEquals(1, garage.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addNullCarThrow() {
        garage.addCar(null);
    }

    @Test
    public void test_getTheMostExpensiveCarSuccess() {
        Car expectedCar = carList.stream()
                .sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .limit(1)
                .findFirst()
                .orElse(null);

        garage.addCar(car);
        garage.addCar(car1);
        garage.addCar(car2);

        Car actualCar = garage.getTheMostExpensiveCar();

        Assert.assertEquals(expectedCar, actualCar);
    }

    @Test
    public void test_findAllCarsByBrandSuccess() {
        List<Car> expectedCars = carList.stream()
                .filter(c -> c.getBrand().equals("Mazda"))
                .collect(Collectors.toList());

        garage.addCar(car);
        garage.addCar(car1);
        garage.addCar(car2);

        List<Car> actualCars = garage.findAllCarsByBrand("Mazda");

        Assert.assertEquals(expectedCars, actualCars);
    }

}