package climate.social.domain.book;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class InventoryService {
    private List<Book> inventory = new ArrayList<>();

    public List<Book> getInventory() {
        return inventory;
    }

    public void addBook(@Observes Book book) {
        inventory.add(book);
    }
}
