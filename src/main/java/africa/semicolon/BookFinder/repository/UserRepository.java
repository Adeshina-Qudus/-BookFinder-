package africa.semicolon.BookFinder.repository;

import africa.semicolon.BookFinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByMail(String mail);
}
