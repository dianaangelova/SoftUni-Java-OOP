package C07_ReflectionAndAnnotation.Lecture03_HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Class reflectionClass = Reflection.class;

        Field[] fields = reflectionClass.getDeclaredFields();
        Method [] methods = reflectionClass.getDeclaredMethods();

        Arrays.stream(fields)
                .filter(field -> !Modifier.isPrivate(field.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(field -> System.out.printf("%s must be private!%n", field.getName()));

        Arrays.stream(methods)
                .filter(method -> !Modifier.isPublic(method.getModifiers()) && method.getName().startsWith("get") && method.getParameterCount()==0)
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method->System.out.printf("%s have to be public!%n", method.getName()));

        Arrays.stream(methods)
                .filter(method -> !Modifier.isPrivate(method.getModifiers()) && method.getName().startsWith("set") && method.getParameterCount()==1)
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method->System.out.printf("%s have to be private!%n", method.getName()));

    }
}
