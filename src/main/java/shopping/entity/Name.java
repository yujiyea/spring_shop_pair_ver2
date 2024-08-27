package shopping.entity;

import jakarta.persistence.Embeddable;
import shopping.Profanity;
import shopping.PurgomalumCliect;

@Embeddable
public class Name {
    String value;

    public Name() {
    }

    public Name(String value){
        PurgomalumCliect purgomalumCliect = new PurgomalumCliect();
        isVaild(value, purgomalumCliect);
        this.value = value;
    }

    public Name(String value, Profanity profanity) {
        isVaild(value, profanity);
        this.value = value;
    }

    private void isVaild(String value, Profanity profanity) {
        if (value == null) {
            throw new IllegalArgumentException("상품명이 null로 입력되었습니다.");
        }
        if (value.length() >= 15) {
            throw new IllegalArgumentException("상품의 이름은 15자를 넘길 수 없습니다.");
        }
        if(profanity.contains(value)) {
            throw new IllegalArgumentException("상품 이름은 비속어가 포함될 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
