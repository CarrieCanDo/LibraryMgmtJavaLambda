package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    //Method to add a book to library
    public void addBook(Book book) {
        books.add(book);
    }

    //Method to remove a book from the library by title
    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equals(title));

    }

    //Method to find all books published in a specific year
    public List<Book> findBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
        //method to find books by author

        public List<Book> findBooksByAuthor (String author){
            return books.stream()
                    .filter(book -> book.getAuthor().equais(author))
                    .collect(Collectors.toList());


        }
    }

}