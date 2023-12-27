/**
 * 1. С помощью JDBC выполнить:
 * 1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
 * 1.2 Добавить в таблицу 10 книг
 * 1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
 **/
package org.example;

import java.sql.*;

public class Jdbc {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:database");
        executePrepare(connection);
        executeComplete(connection);

//        executeUpdate(connection);
//        executeDelete(connection);
//        try (Statement statement = connection.createStatement()){
//            ResultSet resultSet = statement.executeQuery("""
//                    select id,name,author from "books" where id > 2
//                    """);
//            System.out.println("selected (where id>2) :");
//            while (resultSet.next()){
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String author = resultSet.getString("author");
//                System.out.println(id+" "+name+" "+author);
//                }
//            }

        try (PreparedStatement preparedStatement = connection.prepareStatement("""
                select * from "books" where author = ?
                """)){
            String s = "Author4";
            preparedStatement.setObject(1,s);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("selected-Prepared (where name = "+s+") : ");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+" "
                        +resultSet.getString("name")+" "
                        +resultSet.getString("author"));
            }
        }
        connection.close();
        }
    private static void executePrepare(Connection c) throws SQLException {
        try (Statement statement = c.createStatement();){
            statement.execute("""
                    create table "books" (id bigint,name varchar(255),author varchar(255))
                    """);
            }
        }
    private static void executeComplete(Connection c) throws SQLException {
        try (Statement statement = c.createStatement()){
            System.out.println("  inserted : "+
            statement.executeUpdate("""
                            insert into "books" (id,name,author) values (1,'Book1','Author1'),
                            (2,'Book2','Author2'),(3,'Book3','Author3'),(4,'Book4','Author4'),
                            (5,'Book5','Author1'),(6,'Book6','Author2'),(7,'Book7','Author3'),
                            (8,'Book8','Author4'),(9,'Book9','Author4'),(10,'Book10','Author4')
                            """));
            }
        }

//    private static void executeUpdate(Connection c) throws SQLException {
//        try (Statement statement = c.createStatement()){
//            System.out.println("updated : "+
//                    statement.executeUpdate("""
//                            update "user"
//                            set name = 'unknown' where id > 3
//                            """));
//            }
//        }
//    private static void executeDelete(Connection c) throws SQLException {
//        try (Statement statement = c.createStatement()){
//            System.out.println("deleted : "+
//                    statement.executeUpdate("""
//                            delete from "user"
//                            where id = 5
//                            """));
//        }
//    }

    }