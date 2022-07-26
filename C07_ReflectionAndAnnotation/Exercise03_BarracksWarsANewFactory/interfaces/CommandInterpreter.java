package C07_ReflectionAndAnnotation.Exercise03_BarracksWarsANewFactory.interfaces;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName);
}
