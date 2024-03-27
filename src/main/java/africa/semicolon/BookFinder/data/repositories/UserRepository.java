package africa.semicolon.BookFinder.data.repositories;

import africa.semicolon.BookFinder.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByMail(String mail);
}
