package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {
        if (driverRepository.getByName(driverName) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driverName));
        }
        Driver driver = new DriverImpl(driverName);
        driverRepository.add(driver);
        return String.format(DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        Car car = null;
        if (type.equals("Muscle")) {
            car = new MuscleCar(model, horsePower);
        } else if (type.equals("Sports")) {
            car = new SportsCar(model, horsePower);
        }
        carRepository.add(car);
        return String.format(CAR_CREATED, type + "Car", model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        if (carRepository.getByName(carModel) == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        Driver driver = driverRepository.getByName(driverName);
        Car car = carRepository.getByName(carModel);
        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        Driver driver = driverRepository.getByName(driverName);
        Race race = raceRepository.getByName(raceName);
        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Race race = raceRepository.getByName(raceName);
        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }
        int laps = race.getLaps();

        Collection<Driver> drivers = race.getDrivers();

        List<Driver> winners = drivers.stream()
                .sorted((d1, d2) -> Double.compare(d2.getCar().calculateRacePoints(laps), d1.getCar().calculateRacePoints(laps)))
                .limit(3)
                .collect(Collectors.toList());

        raceRepository.remove(race);

        String firstDriverName = winners.get(0).getName();
        String secondDriverName = winners.get(1).getName();
        String thirdDriverName = winners.get(2).getName();

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(DRIVER_FIRST_POSITION, firstDriverName, raceName));
        sb.append(System.lineSeparator());
        sb.append(String.format(DRIVER_SECOND_POSITION, secondDriverName, raceName));
        sb.append(System.lineSeparator());
        sb.append(String.format(DRIVER_THIRD_POSITION, thirdDriverName, raceName));

        return sb.toString();
    }

    @Override
    public String createRace(String raceName, int laps) {
        if (raceRepository.getByName(raceName) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, raceName));
        }
        Race race = new RaceImpl(raceName, laps);
        raceRepository.add(race);
        return String.format(RACE_CREATED, raceName);
    }
}
