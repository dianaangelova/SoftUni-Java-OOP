package C07_ReflectionAndAnnotation.Exercise01_HarvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        Class reflectionCLass = RichSoilLand.class;

        Field[] fields = reflectionCLass.getDeclaredFields();

        while (!"HARVEST".equals(command)) {
            print(fields, command);
            command = scanner.nextLine();
        }

    }

    public static void print(Field[] fields, String type) {

        if (type.equals("protected")) {
            Arrays.stream(fields)
                    .filter(field -> Modifier.isProtected(field.getModifiers()))
                    .forEach(field -> System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName()));
        } else if (type.equals("private")) {
            Arrays.stream(fields)
                    .filter(field -> Modifier.isPrivate(field.getModifiers()))
                    .forEach(field -> System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName()));

        } else if (type.equals("public")) {
            Arrays.stream(fields)
                    .filter(field -> Modifier.isPublic(field.getModifiers()))
                    .forEach(field -> System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName()));

        } else if (type.equals("all")) {
            Arrays.stream(fields)
                    //  .filter(field -> Modifier.isPublic(field.getModifiers()))
                    .forEach(field -> System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName()));

        }
    }
}
