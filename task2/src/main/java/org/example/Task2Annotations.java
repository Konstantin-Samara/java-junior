package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Task2Annotations {
    public static void main(String[] args) {
        List<Method> allMethods = Arrays.stream(TestClass.class.getDeclaredMethods()).toList();
        List<Method> beforeMethods = allMethods.stream()
                        .filter(it->it.isAnnotationPresent(BeforeTest.class)).toList();
        List<Method> afterMethods = allMethods.stream()
                        .filter(it->it.isAnnotationPresent(AfterTest.class)).toList();
        List<Method> testMethods = allMethods.stream()
                .filter(it->it.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(Test.class).order())).toList();

        for (Method method : beforeMethods) {
            try {
                method.invoke(null);
            } catch (InvocationTargetException|IllegalAccessException e){
                throw new RuntimeException("Что-то там...не запустился метод 'before'...");
            }
        }
        for (Method method : testMethods) {
            try {
                method.invoke(null);
            } catch (InvocationTargetException|IllegalAccessException e){
                throw new RuntimeException("Что-то там...не запустился метод 'test'...");
            }        }
        for (Method method : afterMethods) {
            try {
                method.invoke(null);
            } catch (InvocationTargetException|IllegalAccessException e){
                throw new RuntimeException("Что-то там...не запустился метод 'after'...");
            }        }


    }
}
