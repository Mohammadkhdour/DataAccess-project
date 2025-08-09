package com.khdour;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Simple Data Access App.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    
    private static BookDao bookDao;
    private static DataSource dataSource;
    
    @BeforeAll
    static void setUpDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/accessData";
            String user = "root";
            String password = "mohammad1234";

            dataSource = DataSourceConfig.getDataSource();
            FlywayMigration.migrateDatabase(dataSource);
            
            Jdbi jdbi = Jdbi.create(dataSource);
            bookDao = new BookDao(jdbi);

        } catch (Exception e) {
            fail("Failed to set up test database: " + e.getMessage());
        }
    }
    
    @Test
    @Order(1)
    @DisplayName("Test Database Connection")
    void testDatabaseConnection() {
        assertDoesNotThrow(() -> {
            try (Connection connection = dataSource.getConnection()) {
                assertNotNull(connection);
                assertFalse(connection.isClosed());
            }
        });
    }
    
    @Test
    @Order(2)
    @DisplayName("Test Insert Book")
    void testInsertBook() {
        // Create a test book
        Book testBook = new Book();
        testBook.setTitle("Test Book");
        testBook.setIsbn(9999);
        testBook.setLanguage("English");
        testBook.setAuthor("Test Author");
        testBook.setDescription("This is a test book");
        
        int result = bookDao.insertBook(testBook);
        
        assertTrue(result > 0);
        
        Optional<Book> retrievedBook = bookDao.findBookByIsbn(9999);
        assertTrue(retrievedBook.isPresent());
        
        Book found = retrievedBook.get();
        assertEquals("Test Book", found.getTitle());
        assertEquals(9999, found.getIsbn());
        assertEquals("English", found.getLanguage());
        assertEquals("Test Author", found.getAuthor());
        assertEquals("This is a test book", found.getDescription());
        
    }
    
    @Test
    @Order(3)
    @DisplayName("Test Find Book By ISBN")
    void testFindBookByIsbn() {
        Optional<Book> foundBook = bookDao.findBookByIsbn(1001);
        
        assertTrue(foundBook.isPresent());
        
        Book book = foundBook.get();
        assertEquals("History", book.getTitle());
        assertEquals("kareem", book.getAuthor());
        assertEquals("English", book.getLanguage());
        
        Optional<Book> notFound = bookDao.findBookByIsbn(8888);
        assertFalse(notFound.isPresent());
        
    }
    
    @Test
    @Order(4)
    @DisplayName("Test Find All Books")
    void testFindAllBooks() {
        List<Book> allBooks = bookDao.findAllBooks();
        
        assertNotNull(allBooks, "Book list should not be null");
        assertTrue(allBooks.size() > 0);
        
        assertTrue(allBooks.size() >= 6);
        
        for (Book book : allBooks) {
            assertNotNull(book.getTitle());
            assertTrue(book.getIsbn() > 0);
            assertNotNull(book.getLanguage());
            assertNotNull(book.getAuthor());
        }
        
    }
    
    @Test
    @Order(5)
    @DisplayName("Test Update Book")
    void testUpdateBook() {
        Book book = new Book("should update",1313,"arabic","jameel","book to test update method");
        bookDao.insertBook(book);
        book.setTitle("Updated Test Book Done");
        book.setDescription("Updated description Done");
        
        int result = bookDao.updateBook(book);
        assertTrue(result > 0);
        
        Optional<Book> updatedBook = bookDao.findBookByIsbn(1313);
        assertTrue(updatedBook.isPresent());
        
        Book updated = updatedBook.get();
        assertEquals("Updated Test Book Done", updated.getTitle());
        assertEquals("Updated description Done", updated.getDescription());
        assertEquals("jameel", updated.getAuthor());
        assertEquals(1313,updated.getIsbn());
        assertEquals("arabic", updated.getLanguage());

    }
    
    @Test
    @Order(6)
    @DisplayName("Test Book with Description from Migration V2")
    void testBookWithDescriptionFromMigration() {
        // Test the book inserted in V2 migration
        Optional<Book> bookFromV2 = bookDao.findBookByIsbn(1212);
        
        assertTrue(bookFromV2.isPresent());
        
        Book book = bookFromV2.get();
        assertEquals("the men", book.getTitle());
        assertEquals("mohammad khdour", book.getAuthor());
        assertEquals("arabic", book.getLanguage());
        assertEquals("A book about men's experiences and challenges in modern society",
                    book.getDescription());
        
    }
    
    @Test
    @Order(7)
    @DisplayName("Test Delete Book")
    void testDeleteBook() {
        Book bookToDelete = new Book();
        bookToDelete.setTitle("Book to Delete");
        bookToDelete.setIsbn(7777);
        bookToDelete.setLanguage("English");
        bookToDelete.setAuthor("Delete Author");
        bookToDelete.setDescription("This book will be deleted");
        
        bookDao.insertBook(bookToDelete);
        
        Optional<Book> existingBook = bookDao.findBookByIsbn(7777);
        assertTrue(existingBook.isPresent());
        
        int result = bookDao.deleteBookByIsbn(7777);
        assertTrue(result > 0);
        
        Optional<Book> deletedBook = bookDao.findBookByIsbn(7777);
        assertFalse(deletedBook.isPresent());
        
    }

}
