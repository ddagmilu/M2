package org.example;

import org.glassfish.jersey.http.HttpHeaders;

import javax.ws.rs.container.ContainerRequestContext;
import java.util.*;


public class ServiceImplemantation implements serviceinterface{

    private Map<Integer, Book> Books;
    private Map<Integer, Author> Authors;
    private Map<Integer, Publisher> Publishers;

    private Map<Integer, Category> Categories;

    ServiceImplemantation(int maxAuthors, int maxPublishers, int maxBooks, int maxCategories) {
        Authors = new HashMap<Integer, Author>();
        Publishers = new HashMap<Integer, Publisher>();
        Books = new HashMap<Integer, Book>();
        Categories = new HashMap<Integer, Category>();

        for(int i = 0; i < maxPublishers; i++)
            Publishers.put(i, new Publisher(i));
        for(int i = 0; i<maxAuthors; i++)
            Authors.put(i, new Author(i, new Random().nextInt(maxPublishers)));
        for(int i = 0; i<maxBooks; i++)
            Books.put(i, new Book(i, new Random().nextInt(maxBooks)));
        for(int i = 0; i<maxCategories; i++) {
            Categories.put(i, new Category(i));
        }
    }


    //@Override
    //public Author getAuthor(int idE) {
    //    return Authors.get(idE);
    //}
    public List<Book> getAllBooks(int category, String language, int year, int page, int size) {
        ArrayList<Book> L = new ArrayList<>();
        for (Book book : this.Books.values()) {

            /*

            *__
            _*_
            __*
            **_
            *_*
            _**

             */
            // Check if any of the criteria match
            if ((category) == book.getidCategory() && language.isEmpty() && year == -1) {
                L.add(book);
            } else if (category == -1 && language.equalsIgnoreCase(book.getLanguage()) && year == -1) {
                L.add(book);
            } else if (category == -1 && language.isEmpty() && year == book.getPublicationYear()) {
                L.add(book);
            } else if ((category) == book.getidCategory() && language.equalsIgnoreCase(book.getLanguage()) && year == -1) {
                L.add(book);
            } else if ((category) == book.getidCategory() && language.isEmpty() && year == book.getPublicationYear()) {
                L.add(book);
            } else if (category == -1 && language.equalsIgnoreCase(book.getLanguage()) && year == book.getPublicationYear()) {
                L.add(book);
            } else if ((category) == book.getidCategory() && language.equalsIgnoreCase(book.getLanguage()) && year == book.getPublicationYear()) {
                L.add(book);
            } else if (category == -1 && language.isEmpty() && year == -1) {
                L.add(book);
            }

        }
        int totalBooks = L.size(); // 9
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalBooks);

        if (startIndex > totalBooks || startIndex < 0) {
            // Invalid page number, return an empty list or handle accordingly
            return L;
        }

