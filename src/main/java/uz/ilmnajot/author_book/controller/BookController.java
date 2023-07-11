package uz.ilmnajot.author_book.controller;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.author_book.Entity.Book;
import uz.ilmnajot.author_book.dto.BookDTO;
import uz.ilmnajot.author_book.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping("/register")
    public HttpEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO bookDTO1 = bookService.registerBook(bookDTO);
        return ResponseEntity.ok(bookDTO1);
    }
    @GetMapping("/all")
    public HttpEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }
    @GetMapping("/{id}")
    public HttpEntity<BookDTO> getBookById(@PathVariable Long id){
        BookDTO oneBook = bookService.getOneBook(id);
        return ResponseEntity.ok(oneBook);
    }
    @PutMapping("/{id}")
    public HttpEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("successfully deleted");
    }
}
