package org.example;

import java.util.List;

public class SearchResults {
    private int page;
    private List<Book> books;
    private List<Author> authors;
    private List<Category> categories;

    public List<Book> getBooks() {
        return books;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public SearchResults() {
        this.page = page;
        this.books = books;
        this.authors = authors;
        this.categories = categories;
    }

    // Constructor, getters, and setters for Book, Author, and Category lists
    // Implement constructors, getters, and setters based on your requirements
}