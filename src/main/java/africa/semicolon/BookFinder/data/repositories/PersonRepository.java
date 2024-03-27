package africa.semicolon.BookFinder.data.repositories;

import africa.semicolon.BookFinder.data.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository  extends JpaRepository<Person,Long> {
}
