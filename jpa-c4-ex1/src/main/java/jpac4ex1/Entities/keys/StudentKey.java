package jpac4ex1.Entities.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable   // with this annotation you can use this type as an PK in the Entity
public class StudentKey implements Serializable {
    private Long number;
    private String code;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //when you have composed PK , you must define the equals and hashCode to have the uniqueness
    // so that no problems occur in find(), remove() or other queries because they rely on PK

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentKey that = (StudentKey) o;
        return Objects.equals(number, that.number) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, code);
    }
}
