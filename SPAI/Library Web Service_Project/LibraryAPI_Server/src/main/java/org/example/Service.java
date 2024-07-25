package org.example;
import org.glassfish.jersey.http.HttpHeaders;
import org.glassfish.jersey.internal.util.PropertiesClass;

import javax.inject.Singleton;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Path("v1")
public class Service {
    private ServiceImplemantation service;

    private final String expectedToken = "1445";

    public Service() {
        service = new ServiceImplemantation(300,100, 800, 19);
    }

    //////////////////////////////////// Books
    @Path("books")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks(@DefaultValue("-1") @QueryParam(value= "category") int category,
                                  @DefaultValue("") @QueryParam(value= "lang") String language,
                                  @DefaultValue("-1") @QueryParam(value= "year") int year,
                                  @DefaultValue("10") @QueryParam(value= "page") int page,
                                  @DefaultValue("10") @QueryParam(value= "size") int size) {
        return service.getAllBooks(category, language, year, page, size);
    }
    @Path("books/count")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Integer CountBooks() {
        return service.CountBooks();
    }
    @Path("books/{AUTHOR_ID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBookByAuthorId(@PathParam(value="AUTHOR_ID") int AUTHOR_ID) {
        return service.getBookByAuthorId(AUTHOR_ID);
    }
    //////////////////////////////////// Authentication needed
    @Path("books/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddBook(@Context ContainerRequestContext requestContext, Book book) {
        //String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String authorizationHeader = "1445";
        if (authorizationHeader != null && authorizationHeader.equals(expectedToken)) {
            System.out.println("Book ....");
            service.AddBook(book);
            System.out.println("Book added");
            return Response.status(Response.Status.CREATED).entity("Book added: " + book.getTitle()).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
        }
    }

    @Path("authors/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response DeleteAuthor(@Context ContainerRequestContext requestContext, int idAuthor) {
        //String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String authorizationHeader = "1445";
        if (authorizationHeader != null && authorizationHeader.equals(expectedToken)) {
            // Book The Grapes of Wrath 306
            // Author 1 Ken
            System.out.println("Author Deleted");
            service.DeleteAuthorById(idAuthor);
            service.DeleteBooksByAuthor(idAuthor);

            return Response.ok("Permission granted!").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
        }
    }
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            System.out.println("Checked.. "+username+" "+password);
            //String authToken = "1111_2222";
            return Response.status(Response.Status.OK)
                    .header("Authorization", expectedToken)
                    .entity("Authorized")
                    .build();
        } else {
            System.out.println("Wrong.. "+username+" "+password);
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Unauthorized")
                    .build();
        }
    }
    /*
    @Path("books/{BOOK_ID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getBookByID(@PathParam(value="BOOK_ID") int BOOK_ID) {
        return service.getBookByID(BOOK_ID);
    }

    @Path("books/{BOOK_NAME}/rate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getBookRateByName(@PathParam(value="BOOK_NAME") int BOOK_NAME) {
        return service.getBookRateByName(BOOK_NAME);
    }
     */

    //////////////////////////////////// Search
    @Path("search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResults searchByKeyword(@DefaultValue("-1") @QueryParam(value= "keyword") String keyword,
                                         @DefaultValue("-1") @QueryParam(value= "type") String type,
                                         @DefaultValue("10") @QueryParam(value= "size") int size,
                                         @DefaultValue("1") @QueryParam(value= "page") int page) {

        SearchResults searchResultLists = new SearchResults();
        List<Book> F_books = null;
        List<Author> F_authors = null;
        List<Category> F_categories = null;
        int EachSize = (int) Math.ceil((double) size / 3);

        switch (type.toLowerCase()) {
            case "book":
                System.out.println("Its a book");
                F_books = service.getBooksByKeyword(keyword, size, page);
                searchResultLists.setBooks(F_books);
                break;
            case "author":
                System.out.println("Its a author");
                F_authors = service.getAuthorsByKeyword(keyword, size, page);
                searchResultLists.setAuthors(F_authors);
                break;
            case "category":
                System.out.println("Its a category");
                F_categories = service.getCategoriesByKeyword(keyword, size, page);
                searchResultLists.setCategories(F_categories);
                break;
            case "-1":
                System.out.println("Its a default");
                F_books = service.getBooksByKeyword(keyword, EachSize, page);
                F_authors = service.getAuthorsByKeyword(keyword, EachSize, page);
                F_categories = service.getCategoriesByKeyword(keyword, EachSize, page);
                searchResultLists.setBooks(F_books);
                searchResultLists.setAuthors(F_authors);
                searchResultLists.setCategories(F_categories);
                break;
        }
        searchResultLists.setPage(page);
        return searchResultLists;
    }

    //////////////////////////////////// Authors
    @Path("authors")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAllAuthors(@DefaultValue("10") @QueryParam(value= "page") int page,
                                      @DefaultValue("10") @QueryParam(value= "size") int size,
                                      @DefaultValue("") @QueryParam(value = "name") String name) {
        //return service.SearchAuthorsByKeyword(name);
        return service.getAllAuthors();
    }
    @Path("authors/{AUTHOR_ID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Author getAuthorById(@PathParam(value="AUTHOR_ID") int AUTHOR_ID) {
        return service.getAuthor(AUTHOR_ID);
    }

    @Path("authors/{AUTHOR_ID}/publisher")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher getPublisherOfAuthor(@PathParam(value="AUTHOR_ID") int AUTHOR_ID) {
        return service.getPublisherOfAuthor(AUTHOR_ID);
    }

    @Path("authors")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Author addAuthor(Author E) {
        return service.addAuthor(E);
    }

    @Path("authors")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public boolean deleteEmpById(@FormParam("idAuthor") int idA) {
        return service.deleteAuthor(idA);
    }

    //////////////////////////////////// Publisher

    @Path("publishers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Publisher> getPublishers(@DefaultValue("10") @QueryParam(value= "page") int page,
                                         @DefaultValue("10") @QueryParam(value= "size") int size,
                                         @DefaultValue("") @QueryParam(value = "name") String name) {
        //return service.getAllPublishersPaginated(page, size);
        //return service.SearchAuthors(keyword);
        //return service.searchPublishers(keyword);
        return service.getPublishers();
    }


    @Path("publishers/{PUBLISHER_ID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher getPublisherById(@PathParam(value="PUBLISHER_ID") int PUBLISHER_ID) {
        return service.getPublisher(PUBLISHER_ID);
    }

    @Path("publishers")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Publisher addPublisher(Publisher C) {
        return service.addPublisher(C);
    }

    //////////////////////////////////// Categories



    //////////////////////////////////// Countries
    @Path("countries/{COUNTRY_NAME}/authors")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAuthorsByRegion(@PathParam(value="COUNTRY_NAME") String COUNTRY_NAME) {
        return service.getAuthorsByRegion(COUNTRY_NAME);
    }

    @Path("countries/{COUNTRY_NAME}/publishers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Publisher> getPublishersByRegion(@PathParam(value="COUNTRY_NAME") String COUNTRY_NAME) {
        return service.getPublishersByCountry(COUNTRY_NAME);
    }
}
