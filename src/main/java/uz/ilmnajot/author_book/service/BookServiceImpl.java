package uz.ilmnajot.author_book.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.ilmnajot.author_book.Entity.Book;
import uz.ilmnajot.author_book.dto.BookDTO;
import uz.ilmnajot.author_book.exception.ElementNotFoundException;
import uz.ilmnajot.author_book.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public BookDTO registerBook(BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findByTitle(bookDTO.getTitle());
        if (optionalBook.isPresent()) {
            throw new ElementNotFoundException("you have registered this book already");

        }

        Book savedBook = bookRepository.save(Book.builder()
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .publicationYear(bookDTO.getPublicationYear())
                .bookSection(bookDTO.getBookSection())
                .description(bookDTO.getDescription())
                .author(bookDTO.getAuthor())
                .build()

        );
        return BookDTO.of(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getIsbn(),
                savedBook.getPublicationYear(),
                savedBook.getBookSection(),
                savedBook.getDescription(),
                savedBook.getAuthor()
        );


//          Book book = new Book(
//                  bookDTO.getTitle(),
//                  bookDTO.getDescription(),
//                  bookDTO.getBookSection(),
//                  bookDTO.getAuthorList()
//          );
//        bookRepository.save(book);
//        BookDTO bookDTO1 = new BookDTO();
//        bookDTO1.setTitle(bookDTO.getTitle());
//        bookDTO1.setBookSection(bookDTO.getBookSection());
//        bookDTO1.setDescription(bookDTO.getDescription());
//        bookDTO1.setAuthorList(bookDTO.getAuthorList());
//        return bookDTO1;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll(Sort.by("id")).stream().map(BookDTO::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDTO getOneBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new ElementNotFoundException("Book " + id + " not found");
        }
        return BookDTO.toDto(optionalBook.get());
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new ElementNotFoundException("Book " + id + " not found");
        }
        Book book = new Book(
                bookDTO.getTitle(),
                bookDTO.getIsbn(),
                bookDTO.getPublicationYear(),
                bookDTO.getBookSection(),
                bookDTO.getDescription(),
                bookDTO.getAuthor()
        );
        book.setId(id);
        return BookDTO.toDto(book);
    }

    @Override
    public void deleteBook(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else{
            throw new ElementNotFoundException("Book not found");
        }

    }
}
