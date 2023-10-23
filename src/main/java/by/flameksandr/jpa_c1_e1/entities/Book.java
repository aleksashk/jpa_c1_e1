package by.flameksandr.jpa_c1_e1.entities;

import jakarta.persistence.Entity;

@Entity
public class Book extends Product{

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
                '}';
    }
}
