package C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.core.factories;

import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.interfaces.Unit;
import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.models.units.";

    @Override
    public Unit createUnit(String unitType) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class clazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
        Constructor<Unit> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();

    }
}
