package utils;

public class ReflectionUtils {

    public static void inspectClass(Class<?> clazz) {

        System.out.println("Class: " + clazz.getName());

        for (var f : clazz.getDeclaredFields()) {
            System.out.println("Field: " + f.getName());
        }

        for (var m : clazz.getDeclaredMethods()) {
            System.out.println("Method: " + m.getName());
        }
    }
}
