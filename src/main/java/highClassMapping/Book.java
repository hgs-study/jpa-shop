package highClassMapping;

import javax.persistence.Entity;

@Entity
public class Book extends Product {
    private String author;
    private String isbn;
}