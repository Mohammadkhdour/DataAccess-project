package com.khdour;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
     // Load configuration
        String url = "jdbc:mysql://localhost:3306/accessData";
        String user = "root";
        String password = "mohammad1234";

        // Run Flyway migrations
        FlywayMigration.migrateDatabase(url, user, password);

        DataSource dataSource = DataSourceConfig.getDataSource();

        try (Connection connection = dataSource.getConnection()) {

         BookDao bookDao = new BookDao(dataSource);



        bookDao.insertBook(new Book("Alice", 1237, "English", "Author A"));

        System.out.println("Books:");

        bookDao.updateBook(new Book("Alice Smith", 12345, "English", "Author A"));
        bookDao.deleteBookByIsbn(12345);
        bookDao.findAllBooks().forEach(b -> 
        System.out.println(b.toString()));
        System.out.println("Number of books: " + bookDao.findAllBooks().size());
        bookDao.deleteBookByIsbn(1237);
        connection.close();
        } catch (SQLException e) {
        e.printStackTrace();
        }
        
    }
}
  