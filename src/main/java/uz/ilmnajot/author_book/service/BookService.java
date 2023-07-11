package uz.ilmnajot.author_book.service;

import uz.ilmnajot.author_book.dto.BookDTO;

import java.util.List;


public interface BookService {
    BookDTO registerBook(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO getOneBook(Long id);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);
}
