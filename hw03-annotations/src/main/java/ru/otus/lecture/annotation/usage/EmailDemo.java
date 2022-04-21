package ru.otus.lecture.annotation.usage;


import ru.otus.lecture.annotation.Email;
import ru.otus.lecture.annotation.Immutable;
import ru.otus.lecture.annotation.ImmutableEmail;

public class EmailDemo {
    public static void main(String[] args) {

        Class<?> clazz = EmailUse.class;

        System.out.println("immutableEmail:" + clazz.isAnnotationPresent(ImmutableEmail.class));
        System.out.println("email:" + clazz.isAnnotationPresent(Email.class));
        System.out.println("immutable:" + clazz.isAnnotationPresent(Immutable.class));

        System.out.println("------------------");

        Class<?> clazzChild = EmailUseChild.class;

        System.out.println("immutableEmail:" + clazzChild.isAnnotationPresent(ImmutableEmail.class));
        System.out.println("email:" + clazzChild.isAnnotationPresent(Email.class));
        System.out.println("immutable:" + clazzChild.isAnnotationPresent(Immutable.class));

        System.out.println("------------------");
        System.out.println(EmailUseChild.class.isAssignableFrom(EmailUse.class));
        System.out.println(EmailUse.class.isAssignableFrom(EmailUseChild.class));
    }

    @ImmutableEmail
    private static class EmailUse {

    }

    private static class EmailUseChild extends EmailUse {

    }

}
