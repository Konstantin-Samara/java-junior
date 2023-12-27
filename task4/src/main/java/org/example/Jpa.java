/**
2. С помощью JPA(Hibernate) выполнить:
* 2.1 Описать сущность Book из пункта 1.1
* 2.2 Создать Session и сохранить в таблицу 10 книг
* 2.3 Выгрузить список книг какого-то автора

* 3.* Создать сущность Автор (id biging, name varchar), и в сущности Book сделать поле типа Author (OneToOne)
* 3.1 * Выгрузить Список книг и убедиться, что поле author заполнено
* 3.2 ** В классе Author создать поле List<Book>, которое описывает список всех книг этого автора. (OneToMany)
**/
package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class Jpa {
    static ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<Author> authors = new ArrayList<>();
    static ArrayList<Long> booksId = new ArrayList<>();

    public static void main(String[] args) {
        completeItems();

        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for (Book book: books) {session.persist(book);}
            for (Author author: authors) {session.persist(author);}
            session.getTransaction().commit();
        }
        books.clear();
        authors.clear();

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            authors = (ArrayList<Author>) session
                    .createQuery("select a from Author a",Author.class).getResultList();
            books = (ArrayList<Book>) session
                    .createQuery("select b from Book b where authorId=3", Book.class)
                    .getResultList();
            for (Book book:books) {
                System.out.println(book);
            }
            for (Author author:authors){
                System.out.println(author);
            }
        }
    }
    public static void completeItems(){
        for (int i = 1; i < 6; i++) {
            booksId.clear();
            booksId.add((long) i+(i-1));
            Author author = new Author("какой-то","писатель-"+i,booksId);
            authors.add(author);
        }
        booksId.clear();
        booksId.add(2L);
        booksId.add(4L);
        booksId.add(6L);
        booksId.add(8L);
        booksId.add(10L);
        authors.add(new Author("Александр","Пушкин",booksId));
        Long n = 1L;
        for (int i = 1; i < 11; i++) {
            if (i%2==0){
                books.add(new Book("Book-"+i,6L));
            }
            else {
                books.add(new Book("Book-"+i,n++));
            }
        }
    }
}
