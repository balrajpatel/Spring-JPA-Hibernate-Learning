package jpac4ex1.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jpac4ex1.Entities.keys.ProductKey;

@Entity
@IdClass(ProductKey.class)    // it tells where is my PK
public class Product {

    @Id
    private String code;
    @Id
    private long number;

    private String color;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
