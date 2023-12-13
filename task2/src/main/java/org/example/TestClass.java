package org.example;

public class TestClass {
    @Test(order = -2)
    public static void first(){
        System.out.println("Запущен метод \"first\"");
    }
    @Test(order = 5)
    public static void third(){
        System.out.println("Запущен метод \"third\"");
    }
    @Test
    public static void second(){
        System.out.println("Запущен метод \"second\"");
    }
    @BeforeTest
    public static void before(){
        System.out.println("Запущен метод \"before\"");
    }
    @AfterTest
    public static void after(){
        System.out.println("Запущен метод \"after\"");
    }
}
