package shopping;

import java.util.List;

public class FakedProfanity implements Profanity {
    private List<String> list = List.of("욕설");
    @Override
    public boolean contains(String text) {
        return list.contains(text);
    }
}
