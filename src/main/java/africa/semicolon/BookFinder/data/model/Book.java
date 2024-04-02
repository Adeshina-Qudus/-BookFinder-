package africa.semicolon.BookFinder.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    @ElementCollection
    private  List<String> subjects;
    @OneToMany
    private List<Person> authors;
    private boolean copyright;
    private String media_type;
}
