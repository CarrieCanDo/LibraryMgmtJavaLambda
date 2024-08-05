package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create a library instance
        Library library = new Library();

        // Add some books to the library
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 218, "Fiction"));
        library.addBook(new Book("A Brief History of Time", "Stephen Hawking", 1988, 256, "Science"));
        library.addBook(new Book("The Art of War", "Sun Tzu", -500, 273, "History"));
        library.addBook(new Book("1984", "George Orwell", 1949, 328, "Fiction"));

        // Print all book titles
        System.out.println("All book titles:");
        library.printAllBookTitles();

        // Find books by year
        System.out.println("\nBooks published in 1949:");
        library.findBooksByYear(1949).forEach(book -> System.out.println(book.getTitle()));

        // Find books by author
        System.out.println("\nBooks by George Orwell:");
        library.findBooksByAuthor("George Orwell").forEach(book -> System.out.println(book.getTitle()));

        // Find the book with the most pages
        System.out.println("\nBook with the most pages:");
        Book mostPagesBook = library.findBookWithMostPages();
        if (mostPagesBook != null) {
            System.out.println(mostPagesBook.getTitle());
        }

        // Find books with more than 250 pages
        System.out.println("\nBooks with more than 250 pages:");
        library.findBooksWithMoreThanNPages(250).forEach(book -> System.out.println(book.getTitle()));

        // Find books by category
        System.out.println("\nBooks in the Fiction category:");
        library.findBooksByCategory("Fiction").forEach(book -> System.out.println(book.getTitle()));

        // Loan out a book
        System.out.println("\nLoan out '1984':");
        if (library.loanBook("1984")) {
            System.out.println("Successfully loaned out '1984'.");
        } else {
            System.out.println("'1984' is already on loan.");
        }

        // Try to loan out the same book again
        System.out.println("\nAttempt to loan out '1984' again:");
        if (library.loanBook("1984")) {
            System.out.println("Successfully loaned out '1984'.");
        } else {
            System.out.println("'1984' is already on loan.");
        }

        // Return the book
        System.out.println("\nReturn '1984':");
        if (library.returnBook("1984")) {
            System.out.println("Successfully returned '1984'.");
        } else {
            System.out.println("'1984' was not on loan.");
        }

        // Calculate late fees (simulate a late return)
        Book lateBook = new Book("Late Book", "Author", 2020, 300, "Fiction");
        lateBook.setOnLoan(true);
        lateBook.setBorrowDate(LocalDate.now().minusDays(20)); // Borrowed 20 days ago
        System.out.println("\nLate fees for 'Late Book': $" + library.calculateLateFees(lateBook));
    }
}
