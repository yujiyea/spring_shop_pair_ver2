package shopping.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Name {
    String value;

    public Name() {
    }

    public Name(String value) {
        this.value = value;
    }

    private void isVaild() {
        if (this.value == null) {
            throw new IllegalArgumentException("상품명이 null로 입력되었습니다.");
        }
        if (this.value.length() >= 15) {
            throw new IllegalArgumentException("상품의 이름은 15자를 넘길 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
