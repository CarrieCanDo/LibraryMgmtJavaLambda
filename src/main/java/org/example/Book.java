package org.example;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private int pages;


    public Book(String title, String author, int publicationYear, int pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title= " + title +
                ", author= " + author +
                ", publicationYear= " + publicationYear +
                ", pages= " + pages +
                "}";
    }

}
