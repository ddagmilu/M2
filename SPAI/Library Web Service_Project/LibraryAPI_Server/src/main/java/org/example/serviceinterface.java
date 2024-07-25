package org.example;
import javax.ws.rs.container.ContainerRequestContext;
import java.util.List;
public interface serviceinterface {
    Author getAuthor(int idE);
    List<Author> getAllAuthors();

    List<Author> getAuthorsByCountry(String Country);

    List<Author> SearchAuthorsByKeyword(String Keyword);

    List<Author> getAllEmpPaginated(int page, int size);
    List<Publisher> getAllPublishersPaginated(int page, int size);
    int getPublisherIdByAuthorId(int idE);
    Publisher getPublisher(int idC);
    List<Author> getAuthorsByRegion(String Country);
    List<Author> SearchAuthors(String Keyword, String region, int page, int size);
    Publisher getPublisherOfAuthor(int idE);
    List<Publisher> getCompanies();
    List<Publisher> getPublishersByCountry(String Country);
    List<Publisher> searchCompanies(String Keyword);
    Author addAuthor(Author E);
    Publisher addPublisher(Publisher C);

    boolean deleteAuthor(int idE);
    boolean deletePublisher(int idC);

    Author UpdateAuthor(Author E);
    Publisher UpdatePublisher(Publisher C);

    List<Book> getAllBooks(int category, String language, int year, int page, int size);

    List<Book> getBookByAuthorId(int authorId);


    List<Book> getBooksByKeyword(String keyword, int size, int page);

    List<Author> getAuthorsByKeyword(String keyword, int size, int page);

    List<Category> getCategoriesByKeyword(String keyword, int size, int page);
    Book AddBook(Book book);

    void DeleteAuthorById(int idAuthor);

    void DeleteBooksByAuthor(int idAuthor);

    Integer CountBooks();
}
