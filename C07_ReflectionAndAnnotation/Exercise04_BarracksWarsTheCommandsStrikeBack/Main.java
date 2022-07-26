package C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack;

import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.core.Engine;
import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.core.factories.UnitFactoryImpl;
import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.data.UnitRepository;
import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.interfaces.Repository;
import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.interfaces.Runnable;
import C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
