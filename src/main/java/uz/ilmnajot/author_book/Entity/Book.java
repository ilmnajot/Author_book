package uz.ilmnajot.author_book.Entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.author_book.abstracts.BaseEntity;
import uz.ilmnajot.author_book.enums.BookSection;

import java.util.ArrayList;
import java.util.List;

@Entity(name="books")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    private String isbn;

    private Integer publicationYear;

    @Enumerated(EnumType.STRING)
    private BookSection bookSection;

    @Column(nullable = false)
    private String author;

    public Book(
            String title,
            String isbn,
            Integer publicationYear,
            BookSection bookSection,
            String description,
            String author) {
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.bookSection = bookSection;
        this.description = description;
        this.author = author;
    }
}
