package ru.otus.annotations;

import java.lang.reflect.Method;

public class AnnotationAnalyzer {
    public static void test(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        var pass = 0;
        var fail = 0;
        var total = 0;

        for (var method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                total++;
                for (var beforeMethod : methods) {
                    if (beforeMethod.isAnnotationPresent(Before.class)) {
                        try {
                            beforeMethod.invoke(clazz);
                            method.invoke(clazz);
                            pass++;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            fail++;
                        } finally {
                            for (var afterMethod : methods) {
                                if (afterMethod.isAnnotationPresent(After.class)) {
                                    try {
                                        afterMethod.invoke(clazz);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.printf("Всего: %s, Успешно: %s, Провалено: %s%n", total, pass, fail);
    }
}
