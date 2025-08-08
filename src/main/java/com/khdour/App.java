package com.khdour;

import javax.sql.DataSource;
import java.sql.Connection;
import org.jdbi.v3.core.Jdbi;
import java.sql.SQLException;
import java.util.List;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

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


         Jdbi jdbi = Jdbi.create(dataSource);
         jdbi.installPlugin(new SqlObjectPlugin());


         SQLDao dao = jdbi.onDemand(SQLDao.class);
         dao.getTitles("book").forEach(b->System.out.println(b.toString()));
         dao.insert(new Book("soul", 1122, "arabic", "sammer","good book"));


         BookDao bookDao = new BookDao(dataSource);
        bookDao.insertBook(new Book("Alice", 1239, "English", "saleh", "Network book"));

        System.out.println("Books:");

        bookDao.updateBook(new Book("math", 1122, "English","sami" ,"talk about the calculation"));
        System.out.println("deleted row "+bookDao.deleteBookByIsbn(100004));


        bookDao.findAllBooks().forEach(b -> 
        System.out.println(b.toString()));

        System.out.println("Number of books: " + bookDao.findAllBooks().size());

        
    }
}
  