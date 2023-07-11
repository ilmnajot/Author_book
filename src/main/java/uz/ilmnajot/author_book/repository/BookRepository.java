package uz.ilmnajot.author_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.author_book.Entity.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findByTitle(String title);

}
