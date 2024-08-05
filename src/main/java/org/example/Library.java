package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    // Method to add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to remove a book from the library by title
    public void removeBook(String title) {
        Predicate<Book> byTitle = book -> book.getTitle().equals(title);
        books.removeIf(byTitle);
    }

    // Method to find all books published in a specific year
    public List<Book> findBooksByYear(int year) {
        Predicate<Book> byYear = book -> book.getPublicationYear() == year;
        return books.stream()
                .filter(byYear)
                .collect(Collectors.toList());
    }

    // Method to find all books by a specific author
    public List<Book> findBooksByAuthor(String author) {
        Predicate<Book> byAuthor = book -> book.getAuthor().equals(author);
        return books.stream()
                .filter(byAuthor)
                .collect(Collectors.toList());
    }

    // Method to find the book with the most pages
    public Book findBookWithMostPages() {
        return books.stream()
                .max(Comparator.comparingInt(book -> book.getPages()))
                .orElse(null);
    }

    // Method to find all books with more than n pages
    public List<Book> findBooksWithMoreThanNPages(int n) {
        Predicate<Book> moreThanNPages = book -> book.getPages() > n;
        return books.stream()
                .filter(moreThanNPages)
                .collect(Collectors.toList());
    }

    // Method to print all book titles in the library, sorted alphabetically
    public void printAllBookTitles() {
        Consumer<String> printTitle = title -> System.out.println(title);
        books.stream()
                .map(book -> book.getTitle())
                .sorted()
                .forEach(printTitle);
    }

    // Method to find all books in a specific category
    public List<Book> findBooksByCategory(String category) {
        Predicate<Book> byCategory = book -> book.getCategory().equals(category);
        return books.stream()
                .filter(byCategory)
                .collect(Collectors.toList());
    }

    // Method to loan out a book
    public boolean loanBook(String title) {
        Predicate<Book> byTitle = book -> book.getTitle().equals(title);
        Supplier<LocalDate> currentDate = () -> LocalDate.now();
        for (Book book : books) {
            if (byTitle.test(book) && !book.isOnLoan()) {
                book.setOnLoan(true);
                book.setBorrowDate(currentDate.get());
                return true;
            }
        }
        return false;
    }

    // Method to return a book
    public boolean returnBook(String title) {
        Predicate<Book> byTitle = book -> book.getTitle().equals(title);
        for (Book book : books) {
            if (byTitle.test(book) && book.isOnLoan()) {
                book.setOnLoan(false);
                book.setBorrowDate(null);
                return true;
            }
        }
        return false;
    }

    // Method to calculate late fees
    public double calculateLateFees(Book book) {
        Function<Book, Double> calculateFee = b -> {
            if (b.isOnLoan() && b.getBorrowDate() != null) {
                long daysOnLoan = ChronoUnit.DAYS.between(b.getBorrowDate(), LocalDate.now());
                if (daysOnLoan > 14) {
                    return (daysOnLoan - 14) * 0.50; // $0.50 per day after 2 weeks
                }
            }
            return 0.0;
        };
        return calculateFee.apply(book);
    }
}
