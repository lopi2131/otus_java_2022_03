package ru.otus.lecture.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE})
public @interface PackageOwner {
    String owner() default "";
}
