package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "booksId")
    private ArrayList<Long> booksId;
    public Author(){}
    public Author(String firstName, String lastName, ArrayList<Long> books){
        this.firstName = firstName;
        this.lastName = lastName;
        this.booksId = books;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Long> getBooksId() {
        return booksId;
    }

    public void setBooksId(ArrayList<Long> booksId) {
        this.booksId = booksId;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\''+
                '}';
    }
}
