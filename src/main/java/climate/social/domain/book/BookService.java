package climate.social.domain.book;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@ApplicationScoped
public class BookService {
    @Inject
    @ThirteenDigits
    NumberGenerator numberGenerator;

    @Inject
    Event<Book> bookAddedEvent;

//    @Inject @Removed
//    Event<Book> bookRemovedEvent;

    public Book createBook(String title, Float price, String description) {
        Book book = new Book(title, price, description);
        book.setIsbn(numberGenerator.generateNumber());
        bookAddedEvent.fire(book);
        return book;
    }
}
