package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "authorId")
    private Long authorId;

    public Book(){}
    public Book(String name, Long authorId){
        this.name = name;
        this.authorId = authorId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        String s = "";
        for (Author author : Jpa.authors) {
            if (author.getId()==authorId){
                s = author.getFirstName()+" "+author.getLastName();
            }
        }
        return "ID: " + id + " Name: " + name +" Author (ID: "+authorId+" ) "+s;
    }
}
