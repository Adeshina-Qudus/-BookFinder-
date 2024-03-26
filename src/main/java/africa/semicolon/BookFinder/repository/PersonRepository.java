package africa.semicolon.BookFinder.repository;

import africa.semicolon.BookFinder.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository  extends JpaRepository<Person,Long> {
}
