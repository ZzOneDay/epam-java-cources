package com.epam.university.java.core.task018;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Task018Impl implements Task018 {
    @Override
    public boolean isAnnotationPresent(Object toCheck, Class<?> annotationToFind) {
        //TODO Когда-нибудь, нужно будет переписать авбсолюно весь код из за одной недоработки.
        Class<? extends Annotation> annotations = (Class<? extends Annotation>) annotationToFind;

        Constructor[] constructors = toCheck.getClass().getConstructors();
        for (Constructor constructor : constructors) {
            if (constructor.isAnnotationPresent(annotations)) {
                return true;
            }
        }

        Method[] methods = toCheck.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotations)) {
                return true;
            }
        }

        Field[] fields = toCheck.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(annotations)) {
                return true;
            }
        }


        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                if (parameter.isAnnotationPresent(annotations)) {
                    return true;
                }
            }
        }

        if (toCheck.getClass().isAnnotationPresent(annotations)) {
            return true;
        }

        Package aPackage = toCheck.getClass().getPackage();
        if (aPackage.isAnnotationPresent(annotations)) {
            return true;
        }

        if (toCheck.getClass().isAnnotationPresent(annotations)) {
            return true;
        }
        return false;
    }
}
