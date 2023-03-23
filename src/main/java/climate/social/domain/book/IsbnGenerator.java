package climate.social.domain.book;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ThirteenDigits
@ApplicationScoped
public class IsbnGenerator implements NumberGenerator {

    public String generateNumber() {
        return "13-84356-" + Math.abs(new Random().nextInt());
    }
}
