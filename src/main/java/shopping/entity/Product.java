package shopping.entity;

import jakarta.persistence.*;
import shopping.dto.ModifyProductRequestDto;

import static org.springframework.util.StringUtils.hasText;

@Entity
public class Product {
    @Id
    private Long id;
    private Name name;
    private int price;
    @Embedded
    private Image image;

    public Product() {}

    public Product(long id, Name name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = new Image(imageUrl);
    }

    public Product(Name name, int price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.image = new Image(imageUrl);
    }

    public Long getId() {return id;}

    public String getName() {
        return name.getValue();
    }

    public int getPrice() {
        return price;
    }

    public Image getImage() {
        return image;
    }

    public void update(ModifyProductRequestDto request) {
        if(request.productName() != null && hasText(request.productName())) {
            this.name = new Name(request.productName());
        }
        if(request.price() != 0){
            this.price = request.price();
        }
        if(request.imageUrl() != null && hasText(request.imageUrl())){
            this.image = new Image(request.imageUrl());
        }
    }
}
