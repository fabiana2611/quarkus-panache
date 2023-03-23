package climate.social.domain.book;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@EightDigits
@ApplicationScoped
public class IssnGenerator implements NumberGenerator {

    public String generateNumber() {
        return "8-" + Math.abs(new Random().nextInt());
    }
}
