package C07_ReflectionAndAnnotation.Exercise03_BarracksWarsANewFactory;

import C07_ReflectionAndAnnotation.Exercise03_BarracksWarsANewFactory.interfaces.Repository;
import C07_ReflectionAndAnnotation.Exercise03_BarracksWarsANewFactory.interfaces.Runnable;
import C07_ReflectionAndAnnotation.Exercise03_BarracksWarsANewFactory.interfaces.UnitFactory;
import C07_ReflectionAndAnnotation.Exercise03_BarracksWarsANewFactory.core.Engine;
import C07_ReflectionAndAnnotation.Exercise03_BarracksWarsANewFactory.core.factories.UnitFactoryImpl;
import C07_ReflectionAndAnnotation.Exercise03_BarracksWarsANewFactory.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
