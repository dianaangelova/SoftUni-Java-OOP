package C07_ReflectionAndAnnotation.Exercise02_BlackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Class<BlackBoxInt> blackBoxClass = BlackBoxInt.class;

        Method[] methods = blackBoxClass.getDeclaredMethods();

        Constructor<BlackBoxInt> constructor = blackBoxClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();

        Field innerValue = blackBoxClass.getDeclaredField("innerValue");
        innerValue.setAccessible(true);

        while (!"END".equals(input)) {

            String command = input.split("_")[0];
            int number = Integer.parseInt(input.split("_")[1]);

            switch (command) {
                case "add":

                    executeCommand(blackBoxClass, blackBoxInt, number, innerValue, "add");

                    break;
                case "subtract":
                    executeCommand(blackBoxClass, blackBoxInt, number, innerValue, "subtract");

                    break;
                case "multiply":
                    executeCommand(blackBoxClass, blackBoxInt, number, innerValue, "multiply");

                    break;
                case "divide":
                    executeCommand(blackBoxClass, blackBoxInt, number, innerValue, "divide");
                    break;
                case "leftShift":
                    executeCommand(blackBoxClass, blackBoxInt, number, innerValue, "leftShift");
                    break;
                case "rightShift":
                    executeCommand(blackBoxClass, blackBoxInt, number, innerValue, "rightShift");
                    break;
            }

            input = scanner.nextLine();
        }
    }

    private static void executeCommand(Class<BlackBoxInt> blackBoxClass, BlackBoxInt blackBoxInt, int number, Field innerValue, String command) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Method> methods = Arrays.asList(blackBoxClass.getDeclaredMethods());
        Method currentMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(command)) {
                currentMethod = method;
                break;
            }
        }

        currentMethod.setAccessible(true);

        currentMethod.invoke(blackBoxInt, number);
        System.out.println(innerValue.get(blackBoxInt));
    }
}
