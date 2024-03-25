package africa.semicolon.BookFinder.repository;

import africa.semicolon.BookFinder.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {


}
