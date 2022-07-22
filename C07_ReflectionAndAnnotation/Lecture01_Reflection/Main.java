package C07_ReflectionAndAnnotation.Lecture01_Reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class reflectionClass = Reflection.class;

        System.out.println(reflectionClass.toString());
        System.out.println(reflectionClass.getSuperclass().toString());

        Class [] interfaces = reflectionClass.getInterfaces();

        for (Class i: interfaces) {
            System.out.println(i);
        }
        // Arrays.stream(interfaces).forEach(i -> System.out.println(i));
        // Arrays.stream(interfaces).forEach(System.out::println);

        Object reflectionObject = reflectionClass.getDeclaredConstructor().newInstance();

        System.out.println(reflectionObject);
    }

}
