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

    public static void print(Field[] fields, String modifierType) {

        if (modifierType.equals("protected")) {
            fields = Arrays.stream(fields)
                    .filter(field -> Modifier.isProtected(field.getModifiers())).toArray(Field[]::new);
        } else if (modifierType.equals("private")) {
            fields = Arrays.stream(fields)
                    .filter(field -> Modifier.isPrivate(field.getModifiers())).toArray(Field[]::new);
        } else if (modifierType.equals("public")) {
            fields = Arrays.stream(fields)
                    .filter(field -> Modifier.isPublic(field.getModifiers())).toArray(Field[]::new);
        }

        for (Field f : fields) {
            System.out.printf("%s %s %s%n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName());
        }

    }
}
