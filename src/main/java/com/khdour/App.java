package com.khdour;

import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;


public class App 
{
    public static void main( String[] args )
    {
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
         dao.updateTitle(1122,"good morning");


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
  