        return L.subList(startIndex, endIndex);
    }

    public List<Book> getBooksByKeyword(String keyword, int size, int page) {
        ArrayList<Book> Result = new ArrayList<>();
        for (Book book: Books.values())
            if ((book.getTitle().toLowerCase()).contains(keyword.toLowerCase()) )
                Result.add(book);

        int totalBooks = Result.size();
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalBooks);

        if (startIndex > totalBooks || startIndex < 0) {
            // Invalid page number, return an empty list or handle accordingly
            return Result;
        }

        return Result.subList(startIndex, endIndex);
    }
    public List<Author> getAuthorsByKeyword(String keyword, int size, int page) {
        ArrayList<Author> Result = new ArrayList<>();
        for (Author author: Authors.values())
            if ((author.getFirstName()+author.getLastName().toLowerCase()).contains(keyword.toLowerCase()))
                Result.add(author);
        int totalAuthors = Result.size();
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalAuthors);

        if (startIndex > totalAuthors || startIndex < 0) {
            // Invalid page number, return an empty list or handle accordingly
            return Result;
        }

        return Result.subList(startIndex, endIndex);
    }
    public List<Category> getCategoriesByKeyword(String keyword, int size, int page) {
        ArrayList<Category> Result = new ArrayList<>();
        for (Category category: Categories.values())
            if ((category.getCategoryName().toLowerCase()).contains(keyword.toLowerCase()) )
                Result.add(category);
        int totalCategories = Result.size();
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalCategories);

        if (startIndex > totalCategories || startIndex < 0) {
            // Invalid page number, return an empty list or handle accordingly
            return Result;
        }

        return Result.subList(startIndex, endIndex);
    }

    public Book AddBook(Book book) {
        Books.put(Books.size()+1, book);
        return book;
    }

    public Integer CountBooks() {
        return Books.size();
    }
    @Override
    public void DeleteAuthorById(int idAuthor) {
        Authors.remove(idAuthor);
    }

    @Override
    public void DeleteBooksByAuthor(int idAuthor) {
        List<Integer> booksToRemove = new ArrayList<>();

        for (Map.Entry<Integer, Book> entry : Books.entrySet()) {
            if (entry.getValue().getIdAuthor() == idAuthor) {
                booksToRemove.add(entry.getKey());
            }
        }

        for (Integer bookId : booksToRemove) {
            Books.remove(bookId);
        }
    }

    @Override
    public List<Book> getBookByAuthorId(int authorId) {
        ArrayList<Book> L = new ArrayList<>();
        for (Book book: Books.values())
            if (book.getIdAuthor() == authorId )
                L.add(book);
        return L;
    }
    @Override
    public List<Author> getAllAuthors() {
        return new ArrayList<Author>(this.Authors.values());
    }

    @Override
    public List<Author> getAuthorsByCountry(String Country) {
        return null;
    }

    @Override
    public Author getAuthor(int idE) {
        return null;
    }

    //@Override
    //public List<Author> getAllAuthors() {
    //    return null;
    //}

    //@Override
    //public List<Author> SearchAuthorsByKeyword(String Keyword) {
    //    return null;
    //}

    @Override
    public List<Author> getAllEmpPaginated(int page, int size) {
        List<Author> allAuthors = new ArrayList<>(this.Authors.values());

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, allAuthors.size());

        return allAuthors.subList(startIndex, endIndex);
    }

    //@Override
    //public List<Publisher> getAllPublishersPaginated(int page, int size) {
    //    return null;
    //}

    //@Override
    public List<Publisher> getAllPublishersPaginated(int page, int size) {
        List<Publisher> allPublishers = new ArrayList<>(this.Publishers.values());

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, allPublishers.size());

        return allPublishers.subList(startIndex, endIndex);

    }

    @Override
    public int getPublisherIdByAuthorId(int idE) {
        return 0;
    }

    //@Override
    //public int getPublisherIdByAuthorId(int idE) {
    //    return 0;
    //}

    @Override
    public Publisher getPublisher(int idC) {
        return Publishers.get(idC);
    }


    @Override
    public List<Publisher> getCompanies() {
        return null;
    }

    @Override
    public List<Publisher> getPublishersByCountry(String Country) {
        ArrayList<Publisher> L = new ArrayList<>();
        for (Publisher p: Publishers.values())
            if (p.getcountry().equalsIgnoreCase(Country))
                L.add(p);
        return L;
    }

    @Override
    public List<Publisher> searchCompanies(String Keyword) {
        return null;
    }

    @Override
    public List<Author> getAuthorsByRegion(String Country) {
        ArrayList<Author> L = new ArrayList<Author>();
        for (Author e: Authors.values())
            if (e.getNationality().equalsIgnoreCase(Country))
                L.add(e);
        return L;
    }

    @Override
    public List<Author> SearchAuthors(String Keyword, String region, int page, int size) {
        return null;
    }

    @Override
    public Publisher getPublisherOfAuthor(int idE) {
        return null;
    }


    @Override
    public List<Author> SearchAuthorsByKeyword(String Keyword) {
        ArrayList<Author> L = new ArrayList<Author>();
        for (Author e: Authors.values())
            if ((e.getFirstName()+e.getLastName()).toLowerCase().contains(Keyword.toLowerCase()))
                L.add(e);
        return L;
    }


    //@Override
    public List<Publisher> getPublishers() {
        return new ArrayList<Publisher>(Publishers.values());
    }

    //@Override
    //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public List<Publisher> searchPublishers(String Keyword) {
        ArrayList<Publisher> L = new ArrayList<Publisher>();
        for (Publisher p: Publishers.values())
            if ((p.getPublisherName()+p.getcountry()).toLowerCase().contains(Keyword.toLowerCase()))
                L.add(p);
        return L;
    }
    @Override
    public Author addAuthor(Author E) {
        //Authors.put(E.getIdAuthor(), E);
        Authors.put(E.getIdAuthor(), E);
        return E;
    }

    @Override
    public Publisher addPublisher(Publisher C) {
        Publishers.put(C.getIdPublisher(), C);
        return C;
    }

    //@Override
    //public boolean deleteAuthor(int idE) {
    //    return false;
    //}

    @Override
    public boolean deleteAuthor(int idE) {
        if (Authors.containsKey(idE))
            Authors.remove(idE);
        else
            return false;
        return true;
    }

    @Override
    public boolean deletePublisher(int idC) {
        if (Publishers.containsKey(idC))
            Publishers.remove(idC);
        else
            return false;
        return true;
    }

    @Override
    public Author UpdateAuthor(Author E) {
        return Authors.replace(E.getIdAuthor(), E);
    }

    @Override
    public Publisher UpdatePublisher(Publisher C) {
        return Publishers.replace(C.getIdPublisher(), C);
    }

}
