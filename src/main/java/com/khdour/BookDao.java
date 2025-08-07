package com.khdour;

import org.jdbi.v3.core.Jdbi;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;


public class BookDao {
    
     private final Jdbi jdbi;

       public BookDao(DataSource dataSource) {
                  this.jdbi = Jdbi.create(dataSource);

    }
    
    public int insertBook(Book book) {
        return jdbi.withHandle(handle -> 
            handle.createUpdate("INSERT INTO book (title, isbn, language, author) VALUES (:title, :isbn, :language, :author)")
                .bindBean(book)
                .execute()
        );
    }

    public Optional<Book> findBookByIsbn(int isbn) {
        return jdbi.withHandle(handle -> 
            handle.createQuery("SELECT title, isbn, language, author FROM book WHERE isbn = :isbn")
                .bind("isbn", isbn)
                .mapToBean(Book.class)
                .findFirst()
        );
    }

    public List<Book> findAllBooks() {
        return jdbi.withHandle(handle -> 
            handle.createQuery("SELECT title, isbn, language, author FROM book")
                .mapToBean(Book.class)
                .list()
        );
    }

    public int updateBook(Book book) {
        return jdbi.withHandle(handle -> 
            handle.createUpdate("UPDATE book SET title = :title, language = :language, author = :author WHERE isbn = :isbn")
                .bindBean(book)
                .execute()
        );
    }

    public int deleteBookByIsbn(int isbn) {
        return jdbi.withHandle(handle -> 
            handle.createUpdate("DELETE FROM book WHERE isbn = :isbn")
                .bind("isbn", isbn)
                .execute()
        );
    }
    
    // Other methods can be added here as needed

    
    
}
