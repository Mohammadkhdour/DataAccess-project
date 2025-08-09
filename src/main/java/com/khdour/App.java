package com.khdour;

import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App 
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
     // Load configuration
        String url = "jdbc:mysql://localhost:3306/accessData";
        String user = "root";
        String password = "mohammad1234";



        // Run Flyway migrations
        FlywayMigration.migrateDatabase(url, user, password);

        DataSource dataSource = DataSourceConfig.getDataSource(url, user, password);



         Jdbi jdbi = Jdbi.create(dataSource);
         jdbi.installPlugin(new SqlObjectPlugin());


         SQLDao dao = jdbi.onDemand(SQLDao.class);
         dao.getTitles("book").forEach(b -> logger.info(b.toString()));
         dao.insert(new Book("soul", 1122, "arabic", "sammer", "good book"));
         dao.updateTitle(1122, "good morning");


         BookDao bookDao = new BookDao(jdbi);
        bookDao.insertBook(new Book("Alice", 1239, "English", "saleh", "Network book"));

        logger.info("Books:");

        bookDao.updateBook(new Book("math", 1122, "English", "sami", "talk about the calculation"));
        logger.info("deleted row " + bookDao.deleteBookByIsbn(1004));


        bookDao.findAllBooks().forEach(b -> 
        logger.info(b.toString()));

        logger.info("Number of books: " + bookDao.findAllBooks().size());

        
    }
}
