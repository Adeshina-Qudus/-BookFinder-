package africa.semicolon.BookFinder.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Cascade({CascadeType.PERSIST,CascadeType.MERGE})
    private List<Book> readingList;
    private String mail;
    private String name;
    private String password;
    private boolean isLocked = true;
}
