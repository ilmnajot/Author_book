package uz.ilmnajot.author_book.dto;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ilmnajot.author_book.Entity.Book;
import uz.ilmnajot.author_book.enums.BookSection;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class BookDTO {
    private Long id;

    private String title;

    private String isbn;

    private Integer publicationYear;

    private BookSection bookSection;

    private String description;

    @Column(nullable = false)
    private String author;

    public static BookDTO toDto(Book book) {
        return BookDTO.of(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getBookSection(),
                book.getDescription(),
                book.getAuthor());
    }

}
