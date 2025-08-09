package com.khdour;

import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;


public class BookDao{
    
     private final Jdbi jdbi;

       public BookDao(Jdbi jdbi) {
                  this.jdbi = jdbi;

    }
    
    public int insertBook(Book book) {
        return jdbi.withHandle(handle -> 
            handle.createUpdate("INSERT INTO book (title, isbn, language, author, description) VALUES (:title, :isbn, :language, :author, :description)")
                .bindBean(book)
                .execute()
        );
    }

    public Optional<Book> findBookByIsbn(int isbn) {
        return jdbi.withHandle(handle -> 
            handle.createQuery("SELECT title, isbn, language, author, description FROM book WHERE isbn = :isbn")
                .bind("isbn", isbn)
                .mapToBean(Book.class)
                .findFirst()
        );
    }

    public List<Book> findAllBooks() {
        return jdbi.withHandle(handle -> 
            handle.createQuery("SELECT title, isbn, language, author, description FROM book")
                .mapToBean(Book.class)
                .list()
        );
    }

    public int updateBook(Book book) {
        return jdbi.withHandle(handle -> 
            handle.createUpdate("UPDATE book SET title = :title, language = :language, author = :author, description = :description WHERE isbn = :isbn")
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

}
