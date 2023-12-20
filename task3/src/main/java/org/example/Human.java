package org.example;

import java.io.Serializable;

public class Human implements Serializable {
    private String[] name = new String[3];
    private int age;
    public Human(String firstName, String fatherName, String lastName, int age){
        this.name[0] = firstName;
        this.name[1] = fatherName;
        this.name[2] = lastName;
        this.age=age;
    }
    @Override
    public String toString(){
        return this.name[0]+" "+this.name[1]+" "+this.name[2]+" age : "+this.age;
}
}
