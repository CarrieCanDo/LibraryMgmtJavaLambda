package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1 = new Book("Effective Java", "Joshua Bloch", 2008, 416, "Programming");
        book2 = new Book("Clean Code", "Robert C. Martin", 2008, 464, "Programming");
        book3 = new Book("The Pragmatic Programmer", "Andrew Hunt", 1999, 352, "Programming");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
    }

    @Test
    void testAddBook() {
        Book newBook = new Book("Design Patterns", "Erich Gamma", 1994, 395, "Programming");
        library.addBook(newBook);
        assertTrue(library.findBooksByTitle("Design Patterns").contains(newBook));
    }

    @Test
    void testRemoveBook() {
        library.removeBook("Effective Java");
        assertFalse(library.findBooksByTitle("Effective Java").contains(book1));
    }

    @Test
    void testFindBooksByYear() {
        List<Book> books2008 = library.findBooksByYear(2008);
        assertEquals(2, books2008.size());
        assertTrue(books2008.contains(book1));
        assertTrue(books2008.contains(book2));
    }

    @Test
    void testFindBooksByAuthor() {
        List<Book> booksByMartin = library.findBooksByAuthor("Robert C. Martin");
        assertEquals(1, booksByMartin.size());
        assertTrue(booksByMartin.contains(book2));
    }

    @Test
    void testFindBookWithMostPages() {
        Book bookWithMostPages = library.findBookWithMostPages();
        assertEquals(book2, bookWithMostPages);
    }

    @Test
    void testCalculateLateFees() {
        library.loanBook("Effective Java");
        book1.setBorrowDate(LocalDate.now().minusDays(20)); // Simulate the book being borrowed 20 days ago
        double lateFees = library.calculateLateFees(book1);
        assertEquals(3.0, lateFees); // 6 days late * $0.50 per day
    }
}
