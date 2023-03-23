package climate.social.controller;

import climate.social.domain.book.Book;
import climate.social.domain.book.BookService;
import climate.social.domain.book.InventoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/event")
public class EventResource {
    @Inject
    InventoryService inventoryService;

    @Inject
    BookService bookService;

    /*
      curl -d '{"title":"Hello World","price":25.5, "description":"Say hello in 1 day"}' \
      -H "Content-Type: application/json" -X POST http://localhost:8080/event/books
     */
    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        bookService.createBook(book.getTitle(), book.getPrice(), book.getDescription());
        return Response.ok().build();
    }

    // curl http://localhost:8080/event/books
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        return inventoryService.getInventory();
    }

}
