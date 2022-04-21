import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

import static ru.otus.homework.reflection.ReflectionHelper.*;

public class TestRunner {

    public static void run(String className) throws ClassNotFoundException {
        (new TestRunner()).test(className);
    }

    private void test(String className) throws ClassNotFoundException {
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
        System.out.printf("Всего тестов: %s, Успешно: %s, Провалено: %s%n", pass + fail, pass, fail);
    }
}
