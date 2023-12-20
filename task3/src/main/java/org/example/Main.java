package org.example;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Human human = new Human("Иван","Иванович","Иванов",43);
        String str = "./src/main/java/org/example/"
                +human.getClass().getName() +"_"
                + UUID.randomUUID();
        WriteRead.save(human, str);
        System.out.println("Записали :\n"+human+"\nСчитали :\n"+WriteRead.read(str));
    }

}