import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import static ru.otus.reflection.ReflectionHelper.*;

public class AnnotationUtils {

    public static String run(String className) throws ClassNotFoundException {
        return (new AnnotationUtils()).test(className);
    }

    private String test(String className) throws ClassNotFoundException {
        var clazz = Class.forName(className);
        var before = getAnnotationMethods(clazz, Before.class);
        var after = getAnnotationMethods(clazz, After.class);
        var test = getAnnotationMethods(clazz, Test.class);
        var pass = 0;
        var fail = 0;

        for (var testMethod : test) {
            Object newObject = instantiate(clazz);
            try {
                for (var beforeMethod : before) {
                    callMethod(newObject, beforeMethod);
                }
                callMethod(newObject, testMethod);
                pass++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                fail++;
            } finally {
                try {
                    for (var afterMethod : after) {
                        callMethod(newObject, afterMethod);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return String.format("Всего тестов: %s, Успешно: %s, Провалено: %s", pass + fail, pass, fail);
    }
}
