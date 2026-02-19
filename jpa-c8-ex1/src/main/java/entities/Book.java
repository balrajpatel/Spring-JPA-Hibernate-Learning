package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book extends Product {

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", id=" + id +
                '}';
    }
}
