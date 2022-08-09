package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        toys = new ToyRepository();
        houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {

        House house;

        if (type.equals("ShortHouse")) {
            house = new ShortHouse(name);
        } else if (type.equals("LongHouse")) {
            house = new LongHouse(name);
        } else throw new NullPointerException(INVALID_HOUSE_TYPE);

        houses.add(house);

        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        if (type.equals("Ball")) {
            toy = new Ball();
        } else if (type.equals("Mouse")) {
            toy = new Mouse();
        } else throw new IllegalArgumentException(INVALID_TOY_TYPE);

        toys.buyToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);

        if (toy == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }
        House house = houses.stream()
                .filter(h -> h.getName()
                        .equals(houseName))
                .findFirst().orElse(null);

        house.buyToy(toy);
        toys.removeToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        House house = houses.stream()
                .filter(h -> h.getName()
                        .equals(houseName))
                .findFirst().orElse(null);
        Cat cat;

        if (catType.equals("ShorthairCat")) {
            cat = new ShorthairCat(catName, catBreed, price);
        } else if (catType.equals("LonghairCat")) {
            cat = new LonghairCat(catName, catBreed, price);
        } else throw new IllegalArgumentException(String.format(INVALID_CAT_TYPE));

        String houseType = house.getClass().getSimpleName();

        boolean shortHairCatShortHouse = catType.equals("ShorthairCat") && houseType.equals("ShortHouse");
        boolean longHairCatLongHouse = catType.equals("LonghairCat") && houseType.equals("LongHouse");
        if (shortHairCatShortHouse || longHairCatLongHouse) {
            house.addCat(cat);
        } else return String.format(UNSUITABLE_HOUSE);

        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House house = houses.stream()
                .filter(h -> h.getName()
                        .equals(houseName))
                .findFirst().orElse(null);
        house.feeding();
        return String.format(FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.stream()
                .filter(h -> h.getName()
                        .equals(houseName))
                .findFirst().orElse(null);
        Double catsPrice = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        Double toysPrice = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        return String.format(VALUE_HOUSE, houseName, catsPrice + toysPrice);
    }

    @Override
    public String getStatistics() {
        return houses.stream().map(h->h.getStatistics()).collect(Collectors.joining(System.lineSeparator()));
    }
}
