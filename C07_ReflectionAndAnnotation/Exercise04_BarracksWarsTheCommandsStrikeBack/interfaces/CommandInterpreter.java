package C07_ReflectionAndAnnotation.Exercise04_BarracksWarsTheCommandsStrikeBack.interfaces;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName);
}
