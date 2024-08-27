package shopping.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shopping.Profanity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    @DisplayName("상품 이름은 비속어가 포함될 수 없다.")
    void checkProfanityContains(){
        FakedProfanity fakedProfanity = new FakedProfanity();
        assertThatThrownBy(()-> new Name("욕설", fakedProfanity)).isInstanceOf(IllegalArgumentException.class);
    }
}