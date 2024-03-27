package africa.semicolon.BookFinder.data.repositories;

import africa.semicolon.BookFinder.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {


}
