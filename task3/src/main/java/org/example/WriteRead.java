package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteRead {
    public static void save(Object o, String s) {
        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream(s));
            objectOutputStream.writeObject(o);
            objectOutputStream.close();}
        catch(FileNotFoundException e) {System.out.println("Файл не найден.");}
        catch(IOException e) {System.out.println("Ошибка ввода-вывода данных.");}
    }
    public static Object read(String s) {
        Object o = null;

        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new FileInputStream(s));
            o = objectInputStream.readObject();
            objectInputStream.close();
            Files.delete(Path.of(s));
            return o;}
        catch(FileNotFoundException e) {System.out.println("Файл не найден.");}
        catch(IOException e) {System.out.println("Ошибка ввода-вывода данных.");}
        catch(ClassNotFoundException e) {System.out.println("Не найден класс - ObjectInputStream.");}
        return  o;
    }
}
